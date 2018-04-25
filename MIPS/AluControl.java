package alu.main;

public class AluControl {
    private String AluOP;
    private String FunctCode;

    public AluControl(String AluOP, String FunctCode) {
        this.AluOP = AluOP;
        this.FunctCode = FunctCode;
        String AluControlSignal;
        String JRsignal;
        
        switch (this.AluOP){
            case "00":
                AluControlSignal = "010";
                break;
            case "01":
                AluControlSignal = "110";
                break;
            case "10":
                switch (this.FunctCode){
                    case "100000":
                        AluControlSignal = "010";
                        JRsignal = "0";
                        break;
                    case "100010":
                        AluControlSignal = "110";
                        JRsignal = "0";
                        break;
                    case "100100":
                        AluControlSignal = "000";
                        JRsignal = "0";
                        break;
                    case "100101":
                        AluControlSignal = "001";
                        JRsignal = "0";
                        break;
                    case "101010":
                        AluControlSignal = "111";
                        JRsignal = "0";
                        break;
                    case "000100": //jump register instruction
                        JRsignal = "1";
                        break;
                }
                break;
        }
    }
    
    public String getAluOP() {
        return AluOP;
    }

    public void setAluOP(String AluOP) {
        this.AluOP = AluOP;
    }

    public String getFunctCode() {
        return FunctCode;
    }

    public void setFunctCode(String FunctCode) {
        this.FunctCode = FunctCode;
    }
}
