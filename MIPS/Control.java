package MIPS;

public class Control {
    PC programcount;
    Assembler assembler;
    this.opCode = opCode;
        switch(this.opCode){
            case 0: // R Format instructions
                controlArray=new int[]{0,1,0,0,0,0,1,0,0,0,1};
                break;
            case 8: //addi
                controlArray=new int[]{0,0,0,0,0,0,0,0,0,1,1};
                break;
            case 35: //lw
                controlArray=new int[]{0,0,0,1,0,1,0,0,0,1,1};
                break;
            case 43: //sw
                controlArray=new int[]{0,0,0,0,0,0,0,0,1,1,0};
                break;
            case 32: // lb
                controlArray=new int[]{0,0,0,1,0,1,0,0,0,1,1};
                break;
            case 36: //lbu
                controlArray=new int[]{0,0,0,1,0,1,0,0,0,1,1};
                break;
            case 40: //sb
                controlArray=new int[]{0,0,0,0,0,0,0,0,1,1,0};
                break;
            case 4: //beq
                controlArray=new int[]{0,0,1,0,0,0,0,1,0,0,0};
                break;
            case 2: //j
                controlArray=new int[]{0,0,0,0,0,0,0,0,0,0,0};
                jump=1;
                break;
            case 3: //jal
                controlArray=new int[]{1,0,0,0,1,0,0,0,0,0,1};
                jump=1;
                break;
            case 10: //slti
                controlArray=new int[]{0,0,0,0,0,0,1,0,0,1,1};
                break; 
        }
    }
    public String getRegDst(){
        String n =""+controlArray[0]+controlArray[1];
        return n; 
    }
    public int getBranch(){return controlArray[2]; }
    public int getmemRead(){return controlArray[3]; }
    public String getmemroReg(){
        String n =""+controlArray[4]+controlArray[5];
        return n; 
    }
    public String getALUop(){
        String n =""+controlArray[6]+controlArray[7]; 
        return n;
    }
    public int getmemWrite(){return controlArray[8]; }
    public int getALUsrc(){return controlArray[9]; }
    public int getRegWrite(){return controlArray[10]; }
    public int getJump(){return jump;}
}
