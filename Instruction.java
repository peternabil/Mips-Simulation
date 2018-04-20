package cse116;

public class Instruction {
    public String instruction;
    public char instructionType;
    public PC pc;
    public Label label;        //in conditions when provided
    public int offset;         //in case of lw , sw , j , jal , jr
    public int immediate;      //in case of i-type instructions
    public String offsetstr;         //in case of lw , sw , j , jal , jr
    public String immediatestr;      //in case of i-type instructions
    //the machine code of each instruction
    public int opcode;         // the part of the machine code sent to the control unit
    public String opcodestr;
    public int functioncode;
    public String functioncodestr;
    public int shiftamount;
    public String shiftamountstr;
    public int rs;  //the part of the machine code sent to the register
    public int rt;  //the part of the machine code sent to the register
    public int rd;  //the part of the machine code sent to the register
    public String rsstr;  //the part of the machine code sent to the register
    public String rtstr;  //the part of the machine code sent to the register
    public String rdstr;  //the part of the machine code sent to the register
    public Instruction(){
    }
}
