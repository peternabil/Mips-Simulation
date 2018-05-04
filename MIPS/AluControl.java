package alu.main;

public class AluControl {
    private String AluOP;
    private String FunctCode;
    String OpCode;
    String AluControlSignal;
    String JRsignal;

    public AluControl(){
        
    }

    public AluControl(String AluOP, String FunctCode) {
        this.AluOP = AluOP;
        this.FunctCode = FunctCode;
                
        switch (this.AluOP){
            case "00": //i-type
                if (this.OpCode == "001010") //slti
                {
                    AluControlSignal = "0111"; //slt
                }
                else
                    AluControlSignal = "0010"; //add
                break;
            case "01": //beq
                AluControlSignal = "0110"; //sub
                break;
            case "10": //Arithmetic and Logical operations(add,sub,and,or,slt)
                switch (this.FunctCode){
                    case "100000": //add
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
                }
                break;
        }
    }
    
    public String getAluOP() {
        return this.AluOP;
    }

    public void setAluOP(String AluOP) {
        this.AluOP = AluOP;
    }

    public String getFunctCode() {
        return this.FunctCode;
    }

    public void setFunctCode(String FunctCode) {
        this.FunctCode = FunctCode;
    }
}
