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
    private int opcode;         // the part of the machine code sent to the control unit
    private int functioncode;
    private int shiftamount;
    private int rs;  //the part of the machine code sent to the register
    private int rt;  //the part of the machine code sent to the register
    private int rd;  //the part of the machine code sent to the register

    public Assembler(String instruction) {
        this.instruction = instruction;
        setopcodeandfunctioncodeandinstructiontype();
        switch (this.instructionType){
            case 'r':
                setregisters();
                break;
            case 'i':
                if (this.functioncode == 8 )
                    setimmediate();
                else
                    setoffset();
                break;
            case 'j':
                break;
        }
    }

    private void setopcodeandfunctioncodeandinstructiontype(){
        String[] splited = instruction.split("\\s+");
        switch (splited[0]){
            case "add":
                this.opcode = 0;
                this.functioncode = 32;
                this.instructionType = 'r';
                break;
            case "addi":
                this.opcode = 8;
                this.instructionType = 'i';
                break;
            case "lw":
                this.opcode = 35;
                this.instructionType = 'i';
                break;
            case "sw":
                this.opcode = 43;
                this.instructionType = 'i';
                break;
            case "lb":
                this.opcode = 8;
                this.instructionType = 'i';
                break;
            case "lbu":
                this.opcode = 8;
                this.instructionType = 'i';
                break;
            case "sb":
                this.opcode = 8;
                this.instructionType = 'i';
                break;
            case "sll":
                this.opcode = 0;
                this.instructionType = 'i';
                break;
            case "nor":
                this.opcode = 39;
                this.instructionType = 'r';
                break;
            case "beq":
                this.opcode = 4;
                this.instructionType = 'i';
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
    private void setoffset(){
        String[] splited = this.instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        char [] c = splited2[1].toCharArray();
        offset = c[0];
    }
    private void setimmediate(){
        String[] splited = this.instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        immediate = Integer.parseInt(splited2[2]);
    }
    private void setregisters() {
        String[] splited = this.instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
        for (int j = 0 ; j <32 ; j++){
            if(splited2[0] == f.registers[j].name)
                rd = j;
            else if(splited2[1] == f.registers[j].name)
                rs = j;
            else if(splited2[2] == f.registers[j].name)
                rt = j;
        }
    }
}