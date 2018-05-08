package MIPS;

import jdk.nashorn.internal.ir.Labels;

public class Assembler {

    public InstructionMem instructionMem;
    public String program;
    public RegistersFile f = new RegistersFile();
    public int ins;

    public void cleaner(){
        String[] cleanprogram = program.split("\\s*(,|\\s)\\s*");
        program = "";
        int count = 0;
        for (int i = 0 ; i < cleanprogram.length && count < cleanprogram.length; i++){
                if (cleanprogram[count].contains(":")&& cleanprogram[count].length()>1) {
                    program += cleanprogram[count]+"\n";
                    count++;
                    continue;
                }
                else if (cleanprogram[count].equals(":")&&count>0){
                    program+= cleanprogram[count-1]+cleanprogram[count]+"\n";
                    count++;
                    continue;
                }
                else {


                    switch (cleanprogram[count]) {
                        case "add":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";
                            count+=3;
                            break;
                        case "addi":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";
                            count+=3;
                            break;
                        case "sll":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";
                            count+=3;
                            break;
                        case "nor":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";
                            count+=3;
                            break;
                        case "beq":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";
                            count+=3;
                            break;
                        case "bne":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";
                            count+=3;
                            break;
                        case "slt":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";
                            count+=3;
                            break;
                        case "slti":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "," + cleanprogram[count + 3] + "\n";
                            count+=3;
                            break;
                        case "lw":
                            switch (cleanprogram[count + 2].length()) {
                                case 1: // lw $rq 0 ($ra)
                                    switch (cleanprogram[count + 3].length()) {
                                        case 1:
                                            switch (cleanprogram[count + 4].length()) {
                                                case 3:
                                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + cleanprogram[count + 5] + "\n";
                                                    count+=5;
                                                    break;
                                                case 4:
                                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                                    count+=4;
                                                    break;
                                                default:
                                                    //error
                                            }
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=5;
                                            break;
                                        case 5:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 2: // lw $ra 0( $ra)
                                    switch (cleanprogram[i + 3].length()) {
                                        case 3:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=4;
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 5: // lw $ra 0($ra )
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                    count+=3;
                                    break;
                                case 6:
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";
                                    count+=2;
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
                                                    count+=5;
                                                    break;
                                                case 4:
                                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                                    count+=4;
                                                    break;
                                                default:
                                                    //error
                                            }
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=5;
                                            break;
                                        case 5:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 2: // lw $ra 0( $ra)
                                    switch (cleanprogram[i + 3].length()) {
                                        case 3:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=4;
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 5: // lw $ra 0($ra )
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                    count+=3;
                                    break;
                                case 6:
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";
                                    count+=2;
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
                                                    count+=5;
                                                    break;
                                                case 4:
                                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                                    count+=4;
                                                    break;
                                                default:
                                                    //error
                                            }
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=5;
                                            break;
                                        case 5:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 2: // lw $ra 0( $ra)
                                    switch (cleanprogram[i + 3].length()) {
                                        case 3:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=4;
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 5: // lw $ra 0($ra )
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                    count+=3;
                                    break;
                                case 6:
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";
                                    count+=2;
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
                                                    count+=5;
                                                    break;
                                                case 4:
                                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                                    count+=4;
                                                    break;
                                                default:
                                                    //error
                                            }
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=5;
                                            break;
                                        case 5:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 2: // lw $ra 0( $ra)
                                    switch (cleanprogram[i + 3].length()) {
                                        case 3:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=4;
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 5: // lw $ra 0($ra )
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                    count+=3;
                                    break;
                                case 6:
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";
                                    count+=2;
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
                                                    count+=5;
                                                    break;
                                                case 4:
                                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                                    count+=4;
                                                    break;
                                                default:
                                                    //error
                                            }
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=5;
                                            break;
                                        case 5:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 2: // lw $ra 0( $ra)
                                    switch (cleanprogram[i + 3].length()) {
                                        case 3:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + cleanprogram[count + 4] + "\n";
                                            count+=4;
                                            break;
                                        case 4:
                                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                            count+=3;
                                            break;
                                        default:
                                            //error
                                    }
                                    break;
                                case 5: // lw $ra 0($ra )
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + cleanprogram[count + 3] + "\n";
                                    count+=3;
                                    break;
                                case 6:
                                    program += cleanprogram[count] + " " + cleanprogram[count + 1] + "," + cleanprogram[count + 2] + "\n";
                                    count+=2;
                                    break;
                                default:
                                    //error
                            }

                            break;
                        case "j":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "\n";
                            count+=2;
                            break;
                        case "jal":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "\n";
                            count+=2;
                            break;
                        case "jr":
                            program += cleanprogram[count] + " " + cleanprogram[count + 1] + "\n";
                            count+=2;
                            break;
                        default:
                            count++;
                            //error
                    }
                }
        }
    }

    public void assemble() {
        cleaner();
        String[] splited = this.program.split("\n");
        int iii = splited.length;
        instructionMem = new InstructionMem(iii);
        String[] instructs = new String[iii];
        ins = 1;
        for (int loo = 0 ; loo<iii ; loo++){
            if (splited[loo].endsWith(":")){
                String[] n = splited[loo].split(":");
                Label x = new Label(n[0].trim(), new PC(ins));
                this.instructionMem.instructions[ins].label = x;
                instructionMem.labels[ins] = x;
            }
            else if ((splited[loo].trim().length())==0){
                continue;
            }
            else {
                instructs[ins] = splited[loo];
                ins++;
            }
        }
        for (int i = 1; i < ins; i++) {
            instructionMem.instructions[i].instruction = instructs[i];
            instructionMem.instructions[i].pc = new PC(i);
            this.setopcodeandfunctioncodeandinstructiontypeandregister(instructionMem.instructions[i], i);
            System.out.println(instructionMem.instructions[i].instruction);
            System.out.println(instructionMem.instructions[i].machinecode);
            System.out.println(instructionMem.instructions[i].machinecode.length());
            System.out.println(instructionMem.instructions[i].pc);
        }
    }

    private void setlabel(String s,int i) {
        int x = -1000000;
        for (int j = 0; j < 1000; j++) {
            if(instructionMem.labels[j]!=null) {
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
        if (number == 0)
            return zeroToString(x);
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
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
            if (splited2[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
            if (splited2[2].equals(f.registers[j].name))
                instructionMem.instructions[i].rt = j;
        }
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
        StringBuilder s6 = new StringBuilder();
        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s6.append("0");
        s6.append(instructionMem.instructions[i].shiftamountstr);
        instructionMem.instructions[i].shiftamountstr = s6.toString();
        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;

    }

    private void assembleaddi(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");
        this.instructionMem.instructions[i].opcode = 8;
        this.instructionMem.instructions[i].rt = 0;
        this.instructionMem.instructions[i].instructionType = 'i';

        for (int j = 0; j < 32; j++) {
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
            if (splited2[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
        }
        instructionMem.instructions[i].offset = Integer.parseInt(splited2[2]);
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();
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
            instructionMem.instructions[i].offset*=-1;
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
            if (s.equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
        }
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s1 = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");
        s1.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s2.toString();

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
        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;
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
            if (s.equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rt = j;
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
                instructionMem.instructions[i].rd = j;
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
        }
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s1 = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");
        s1.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s2.toString();

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
        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

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
                instructionMem.instructions[i].rd = j;
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
        }
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s1 = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s1.append("0");
        s1.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s2.toString();

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

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

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
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
            if (splited2[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
        }


        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
        StringBuilder s6 = new StringBuilder();
        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s6.append("0");
        s6.append(instructionMem.instructions[i].shiftamountstr);
        instructionMem.instructions[i].shiftamountstr = s6.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
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
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
            if (splited2[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
            if (splited2[2].equals(f.registers[j].name))
                instructionMem.instructions[i].rt = j;
        }
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
        StringBuilder s6 = new StringBuilder();
        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s6.append("0");
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
        instructionMem.instructions[i].offset =  this.instructionMem.instructions[i].offset - this.instructionMem.instructions[i].pc.getPc();
        this.instructionMem.instructions[i].pc.getPc();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s5.toString();

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
        instructionMem.instructions[i].offset =  this.instructionMem.instructions[i].offset - this.instructionMem.instructions[i].pc.getPc();
        this.instructionMem.instructions[i].pc.getPc();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s5.toString();

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

    private void assemblej(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        this.instructionMem.instructions[i].opcode = 2;
        this.instructionMem.instructions[i].instructionType = 'j';
        this.setlabel(splited[1], i);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 26; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s5.toString();

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].offsetstr;
    }

    private void assemblejr(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        this.instructionMem.instructions[i].opcode = 0;
        this.instructionMem.instructions[i].functioncode = 8;
        this.instructionMem.instructions[i].instructionType = 'r';
        this.instructionMem.instructions[i].rd=this.instructionMem.instructions[i].rt=this.instructionMem.instructions[i].shiftamount=0;
        this.instructionMem.instructions[i].rdstr=this.instructionMem.instructions[i].rtstr=this.instructionMem.instructions[i].shiftamountstr="00000";
        for (int j = 0; j < 32; j++) {
            if (splited[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s5.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s6 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s6.append("0");
        s6.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s6.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s7 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s7.append("0");
        s7.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s7.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s8 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s8.append("0");
        s8.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s8.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
        StringBuilder s9 = new StringBuilder();
        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s9.append("0");
        s9.append(instructionMem.instructions[i].shiftamountstr);
        instructionMem.instructions[i].shiftamountstr = s9.toString();

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr+this.instructionMem.instructions[i].rtstr+this.instructionMem.instructions[i].rdstr+this.instructionMem.instructions[i].shiftamountstr+this.instructionMem.instructions[i].functioncodestr;
    }

    private void assemblejal(Instruction in, int i){
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");

        this.instructionMem.instructions[i].opcode = 3;
        this.instructionMem.instructions[i].instructionType = 'j';
        this.setlabel(splited[1], i);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 26; j++) s5.append("0");
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
        this.instructionMem.instructions[i].shiftamount = this.instructionMem.instructions[i].rd=this.instructionMem.instructions[i].rt = 0;
        for (int j = 0; j < 32; j++) {
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
            if (splited2[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
            if (splited2[2].equals(f.registers[j].name))
                instructionMem.instructions[i].rt = j;
        }
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
        StringBuilder s6 = new StringBuilder();
        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j < 5; j++) s6.append("0");
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
        this.instructionMem.instructions[i].rt = 0;
        this.instructionMem.instructions[i].offset = Integer.parseInt(splited2[2]);
        for (int j = 0; j < 32; j++) {
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
            if (splited2[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;

        }
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j < 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j < 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j < 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s42 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s42.append("0");
        s42.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s42.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();

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

        this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;

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
        if (!b)
            builder.append("1", 0, 7);

        twos = builder.toString();

        return twos;
    }

    // Returns '0' for '1' and '1' for '0'
    public char flip(char c) {
        return (c == '0') ? '1' : '0';
    }
}
