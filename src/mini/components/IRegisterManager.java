package mini.components;

public interface IRegisterManager {
    /**
     * Allocates a new register.
     *
     * @return the id of the register, -1 if no registers are available.
     */
    int allocate();

    /**
     * Frees a register. If the register was not allocated outputs a warning to standard out.
     *
     * @param reg the register to free.
     */
    void free(int reg);
}
