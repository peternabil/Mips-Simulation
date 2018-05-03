//
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import java.util.*;
//import jdk.nashorn.internal.ir.Labels;
//
//public  class Main extends JFrame{
//
//
//    public static void main(String[] args) {
//        Gui m =new Gui();
//        m.setVisible(true);
//
//
//
//
//    }
//
//
//
//}
//
// class Instruction {
//
//    public String instruction;
//
//    public char instructionType;
//
//    public PC pc;
//
//    public Label label;        //in conditions when provided
//
//    public int offset;         //in case of lw , sw , j , jal , jr
//
//    public int immediate;      //in case of i-type instructions
//
//    public String offsetstr;         //in case of lw , sw , j , jal , jr
//
//    public String immediatestr;      //in case of i-type instructions
//
//    //the machine code of each instruction
//
//    public int opcode;         // the part of the machine code sent to the control unit
//
//    public String opcodestr;
//
//    public int functioncode;
//
//    public String functioncodestr;
//
//    public int shiftamount;
//
//    public String shiftamountstr;
//
//    public int rs;  //the part of the machine code sent to the register
//
//    public int rt;  //the part of the machine code sent to the register
//
//    public int rd;  //the part of the machine code sent to the register
//
//    public String rsstr;  //the part of the machine code sent to the register
//
//    public String rtstr;  //the part of the machine code sent to the register
//
//    public String rdstr;  //the part of the machine code sent to the register
//
//    public String machinecode;
//
//    public Instruction(int count){
//
//        this.pc = new PC(count);
//
//        this.label = new Label(null,pc);
//
//    }
//
//
//
//    public String setcontrolunitbinary(){
//
//        return this.opcodestr;
//
//    }
//
//    public int setcontrolunitints(){
//
//        return this.opcode;
//
//    }
//
//
//
//    public String setregisterread1binary(){
//
//        return this.machinecode.substring(31-25, 31-21);
//
//    }
//
//    public int setregisterread1ints(){
//
//        return this.rs;
//
//    }
//
//
//
//    public String setregisterread2binary(){
//
//        return this.machinecode.substring(31-20, 31-16);
//
//    }
//
//    public int setregisterread2ints(){
//
//        return this.rt;
//
//    }
//
//
//
//    public String setmux1binary(){
//
//        return this.machinecode.substring(31-15, 31-11);
//
//    }
//
//    public int setmux1ints(){
//
//        return this.rs;
//
//    }
//
//
//
//    public String setsignextendbinary(){
//
//        return this.machinecode.substring(31-15, 31-0);
//
//    }
//
//    public int setsignextendints(){
//
//        return this.rs;
//
//    }
//
//}
// class InstructionMem {
//
//    Label[] labels = new Label[1000];
//
//    Instruction[] instructions = new Instruction[1000];
//
//    public InstructionMem(int len){
//
//        for (int i = 0 ; i <len ; i++){
//
//            instructions[i] = new Instruction(i);
//
//        }
//
//    }
//
//}
//
//class PC {
//
//    private int pc ; //this variable represents the program counter
//
//    private String pcbinary;
//
//    public PC(int pc){ this.pc = pc;StringBuilder s = new StringBuilder();
//
//        pcbinary = Integer.toBinaryString(pc);
//
//        for (int h = pcbinary.length() ; h < 32 ; h++){s.append("0");}
//
//        s.append(pcbinary);pcbinary=s.toString();
//
//    } //the constructor to be used to intialize the pc at the beggining
//
//    @Override
//
//    public String toString() { return "Program Counter = "+pc; } //designs the appropriate string to be used in representation of the results
//
//    public int getPc() { return pc; } //getter for the program counter to be called by the "InstructionMem" class and used to know the next instruction
//
//    public String getPcbinary() { return pcbinary; } //getter for the program counter in binary to be called by the "InstructionMem" class and used to know the next instruction
//
//
//
//}
//class Label {
//
//    String labelname;     // the name given by the user
//
//    PC position;          // the corresponding position to that label
//
//    public Label(String labelname,PC position){
//
//        this.labelname = labelname;
//
//        this.position = position;
//
//    }
//
//    public int findlabel(String s){
//
//        if (this.labelname.equals(s))
//
//            return this.position.getPc();
//
//        else
//
//            return -1000000;
//
//    }
//
//}
//class Assembler {
//
//
//
//    InstructionMem instructionMem;
//
//    String program;
//
//    private RegistersFile f = new RegistersFile();
//
//
//
//
//
//    public void assemble(String p) {
//        this.program=p;
//        String[] splited = this.program.split("\n");
//
//        int iii = splited.length;
//
//        instructionMem = new InstructionMem(iii);
//
//        String[] instructs = new String[iii];
//
//        int ins = 0;
//
//        for (int loo = 0 ; loo<iii ; loo++){
//
//            if (splited[loo].endsWith(":")){
//
//                String[] n = splited[loo].split(":");
//
//                this.instructionMem.instructions[loo+1].label.labelname = n[0];
//
//                instructionMem.labels[loo] = new Label(n[0], new PC(loo));
//
//            }
//
//            else {
//
//                instructs[ins] = splited[loo];
//
//                ins++;
//
//            }
//
//        }
//
//        for (int i = 0; i < ins; i++) {
//
//            instructionMem.instructions[i].instruction = instructs[i];
//
//            instructionMem.instructions[i].pc = new PC(i);
//
//            this.setopcodeandfunctioncodeandinstructiontypeandregister(instructionMem.instructions[i], i);
//
//            System.out.println(instructionMem.instructions[i].instruction);
//
//            System.out.println(instructionMem.instructions[i].machinecode);
//
//            System.out.println(instructionMem.instructions[i].machinecode.length());
//
//            System.out.println(instructionMem.instructions[i].pc);
//
//        }
//
//    }
//
//
//
//
//
//    private void setlabel(String s,int i) {
//
//        int x = -1000000;
//
//        for (int j = 0; j < 1000; j++) {
//
//            x = this.instructionMem.labels[j].findlabel(s);
//
//            if (x != -1000000) {
//
//                this.instructionMem.instructions[i].offset = x;
//
//                break;
//
//            } else {
//
//                this.instructionMem.instructions[i].offset = 0;
//
//            }
//
//        }
//
//    }
//
//
//
//      public static String intToString(int number, int x) {
//
//        if (number == 0)
//
//            return zeroToString(x);
//
//        String binaryString = Integer.toBinaryString(number);
//        switch(binaryString.length()){
//            case 1:
//                binaryString ="0000"+binaryString;
//            case 2:
//                binaryString ="000"+binaryString;
//            case 3:
//                binaryString ="00"+binaryString;
//            case 4:
//                binaryString ="0"+binaryString;
//            default:
//                binaryString = binaryString.substring(binaryString.length() - x);
//        }
//
//        return binaryString;
//
//    }
//
//
//
//    public static String zeroToString(int x) {
//
//        String s = "";
//
//        for (int i = 0; i < x; i++) {
//
//            s += "0";
//
//        }
//
//        return s;
//
//    }
//
//
//
//
//
//    private void assembleadd(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        this.instructionMem.instructions[i].opcode = 0;
//
//        this.instructionMem.instructions[i].functioncode = 32;
//
//        this.instructionMem.instructions[i].instructionType = 'r';
//
//        this.instructionMem.instructions[i].shiftamount = 0;
//
//        for (int j = 0; j < 32; j++) {
//
//            if (splited2[0].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//            if (splited2[2].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rt = j;
//
//        }
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].rtstr);
//
//        instructionMem.instructions[i].rtstr = s4.toString();
//
//
//
//        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
//
//        StringBuilder s5 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
//
//        s5.append(instructionMem.instructions[i].functioncodestr);
//
//        instructionMem.instructions[i].functioncodestr = s5.toString();
//
//
//
//        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
//
//        StringBuilder s6 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s6.append("0");
//
//        s6.append(instructionMem.instructions[i].shiftamountstr);
//
//        instructionMem.instructions[i].shiftamountstr = s6.toString();
//
//    }
//
//
//
//    private void assembleaddi(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        this.instructionMem.instructions[i].opcode = 8;
//
//        this.instructionMem.instructions[i].rt = 0;
//
//        this.instructionMem.instructions[i].instructionType = 'i';
//
//        for (int j = 0; j < 32; j++) {
//
//            if (splited2[0].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//        }
//
//        instructionMem.instructions[i].immediate = Integer.parseInt(splited2[2]);
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].immediatestr = Integer.toBinaryString(instructionMem.instructions[i].immediate);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].immediatestr.length(); j < 16; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].immediatestr);
//
//        instructionMem.instructions[i].immediatestr = s4.toString();
//
//    }
//
//
//
//   private void assemblelw(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        String[] splited3 = splited2[1].split("");
//
//        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
//
//        String s = splited3[2] + splited3[3] + splited3[4];
//
//        this.instructionMem.instructions[i].opcode = 35;
//
//        this.instructionMem.instructions[i].instructionType = 'i';
//
//        for (int j = 0; j < 32; j++) {
//
//            if (s.equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//            if (splited2[0].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//        }
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s1 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");
//
//        s1.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s1.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].offsetstr);
//
//        instructionMem.instructions[i].offsetstr = s4.toString();
//
//    }
//
//
//
//    private void assemblesw(Instruction in, int i) {
//
//          String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        String[] splited3 = splited2[1].split("");
//
//        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
//
//        String s = splited3[2] + splited3[3] + splited3[4];
//
//        this.instructionMem.instructions[i].opcode = 43;
//
//        this.instructionMem.instructions[i].instructionType = 'i';
//
//        for (int j = 0; j < 32; j++) {
//
//            if (s.equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//            if (splited2[0].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//        }
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s1 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");
//
//        s1.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s1.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].offsetstr);
//
//        instructionMem.instructions[i].offsetstr = s4.toString();
//
//    }
//
//
//
//    private void assemblelb(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        String[] splited3 = splited2[1].split("");
//
//        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
//
//        String s = splited3[2] + splited3[3] + splited3[4];
//
//        this.instructionMem.instructions[i].opcode = 43;
//
//        this.instructionMem.instructions[i].instructionType = 'i';
//
//        for (int j = 0; j < 32; j++) {
//
//            if (s.equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//        }
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s1 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");
//
//        s1.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s1.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].offsetstr);
//
//        instructionMem.instructions[i].offsetstr = s4.toString();
//
//    }
//
//
//
//    private void assemblelbu(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        String[] splited3 = splited2[1].split("");
//
//        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
//
//        String s = splited3[2] + splited3[3] + splited3[4];
//
//        this.instructionMem.instructions[i].opcode = 43;
//
//        this.instructionMem.instructions[i].instructionType = 'i';
//
//        for (int j = 0; j < 32; j++) {
//
//            if (s.equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//        }
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s1 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");
//
//        s1.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s1.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].offsetstr);
//
//        instructionMem.instructions[i].offsetstr = s4.toString();
//
//    }
//
//
//
//    private void assemblesb(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        String[] splited3 = splited2[1].split("");
//
//        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
//
//        String s = splited3[2] + splited3[3] + splited3[4];
//
//        this.instructionMem.instructions[i].opcode = 43;
//
//        this.instructionMem.instructions[i].instructionType = 'i';
//
//        for (int j = 0; j < 32; j++) {
//
//            if (s.equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rt = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//        }
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s1 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");
//
//        s1.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s1.toString();
//
//
//
//        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rtstr);
//
//        instructionMem.instructions[i].rtstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].offsetstr);
//
//        instructionMem.instructions[i].offsetstr = s4.toString();
//
//    }
//
//
//
//    private void assemblesll(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        this.instructionMem.instructions[i].opcode = 0;
//
//        this.instructionMem.instructions[i].functioncode = 0;
//
//        this.instructionMem.instructions[i].instructionType = 'r';
//
//        this.instructionMem.instructions[i].rt = 0;
//
//        this.instructionMem.instructions[i].shiftamount = Integer.parseInt(splited2[2]);
//
//        for (int j = 0; j < 32; j++) {
//
//            if (splited2[0].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//        }
//
//
//
//
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].rtstr);
//
//        instructionMem.instructions[i].rtstr = s4.toString();
//
//
//
//        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
//
//        StringBuilder s6 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s6.append("0");
//
//        s6.append(instructionMem.instructions[i].shiftamountstr);
//
//        instructionMem.instructions[i].shiftamountstr = s6.toString();
//
//
//
//        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
//
//        StringBuilder s5 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
//
//        s5.append(instructionMem.instructions[i].functioncodestr);
//
//        instructionMem.instructions[i].functioncodestr = s5.toString();
//
//    }
//
//
//
//    private void assemblenor(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        this.instructionMem.instructions[i].opcode = 0;
//
//        this.instructionMem.instructions[i].functioncode = 39;
//
//        this.instructionMem.instructions[i].instructionType = 'r';
//
//        this.instructionMem.instructions[i].shiftamount = 0;
//
//        for (int j = 0; j < 32; j++) {
//
//            if (splited2[0].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//            if (splited2[2].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rt = j;
//
//        }
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].rtstr);
//
//        instructionMem.instructions[i].rtstr = s4.toString();
//
//
//
//        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
//
//        StringBuilder s5 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
//
//        s5.append(instructionMem.instructions[i].functioncodestr);
//
//        instructionMem.instructions[i].functioncodestr = s5.toString();
//
//
//
//        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
//
//        StringBuilder s6 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s6.append("0");
//
//        s6.append(instructionMem.instructions[i].shiftamountstr);
//
//        instructionMem.instructions[i].shiftamountstr = s6.toString();
//
//
//
//    }
//
//
//
//    private void assemblebeq(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        this.instructionMem.instructions[i].opcode = 4;
//
//        this.instructionMem.instructions[i].instructionType = 'i';
//
//        for (int j = 0; j < 32; j++) {
//
//            if (splited2[0].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rt = j;
//
//        }
//
//        this.setlabel(splited2[2], i);
//
//        instructionMem.instructions[i].offset = this.instructionMem.instructions[i].pc.getPc() - this.instructionMem.instructions[i].offset;
//
//        f.registers[31].setValue(this.instructionMem.instructions[i].pc.getPc());
//
//
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].rtstr);
//
//        instructionMem.instructions[i].rtstr = s4.toString();
//
//
//
//        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
//
//        StringBuilder s5 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s5.append("0");
//
//        s5.append(instructionMem.instructions[i].offsetstr);
//
//        instructionMem.instructions[i].offsetstr = s5.toString();
//
//    }
//
//
//
//    private void assemblej(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        this.instructionMem.instructions[i].opcode = 2;
//
//        this.instructionMem.instructions[i].instructionType = 'j';
//
//        instructionMem.instructions[i].label = new Label(splited[1], new PC(i));
//
//        this.setlabel(splited[1], i);
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
//
//        StringBuilder s5 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 26; j++) s5.append("0");
//
//        s5.append(instructionMem.instructions[i].offsetstr);
//
//        instructionMem.instructions[i].offsetstr = s5.toString();
//
//    }
//
//
//
//    private void assemblejr(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        this.instructionMem.instructions[i].opcode = 0;
//
//        this.instructionMem.instructions[i].functioncode = 8;
//
//        this.instructionMem.instructions[i].instructionType = 'r';
//
//        this.instructionMem.instructions[i].rd=this.instructionMem.instructions[i].rt=this.instructionMem.instructions[i].shiftamount=0;
//
//        this.instructionMem.instructions[i].rdstr=this.instructionMem.instructions[i].rtstr=this.instructionMem.instructions[i].shiftamountstr="00000";
//
//        for (int j = 0; j < 32; j++) {
//
//            if (splited[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//        }
//
//
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s5 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s5.append("0");
//
//        s5.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s5.toString();
//
//
//
//        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
//
//        StringBuilder s6 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s6.append("0");
//
//        s6.append(instructionMem.instructions[i].rtstr);
//
//        instructionMem.instructions[i].rtstr = s6.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s7 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s7.append("0");
//
//        s7.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s7.toString();
//
//
//
//        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
//
//        StringBuilder s8 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s8.append("0");
//
//        s8.append(instructionMem.instructions[i].functioncodestr);
//
//        instructionMem.instructions[i].functioncodestr = s8.toString();
//
//
//
//        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
//
//        StringBuilder s9 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s9.append("0");
//
//        s9.append(instructionMem.instructions[i].shiftamountstr);
//
//        instructionMem.instructions[i].shiftamountstr = s9.toString();
//
//    }
//
//
//
//    private void assemblejal(Instruction in, int i){
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        this.instructionMem.instructions[i].opcode = 3;
//
//        this.instructionMem.instructions[i].instructionType = 'j';
//
//        this.setlabel(splited[1], i);
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
//
//        StringBuilder s5 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 26; j++) s5.append("0");
//
//        s5.append(instructionMem.instructions[i].offsetstr);
//
//        instructionMem.instructions[i].offsetstr = s5.toString();
//
//    }
//
//
//
//    private void assembleslt(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        this.instructionMem.instructions[i].opcode = 0;
//
//        this.instructionMem.instructions[i].functioncode = 42;
//
//        this.instructionMem.instructions[i].instructionType = 'r';
//
//        this.instructionMem.instructions[i].shiftamount = this.instructionMem.instructions[i].rd=this.instructionMem.instructions[i].rt = 0;
//
//        for (int j = 0; j < 32; j++) {
//
//            if (splited2[0].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//            if (splited2[2].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rt = j;
//
//        }
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].rtstr);
//
//        instructionMem.instructions[i].rtstr = s4.toString();
//
//
//
//        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
//
//        StringBuilder s5 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
//
//        s5.append(instructionMem.instructions[i].functioncodestr);
//
//        instructionMem.instructions[i].functioncodestr = s5.toString();
//
//
//
//        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
//
//        StringBuilder s6 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s6.append("0");
//
//        s6.append(instructionMem.instructions[i].shiftamountstr);
//
//        instructionMem.instructions[i].shiftamountstr = s6.toString();
//
//    }
//
//
//
//    private void assembleslti(Instruction in, int i) {
//
//        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
//
//        String[] splited2 = splited[1].split(",");
//
//        this.instructionMem.instructions[i].opcode = 10;
//
//        this.instructionMem.instructions[i].functioncode = 0;
//
//        this.instructionMem.instructions[i].instructionType = 'i';
//
//        this.instructionMem.instructions[i].rt = 0;
//
//        this.instructionMem.instructions[i].immediate = Integer.parseInt(splited2[2]);
//
//        for (int j = 0; j < 32; j++) {
//
//            if (splited2[0].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rd = j;
//
//            if (splited2[1].equals(f.registers[j].name))
//
//                instructionMem.instructions[i].rs = j;
//
//
//
//        }
//
//        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
//
//        StringBuilder s = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
//
//        s.append(instructionMem.instructions[i].opcodestr);
//
//        instructionMem.instructions[i].opcodestr = s.toString();
//
//
//
//        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
//
//        StringBuilder s2 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
//
//        s2.append(instructionMem.instructions[i].rsstr);
//
//        instructionMem.instructions[i].rsstr = s2.toString();
//
//
//
//        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
//
//        StringBuilder s3 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
//
//        s3.append(instructionMem.instructions[i].rdstr);
//
//        instructionMem.instructions[i].rdstr = s3.toString();
//
//
//
//        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
//
//        StringBuilder s4 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
//
//        s4.append(instructionMem.instructions[i].rtstr);
//
//        instructionMem.instructions[i].rtstr = s4.toString();
//
//
//
//        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
//
//        StringBuilder s5 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
//
//        s5.append(instructionMem.instructions[i].functioncodestr);
//
//        instructionMem.instructions[i].functioncodestr = s5.toString();
//
//
//
//        instructionMem.instructions[i].immediatestr = Integer.toBinaryString(instructionMem.instructions[i].immediate);
//
//        StringBuilder s6 = new StringBuilder();
//
//        for (int j = instructionMem.instructions[i].immediatestr.length(); j < 16; j++) s6.append("0");
//
//        s6.append(instructionMem.instructions[i].immediatestr);
//
//        instructionMem.instructions[i].immediatestr = s6.toString();
//
//    }
//
//
//
//    public void setopcodeandfunctioncodeandinstructiontypeandregister(Instruction in, int i) {
//
//       if (in != null) {
//
//           String[] splited = this.instructionMem.instructions[i].instruction.split("\\s+");
//
//           switch (splited[0]) {
//
//               case "add":
//
//                   assembleadd(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;
//
//                   break;
//
//               case "addi":
//
//                   assembleaddi(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].immediatestr;
//
//                   break;
//
//              case "lw":
//
//                   assemblelw(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;
//
//                   break;
//
//               case "sw":
//
//                   assemblesw(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;
//
//                   break;
//               case "lb":
//
//                   assemblelb(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;
//
//                   break;
//
//               case "lbu":
//
//                   assemblelbu(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;
//
//                   break;
//
//               case "sb":
//
//                   assemblesb(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;
//
//                   break;
//
//               case "sll":
//
//                   assemblesll(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;
//
//                   break;
//
//               case "nor":
//
//                   assemblenor(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;
//
//                   break;
//
//               case "beq":
//
//                   assemblebeq(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;
//
//                   break;
//
//               case "j":
//
//                   assemblej(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].offsetstr;
//
//                   break;
//
//               case "jal":
//
//                   assemblejal(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].offsetstr;
//
//                   break;
//
//               case "jr":
//
//                   assemblejr(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr+this.instructionMem.instructions[i].rtstr+this.instructionMem.instructions[i].rdstr+this.instructionMem.instructions[i].shiftamountstr+this.instructionMem.instructions[i].functioncodestr;
//
//                   break;
//
//               case "slt":
//
//                   assembleslt(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;
//
//                   break;
//
//               case "slti":
//
//                   assembleslti(in, i);
//
//                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].immediatestr;
//
//                   break;
//
//            }
//
//        }
//
//    }
//
//}
//class RegistersFile {
//
//    public register[] registers;
//
//
//    public RegistersFile() {
//
//        registers = new register[32];
//
//        registers[0] = new register("$zero",0);
//
//        registers[1] = new register("$at",0);
//
//        registers[2] = new register("$v0",0);
//
//        registers[3] = new register("$v1",0);
//
//        registers[4] = new register("$$a0",0);
//
//        registers[5] = new register("$a1",0);
//
//        registers[6] = new register("$a2",0);
//
//        registers[7] = new register("$a3",0);
//
//        registers[8] = new register("$t0",0);
//
//        registers[9] = new register("$t1",0);
//
//        registers[10] = new register("$t2",0);
//
//        registers[11] = new register("$t3",0);
//
//        registers[12] = new register("$t4",0);
//
//        registers[13] = new register("$t5",0);
//
//        registers[14] = new register("$t6",0);
//
//        registers[15] = new register("$t7",0);
//
//        registers[16] = new register("$s0",0);
//
//        registers[17] = new register("$s1",0);
//
//        registers[18] = new register("$s2",0);
//
//        registers[19] = new register("$s3",0);
//
//        registers[20] = new register("$s4",0);
//
//        registers[21] = new register("$s5",0);
//
//        registers[22] = new register("$s6",0);
//
//        registers[23] = new register("$s7",0);
//
//        registers[24] = new register("$t8",0);
//
//        registers[25] = new register("$t9",0);
//
//        registers[26] = new register("$k0",0);
//
//        registers[27] = new register("$k1",0);
//
//        registers[28] = new register("$gp",0);
//
//        registers[29] = new register("$sp",0);
//
//        registers[30] = new register("$fp",0);
//
//        registers[31] = new register("$ra",0);
//
//    }
//
//    public int getvalue(int registernumber) {
//
//        return registers[registernumber].getValue();
//
//    }
//     public void setvalue(int registernumber,int val) {
//
//        this.registers[registernumber].setValue(val);
//
//    }
//    public register getRegister(int registernumber){
//         return registers[registernumber];
//    }
//
//}
//
//class register {
//
//    public String name;
//
//    private int value;
//
//    public register(String name,int value) {
//
//        this.name = name;
//        this.value=value;
//
//
//    }
//
//    public void setValue(int value) {
//
//        if (!(this.name.equals("$zero"))) {
//            this.value = value;
//        }
//
//    }
//
//    public int getValue() {
//
//        return this.value;
//
//    }
//
//}
//
//class Control {
//
//    PC programcount;
//
//    Assembler assembler;
//
//    private int opCode; //the pased opcode array
//
//    private int[] controlArray;
//
//    private int jump = 0;
//    private String aluop;
//    public Control(int opCode) {
//
//        this.opCode = opCode;
//
//        switch (this.opCode) {
//
//            case 0: // R Format instructions
//
//                controlArray = new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1};
//                aluop ="10";
//
//                break;
//
//            case 8: //addi
//
//                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1};
//                aluop ="00";
//                break;
//
//            case 35: //lw
//
//                controlArray = new int[]{0, 0, 1, 1, 0, 0, 0, 1, 1};
//                aluop ="00";
//                break;
//
//            case 43: //sw
//
//                controlArray = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 0};
//                aluop ="00";
//                break;
//
//            case 32:
//
//                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
//                aluop ="00";
//                break;
//
//            case 36:
//
//                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
//                aluop ="00";
//                break;
//
//            case 40:
//
//                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
//                aluop ="00";
//                break;
//
//            case 39:
//
//                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
//                aluop ="00";
//                break;
//
//            case 4: //beq
//
//                controlArray = new int[]{0, 1, 0, 0, 0, 1, 0, 0, 0};
//                aluop ="01";
//                break;
//
//            case 2: //j
//
//                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
//                aluop ="00";
//                jump = 1;
//
//                break;
//
//            case 3:
//
//                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
//                aluop ="00";
//                break;
//
//            case 42: //slt
//
//                controlArray = new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1};
//                aluop ="10";
//                break;
//
//            case 10:
//
//                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
//                aluop ="00";
//                break;
//
//        }
//
//    }
//
//    public int getRegDst() {
//        return controlArray[0];
//    }
//
//    public int getBranch() {
//        return controlArray[1];
//    }
//
//    public int getmemRead() {
//        return controlArray[2];
//    }
//
//    public int getmemroReg() {
//        return controlArray[3];
//    }
//
//    public String getALUop() {
//
//        return this.aluop;
//
//    }
//
//    public int getmemWrite() {
//        return controlArray[6];
//    }
//
//    public int getALUsrc() {
//        return controlArray[7];
//    }
//
//    public int getRegWrite() {
//        return controlArray[8];
//    }
//
//    public int getJump() {
//        return jump;
//    }
//
//}
//
//class ALU {
//
//    private register r1;
//
//    private register r2;
//
//    private int ALUSrc;
//
//    private int offset;
//
//    private String AluControlSignal;
//    private int result=0;
//
//
//    public ALU(register r1, register r2, int ALUSrc, int address,String aluc) {
//
//        this.r1 = r1;
//
//        this.r2 = r2;
//
//        this.ALUSrc = ALUSrc;
//
//        this.offset = address;
//        this.AluControlSignal=aluc;
//
//        if (ALUSrc == 1) {
//
//            result = r1.getValue() + this.offset;
//
//        } else if (ALUSrc == 0) {
//
//            switch (AluControlSignal) {
//
//                case "000":
//
//                    result = r1.getValue() & r2.getValue();
//
//                    break;
//
//                case "001":
//
//                    result = r1.getValue() | r2.getValue();
//
//                    break;
//
//                case "010":
//
//                    result = r1.getValue() + r2.getValue();
//
//                case "110":
//
//                    result = r1.getValue() - r2.getValue();
//
//                case "111":
//
//                    if (r1.getValue() < r2.getValue()) {
//                        result = 1;
//                    } else {
//                        result = 0;
//                    }
//
//                    break;
//
//            }
//
//        }
//
//    }
//    public ALU(){
//
//    }
//
//    public register getR1() {
//
//        return r1;
//
//    }
//
//    public void setR1(register r1) {
//
//        this.r1 = r1;
//
//    }
//
//    public register getR2() {
//
//        return r2;
//
//    }
//
//    public void setR2(register r2) {
//
//        this.r2 = r2;
//
//    }
//
//    public int getALUSrc() {
//
//        return ALUSrc;
//
//    }
//
//    public void setALUSrc(int ALUSrc) {
//
//        this.ALUSrc = ALUSrc;
//
//    }
//
//    public String getAluControlSignal() {
//
//        return AluControlSignal;
//
//    }
//
//    public void setAluControlSignal(String AluControlSignal) {
//
//        this.AluControlSignal = AluControlSignal;
//
//    }
//    public int getResult(){
//        return this.result;
//    }
//
//}
//class AluControl {
//
//    private String AluOP;
//
//    private String FunctCode="";
//
//    private String AluControlSignal="";
//
//    public AluControl(String AluOP, String FunctCode) {
//
//        this.AluOP = AluOP;
//
//        this.FunctCode = FunctCode;
//
//
//
//        switch (this.AluOP){
//
//            case "00":
//
//                AluControlSignal = "010";
//
//                break;
//
//            case "01":
//
//                AluControlSignal = "110";
//
//                break;
//
//            case "10":
//
//                switch (this.FunctCode){
//
//                    case "100000":
//
//                        AluControlSignal = "010";
//
//                        break;
//
//                    case "100010":
//
//                        AluControlSignal = "110";
//
//                        break;
//
//                    case "100100":
//
//                        AluControlSignal = "000";
//
//                        break;
//
//                    case "100101":
//
//                        AluControlSignal = "001";
//
//                        break;
//
//                    case "101010":
//
//                        AluControlSignal = "111";
//
//                        break;
//
//                }
//
//                break;
//
//        }
//
//    }
//
//
//
//    public String getAluOP() {
//
//        return AluOP;
//
//    }
//
//
//
//    public void setAluOP(String AluOP) {
//
//        this.AluOP = AluOP;
//
//    }
//
//
//
//    public String getFunctCode() {
//
//        return FunctCode;
//
//    }
//
//    public String getAluSignal() {
//
//        return AluControlSignal;
//
//    }
//
//    public void setFunctCode(String FunctCode) {
//
//        this.FunctCode = FunctCode;
//
//    }
//
//}
//class Memory {
//
//    private int value;  // Value stored inside the memory object
//    //private int location;  // Location of the memory object
//
//    public Memory(int value) {
//        this.value = value;
//        // this.location = location;
//    }  // Initialize a memory object with a location and a value
//
//    public int getValue() {
//        return this.value;  // Returns the value stored inside a memory object with specified location
//    }
//
//    public void write(int value) {
//        this.value = value;  // Overwrites the value of a memory object
//    }
//}
//class DataMem{
//    Memory[] dataMemory =new Memory[1000];
//    public DataMem(){
//        for(int i=0;i<1000;i++){
//            dataMemory[i]=new Memory(0);
//        }
//    }
//    public int read(int address){
//        return dataMemory[address].getValue();
//    }
//    public void write(int value,int address){
//        dataMemory[address].write(value);
//    }
//}
