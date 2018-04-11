package cse116;

public class Main {
    static PC pc;
    Assembler a;
    RegistersFile r;
    InstructionMem im;
    public Main(){
        a = new Assembler("add $s1,$s1,$s2");
        im = new InstructionMem(a.toString());
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.a.toString());
        System.out.println("00000010001100101000100000100000");

    }
}
