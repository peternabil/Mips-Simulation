package cse116;

public class InstructionMem {
    //the assembly representation of each instruction
    private String label;       //in conditions when provided
    private String instruction; //the instruction (one of the following) : add , addi , lw , sw , lb , lbu , sb , sll , nor , beq , j , jal , jr , slt , slti
    private String register1;   //the first register
    private String register2;   //the second register
    private String register3;   //the third register
    private int offset;         //in case of lw , sw , j , jal , jr
    private int immediate;      //in case of i-type instructions
    //the machine code of each instruction
    private int [] machinecode;       //the total machine code representation of the instruction
    private int [] registerfilecode;  //the part of the machine code sent to the register
    private int [] controlunitcode;   //the part of the machine code sent to the control unit
//constructor
    public InstructionMem(String s){
        String[] splited = s.split("\\s+");
        instruction = splited[0];
        register1 = splited[1];
        register2 = splited[2];
        register3 = splited[3];
        offset = splited.length;
    }
    //setters for each assembly instruction
    public void setInstruction(String instruction) { this.instruction = instruction; }
    public void setLabel(String label) { this.label = label; }
    public void setRegister1(String register1) { this.register1 = register1; }
    public void setRegister2(String register2) { this.register2 = register2; }
    public void setRegister3(String register3) { this.register3 = register3; }
    public void setOffset(int offset) { this.offset = offset; }
    public void setImmediate(int immediate) { this.immediate = immediate; }

    //getters for each assembly instruction
    public String getInstruction() { return instruction; }
    public String getLabel() { return label; }
    public String getRegister1() { return register1; }
    public String getRegister2() { return register2; }
    public String getRegister3() { return register3; }
    public int getOffset() { return offset; }
    public int getImmediate() { return immediate; }

    //converts into machinecode
    private void setMachinecode(){ }

    //setter for registerfilecode
    private void setRegisterfilecode(){ }

    //setter for controlunitcode
    private void setControlunitcode(){ }

    //getter for machinecode
    public int[] getMachinecode() { return machinecode; }

    //getter for registerfilecode to be called by the registerfile class
    public int[] getRegisterfilecode() { return registerfilecode; }

    //getter for controlunitcode to be called by the controlunit class
    public int[] getControlunitcode() { return controlunitcode; }
}