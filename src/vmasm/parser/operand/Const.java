package vmasm.parser.operand;

public record Const(int value) implements Operand, POperand {
}
