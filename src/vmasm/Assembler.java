package vmasm;

import vmasm.parser.symbol.InstrType;
import vmasm.parser.symbol.Instruction;
import vmasm.parser.symbol.Label;
import vmasm.parser.symbol.Symbol;
import vmasm.parser.operand.*;

import java.util.*;

public class Assembler {
    public Code assemble(List<Symbol> prog) {
        List<Integer> ops = new ArrayList<>();
        List<Operand> data = new ArrayList<>();

        Map<String, Integer> jumps = this.makeJumpTable(prog);
        for (Symbol symbol : prog) {
            switch (symbol) {
                case Instruction i -> this.assemble(i, jumps, ops, data);
                case Label l -> {}
            }
        }
        return new Code(ops, data);
    }

    private Map<String, Integer> makeJumpTable(List<Symbol> prog) {
        Map<String, Integer> table = new TreeMap<>();

        int idx = 0;
        for (Symbol symbol : prog) {
            switch (symbol) {
                case Instruction _i -> idx++;
                case Label label -> {
                    String name = label.name();
                    if (table.containsKey(name)) {
                        throw new RuntimeException("duplicate label declaration");
                    }
                    table.put(name, idx);
                }
            }
        }
        return table;
    }

    private void assemble(Instruction i, Map<String, Integer> jumps, List<Integer> ops, List<Operand> data) {
        InstrType inst = i.op();
        POperand[] args = i.args();

        // validate the correct arity
        if (args.length != inst.getArity()) {
            throw new RuntimeException("incorrect number of arguments");
        }

        // add the operator and operands to the code
        ops.add(inst.ordinal());
        for (POperand arg : args) {
            ops.add(data.size());
            switch (arg) {
                case Register o -> data.add(o);
                case Const o -> data.add(o);
                case JumpLabel j -> {
                    Integer idx = jumps.get(j.name());
                    if (idx == null) {
                        throw new RuntimeException("unknown label in jump");
                    }
                    data.add(new Const(idx));
                }
            }
        }
    }
}
