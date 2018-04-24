package MIPS;

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
    public String machinecode;
    public Instruction(){
    }

    public String setcontrolunitbinary(){
        return this.opcodestr;
    }
    public int setcontrolunitints(){
        return this.opcode;
    }

    public String setregisterread1binary(){
        return this.machinecode.substring(31-25, 31-21);
    }
    public int setregisterread1ints(){
        return this.rs;
    }

    public String setregisterread2binary(){
        return this.machinecode.substring(31-20, 31-16);
    }
    public int setregisterread2ints(){
        return this.rt;
    }

    public String setmux1binary(){
        return this.machinecode.substring(31-15, 31-11);
    }
    public int setmux1ints(){
        return this.rs;
    }

    public String setsignextendbinary(){
        return this.machinecode.substring(31-15, 31-0);
    }
    public int setsignextendints(){
        return this.rs;
    }
}
