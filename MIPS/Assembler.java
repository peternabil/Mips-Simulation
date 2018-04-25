package MIPS;

import jdk.nashorn.internal.ir.Labels;

public class Assembler {

    InstructionMem instructionMem;
    String program;
    public RegistersFile f = new RegistersFile();


    public void assemble() {
        String[] splited = this.program.split("\n");
        int iii = splited.length;
        instructionMem = new InstructionMem(iii);
        String[] instructs = new String[iii];
        int ins = 0;
        for (int loo = 0 ; loo<iii ; loo++){
            if (splited[loo].endsWith(":")){
                String[] n = splited[loo].split(":");
                this.instructionMem.instructions[loo+1].label.labelname = n[0];
                instructionMem.labels[loo] = new Label(n[0], new PC(loo));
            }
            else {
                instructs[ins] = splited[loo];
                ins++;
            }
        }
        for (int i = 0; i < ins; i++) {
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
            x = this.instructionMem.labels[j].findlabel(s);
            if (x != -1000000) {
                this.instructionMem.instructions[i].offset = x;
                break;
            } else {
                this.instructionMem.instructions[i].offset = 0;
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
        instructionMem.instructions[i].immediate = Integer.parseInt(splited2[2]);
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

        instructionMem.instructions[i].immediatestr = Integer.toBinaryString(instructionMem.instructions[i].immediate);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].immediatestr.length(); j < 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].immediatestr);
        instructionMem.instructions[i].immediatestr = s4.toString();
    }

    private void assemblelw(Instruction in, int i) {
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
            if (splited2[1].equals(f.registers[j].name))
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

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
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
            if (splited2[1].equals(f.registers[j].name))
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

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
    }

    private void assemblelb(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        String[] splited3 = splited2[1].split("");
        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
        String s = splited3[2] + splited3[3] + splited3[4];
        this.instructionMem.instructions[i].opcode = 43;
        this.instructionMem.instructions[i].instructionType = 'i';
        for (int j = 0; j < 32; j++) {
            if (s.equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
            if (splited2[1].equals(f.registers[j].name))
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

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
    }

    private void assemblelbu(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        String[] splited3 = splited2[1].split("");
        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
        String s = splited3[2] + splited3[3] + splited3[4];
        this.instructionMem.instructions[i].opcode = 43;
        this.instructionMem.instructions[i].instructionType = 'i';
        for (int j = 0; j < 32; j++) {
            if (s.equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
            if (splited2[1].equals(f.registers[j].name))
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

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
    }

    private void assemblesb(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        String[] splited3 = splited2[1].split("");
        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
        String s = splited3[2] + splited3[3] + splited3[4];
        this.instructionMem.instructions[i].opcode = 43;
        this.instructionMem.instructions[i].instructionType = 'i';
        for (int j = 0; j < 32; j++) {
            if (s.equals(f.registers[j].name))
                instructionMem.instructions[i].rt = j;
            if (splited2[1].equals(f.registers[j].name))
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

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
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

    }

    private void assemblebeq(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        this.instructionMem.instructions[i].opcode = 4;
        this.instructionMem.instructions[i].instructionType = 'i';
        for (int j = 0; j < 32; j++) {
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
            if (splited2[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rt = j;
        }
        this.setlabel(splited2[2], i);
        instructionMem.instructions[i].offset = this.instructionMem.instructions[i].pc.getPc() - this.instructionMem.instructions[i].offset;
        f.registers[31].setValue(this.instructionMem.instructions[i].pc.getPc());

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
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j < 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j < 16; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s5.toString();
    }

    private void assemblej(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        this.instructionMem.instructions[i].opcode = 2;
        this.instructionMem.instructions[i].instructionType = 'j';
        instructionMem.instructions[i].label = new Label(splited[1], new PC(i));
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
    }

    private void assembleslti(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        this.instructionMem.instructions[i].opcode = 10;
        this.instructionMem.instructions[i].functioncode = 0;
        this.instructionMem.instructions[i].instructionType = 'i';
        this.instructionMem.instructions[i].rt = 0;
        this.instructionMem.instructions[i].immediate = Integer.parseInt(splited2[2]);
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

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j < 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();

        instructionMem.instructions[i].immediatestr = Integer.toBinaryString(instructionMem.instructions[i].immediate);
        StringBuilder s6 = new StringBuilder();
        for (int j = instructionMem.instructions[i].immediatestr.length(); j < 16; j++) s6.append("0");
        s6.append(instructionMem.instructions[i].immediatestr);
        instructionMem.instructions[i].immediatestr = s6.toString();
    }

    public void setopcodeandfunctioncodeandinstructiontypeandregister(Instruction in, int i) {
       if (in != null) {
           String[] splited = this.instructionMem.instructions[i].instruction.split("\\s+");
           switch (splited[0]) {
               case "add":
                   assembleadd(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;
                   break;
               case "addi":
                   assembleaddi(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].immediatestr;
                   break;
               case "lw":
                   assemblelw(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].offsetstr;
                   break;
               case "sw":
                   assemblesw(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;
                   break;
               case "lb":
                   assemblelb(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;
                   break;
               case "lbu":
                   assemblelbu(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].offsetstr;
                   break;
               case "sb":
                   assemblesb(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;
                   break;
               case "sll":
                   assemblesll(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;
                   break;
               case "nor":
                   assemblenor(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;
                   break;
               case "beq":
                   assemblebeq(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].offsetstr;
                   break;
               case "j":
                   assemblej(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].offsetstr;
                   break;
               case "jal":
                   assemblejal(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].offsetstr;
                   break;
               case "jr":
                   assemblejr(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr+this.instructionMem.instructions[i].rtstr+this.instructionMem.instructions[i].rdstr+this.instructionMem.instructions[i].shiftamountstr+this.instructionMem.instructions[i].functioncodestr;
                   break;
               case "slt":
                   assembleslt(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rtstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].shiftamountstr + this.instructionMem.instructions[i].functioncodestr;
                   break;
               case "slti":
                   assembleslti(in, i);
                   this.instructionMem.instructions[i].machinecode = this.instructionMem.instructions[i].opcodestr + this.instructionMem.instructions[i].rsstr + this.instructionMem.instructions[i].rdstr + this.instructionMem.instructions[i].immediatestr;
                   break;
            }
        }
    }

    public static void main(String[] args) {
        Assembler a = new Assembler();
        a.program = "label:\naddi $s1,$s1,9\nlabel2:\nadd $s2,$s1,$s2\nlw $s1,2($s2)\nsw $s1,2($s2)\nlb $s1,2($s2)\nsb $s1,2($s2)\nnor $s2,$s1,$s2\njal label\nsll $t1,$t2,2\nslti $v0,$a1,9\nbeq $v0,$t1,label";
        System.out.println(a.program);
        a.assemble();
//        System.out.println(a.instructionMem.instructions[0].instruction);
//        System.out.println(a.instructionMem.instructions[0].machinecode);
//        System.out.println(a.instructionMem.instructions[0].machinecode.length());
//        System.out.println(a.instructionMem.instructions[1].instruction);
//        System.out.println(a.instructionMem.instructions[1].machinecode);
//        System.out.println(a.instructionMem.instructions[1].machinecode.length());
//        System.out.println(a.instructionMem.instructions[2].instruction);
//        System.out.println(a.instructionMem.instructions[2].machinecode);
//        System.out.println(a.instructionMem.instructions[2].machinecode.length());
//        System.out.println(a.instructionMem.instructions[3].instruction);
//        System.out.println(a.instructionMem.instructions[3].machinecode);
//        System.out.println(a.instructionMem.instructions[3].machinecode.length());

        // only tested add,addi,sw,lw,sb,lb,lbu,jal
    }
}
