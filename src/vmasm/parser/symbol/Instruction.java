package vmasm.parser.symbol;

import vmasm.parser.operand.POperand;

import java.util.Arrays;

public record Instruction(InstrType op, POperand... args) implements Symbol {
    @Override
    public String toString() {
        return op.toString() + " " + Arrays.toString(args);
    }
};
