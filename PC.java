package cse116;

public class PC {
    private int pc ; //this variable represents the program counter
    public PC(int pc){ this.pc = pc; } //the constructor to be used to intialize the pc at the beggining
    @Override
    public String toString() { return "Program Counter = "+pc; } //designs the appropriate string to be used in representation of the results
    public int getPc() { return pc; } //getter for the program counter to be called by the "InstructionMem" class and used to know the next instruction
}