package MIPS;

public class Simulation {
    String prog;
    Assembler a = new Assembler();



    public void simulate(){
        a.program = "j main\n" +
                "sum:\n" +
                "slti $t1,$a0,2\n" +
                "beq $t1,$zero,exit\n" +
                "addi $sp,$sp,-4\n" +
                "sw $ra,0($sp)\n" +
                "addi $sp,$sp,-4\n" +
                "sw $a0,0($sp)\n" +
                "addi $a0,$a0,-1\n" +
                "jal sum\n" +
                "lw $a0,0($sp)\n" +
                "addi $sp,$sp,4\n" +
                "add $v0,$v0,$a0\n" +
                "lw $ra,0($sp)\n" +
                "addi $sp,$sp,4\n" +
                "jr $ra\n" +
                "exit:\naddi $v0,$zero,1\n" +
                "jr $ra\n" +
                "\n" +
                "main:\n" +
                "addi $a0,$zero,5\n" +
                "jal sum\n" +
                "addi $a0,$v0,0\n";
        a.assemble();
        for (int i = 1 ; i< a.ins ; i++){
            System.out.println(a.instructionMem.instructions[i].setregisterread1binary());

        }
    }

    public static void main(String[] args) {
        Simulation a = new Simulation();
        a.simulate();
    }
}
