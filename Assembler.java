package cse116;

import jdk.nashorn.internal.ir.Labels;

public class Assembler {

    InstructionMem instructionMem = new InstructionMem();
    String program;

    private void setlabel(String s, int i) {
        int x = 0;
        for (int j = 0; j < 1000; j++)
            x = this.instructionMem.labels[j].findlabel(s);
        if (x != -1000000)
            this.instructionMem.instructions[i].offset = x;
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

    public Assembler() {
        String[] splited = this.program.split("\n");
        for (int i = 0; i < splited.length; i++) {
            instructionMem.instructions[i] = new Instruction();
            instructionMem.instructions[i].pc = new PC(i);
            this.setopcodeandfunctioncodeandinstructiontypeandregister(instructionMem.instructions[i], i);
            if (splited[0].contains(":"))
                i--;
        }
    }

    private void assembleadd(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j <= 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j <= 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();
    }

    private void assembleaddi(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].immediatestr = Integer.toBinaryString(instructionMem.instructions[i].immediate);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].immediatestr.length(); j < 16; j++) s4.append("0");
        s3.append(instructionMem.instructions[i].immediatestr);
        instructionMem.instructions[i].immediatestr = s4.toString();
    }

    private void assemblelw(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        String[] splited3 = splited2[1].split("");
        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
        RegistersFile f = new RegistersFile();
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s1.append("0");
        s1.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s2.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s3.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j <= 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
    }

    private void assemblesw(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        String[] splited3 = splited2[1].split("");
        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
        RegistersFile f = new RegistersFile();
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s1.append("0");
        s1.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s2.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s3.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j <= 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
    }

    private void assemblelb(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        String[] splited3 = splited2[1].split("");
        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
        RegistersFile f = new RegistersFile();
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s1.append("0");
        s1.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s2.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s3.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j <= 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
    }

    private void assemblelbu(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        String[] splited3 = splited2[1].split("");
        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
        RegistersFile f = new RegistersFile();
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s1.append("0");
        s1.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s2.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s3.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j <= 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
    }

    private void assemblesb(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        String[] splited3 = splited2[1].split("");
        instructionMem.instructions[i].offset = Integer.parseInt(splited3[0]);
        RegistersFile f = new RegistersFile();
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s1.append("0");
        s1.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s1.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s2.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s3.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j <= 16; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s4.toString();
    }

    private void assemblesll(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j <= 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].shiftamountstr = Integer.toBinaryString(instructionMem.instructions[i].shiftamount);
        StringBuilder s6 = new StringBuilder();
        for (int j = instructionMem.instructions[i].shiftamountstr.length(); j <= 6; j++) s6.append("0");
        s6.append(instructionMem.instructions[i].shiftamountstr);
        instructionMem.instructions[i].shiftamountstr = s6.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j <= 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();
    }

    private void assemblenor(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j <= 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j <= 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();
    }

    private void assemblebeq(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
        this.instructionMem.instructions[i].opcode = 4;
        this.instructionMem.instructions[i].instructionType = 'i';
        for (int j = 0; j < 32; j++) {
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
            if (splited2[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rt = j;
        }
        Instruction ikk = new Instruction();
        ikk.instruction = splited2[2];
        instructionMem.instructions[i].label = new Label(ikk, new PC(i));
        this.setlabel(instructionMem.instructions[i].label.labelname.instruction, i);
        instructionMem.instructions[i].offset = this.instructionMem.instructions[i].pc.getPc() - this.instructionMem.instructions[i].offset;

        f.registers[31].setValue(this.instructionMem.instructions[i].label.position.getPc());

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j <= 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j <= 16; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s5.toString();
    }

    private void assemblej(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        this.instructionMem.instructions[i].opcode = 2;
        this.instructionMem.instructions[i].instructionType = 'j';
        Instruction ikk = new Instruction();
        ikk.instruction = splited2[2];
        instructionMem.instructions[i].label = new Label(ikk, new PC(i));
        this.setlabel(instructionMem.instructions[i].label.labelname.instruction, i);
        instructionMem.instructions[i].offset = ikk.pc.getPc();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j <= 26; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s5.toString();
    }

    private void assemblejr(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
        this.instructionMem.instructions[i].opcode = 2;
        this.instructionMem.instructions[i].instructionType = 'j';
        Instruction ikk = new Instruction();
        ikk.instruction = splited2[2];
        instructionMem.instructions[i].label = new Label(ikk, new PC(i));
        this.setlabel(instructionMem.instructions[i].label.labelname.instruction, i);
        instructionMem.instructions[i].offset = f.registers[31].value;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].offsetstr = Integer.toBinaryString(instructionMem.instructions[i].offset);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].offsetstr.length(); j <= 26; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].offsetstr);
        instructionMem.instructions[i].offsetstr = s5.toString();
    }

    private void assembleslt(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
        this.instructionMem.instructions[i].opcode = 0;
        this.instructionMem.instructions[i].functioncode = 42;
        this.instructionMem.instructions[i].instructionType = 'r';
        this.instructionMem.instructions[i].rt = 0;
        for (int j = 0; j < 32; j++) {
            if (splited2[0].equals(f.registers[j].name))
                instructionMem.instructions[i].rd = j;
            if (splited2[1].equals(f.registers[j].name))
                instructionMem.instructions[i].rs = j;
            if (splited2[2].equals(f.registers[j].name))
                instructionMem.instructions[i].shiftamount = j;
        }
        instructionMem.instructions[i].opcodestr = Integer.toBinaryString(instructionMem.instructions[i].opcode);
        StringBuilder s = new StringBuilder();
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j <= 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j <= 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();
    }

    private void assembleslti(Instruction in, int i) {
        String[] splited = instructionMem.instructions[i].instruction.split("\\s+");
        String[] splited2 = splited[1].split(",");
        RegistersFile f = new RegistersFile();
        this.instructionMem.instructions[i].opcode = 10;
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
        for (int j = instructionMem.instructions[i].opcodestr.length(); j <= 6; j++) s.append("0");
        s.append(instructionMem.instructions[i].opcodestr);
        instructionMem.instructions[i].opcodestr = s.toString();

        instructionMem.instructions[i].rsstr = Integer.toBinaryString(instructionMem.instructions[i].rs);
        StringBuilder s2 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rsstr.length(); j <= 5; j++) s2.append("0");
        s2.append(instructionMem.instructions[i].rsstr);
        instructionMem.instructions[i].rsstr = s2.toString();

        instructionMem.instructions[i].rdstr = Integer.toBinaryString(instructionMem.instructions[i].rd);
        StringBuilder s3 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rdstr.length(); j <= 5; j++) s3.append("0");
        s3.append(instructionMem.instructions[i].rdstr);
        instructionMem.instructions[i].rdstr = s3.toString();

        instructionMem.instructions[i].rtstr = Integer.toBinaryString(instructionMem.instructions[i].rt);
        StringBuilder s4 = new StringBuilder();
        for (int j = instructionMem.instructions[i].rtstr.length(); j <= 5; j++) s4.append("0");
        s4.append(instructionMem.instructions[i].rtstr);
        instructionMem.instructions[i].rtstr = s4.toString();

        instructionMem.instructions[i].functioncodestr = Integer.toBinaryString(instructionMem.instructions[i].functioncode);
        StringBuilder s5 = new StringBuilder();
        for (int j = instructionMem.instructions[i].functioncodestr.length(); j <= 6; j++) s5.append("0");
        s5.append(instructionMem.instructions[i].functioncodestr);
        instructionMem.instructions[i].functioncodestr = s5.toString();
    }

    public void setopcodeandfunctioncodeandinstructiontypeandregister(Instruction in, int i) {
        while (in != null) {
            if (this.instructionMem.instructions[i].instruction.contains(":"))
                instructionMem.labels[i] = new Label(in, new PC(i));
            else {
                String[] splited = in.instruction.split("\\s+");
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
                    case "j":
                        assemblej(in, i);
                        break;
                    case "jal":
                        //assemblejal(in,i);
                        break;
                    case "jr":
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
    }

}
