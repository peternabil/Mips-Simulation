package cse116;

public class Main {
    Assembler a;
    InstructionMem[] instructions;
    String s;    // the full program coming from the user

    public Main() {
        instructions = new InstructionMem[1000];
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.savingInstructions(m.s);
    }
    // this function splits the program string in an array of instructions
    public void savingInstructions(String s) {
        String[] splited = s.split("\n");
        for (int i = 0; i < splited.length; i++) {
            a = new Assembler(splited[i]);
            a.setopcodeandfunctioncodeandinstructiontypeandregister();
            instructions[i].machinecode = a.toString();
        }
    }
}
