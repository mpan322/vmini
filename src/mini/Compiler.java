package mini;

import mini.components.IRegisterManager;
import mini.components.IVariableManager;
import mini.components.RegisterManager;
import mini.components.VariableManager;
import mini.parser.ast.*;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class Compiler {
    // TODO: make a builder to improve modularity
    // e.g. can take in IRegisterManager, IVariableManager, ICodeGenerator -> swappable

    private final List<FunDecl> prog;
    private final PrintWriter writer;
    private final IRegisterManager regs;
    private final IVariableManager locals;

    public Compiler(List<FunDecl> prog, PrintWriter pw) {
        this.prog = prog;
        this.writer = pw;
        this.locals = new VariableManager();
        this.regs = new RegisterManager(16);
    }

    public void compile() {
        for (FunDecl fun : this.prog) {
            this.compile(fun);
        }
    }

    private void enterStackFrame(int space) {
        // output asm to make stack space
        this.writer.println("push %1");                 // push bp
        this.writer.println("mov %2 %1");               // bp := sp
        this.writer.printf("add $%d %%2 %%2\n", space); // sp := space
    }

    private void exitStackFrame(int space) {
        this.writer.println("mov %sp %bp");
        this.writer.println("pop %rp");
        this.writer.println("sub $1 %2 %2");
    }

    private void compile(FunDecl fun) {
        List<String> params = fun.params();
        int numParams = params.size();

        Map<String, Integer> vars = fun.makeLocalVarTable(numParams);
        int varCounts = vars.size();
        this.enterStackFrame(varCounts);

        // add stack space for params
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            vars.put(param, i);
        }

        this.compileStatements(fun.body());
        this.exitStackFrame(varCounts);
    }

    private void compileStatements(List<Statement> body) {
        for (Statement stmt : body) {
            this.compileStatement(stmt);
        }
    }

    private void compileStatement(Statement stmt) {
        switch (stmt) {
            case AssignStatement assign -> {
                String ident = assign.ident();
                this.addrs.put(ident, this.sp);
            }
            case LetStatement letStatement -> {
            }
            case ReturnStatement returnStatement -> {
            }
        }
    }
}
