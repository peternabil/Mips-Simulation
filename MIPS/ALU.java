package alu.main;

public class ALU {

    private register r1;
    private register r2;
    private int ALUSrc;
    private int address;
    private int shamt;
    private String AluControlSignal;
    int result;

    public ALU(register r1, register r2, int ALUSrc, int address, int shamt) {
        this.r1 = r1;
        this.r2 = r2;
        this.ALUSrc = ALUSrc;
        this.address = address;
        this.shamt = shamt;
        this.result = 0;

        if (ALUSrc == 1) //i-type instructions
        {
            if (AluControlSignal == "0010") //lw , sw , addi
            {
                result = r1.value + this.address;
            } 
            else if (AluControlSignal == "0111") //slti
            {
                if (r1.value < this.address)
                    result = 1;
            }
        } 
        else if (ALUSrc == 0)//r-type instructions
        {
            switch (AluControlSignal) {
                case "0000": //and
                    result = r1.value & r2.value;
                    break;
                case "0001": //or
                    result = r1.value | r2.value;
                    break;
                case "0010": //add
                    result = r1.value + r2.value;
                    break;
                case "0110": //sub
                    result = r1.value - r2.value;
                    break;
                case "0111": //slt
                    if (r1.value < r2.value)
                        result = 1;
                    break;
                case "1100"://nor
                    result = ~(r1.value | r2.value);
                    break;
                case "1111"://suppose "1111" is sll
                    result = r1.value << shamt;
                    break;
            }
        }
    }

    public register getR1() {
        return r1;
    }

    public void setR1(register r1) {
        this.r1 = r1;
    }

    public register getR2() {
        return r2;
    }

    public void setR2(register r2) {
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
}
