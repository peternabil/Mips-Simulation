package cse116;

public class Control {
    PC programcount;
    Assembler assembler;
    private int opCode; //the pased opcode array
    private int[] controlArray;
    private int jump=0;
    public Control(int opCode) {
        this.opCode = opCode;
        switch(this.opCode){
            case 0: // R Format instructions
                controlArray=new int[]{1,0,0,0,1,0,0,0,1};
                break;
            case 8: //addi
                controlArray=new int[]{0,0,0,0,1,0,0,1,1};
                break;
            case 35: //lw
                controlArray=new int[]{0,0,1,1,1,0,0,1,1};
                break;
            case 43: //sw
                controlArray=new int[]{0,0,0,0,1,0,1,1,0};
                break;
            case 32:
                controlArray=new int[]{0,0,0,0,0,0,0,0,0};
                break;
            case 36:
                controlArray=new int[]{0,0,0,0,0,0,0,0,0};
                break;
            case 40:
                controlArray=new int[]{0,0,0,0,0,0,0,0,0};
                break;
            case 39:
                controlArray=new int[]{0,0,0,0,0,0,0,0,0};
                break;
            case 4: //beq
                controlArray=new int[]{0,1,0,0,0,1,0,0,0};
                break;
            case 2: //j
                controlArray=new int[]{0,0,0,0,0,0,0,0,0};
                jump=1;
                break;
            case 3:
                controlArray=new int[]{0,0,0,0,0,0,0,0,0};
                break;
            case 42: //slt
                controlArray=new int[]{1,0,0,0,1,0,0,0,1};
                break;
            case 10:
                controlArray=new int[]{0,0,0,0,0,0,0,0,0};
                break; 
        }
    }
    public int getRegDst(){return controlArray[0]; }
    public int getBranch(){return controlArray[1]; }
    public int getmemRead(){return controlArray[2]; }
    public int getmemroReg(){return controlArray[3]; }
    public int[] getALUop(){
        int[] n =new int []{controlArray[4],controlArray[5]}; 
        return n;
    }
    public int getmemWrite(){return controlArray[6]; }
    public int getALUsrc(){return controlArray[7]; }
    public int getRegWrite(){return controlArray[8]; }
    public int getJump(){return jump;}
}
