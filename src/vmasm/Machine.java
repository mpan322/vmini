package vmasm;

import vmasm.parser.operand.Const;
import vmasm.parser.operand.Operand;
import vmasm.parser.operand.Register;
import vmasm.parser.symbol.InstrType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Machine {
    private final Code code;
    private int ip;

    private final List<Integer> stack;
    private final int[] registers;

    public Machine(Code code) {
        this.code = code;
        this.registers = new int[16];
        this.stack = new ArrayList<>();
    }

    private boolean hasNext() {
        return this.ip < this.code.ops().size();
    }

    private int getNext() {
        return this.code.ops().get(this.ip++);
    }

    private int getNextDataValue() {
        Operand operand =this.code.data().get(this.getNext());
        return switch (operand) {
            case Const c -> c.value();
            case Register r -> this.registers[r.value()];
        };
    }

    private int getNextDataReg() {
        Operand op = this.code.data().get(this.getNext());
        return switch (op) {
            case Const _c -> throw new RuntimeException("pop into const");
            case Register r -> r.value();
        };
    }


    public void exec() {
        while(this.hasNext()) {
            System.out.println(stack);
            System.out.println(Arrays.toString(registers));
            int op = this.getNext();
            this.exec(op);
        }
        System.out.println(Arrays.toString(registers));
    }

    private void exec(int op) {
        InstrType type = InstrType.fromOrdinal(op);
        switch (type) {
            case ADD -> this.add();
            case MUL -> this.mul();
            case SUB -> this.sub();
            case POP -> this.pop();
            case PUSH -> this.push();
            case JUMP -> this.jump();
            case MOV -> this.mov();
        }
    }

    private void mov() {
        int v = this.getNextDataValue();
        int r = this.getNextDataReg();
        this.registers[r] = v;
    }

    private void jump() {
        this.ip = this.getNextDataValue();
    }

    private void push() {
        this.stack.add(this.getNextDataValue());
    }

    private void pop() {
        if (this.stack.isEmpty()) {
            throw new RuntimeException("pop from empty stack");
        }

        int reg = this.getNextDataReg();
        this.registers[reg] = this.stack.removeLast();
    }

    private void sub() {
        int a = this.getNextDataValue();
        int b = this.getNextDataValue();
        int r = this.getNextDataReg();
        this.registers[r] = a - b;
    }

    private void mul() {
        int a = this.getNextDataValue();
        int b = this.getNextDataValue();
        int r = this.getNextDataReg();
        this.registers[r] = a * b;
    }

    private void add() {
        int a = this.getNextDataValue();
        int b = this.getNextDataValue();
        int r = this.getNextDataReg();
        this.registers[r] = a + b;
    }
}
