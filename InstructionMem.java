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
    private int opcode;         // the part of the machine code sent to the control unit
    private int functioncode;
    private int registerfilecode1;  //the part of the machine code sent to the register
    private int registerfilecode2;  //the part of the machine code sent to the register
    private int registerfilecode3;  //the part of the machine code sent to the register
//constructor
    public InstructionMem(String s){
        String[] splited = s.split("\\s+");
        instruction = splited[0];
        register1 = splited[1];
        register2 = splited[2];
        register3 = splited[3];
        offset = splited.length;
        this.setOpcode();
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


    //setter for opcode and function code 
    private void setOpcode(){
        switch (this.instruction){
            case "add":
                this.opcode = 0;
                this.functioncode = 32;
                break;
            case "addi":
                this.opcode = 8;
                break;
            case "lw":
                this.opcode = 35;
                break;
            case "sw":
                this.opcode = 43;
                break;
            case "lb":
                this.opcode = 8;
                break;
            case "lbu":
                this.opcode = 8;
                break;
            case "sb":
                this.opcode = 8;
                break;
            case "sll":
                this.opcode = 0;
                break;
            case "nor":
                this.opcode = 39;
                break;
            case "beq":
                this.opcode = 4;
                break;
            case "j":
                this.opcode = 2;
                break;
            case "jal":
                this.opcode = 3;
                break;
            case "jr":
                this.opcode = 8;
                break;
            case "slt":
                this.opcode = 42;
                break;
            case "slti":
                this.opcode = 10;
                break;
        }
    }
    //setter for registerfilecode
    private void setRegisterfilecode1(){ }
    private void setRegisterfilecode2(){ }
    private void setRegisterfilecode3(){ }
    //setter for controlunitcode
    private void setControlunitcode(){ }

    //getter for opcode
    public int getOpcode() { return opcode; }

    public int getFunctioncode() { return functioncode; }

    //getter for registerfilecode to be called by the registerfile class
    public int getRegisterfilecode1() { return registerfilecode1; }
    public int getRegisterfilecode2() { return registerfilecode2; }
    public int getRegisterfilecode3() { return registerfilecode3; }
    //getter for controlunitcode to be called by the controlunit class

}