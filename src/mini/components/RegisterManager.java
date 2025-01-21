package mini.components;

public class RegisterManager implements IRegisterManager {
    // table storing whether each register is allocated
    private final boolean[] allocTable;

    public RegisterManager(int regCount) {
        this.allocTable = new boolean[regCount];
    }

    @Override
    public int allocate() {
        for (int i = 0; i < this.allocTable.length; i++) {
            if (!this.allocTable[i]) {
                this.allocTable[i] = true;
                return i;
            }
        }

        return -1;
    }

    @Override
    public void free(int reg) {
        this.allocTable[reg] = false;
    }
}
