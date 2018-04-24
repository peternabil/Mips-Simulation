package alu.main;

public class ALU {
    private register r1;
    private register r2;
    private int ALUSrc;
    private int address;
    private String AluControlSignal;
    
    public ALU(register r1 , register r2 , int ALUSrc , int address) {
        this.r1 = r1;
        this.r2 = r2;
        this.ALUSrc = ALUSrc;
        this.address = address;
        int result = 0;
        
        if (ALUSrc == 1){
            result = r1.value + this.address;
        }
        
        else if (ALUSrc == 0){
            switch (AluControlSignal){
                case "000":
                    result = r1.value & r2.value;
                    break;
                case "001":
                    result = r1.value | r2.value;
                    break;
                case "010":
                    result = r1.value + r2.value;
                case "110":
                    result = r1.value - r2.value;
                case "111":
                    if (r1.value < r2.value)
                        result = 1;
                    else
                        result = 0;
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
