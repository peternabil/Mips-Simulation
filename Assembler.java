package cse116;

public class Assembler {
    private String instruction;
    private char instructionType;
    class Label {
        String labelname;     // the name given by the user
        PC position;          // the corresponding position to that label
        public Label(String labelname,PC position){
            this.labelname = labelname;
            this.position = position;
        }
    }
    private Label label;       //in conditions when provided
    private int offset;         //in case of lw , sw , j , jal , jr
    private int immediate;      //in case of i-type instructions
    //the machine code of each instruction
    public int opcode;         // the part of the machine code sent to the control unit
    public int functioncode;
    public int shiftamount;
    public int rs;  //the part of the machine code sent to the register
    public int rt;  //the part of the machine code sent to the register
    public int rd;  //the part of the machine code sent to the register


    public static String intToString(int number,int x) {
        if (number == 0)
            return zeroToString(x);
        String binaryString = Integer.toBinaryString(number);
        binaryString = binaryString.substring(binaryString.length() - x);
        return binaryString;
    }
    public static String zeroToString(int x){
        String s = "";
        for (int i = 0 ; i <x ; i++){
            s+="0";
        }
        return s;
    }
    public Assembler(String instruction) {
        this.instruction = instruction;
        setopcodeandfunctioncodeandinstructiontypeandregister();
    }
    @Override
    // to string returns a string of the machine code of the instruction
    // i only did the r-type , i will finish the rest later
    // i will create another that allows you to access the machinecode as an array of integers
    public String toString() {
        String s = "";
        switch (this.instructionType) {
            case 'r':
                s += intToString(opcode,6)
                        + intToString(rs,5)+intToString(rt,5)+intToString(rd,5)+intToString(shiftamount,5)+intToString(functioncode,6);
                //s +=  String.valueOf(opcode)+ String.valueOf(rs)+ String.valueOf(rt)+ String.valueOf(rd);
                break;
            case 'i':
                break;
            case 'j':
                break;
        }
        return s;
    }
    public void setopcodeandfunctioncodeandinstructiontypeandregister(){
        String[] splited = instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
        switch (splited[0]){
            case "add":
                this.opcode = 0;
                this.functioncode = 32;
                this.instructionType = 'r';
                this.shiftamount = 0;
                for (int j = 0 ; j <32 ; j++) {
                    if (splited2[0].equals(f.registers[j].name))
                        rd = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rs = j;
                    if (splited2[2].equals(f.registers[j].name))
                        rt = j;
                }
                break;
            case "addi":
                this.opcode = 8;
                this.instructionType = 'i';
                for (int j = 0 ; j <32 ; j++) {
                    if (splited2[0].equals(f.registers[j].name))
                        rd = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rs = j;
                }
                break;
            case "lw":
                this.opcode = 35;
                this.instructionType = 'i';
                String[] splited3 = splited2[1].split("");
                String s2 =splited3[2]+splited3[3]+splited3[4];
                offset = Integer.parseInt(splited3[0]);
                for (int j = 0 ; j <32 ; j++) {
                    if (s2.equals(f.registers[j].name))
                        rs = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rd = j;
                }
                break;
            case "sw":
                this.opcode = 43;
                this.instructionType = 'i';
                String[] splited4 = splited2[1].split("");
                String s3 =splited4[2]+splited4[3]+splited4[4];
                offset = Integer.parseInt(splited4[0]);
                for (int j = 0 ; j <32 ; j++) {
                    if (s3.equals(f.registers[j].name))
                        rd = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rs = j;
                }
                break;
            case "lb":
                this.opcode = 8;
                this.instructionType = 'i';
                String[] splited5 = splited2[1].split("");
                String s4 =splited5[2]+splited5[3]+splited5[4];
                offset = Integer.parseInt(splited5[0]);
                for (int j = 0 ; j <32 ; j++) {
                    if (s4.equals(f.registers[j].name))
                        rs = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rd = j;
                }
                break;
            case "lbu":
                this.opcode = 8;
                this.instructionType = 'i';
                String[] splited6 = splited2[1].split("");
                String s5 =splited6[2]+splited6[3]+splited6[4];
                offset = Integer.parseInt(splited6[0]);
                for (int j = 0 ; j <32 ; j++) {
                    if (s5.equals(f.registers[j].name))
                        rs = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rd = j;
                }
                break;
            case "sb":
                this.opcode = 8;
                this.instructionType = 'i';
                String[] splited7 = splited2[1].split("");
                String s6 =splited7[2]+splited7[3]+splited7[4];
                offset = Integer.parseInt(splited7[0]);
                for (int j = 0 ; j <32 ; j++) {
                    if (s6.equals(f.registers[j].name))
                        rd = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rs = j;
                }
                break;
            case "sll":
                this.opcode = 0;
                this.functioncode = 0;
                this.instructionType = 'r';
                this.rt = 0;
                this.shiftamount = Integer.parseInt(splited2[2]);
                for (int j = 0 ; j <32 ; j++) {
                    if (splited2[0].equals(f.registers[j].name))
                        rd = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rs = j;
                }
                break;
            case "nor":
                this.opcode = 0;
                this.functioncode = 39;
                this.instructionType = 'r';
                this.shiftamount = 0;
                for (int j = 0 ; j <32 ; j++) {
                    if (splited2[0].equals(f.registers[j].name))
                        rd = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rs = j;
                    if (splited2[2].equals(f.registers[j].name))
                        rt = j;
                }
                break;
            case "beq":
                this.opcode = 4;
                this.instructionType = 'i';
                for (int j = 0 ; j <32 ; j++) {
                    if (splited2[0].equals(f.registers[j].name))
                        rd = j;
                    if (splited2[1].equals(f.registers[j].name))
                        rs = j;
                }
                /*
                i am still working on the jumping amount
                 */



                break;
            case "j":
                this.opcode = 2;
                this.instructionType = 'j';
                break;
            case "jal":
                this.opcode = 3;
                this.instructionType = 'j';
                break;
            case "jr":
                this.opcode = 8;
                this.instructionType = 'j';
                break;
            case "slt":
                this.opcode = 42;
                this.instructionType = 'r';
                break;
            case "slti":
                this.opcode = 10;
                this.instructionType = 'i';
                break;
        }
    }

    public int getImmediate() {
        return immediate;
    }

    public int getOffset() {
        return offset;
    }

    public int getFunctioncode() {
        return functioncode;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getRd() {
        return rd;
    }

    public int getRs() {
        return rs;
    }

    public int getRt() {
        return rt;
    }

    public int getShiftamount() {
        return shiftamount;
    }
}
