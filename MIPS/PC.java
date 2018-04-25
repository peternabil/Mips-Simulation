package MIPS;

public class PC {
    private int pc ; //this variable represents the program counter
    private String pcbinary;
    public PC(int pc){ this.pc = pc;StringBuilder s = new StringBuilder();
        pcbinary = Integer.toBinaryString(pc);
        for (int h = pcbinary.length() ; h < 32 ; h++){s.append("0");}
        s.append(pcbinary);pcbinary=s.toString();
    } //the constructor to be used to intialize the pc at the beggining
    @Override
    public String toString() { return "Program Counter = "+pc; } //designs the appropriate string to be used in representation of the results
    public int getPc() { return pc; } //getter for the program counter to be called by the "InstructionMem" class and used to know the next instruction
    public String getPcbinary() { return pcbinary; } //getter for the program counter in binary to be called by the "InstructionMem" class and used to know the next instruction

}