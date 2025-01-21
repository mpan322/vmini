package mini.parser.ast;

import java.util.*;

public record FunDecl(String ident, List<String> params, List<Statement> body) {
    // TODO: rather than taking this approach hoist variables in the AST, more mem efficient?
    public Map<String, Integer> makeLocalVarTable(int offset) {
        Map<String, Integer> vars = new TreeMap<>();
        int i = offset;
        for (Statement stmt : this.body) {
            if (stmt instanceof LetStatement) {
                String ident = ((LetStatement) stmt).ident();
                vars.put(ident, i);
                i += 1;
            }
        }
        return vars;
    }
}
