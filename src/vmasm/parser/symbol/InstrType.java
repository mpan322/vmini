package vmasm.parser.symbol;

public enum InstrType {
    ADD(3),
    MUL(3),
    SUB(3),
    POP(1),
    PUSH(1),
    JUMP(1),
    MOV(2),
    ;

    private final int arity;
    InstrType(int arity) {
        this.arity = arity;
    }

    public int getArity() {
        return this.arity;
    }

    public static InstrType fromOrdinal(int ord) {
        return InstrType.values()[ord];
    }
}
