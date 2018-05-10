package guim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import jdk.nashorn.internal.ir.Labels;

public class Main extends JFrame {

    public static void main(String[] args) {
        Gui m = new Gui();
        m.setVisible(true);

    }

}

class Instruction {

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

    public Instruction(int count) {

        this.pc = new PC(count);

        this.label = new Label(null, pc);

    }

    public String setcontrolunitbinary() {

        return this.opcodestr;

    }

    public int setcontrolunitints() {

        return this.opcode;

    }

    public String setregisterread1binary() {

        return this.machinecode.substring(31 - 25, 31 - 21);

    }

    public int setregisterread1ints() {

        return this.rs;

    }

    public String setregisterread2binary() {

        return this.machinecode.substring(31 - 20, 31 - 16);

    }

    public int setregisterread2ints() {

        return this.rt;

    }

    public String setmux1binary() {

        return this.machinecode.substring(31 - 15, 31 - 11);

    }

    public int setmux1ints() {

        return this.rs;

    }

    public String setsignextendbinary() {

        return this.machinecode.substring(31 - 15, 31 - 0);

    }

    public int setsignextendints() {

        return this.rs;

    }

}

class InstructionMem {

    Label[] labels = new Label[1000];

    Instruction[] instructions = new Instruction[1000];

    public InstructionMem(int len) {

        for (int i = 0; i < len; i++) {

            instructions[i] = new Instruction(i);

        }

    }

}

class PC {

    private int pc; //this variable represents the program counter

    private String pcbinary;

    public PC(int pc) {
        this.pc = pc;
        StringBuilder s = new StringBuilder();

        pcbinary = Integer.toBinaryString(pc);

        for (int h = pcbinary.length(); h < 32; h++) {
            s.append("0");
        }

        s.append(pcbinary);
        pcbinary = s.toString();

    } //the constructor to be used to intialize the pc at the beggining

    @Override

    public String toString() {
        return "Program Counter = " + pc;
    } //designs the appropriate string to be used in representation of the results

    public int getPc() {
        return pc;
    } //getter for the program counter to be called by the "InstructionMem" class and used to know the next instruction

    public String getPcbinary() {
        return pcbinary;
    } //getter for the program counter in binary to be called by the "InstructionMem" class and used to know the next instruction

}

class Label {

    String labelname;     // the name given by the user

    PC position;          // the corresponding position to that label

    public Label(String labelname, PC position) {

        this.labelname = labelname;

        this.position = position;

    }

    public int findlabel(String s) {

        if (this.labelname.equals(s)) {
            return this.position.getPc();
        } else {
            return -1000000;
        }
    }
}

class Assembler {

    public InstructionMem instructionMem;

    public String program;

    public RegistersFile f = new RegistersFile();

    public int ins;

    public void cleaner() {

        String[] cleanprogram = program.split("\\s*(,|\\s)\\s*");

        program = "";

        int count = 0;

        for (int i = 0; i < cleanprogram.length && count < cleanprogram.length; i++) {

            if (cleanprogram[count].contains(":") && cleanprogram[count].length() > 1) {

                program += cleanprogram[count] + "\n";

                count++;

                continue;

            } else if (cleanprogram[count].equals(":") && count > 0) {

                program += cleanprogram[count - 1] + cleanprogram[count] + "\n";

                count++;

                continue;

            } else {

                switch (cleanprogram[count]) {

                    case "add":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";

                        count += 3;

                        break;

                    case "addi":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";

                        count += 3;

                        break;

                    case "sll":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";

                        count += 3;

                        break;

                    case "nor":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";

                        count += 3;

                        break;

                    case "beq":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";

                        count += 3;

                        break;

                    case "bne":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";

                        count += 3;

                        break;

                    case "slt":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";

                        count += 3;

                        break;

                    case "slti":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";

                        count += 3;

                        break;

                    case "lw":

                        switch (cleanprogram[count + 2].length()) {

                            case 1: // lw $rq 0 ($ra)

                                switch (cleanprogram[count + 3].length()) {

                                    case 1:

                                        switch (cleanprogram[count + 4].length()) {

                                            case 3:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + cleanprogram[count + 5] + "\n";

                                                count += 5;

                                                break;

                                            case 4:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                                count += 4;

                                                break;

                                            default:

                                            //error
                                        }

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 5;

                                        break;

                                    case 5:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 2: // lw $ra 0( $ra)

                                switch (cleanprogram[i + 3].length()) {

                                    case 3:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 4;

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 5: // lw $ra 0($ra )

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                count += 3;

                                break;

                            case 6:

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";

                                count += 2;

                                break;

                            default:

                            //error
                        }

                        break;

                    case "sw":

                        switch (cleanprogram[count + 2].length()) {

                            case 1: // lw $rq 0 ($ra)

                                switch (cleanprogram[count + 3].length()) {

                                    case 1:

                                        switch (cleanprogram[count + 4].length()) {

                                            case 3:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + cleanprogram[count + 5] + "\n";

                                                count += 5;

                                                break;

                                            case 4:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                                count += 4;

                                                break;

                                            default:

                                            //error
                                        }

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 5;

                                        break;

                                    case 5:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 2: // lw $ra 0( $ra)

                                switch (cleanprogram[i + 3].length()) {

                                    case 3:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 4;

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 5: // lw $ra 0($ra )

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                count += 3;

                                break;

                            case 6:

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";

                                count += 2;

                                break;

                            default:

                            //error
                        }

                        break;

                    case "lb":

                        switch (cleanprogram[count + 2].length()) {

                            case 1: // lw $rq 0 ($ra)

                                switch (cleanprogram[count + 3].length()) {

                                    case 1:

                                        switch (cleanprogram[count + 4].length()) {

                                            case 3:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + cleanprogram[count + 5] + "\n";

                                                count += 5;

                                                break;

                                            case 4:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                                count += 4;

                                                break;

                                            default:

                                            //error
                                        }

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 5;

                                        break;

                                    case 5:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 2: // lw $ra 0( $ra)

                                switch (cleanprogram[i + 3].length()) {

                                    case 3:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 4;

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 5: // lw $ra 0($ra )

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                count += 3;

                                break;

                            case 6:

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";

                                count += 2;

                                break;

                            default:

                            //error
                        }

                        break;

                    case "sb":

                        switch (cleanprogram[count + 2].length()) {

                            case 1: // lw $rq 0 ($ra)

                                switch (cleanprogram[count + 3].length()) {

                                    case 1:

                                        switch (cleanprogram[count + 4].length()) {

                                            case 3:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + cleanprogram[count + 5] + "\n";

                                                count += 5;

                                                break;

                                            case 4:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                                count += 4;

                                                break;

                                            default:

                                            //error
                                        }

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 5;

                                        break;

                                    case 5:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 2: // lw $ra 0( $ra)

                                switch (cleanprogram[i + 3].length()) {

                                    case 3:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 4;

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 5: // lw $ra 0($ra )

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                count += 3;

                                break;

                            case 6:

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";

                                count += 2;

                                break;

                            default:

                            //error
                        }

                        break;

                    case "lbu":

                        switch (cleanprogram[count + 2].length()) {

                            case 1: // lw $rq 0 ($ra)

                                switch (cleanprogram[count + 3].length()) {

                                    case 1:

                                        switch (cleanprogram[count + 4].length()) {

                                            case 3:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + cleanprogram[count + 5] + "\n";

                                                count += 5;

                                                break;

                                            case 4:

                                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                                count += 4;

                                                break;

                                            default:

                                            //error
                                        }

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 5;

                                        break;

                                    case 5:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 2: // lw $ra 0( $ra)

                                switch (cleanprogram[i + 3].length()) {

                                    case 3:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";

                                        count += 4;

                                        break;

                                    case 4:

                                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                        count += 3;

                                        break;

                                    default:

                                    //error
                                }

                                break;

                            case 5: // lw $ra 0($ra )

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";

                                count += 3;

                                break;

                            case 6:

                                program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";

                                count += 2;

                                break;

                            default:

                            //error
                        }

                        break;

                    case "j":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "\n";

                        count += 2;

                        break;

                    case "jal":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "\n";

                        count += 2;

                        break;

                    case "jr":

                        program += cleanprogram[count] + " " + cleanprogram[count + 1] + "\n";

                        count += 2;

                        break;

                    default:

                        count++;

                    //error
                }

            }

        }

    }

    public void assemble(String s) {
        this.program = s;
        cleaner();

        String[] splited = this.program.split("\n");

        int iii = splited.length;

        instructionMem = new InstructionMem(iii);

        String[] instructs = new String[iii];

        ins = 0;

        for (int loo = 0; loo < iii; loo++) {

            if (splited[loo].endsWith(":")) {

                String[] n = splited[loo].split(":");

                Label x = new Label(n[0].trim(), new PC(ins));

                this.instructionMem.instructions[ins].label = x;

                instructionMem.labels[ins] = x;

            } else if ((splited[loo].trim().length()) == 0) {

                continue;

            } else {

                instructs[ins] = splited[loo];

                ins++;

            }

        }

        for (int i = 0; i < ins; i++) {
            try{
                instructionMem.instructions[i].instruction = instructs[i];

                instructionMem.instructions[i].pc = new PC(i);
           
                this.setopcodeandfunctioncodeandinstructiontypeandregister(instructionMem.instructions[i], i);
            }catch (Exception e){
            JOptionPane.showMessageDialog(null,"There seems to be a problem\nPlease check if you:\nJumping or Branching to a nonexisting label\nEntered a non Existing Instruction or Register\nNOTE : The program doesn't accept any instruction not preceded by a space\nor any register in the form of it's number");
            }

            /*System.out.println(instructionMem.instructions[i].instruction);

            System.out.println(instructionMem.instructions[i].machinecode);

            System.out.println(instructionMem.instructions[i].machinecode.length());

            System.out.println(instructionMem.instructions[i].pc);*/

        }

    }

    private void setlabel(String s, int i) {

        int x = -1000000;

        for (int j = 0; j < 1000; j++) {

            if (instructionMem.labels[j] != null) {

                x = this.instructionMem.labels[j].findlabel(s);

                if (x != -1000000) {

                    this.instructionMem.instructions[i].offset = x;

                    break;

                } else {

                    this.instructionMem.instructions[i].offset = 0;

                }

            }

        }

    }

    public static String intToString(int number, int x) {

        if (number == 0) {
            return zeroToString(x);
        }

        String binaryString = Integer.toBinaryString(number);

        binaryString = binaryString.substring(binaryString.length() - x);

        return binaryString;

    }

    public static String zeroToString(int x) {

        String s = "";

        for (int i = 0; i < x; i++) {

            s += "0";

        }

        return s;

    }

    private void assembleadd(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        this.instructionMem.instructions[i].opcode = 0;

        this.instructionMem.instructions[i].functioncode = 32;

        this.instructionMem.instructions[i].instructionType = 'r';

        this.instructionMem.instructions[i].shiftamount = 0;

        for (int j = 0; j < 32; j++) {

            if (splited2[0].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rd = j;
            }

            if (splited2[1].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rs = j;
            }

            if (splited2[2].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rt = j;
            }

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) {
            s3.append("0");
        }

        s3.append(instructionMem.instructions[i].rdstr);

        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s4 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s4.append("0");
        }

        s4.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].functioncodestr);

        instructionMem.instructions[i].functioncodestr = s5.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);

        StringBuilder s6 = new StringBuilder();

        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) {
            s6.append("0");
        }

        s6.append(instructionMem.instructions[i].shiftamountstr);

        instructionMem.instructions[i].shiftamountstr = s6.toString();

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;

    }

    private void assembleaddi(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        this.instructionMem.instructions[i].opcode = 8;

        this.instructionMem.instructions[i].rd = 0;

        this.instructionMem.instructions[i].instructionType = 'i';

        for (int j = 0; j < 32; j++) {

            if (splited2[0].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rt = j;
            }

            if (splited2[1].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rs = j;
            }

        }

        instructionMem.instructions[i].offset = Integer.parseInt(splited2[2]);

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s3.append("0");
        }

        s3.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s3.toString();

        if (instructionMem.instructions[i].offset >= 0) {

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

        } else {

            instructionMem.instructions[i].offset *= -1;

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].offsetstr = this.twosCompliment(this.instructionMem.instructions[i].offsetstr);

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

        }

    }

    private void assemblelw(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        String[] splited3 = splited2[1].split("");

        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);

        String s = splited3[2] + splited3[3] + splited3[4];

        this.instructionMem.instructions[i].opcode = 35;

        this.instructionMem.instructions[i].instructionType = 'i';

        for (int j = 0; j < 32; j++) {

            if (s.equals(f.registers[j].name)) {
                instructionMem.instructions[i].rs = j;
            }

            if (splited2[0].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rt = j;
            }

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s1 = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s1.append("0");
        }

        s1.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s2.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s3.append("0");
        }

        s3.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s3.toString();

        if (instructionMem.instructions[i].offset >= 0) {

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

        } else {

            instructionMem.instructions[i].offset *= -1;

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].offsetstr = this.twosCompliment(this.instructionMem.instructions[i].offsetstr);

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

        }

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

    }

    private void assemblesw(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        String[] splited3 = splited2[1].split("");

        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);

        String s = splited3[2] + splited3[3] + splited3[4];

        this.instructionMem.instructions[i].opcode = 43;

        this.instructionMem.instructions[i].instructionType = 'i';

        for (int j = 0; j < 32; j++) {

            if (s.equals(f.registers[j].name)) {
                instructionMem.instructions[i].rs = j;
            }

            if (splited2[0].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rt = j;
            }

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s1 = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s1.append("0");
        }

        s1.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s2.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s3.append("0");
        }

        s3.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s3.toString();

        if (instructionMem.instructions[i].offset >= 0) {

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

        } else {

            instructionMem.instructions[i].offset *= -1;

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].offsetstr = this.twosCompliment(this.instructionMem.instructions[i].offsetstr);

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

        }

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

    }

    private void assemblelb(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");



        String[] splited2 = splited[1].split(",");



        String[] splited3 = splited2[1].split("");

        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);

        String s = splited3[2] + splited3[3] + splited3[4];

        this.instructionMem.instructions[i].opcode = 32;

        this.instructionMem.instructions[i].instructionType = 'i';

        for (int j = 0; j < 32; j++) {

            if (s.equals(f.registers[j].name))

                instructionMem.instructions[i].rt = j;

            if (splited2[0].equals(f.registers[j].name))

                instructionMem.instructions[i].rs = j;

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s1 = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");

        s1.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s1.toString();



        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s2.append("0");

        s2.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s2.toString();



        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s3.append("0");

        s3.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s3.toString();



        if (instructionMem.instructions[i].offset>=0) {

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

        }

        else{

            instructionMem.instructions[i].offset*=-1;

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].offsetstr=this.twosCompliment(this.instructionMem.instructions[i].offsetstr);

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

        }

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;



    }



    private void assemblelbu(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");



        String[] splited2 = splited[1].split(",");



        String[] splited3 = splited2[1].split("");

        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);

        String s = splited3[2] + splited3[3] + splited3[4];

        this.instructionMem.instructions[i].opcode = 36;

        this.instructionMem.instructions[i].instructionType = 'i';

        for (int j = 0; j < 32; j++) {

            if (s.equals(f.registers[j].name))

                instructionMem.instructions[i].rt = j;

            if (splited2[0].equals(f.registers[j].name))

                instructionMem.instructions[i].rs = j;

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s1 = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");

        s1.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s1.toString();



        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s2.append("0");

        s2.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s2.toString();



        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s3.append("0");

        s3.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s3.toString();



        if (instructionMem.instructions[i].offset>=0) {

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

        }

        else{

            instructionMem.instructions[i].offset*=-1;

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].offsetstr=this.twosCompliment(this.instructionMem.instructions[i].offsetstr);

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

        }



        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;



    }



    private void assemblesb(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");



        String[] splited2 = splited[1].split(",");

        String[] splited3 = splited2[1].split("");

        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);

        String s = splited3[2] + splited3[3] + splited3[4];

        this.instructionMem.instructions[i].opcode = 40;

        this.instructionMem.instructions[i].instructionType = 'i';

        int key = 0;

        for (int j = 0; j < 32 &&key!=2; j++) {

            if (s.equals(f.registers[j].name)) {

                instructionMem.instructions[i].rt = j;

                key++;

            }

            if (splited2[0].equals(f.registers[j].name)) {

                instructionMem.instructions[i].rs = j;

                key++;

            }

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s1 = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");

        s1.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s1.toString();



        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s2.append("0");

        s2.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s2.toString();



        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s3.append("0");

        s3.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s3.toString();





        if (instructionMem.instructions[i].offset>=0) {

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

        }

        else{

            instructionMem.instructions[i].offset*=-1;

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].offsetstr=this.twosCompliment(this.instructionMem.instructions[i].offsetstr);

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

        }





        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

    }

    private void assemblesll(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        this.instructionMem.instructions[i].opcode = 0;

        this.instructionMem.instructions[i].functioncode = 0;

        this.instructionMem.instructions[i].instructionType = 'r';

        this.instructionMem.instructions[i].rt = 0;

        this.instructionMem.instructions[i].shiftamount = Integer.parseInt(splited2[2]);

        for (int j = 0; j < 32; j++) {

            if (splited2[0].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rd = j;
            }

            if (splited2[1].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rs = j;
            }

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) {
            s3.append("0");
        }

        s3.append(instructionMem.instructions[i].rdstr);

        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s4 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s4.append("0");
        }

        s4.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);

        StringBuilder s6 = new StringBuilder();

        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) {
            s6.append("0");
        }

        s6.append(instructionMem.instructions[i].shiftamountstr);

        instructionMem.instructions[i].shiftamountstr = s6.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].functioncodestr);

        instructionMem.instructions[i].functioncodestr = s5.toString();

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;

    }

    private void assemblenor(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        this.instructionMem.instructions[i].opcode = 0;

        this.instructionMem.instructions[i].functioncode = 39;

        this.instructionMem.instructions[i].instructionType = 'r';

        this.instructionMem.instructions[i].shiftamount = 0;

        for (int j = 0; j < 32; j++) {

            if (splited2[0].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rd = j;
            }

            if (splited2[1].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rs = j;
            }

            if (splited2[2].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rt = j;
            }

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) {
            s3.append("0");
        }

        s3.append(instructionMem.instructions[i].rdstr);

        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s4 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s4.append("0");
        }

        s4.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].functioncodestr);

        instructionMem.instructions[i].functioncodestr = s5.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);

        StringBuilder s6 = new StringBuilder();

        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) {
            s6.append("0");
        }

        s6.append(instructionMem.instructions[i].shiftamountstr);

        instructionMem.instructions[i].shiftamountstr = s6.toString();

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;

    }

    private void assemblebeq(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        this.instructionMem.instructions[i].opcode = 4;

        this.instructionMem.instructions[i].instructionType = 'i';

        int key = 0;

        for (int j = 0; j < 32; j++) {

            if (splited2[0].equals(f.registers[j].name)) {

                instructionMem.instructions[i].rs = j;

                key++;

            }

            if (splited2[1].equals(f.registers[j].name)) {

                instructionMem.instructions[i].rt = j;

                key++;

            }

        }

        this.setlabel(splited2[2], i);

        instructionMem.instructions[i].offset = this.instructionMem.instructions[i].offset - this.instructionMem.instructions[i].pc.getPc();

        this.instructionMem.instructions[i].pc.getPc();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s5.toString();

        if (instructionMem.instructions[i].offset >= 0) {

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

        } else {

            instructionMem.instructions[i].offset *= -1;

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].offsetstr = this.twosCompliment(this.instructionMem.instructions[i].offsetstr);

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

        }

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

    }

    private void assemblebne(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        this.instructionMem.instructions[i].opcode = 5;

        this.instructionMem.instructions[i].instructionType = 'i';

        int key = 0;

        for (int j = 0; j < 32; j++) {

            if (splited2[0].equals(f.registers[j].name)) {

                instructionMem.instructions[i].rs = j;

                key++;

            }

            if (splited2[1].equals(f.registers[j].name)) {

                instructionMem.instructions[i].rt = j;

                key++;

            }

        }

        this.setlabel(splited2[2], i);

        instructionMem.instructions[i].offset = this.instructionMem.instructions[i].offset - this.instructionMem.instructions[i].pc.getPc();

        this.instructionMem.instructions[i].pc.getPc();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s5.toString();

        if (instructionMem.instructions[i].offset >= 0) {

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

        } else {

            instructionMem.instructions[i].offset *= -1;

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].offsetstr = this.twosCompliment(this.instructionMem.instructions[i].offsetstr);

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

        }

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

    }

    private void assemblej(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        this.instructionMem.instructions[i].opcode = 2;

        this.instructionMem.instructions[i].instructionType = 'j';

        this.setlabel(splited[1], i);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 26; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].offsetstr);

        instructionMem.instructions[i].offsetstr = s5.toString();

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].offsetstr;

    }

    private void assemblejr(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        this.instructionMem.instructions[i].opcode = 0;

        this.instructionMem.instructions[i].functioncode = 8;

        this.instructionMem.instructions[i].instructionType = 'r';

        this.instructionMem.instructions[i].rd = this.instructionMem.instructions[i].rt = this.instructionMem.instructions[i].shiftamount = 0;

        this.instructionMem.instructions[i].rdstr = this.instructionMem.instructions[i].rtstr = this.instructionMem.instructions[i].shiftamountstr = "00000";

        for (int j = 0; j < 32; j++) {

            if (splited[1].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rs = j;
            }

        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s5.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s6 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s6.append("0");
        }

        s6.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s6.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);

        StringBuilder s7 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) {
            s7.append("0");
        }

        s7.append(instructionMem.instructions[i].rdstr);

        instructionMem.instructions[i].rdstr = s7.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);

        StringBuilder s8 = new StringBuilder();

        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) {
            s8.append("0");
        }

        s8.append(instructionMem.instructions[i].functioncodestr);

        instructionMem.instructions[i].functioncodestr = s8.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);

        StringBuilder s9 = new StringBuilder();

        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) {
            s9.append("0");
        }

        s9.append(instructionMem.instructions[i].shiftamountstr);

        instructionMem.instructions[i].shiftamountstr = s9.toString();

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;

    }

    private void assemblejal(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        this.instructionMem.instructions[i].opcode = 3;

        this.instructionMem.instructions[i].instructionType = 'j';

        this.setlabel(splited[1], i);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 26; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].offsetstr);

        instructionMem.instructions[i].offsetstr = s5.toString();

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].offsetstr;

    }

    private void assembleslt(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        this.instructionMem.instructions[i].opcode = 0;

        this.instructionMem.instructions[i].functioncode = 42;

        this.instructionMem.instructions[i].instructionType = 'r';

        this.instructionMem.instructions[i].shiftamount = this.instructionMem.instructions[i].rd = this.instructionMem.instructions[i].rt = 0;

        for (int j = 0; j < 32; j++) {

            if (splited2[0].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rd = j;
            }

            if (splited2[1].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rs = j;
            }

            if (splited2[2].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rt = j;
            }

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) {
            s3.append("0");
        }

        s3.append(instructionMem.instructions[i].rdstr);

        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s4 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s4.append("0");
        }

        s4.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].functioncodestr);

        instructionMem.instructions[i].functioncodestr = s5.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);

        StringBuilder s6 = new StringBuilder();

        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) {
            s6.append("0");
        }

        s6.append(instructionMem.instructions[i].shiftamountstr);

        instructionMem.instructions[i].shiftamountstr = s6.toString();

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;

    }

    private void assembleslti(Instruction in, int i) {

        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        this.instructionMem.instructions[i].opcode = 10;

        this.instructionMem.instructions[i].functioncode = 0;

        this.instructionMem.instructions[i].instructionType = 'i';

        this.instructionMem.instructions[i].rd = 0;

        this.instructionMem.instructions[i].offset = Integer.parseInt(splited2[2]);

        for (int j = 0; j < 32; j++) {

            if (splited2[0].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rt = j;
            }

            if (splited2[1].equals(f.registers[j].name)) {
                instructionMem.instructions[i].rs = j;
            }

        }

        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);

        StringBuilder s = new StringBuilder();

        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) {
            s.append("0");
        }

        s.append(instructionMem.instructions[i].opcodestr);

        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);

        StringBuilder s2 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) {
            s2.append("0");
        }

        s2.append(instructionMem.instructions[i].rsstr);

        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);

        StringBuilder s3 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) {
            s3.append("0");
        }

        s3.append(instructionMem.instructions[i].rtstr);

        instructionMem.instructions[i].rtstr = s3.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);

        StringBuilder s42 = new StringBuilder();

        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) {
            s42.append("0");
        }

        s42.append(instructionMem.instructions[i].rdstr);

        instructionMem.instructions[i].rdstr = s42.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);

        StringBuilder s5 = new StringBuilder();

        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) {
            s5.append("0");
        }

        s5.append(instructionMem.instructions[i].functioncodestr);

        instructionMem.instructions[i].functioncodestr = s5.toString();

        if (instructionMem.instructions[i].offset >= 0) {

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

        } else {

            instructionMem.instructions[i].offset *= -1;

            instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);

            StringBuilder s4 = new StringBuilder();

            for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) {
                s4.append("0");
            }

            s4.append(instructionMem.instructions[i].offsetstr);

            instructionMem.instructions[i].offsetstr = s4.toString();

            this.instructionMem.instructions[i].offsetstr = this.twosCompliment(this.instructionMem.instructions[i].offsetstr);

            this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

        }

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;

    }

    public void setopcodeandfunctioncodeandinstructiontypeandregister(Instruction in, int i) {

        if (in != null) {

            String[] splited = this.instructionMem.instructions[i].instruction.split("\\s+");

            switch (splited[0]) {

                case "add":

                    assembleadd(in, i);

                    break;

                case "addi":

                    assembleaddi(in, i);

                    break;

                case "lw":

                    assemblelw(in, i);

                    break;

                case "sw":

                    assemblesw(in, i);

                    break;

                case "lb":

                    assemblelb(in, i);

                    break;

                case "lbu":

                    assemblelbu(in, i);

                    break;

                case "sb":

                    assemblesb(in, i);

                    break;

                case "sll":

                    assemblesll(in, i);

                    break;

                case "nor":

                    assemblenor(in, i);

                    break;

                case "beq":

                    assemblebeq(in, i);

                    break;

                case "bne":

                    assemblebne(in, i);

                    break;

                case "j":

                    assemblej(in, i);

                    break;

                case "jal":

                    assemblejal(in, i);

                    break;

                case "jr":

                    assemblejr(in, i);

                    break;

                case "slt":

                    assembleslt(in, i);

                    break;

                case "slti":

                    assembleslti(in, i);

                    break;

            }

        }

    }

    public String twosCompliment(String bin) {

        String twos = "", ones = "";

        for (int i = 0; i < bin.length(); i++) {

            ones += flip(bin.charAt(i));

        }

        int number0 = Integer.parseInt(ones, 2);

        StringBuilder builder = new StringBuilder(ones);

        boolean b = false;

        for (int i = ones.length() - 1; i > 0; i--) {

            if (ones.charAt(i) == '1') {

                builder.setCharAt(i, '0');

            } else {

                builder.setCharAt(i, '1');

                b = true;

                break;

            }

        }

        if (!b) {
            builder.append("1", 0, 7);
        }

        twos = builder.toString();

        return twos;

    }

    // Returns '0' for '1' and '1' for '0'
    public char flip(char c) {

        return (c == '0') ? '1' : '0';

    }

}

class RegistersFile {

    public register[] registers;

    public RegistersFile() {

        registers = new register[32];

        registers[0] = new register("$zero", 0);

        registers[1] = new register("$at", 0);

        registers[2] = new register("$v0", 0);

        registers[3] = new register("$v1", 0);

        registers[4] = new register("$a0", 0);

        registers[5] = new register("$a1", 0);

        registers[6] = new register("$a2", 0);

        registers[7] = new register("$a3", 0);

        registers[8] = new register("$t0", 0);

        registers[9] = new register("$t1", 0);

        registers[10] = new register("$t2", 0);

        registers[11] = new register("$t3", 0);

        registers[12] = new register("$t4", 0);

        registers[13] = new register("$t5", 0);

        registers[14] = new register("$t6", 0);

        registers[15] = new register("$t7", 0);

        registers[16] = new register("$s0", 0);

        registers[17] = new register("$s1", 0);

        registers[18] = new register("$s2", 0);

        registers[19] = new register("$s3", 0);

        registers[20] = new register("$s4", 0);

        registers[21] = new register("$s5", 0);

        registers[22] = new register("$s6", 0);

        registers[23] = new register("$s7", 0);

        registers[24] = new register("$t8", 0);

        registers[25] = new register("$t9", 0);

        registers[26] = new register("$k0", 0);

        registers[27] = new register("$k1", 0);

        registers[28] = new register("$gp", 0);

        registers[29] = new register("$sp", 10000);

        registers[30] = new register("$fp", 0);

        registers[31] = new register("$ra", 0);

    }

    public int getvalue(int registernumber) {

        return registers[registernumber].getValue();

    }

    public void setvalue(int registernumber, int val) {

        this.registers[registernumber].setValue(val);

    }

    public register getRegister(int registernumber) {
        return registers[registernumber];
    }

}

class register {

    public String name;

    private int value;

    public register(String name, int value) {

        this.name = name;
        this.value = value;

    }

    public void setValue(int value) {

        if (!(this.name.equals("$zero"))) {
            this.value = value;
        }

    }

    public int getValue() {

        return this.value;

    }

}

class Control {

    PC programcount;

    Assembler assembler;

    private int opCode; //the pased opcode array

    private int[] controlArray;

    private int jump = 0;
    private String aluop;
    private String regdst;
    private String memtoreg;

    public Control(int opCode) {

        this.opCode = opCode;

        switch (this.opCode) {

            case 0: // R Format instructions

                controlArray = new int[]{0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1};
                aluop = "10";
                regdst = "01";
                memtoreg = "00";
                break;

            case 8: //addi

                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1};
                aluop = "00";
                regdst = "00";
                memtoreg = "00";
                break;

            case 35: //lw

                controlArray = new int[]{0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1};
                aluop = "00";
                regdst = "00";
                memtoreg = "01";
                break;

            case 43: //sw

                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0};
                aluop = "00";
                regdst = "00";
                memtoreg = "00";
                break;

            case 32: // lb

                controlArray = new int[]{0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1};
                aluop = "00";
                regdst = "00";
                memtoreg = "01";
                break;

            case 36: //lbu

                controlArray = new int[]{0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1};
                aluop = "00";
                regdst = "00";
                memtoreg = "01";
                break;

            case 40: //sb

                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0};
                aluop = "00";
                regdst = "00";
                memtoreg = "00";
                break;

            case 4: //beq

                controlArray = new int[]{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0};
                aluop = "01";
                regdst = "00";
                memtoreg = "00";
                break;

            case 2: //j

                controlArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                aluop = "00";
                regdst = "00";
                memtoreg = "00";
                jump = 1;

                break;

            case 3: //jal

                controlArray = new int[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1};
                aluop = "00";
                regdst = "10";
                memtoreg = "10";
                jump = 1;

                break;

            case 10: //slti

                controlArray = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1};
                aluop = "00";
                regdst = "00";
                memtoreg = "00";
                break;

        }

    }

    public String getRegDst() {

        return this.regdst;

    }

    public int getBranch() {
        return controlArray[2];
    }

    public int getmemRead() {
        return controlArray[3];
    }

    public String getmemroReg() {
        return this.memtoreg;

    }

    public String getALUop() {

        return this.aluop;

    }

    public int getmemWrite() {
        return controlArray[8];
    }

    public int getALUsrc() {
        return controlArray[9];
    }

    public int getRegWrite() {
        return controlArray[10];
    }

    public int getJump() {
        return jump;
    }

}

class ALU {

    private int r1;

    private int r2;

    private int ALUSrc;

    private int offset;

    private String AluControlSignal;
    private int result = 0;
    private int shamt;

    public ALU(int r1, int r2, int ALUSrc, int address, String aluc, int shamt) {

        this.r1 = r1;

        this.r2 = r2;

        this.ALUSrc = ALUSrc;

        this.offset = address;
        this.AluControlSignal = aluc;
        this.shamt = shamt;

        if (ALUSrc == 1) 
        {
            if (AluControlSignal.equals("0010")) //lw , sw , addi
            {

                result = r1 + this.offset;

            } 
            else if (AluControlSignal.equals("0111")) //slti
            {

                if (r1 < this.offset) {
                    result = 1;
                }

            }
        } 
        else if (ALUSrc == 0) {

            switch (AluControlSignal.trim()) {

                case "0000": //and

                    result = r1 & r2;

                    break;

                case "0001": //or

                    result = r1 | r2;

                    break;

                case "0010": //add

                    result = this.r1 + this.r2;

                    break;

                case "0110": //sub

                    result = r1 - r2;

                    break;

                case "0111": //slt

                    if (r1 < r2) {
                        result = 1;
                    }

                    break;

                case "1100"://nor

                    result = ~(r1 | r2);

                    break;

                case "1111"://suppose "1111" is sll

                    result = r1 * shamt * 2;

                    break;
            }
        }

    }

    public int getR1() {

        return r1;

    }

    public void setR1(int r1) {

        this.r1 = r1;

    }

    public int getR2() {

        return r2;

    }

    public void setR2(int r2) {

        this.r2 = r2;

    }

    public int getALUSrc() {

        return ALUSrc;

    }

    public void setALUSrc(int ALUSrc) {

        this.ALUSrc = ALUSrc;

    }

    public String getAluControlSignal() {

        return AluControlSignal;

    }

    public void setAluControlSignal(String AluControlSignal) {

        this.AluControlSignal = AluControlSignal;

    }

    public int getResult() {
        return this.result;
    }

}

class AluControl {

    private String AluOP;

    private String FunctCode = "";

    private String AluControlSignal = "";
    private String JRsignal = "";
    private String OpCode;

    public AluControl(String AluOP, String FunctCode, String opcode) {

        this.AluOP = AluOP;

        this.FunctCode = FunctCode;

        this.OpCode = opcode;

        switch (this.AluOP) {

            case "00":

                if (this.OpCode.equals("001010")) //slti
                {

                    AluControlSignal = "0111"; //slt

                } 
                else {
                    AluControlSignal = "0010"; //add
                }
                break;

            case "01":

                AluControlSignal = "0110";

                break;

            case "10":

                switch (this.FunctCode) {

                    case "100000":

                        AluControlSignal = "0010"; // add

                        JRsignal = "0";

                        break;

                    case "100010": //sub

                        AluControlSignal = "0110"; //sub

                        JRsignal = "0";

                        break;

                    case "100100": //and

                        AluControlSignal = "0000"; //and

                        JRsignal = "0";

                        break;

                    case "100101": //or

                        AluControlSignal = "0001"; //or

                        JRsignal = "0";

                        break;

                    case "101010": //slt

                        AluControlSignal = "0111"; //slt

                        JRsignal = "0";

                        break;

                    case "001000": //jump register instruction

                        JRsignal = "1";

                        break;
                    case "000000": //jump register instruction
                        AluControlSignal = "1111";
                        JRsignal = "0";

                        break;
                }
                break;

        }

    }

    public String getAluOP() {

        return AluOP;

    }
    public String getjrsignal() {

        return JRsignal;

    }
    public void setAluOP(String AluOP) {

        this.AluOP = AluOP;

    }

    public String getFunctCode() {

        return FunctCode;

    }

    public String getAluSignal() {

        return AluControlSignal;

    }

    public void setFunctCode(String FunctCode) {

        this.FunctCode = FunctCode;

    }

}

class Memory {
    /**
     * Value stored inside the memory object*
     */
    private int value;
    /**
     * Most significant byte*
     */
    private int b1;
    /**
     * Second byte from the left*
     */
    private int b2;
    /**
     * Second byte from the right*
     */
    private int b3;
    /**
     * Least significant byte*
     */
    private int b4;
    /**
     * Initialize a memory object with a value*
     */
    public Memory(int value) {
        this.value = value;
    }
    /**
     * Returns the value stored inside a memory object*
     */
    public int read() {
        return value;
    }
    /**
     * Overwrites the value of a memory object*
     */
    public void write(int value) {
        this.value = value;
    }
    /**
     * Returns a string representing the value inside the memory word in binary*
     */
    public String getValueasBinaryString() {
        StringBuffer sb;
        if (value >= 0) {
            String s = Integer.toBinaryString(value);
            sb = new StringBuffer(s);
//            for (int i = 0; i < s.length(); i++) {
//
//                sb.setCharAt(i, s.charAt(i));
//            }
            sb = sb.reverse();
            for (int i = s.length(); i < 32; i++) {
                sb.append("0");
            }
            sb = sb.reverse();
            String newValue = sb.toString();
            return newValue;
        } else {
            int temp = -value;
            String s = Integer.toBinaryString(temp);
            sb = new StringBuffer(s);
            sb = sb.reverse();
            int i;
            for (i = 0; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, sb.charAt(i));
                    break;
                }
                sb.setCharAt(i, sb.charAt(i));
            }
            for (i = i + 1; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                } else {
                    sb.setCharAt(i, '1');
                }
            }
            for (i = sb.length(); i < 32; i++) {

                sb.append("1");
            }
            sb = sb.reverse();
            return sb.toString();
        }
    }
    /**
     * Set the most significant byte to a certain value*
     */
    public void setbyte1(int b) {
        b1 = b;
    }
    /**
     * Set the second most significant byte to a certain value*
     */
    public void setbyte2(int b) {
        b2 = b;
    }
    /**
     * Set the second least significant byte to a certain value*
     */
    public void setbyte3(int b) {
        b3 = b;
    }
    /**
     * Set the least significant byte to a certain value*
     */
    public void setbyte4(int b) {
        b4 = b;
    }
    /**
     * Returns the most significant byte*
     */
    public int getB1() {
        return b1;
    }
    /**
     * Returns the second most significant byte*
     */
    public int getB2() {
        return b2;
    }
    /**
     * Returns the second least significant byte*
     */
    public int getB3() {
        return b3;
    }
    /**
     * Returns the least significant byte*
     */
    public int getB4() {
        return b4;
    }
    /**
     * Returns the most significant byte as a string*
     */
    public String getB1String() {
        return Integer.toString(b1);
    }
    /**
     * Returns the second most significant byte as a string*
     */
    public String getB2String() {
        return Integer.toString(b2);
    }
    /**
     * Returns the second least significant byte as a string*
     */
    public String getB3String() {
        return Integer.toString(b3);
    }
    /**
     * Returns the least significant byte as a string*
     */
    public String getB4String() {
        return Integer.toString(b4);
    }
    /**
     * Update the value inside the memory word when a byte is changed using sb instruction*
     */
    public void updateValue() {
        String byte1, byte2, byte3, byte4;
          if (b1 >= 0) {
            String s = Integer.toBinaryString(b1);
            StringBuffer sb = new StringBuffer(s);
//            for (int i = 0; i < s.length(); i++) {
//
//                sb.setCharAt(i, s.charAt(i));
//            }
            for (int i = s.length(); i < 8; i++) {
                sb.append("0");
            }
            sb = sb.reverse();
            byte1 = sb.toString();
        } else {
            int temp = -b1;
            String s = Integer.toBinaryString(temp);
            StringBuffer sb = new StringBuffer(s);
            sb = sb.reverse();
            int i;
            for (i = 0; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, sb.charAt(i));
                    break;
                }
                sb.setCharAt(i, sb.charAt(i));
            }
            for (i = i + 1; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                } else {
                    sb.setCharAt(i, '1');
                }
            }
            for (i = sb.length(); i < 8; i++) {
                sb.append("1");
            }
            sb = sb.reverse();
            byte1 = sb.toString();
        }
        if (b2 >= 0) {
            String s = Integer.toBinaryString(b2);
            StringBuffer sb = new StringBuffer(s);
//            for (int i = 0; i < s.length(); i++) {
//
//                sb.setCharAt(i, s.charAt(i));
//            }
            for (int i = s.length(); i < 8; i++) {
                sb.append("0");
            }
            sb = sb.reverse();
            byte2 = sb.toString();
        } else {
            int temp = -b2;
            String s = Integer.toBinaryString(temp);
            StringBuffer sb = new StringBuffer(s);
            sb = sb.reverse();
            int i;
            for (i = 0; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, sb.charAt(i));
                    break;
                }
                sb.setCharAt(i, sb.charAt(i));
            }
            for (i = i + 1; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                } else {
                    sb.setCharAt(i, '1');
                }
            }
            for (i = sb.length(); i < 8; i++) {
                sb.append("1");
            }
            sb = sb.reverse();
            byte2 = sb.toString();
        }
        if (b3 >= 0) {
            String s = Integer.toBinaryString(b3);
            StringBuffer sb = new StringBuffer(s);
//            for (int i = 0; i < s.length(); i++) {
//
//                sb.setCharAt(i, s.charAt(i));
//            }
            for (int i = s.length(); i < 8; i++) {
                sb.append("0");
            }
            sb = sb.reverse();
            byte3 = sb.toString();
        } else {
            int temp = -b3;
            String s = Integer.toBinaryString(temp);
            StringBuffer sb = new StringBuffer(s);
            sb = sb.reverse();
            int i;
            for (i = 0; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, sb.charAt(i));
                    break;
                }
                sb.setCharAt(i, sb.charAt(i));
            }
            for (i = i + 1; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                } else {
                    sb.setCharAt(i, '1');
                }
            }
            for (i = sb.length(); i < 8; i++) {
                sb.append("1");
            }
            sb = sb.reverse();
            byte3 = sb.toString();
        }
        if (b4 >= 0) {
            String s = Integer.toBinaryString(b4);
            StringBuffer sb = new StringBuffer(s);
//            for (int i = 0; i < s.length(); i++) {
//
//                sb.setCharAt(i, s.charAt(i));
//            }
            for (int i = s.length(); i < 8; i++) {
                sb.append("0");
            }
            sb = sb.reverse();
            byte4 = sb.toString();
        } else {
            int temp = -b4;
            String s = Integer.toBinaryString(temp);
            StringBuffer sb = new StringBuffer(s);
            sb = sb.reverse();
            int i;
            for (i = 0; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, sb.charAt(i));
                    break;
                }
                sb.setCharAt(i, sb.charAt(i));
            }
            for (i = i + 1; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                } else {
                    sb.setCharAt(i, '1');
                }
            }
            for (i = sb.length(); i < 8; i++) {
                sb.append("1");
            }
            sb = sb.reverse();
            byte4 = sb.toString();
        }
        String s = byte1 + byte2 + byte3 + byte4;
        value = Integer.parseInt(s,2);
    }
    /**
     * Update the bytes inside the memory word when the value is overwritten
     * using sw instruction or using write() method*
     */
    public void updateValueBytes() {
        if (value >= 0) {
            String s = Integer.toBinaryString(value);
            StringBuffer sb = new StringBuffer(s);
//            for (int i = 0; i < s.length(); i++) {
//
//                sb.setCharAt(i, s.charAt(i));
//            }
            for (int i = s.length(); i < 32; i++) {
                sb.append("0");
            }
            sb = sb.reverse();
            String newValue = sb.toString();
            String s1 = newValue.substring(0, 7);
            String s2 = newValue.substring(8, 15);
            String s3 = newValue.substring(16, 23);
            String s4 = newValue.substring(24, 31);
            b1 = Integer.parseInt(s1);
            b2 = Integer.parseInt(s2);
            b3 = Integer.parseInt(s3);
            b4 = Integer.parseInt(s4);
        } else {
            int temp = -value;
            String s = Integer.toBinaryString(temp);
            StringBuffer sb = new StringBuffer(s);
            sb = sb.reverse();
            int i;
            for (i = 0; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, sb.charAt(i));
                    break;
                }
                sb.setCharAt(i, sb.charAt(i));
            }
            for (i = i + 1; i < s.length(); i++) {
                if (sb.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                } else {
                    sb.setCharAt(i, '1');
                }
            }
            for (i = sb.length(); i < 32; i++) {
                sb.append("1");
            }
            sb = sb.reverse();
            String newValue = sb.toString();
            String s1 = newValue.substring(0, 8);
            String s2 = newValue.substring(8, 16);
            String s3 = newValue.substring(16, 24);
            String s4 = newValue.substring(24, 32);
            b1 = Integer.parseInt(s1);
            b2 = Integer.parseInt(s2);
            b3 = Integer.parseInt(s3);
            b4 = Integer.parseInt(s4);
        }
    }
}
class DataMem {

    Memory[] dataMemory = new Memory[10000];

    public DataMem() {
        for (int i = 0; i < 10000; i++) {
            dataMemory[i] = new Memory(0);
        }
    }

    public int read(int address) {
        return dataMemory[address].read();
    }

    public void write(int value, int address) {
        dataMemory[address].write(value);
        dataMemory[address].updateValueBytes();
    }
    public void setByte1(int value,int address){
        dataMemory[address].setbyte1(value);
        
    }
     public void setByte2(int value,int address){
        dataMemory[address].setbyte2((byte)value);
        dataMemory[address].updateValue();
    }
      public void setByte3(int value,int address){
        dataMemory[address].setbyte3((byte)value);
        dataMemory[address].updateValue();
    }
    public void setByte4(int value, int address) {
        dataMemory[address].setbyte4((byte) value);
        dataMemory[address].updateValue();
    }

    public int getByte1( int address) {
        return dataMemory[address].getB1();

    }

    public int getByte2( int address) {
        return dataMemory[address].getB2();

    }

    public int getByte3( int address) {
        return dataMemory[address].getB3();

    }

    public int getByte4( int address) {
        return dataMemory[address].getB4();
         
    }
    
}
