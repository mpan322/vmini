package mini.components;

import java.util.List;

public interface IVariableManager {

    /**
     * Gets position of a local variable relative to the base pointer.
     *
     * @param variable the variable to lookup.
     * @return the position of the variable relative to the base pointer of the frame.
     * @throws NoSuchVariableException no variable exists with the provided identifier.
     * @throws CallStackEmptyException the call stack is currently empty (so no local variables can exist).
     */
    int getLocalRelative(String variable) throws NoSuchVariableException, CallStackEmptyException;

    /**
     * Creates a new stack frame for a function call.
     *
     * @param params the parameters passed by the caller.
     * @param locals the local variables of the callee.
     * @throws DuplicateVariableException there are multiple variables with the same name.
     */
    void createFrame(List<String> params, List<String> locals) throws DuplicateVariableException;

    /**
     * Exits the current stack frame of the callee.
     *
     * @throws CallStackEmptyException the call stack is empty so no callee could be exited.
     */
    void exitFrame() throws CallStackEmptyException;

}
