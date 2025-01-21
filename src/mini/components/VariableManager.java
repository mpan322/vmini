package mini.components;

import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class VariableManager implements IVariableManager {
    private record Frame(int paramCount, Map<String, Integer> variables) {
    }

    private Stack<Frame> stack = new Stack<>();

    public int getLocalRelative(String variable) throws NoSuchVariableException, CallStackEmptyException {
        if (this.stack.isEmpty()) {
            throw new CallStackEmptyException();
        }

        Frame frame = this.stack.peek();
        Map<String, Integer> variables = frame.variables;
        if (!variables.containsKey(variable)) {
            throw new NoSuchVariableException();
        }
        return variables.get(variable);
    }

    public void createFrame(List<String> params, List<String> locals) throws DuplicateVariableException {
        // TODO: choose between different map types depending on characteristics of variables
        Map<String, Integer> variables = new TreeMap<>();
        int offset = params.size();

        int i = 0;
        i = VariableManager.addVars(params, variables, i, offset);
        VariableManager.addVars(locals, variables, i, offset);
        this.stack.push(new Frame(offset, variables));
    }

    private static int addVars(List<String> params, Map<String, Integer> variables, int i, int offset) throws DuplicateVariableException {
        for (String var : params) {
            if (variables.containsKey(var)) {
                throw new DuplicateVariableException();
            }
            variables.put(var, i - offset);
            i++;
        }
        return i;
    }

    public void exitFrame() throws CallStackEmptyException {
        if (this.stack.isEmpty()) {
            throw new CallStackEmptyException();
        }
        this.stack.pop();
    }
}
