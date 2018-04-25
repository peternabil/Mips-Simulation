package MIPS;

public class InstructionMem {
    Label[] labels = new Label[1000];
    Instruction[] instructions = new Instruction[1000];
    public InstructionMem(int len){
        for (int i = 0 ; i <len ; i++){
            instructions[i] = new Instruction(i);
        }
    }
}
