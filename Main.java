
package guim;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
 
/*public class GuiM extends JFrame{
    private javax.swing.JTextField offset;
    private javax.swing.JComboBox<String> op1;
    private javax.swing.JComboBox<String> reg[]= new JComboBox[3];
    private String operation;
    private String regsrc;
    private String regtarget;
    private String regdst;
    private int number;
    private JButton ok;
    private JTextArea program;
    public GuiM(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,800);
        Container c= this.getContentPane();
        c.setLayout(null);
        program =new JTextArea();
        program.setBounds(150,50,500,100);
        c.add(program);
         offset =new JTextField();
        offset.setBounds(500,200,100,22);
        c.add(offset);
        ok = new JButton("OK");
        ok.setBounds(600, 200,100, 20);
        c.add(ok);
        
        
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                operation = (String)op1.getSelectedItem();
                regsrc=(String)reg[0].getSelectedItem();
                regtarget=(String)reg[1].getSelectedItem();
                regdst=(String)reg[2].getSelectedItem();
                if(offset.isEnabled())
                number = Integer.parseInt(offset.getText());
                if(((String)op1.getSelectedItem()).equals("lw")||((String)op1.getSelectedItem()).equals("sw")||((String)op1.getSelectedItem()).equals("sb")||((String)op1.getSelectedItem()).equals("lb"))
                program.setText(program.getText()+(String)op1.getSelectedItem()+" "+(String)reg[0].getSelectedItem()+","+offset.getText()+"("+(String)reg[1].getSelectedItem()+")\n");
                else if (((String)op1.getSelectedItem()).equals("addi")||((String)op1.getSelectedItem()).equals("sll"))
                 program.setText(program.getText()+(String)op1.getSelectedItem()+" "+(String)reg[0].getSelectedItem()+","+(String)reg[1].getSelectedItem()+","+offset.getText()+"\n");
                else
                program.setText(program.getText()+(String)op1.getSelectedItem()+" "+(String)reg[0].getSelectedItem()+","+(String)reg[1].getSelectedItem()+","+(String)reg[2].getSelectedItem()+"\n");
            }
        });
        
        
        op1 = new JComboBox();
        op1.addItem("add");
        op1.addItem("addi");
        op1.addItem("slti");
        op1.addItem("lw");
        op1.addItem("sw");
        op1.addItem("sb");
        op1.addItem("slt");
        op1.addItem("lb");
        op1.addItem("lbu");
        op1.addItem("beq");
        op1.addItem("j");
        op1.addItem("sll");
        op1.addItem("nor");
        op1.addItem("jal");
        op1.addItem("jr");
        op1.setSelectedItem(null);
        op1.setBounds(100, 200, 100, 20);
         this.add(op1);
        op1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
          if(((String)op1.getSelectedItem()).equals("lw")||((String)op1.getSelectedItem()).equals("sw")||((String)op1.getSelectedItem()).equals("sb")||
                  ((String)op1.getSelectedItem()).equals("lb")||((String)op1.getSelectedItem()).equals("addi")||((String)op1.getSelectedItem()).equals("sll")){
              reg[2].setEnabled(false);
              reg[0].setEnabled(true);
              reg[1].setEnabled(true);
              offset.setEnabled(true);
            }
            else if (((String)op1.getSelectedItem()).equals("j")){
               for(int i=0;i<3;i++){
                   reg[i].setEnabled(false);
               }
            }
            else{
                for(int i=0;i<3;i++)
                   reg[i].setEnabled(true);
               offset.setEnabled(false);
            }
        }
        });
        
        for(int i=0;i<3;i++){
        reg[i] = new JComboBox();
        reg[i].addItem("$zer0");
        reg[i].addItem("$at");
        reg[i].addItem("$v0");
        reg[i].addItem("$v1");
        reg[i].addItem("$a0");
        reg[i].addItem("$a1");
        reg[i].addItem("$a2");
        reg[i].addItem("$a3");
        reg[i].addItem("$t0");
        reg[i].addItem("$t1");
        reg[i].addItem("$t2");
        reg[i].addItem("$t3");
        reg[i].addItem("$t4");
        reg[i].addItem("$t5");
        reg[i].addItem("$t6");
        reg[i].addItem("$t7");
        reg[i].addItem("$s0");
        reg[i].addItem("$s1");
        reg[i].addItem("$s2");
        reg[i].addItem("$s3");
        reg[i].addItem("$s4");
        reg[i].addItem("$s5");
        reg[i].addItem("$s6");
        reg[i].addItem("$s7");
        reg[i].addItem("$t8");
        reg[i].addItem("$t9");
        reg[i].addItem("$k0");
        reg[i].addItem("$k1");
        reg[i].addItem("$gp");
        reg[i].addItem("$sp");
        reg[i].addItem("$fp");
        reg[i].addItem("$ra");
        reg[i].setBounds(200+i*100, 200, 100, 20);
        reg[i].setSelectedItem(null);
        this.add(reg[i]);
        
        }
          
    }
    public String getOperation(){
            return this.operation;
        }
    public String getregsrc(){
            return this.regsrc;
        }
    public String getregtarget(){
            return this.regtarget;
        }
    public String getregdst(){
            return this.regdst;
        }
    public int getOffset(){
            return this.number;
        }
    public static void main(String[] args) {
       GuiM m =new GuiM();
       m.setVisible(true);
    }
    
}*/
public  class Main extends JFrame{
    

    public static void main(String[] args) {
        Gui m =new Gui();
        m.setVisible(true);
        
   

    }

    // this function splits the program string in an array of instructions

    /*public void savingInstructions(String s) {

        String[] splited = s.split("\n");

        for (int i = 0; i < splited.length; i++) {

            a = new Assembler(splited[i]);

            
            instructions[i]=new InstructionMem(s);
            instructions[i].machinecode = a.toString();
            binarycode.setText(a.functioncode+"");
            
        }

    }*/

}
 class InstructionMem {

      public String machinecode="";

    public InstructionMem(String s){

       this.machinecode = s;

    }

    public String getMachinecode() {

        return machinecode;

    }

}
 class PC {

    private int pc ; //this variable represents the program counter

    public PC(int pc){ this.pc = pc; } //the constructor to be used to intialize the pc at the beggining

    @Override

    public String toString() { return "Program Counter = "+pc; } //designs the appropriate string to be used in representation of the results

    public int getPc() { return pc; } //getter for the program counter to be called by the "InstructionMem" class and used to know the next instruction

}
 class Assembler {

    private String instruction;

    private char instructionType;

    class Label {

        String labelname;     // the name given by the user

        PC position;          // the corresponding position to that label

        public Label(String labelname,PC position){

            this.labelname = labelname;

            this.position = position;

        }

    }

    private Label label;       //in conditions when provided

    private int offset;         //in case of lw , sw , j , jal , jr

    private int immediate;      //in case of i-type instructions

    //the machine code of each instruction

    public int opcode;         // the part of the machine code sent to the control unit

    public int functioncode;

    public int shiftamount;

    public int rs;  //the part of the machine code sent to the register

    public int rt;  //the part of the machine code sent to the register

    public int rd;  //the part of the machine code sent to the register





    public static String intToString(int number,int x) {

     if (number == 0) {
            return zeroToString(x);
        }

        String binaryString = Integer.toBinaryString(number);
        if (binaryString.length() < x) {
            binaryString = "00" + binaryString;
        } else {
            binaryString = binaryString.substring(binaryString.length() - x);
        }

        return binaryString;

    }

    public static String zeroToString(int x){

        String s = "";

        for (int i = 0 ; i <x ; i++){

            s+="0";

        }

        return s;

    }

    public Assembler(String instruction) {

        this.instruction = instruction;

        setopcodeandfunctioncodeandinstructiontypeandregister(this.instruction);

    }

    @Override

    // to string returns a string of the machine code of the instruction

    // i only did the r-type , i will finish the rest later

    // i will create another that allows you to access the machinecode as an array of integers

    public String toString() {

        String s = "";

        switch (this.instructionType) {

            case 'r':

                s += intToString(opcode,6)

                        + intToString(rs,5)+intToString(rt,5)+intToString(rd,5)+intToString(shiftamount,5)+intToString(functioncode,6);

                //s +=  String.valueOf(opcode)+ String.valueOf(rs)+ String.valueOf(rt)+ String.valueOf(rd);

                break;

            case 'i':

                break;

            case 'j':

                break;

        }

        return s;

    }

    public void setopcodeandfunctioncodeandinstructiontypeandregister(String instruction){

        String[] splited = instruction.split("\\s+");

        String[] splited2 = splited[1].split(",");

        RegistersFile f = new RegistersFile();

        switch (splited[0]){

            case "add":

                this.opcode = 0;

                this.functioncode = 32;

                this.instructionType = 'r';

                this.shiftamount = 0;

                for (int j = 0 ; j <32 ; j++) {

                    if (splited2[0].equals(f.registers[j].name))

                        rd = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rs = j;

                    if (splited2[2].equals(f.registers[j].name))

                        rt = j;

                }

                break;

            case "addi":

                this.opcode = 8;

                this.instructionType = 'i';

                for (int j = 0 ; j <32 ; j++) {

                    if (splited2[0].equals(f.registers[j].name))

                        rd = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rs = j;

                }

                break;

            case "lw":

                this.opcode = 35;

                this.instructionType = 'i';

                String[] splited3 = splited2[1].split("");

                String s2 =splited3[2]+splited3[3]+splited3[4];

                offset = Integer.parseInt(splited3[0]);

                for (int j = 0 ; j <32 ; j++) {

                    if (s2.equals(f.registers[j].name))

                        rs = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rd = j;

                }

                break;

            case "sw":

                this.opcode = 43;

                this.instructionType = 'i';

                String[] splited4 = splited2[1].split("");

                String s3 =splited4[2]+splited4[3]+splited4[4];

                offset = Integer.parseInt(splited4[0]);

                for (int j = 0 ; j <32 ; j++) {

                    if (s3.equals(f.registers[j].name))

                        rd = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rs = j;

                }

                break;

            case "lb":

                this.opcode = 8;

                this.instructionType = 'i';

                String[] splited5 = splited2[1].split("");

                String s4 =splited5[2]+splited5[3]+splited5[4];

                offset = Integer.parseInt(splited5[0]);

                for (int j = 0 ; j <32 ; j++) {

                    if (s4.equals(f.registers[j].name))

                        rs = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rd = j;

                }

                break;

            case "lbu":

                this.opcode = 8;

                this.instructionType = 'i';

                String[] splited6 = splited2[1].split("");

                String s5 =splited6[2]+splited6[3]+splited6[4];

                offset = Integer.parseInt(splited6[0]);

                for (int j = 0 ; j <32 ; j++) {

                    if (s5.equals(f.registers[j].name))

                        rs = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rd = j;

                }

                break;

            case "sb":

                this.opcode = 8;

                this.instructionType = 'i';

                String[] splited7 = splited2[1].split("");

                String s6 =splited7[2]+splited7[3]+splited7[4];

                offset = Integer.parseInt(splited7[0]);

                for (int j = 0 ; j <32 ; j++) {

                    if (s6.equals(f.registers[j].name))

                        rd = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rs = j;

                }

                break;

            case "sll":

                this.opcode = 0;

                this.functioncode = 0;

                this.instructionType = 'r';

                this.rt = 0;

                this.shiftamount = Integer.parseInt(splited2[2]);

                for (int j = 0 ; j <32 ; j++) {

                    if (splited2[0].equals(f.registers[j].name))

                        rd = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rs = j;

                }

                break;

            case "nor":

                this.opcode = 0;

                this.functioncode = 39;

                this.instructionType = 'r';

                this.shiftamount = 0;

                for (int j = 0 ; j <32 ; j++) {

                    if (splited2[0].equals(f.registers[j].name))

                        rd = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rs = j;

                    if (splited2[2].equals(f.registers[j].name))

                        rt = j;

                }

                break;

            case "beq":

                this.opcode = 4;

                this.instructionType = 'i';

                for (int j = 0 ; j <32 ; j++) {

                    if (splited2[0].equals(f.registers[j].name))

                        rd = j;

                    if (splited2[1].equals(f.registers[j].name))

                        rs = j;

                }

                /*

                i am still working on the jumping amount

                 */

                break;

            case "j":

                this.opcode = 2;

                this.instructionType = 'j';

                break;

            case "jal":

                this.opcode = 3;

                this.instructionType = 'j';

                break;

            case "jr":

                this.opcode = 8;

                this.instructionType = 'j';

                break;

            case "slt":

                this.opcode = 42;

                this.instructionType = 'r';

                break;

            case "slti":

                this.opcode = 10;

                this.instructionType = 'i';

                break;

        }

    }



    public int getImmediate() {

        return immediate;

    }



    public int getOffset() {

        return offset;

    }



    public int getFunctioncode() {

        return functioncode;

    }



    public int getOpcode() {

        return opcode;

    }



    public int getRd() {

        return rd;

    }



    public int getRs() {

        return rs;

    }



    public int getRt() {

        return rt;

    }



    public int getShiftamount() {

        return shiftamount;

    }

}


 class RegistersFile {

    public register[] registers;



    public RegistersFile() {

        registers = new register[32];

        registers[0] = new register("$zero");

        registers[1] = new register("$at");

        registers[2] = new register("$v0");

        registers[3] = new register("$v1");

        registers[4] = new register("$$a0");

        registers[5] = new register("$a1");

        registers[6] = new register("$a2");

        registers[7] = new register("$a3");

        registers[8] = new register("$t0");

        registers[9] = new register("$t1");

        registers[10] = new register("$t2");

        registers[11] = new register("$t3");

        registers[12] = new register("$t4");

        registers[13] = new register("$t5");

        registers[14] = new register("$t6");

        registers[15] = new register("$t7");

        registers[16] = new register("$s0");

        registers[17] = new register("$s1");

        registers[18] = new register("$s2");

        registers[19] = new register("$s3");

        registers[20] = new register("$s4");

        registers[21] = new register("$s5");

        registers[22] = new register("$s6");

        registers[23] = new register("$s7");

        registers[24] = new register("$t8");

        registers[25] = new register("$t9");

        registers[26] = new register("$k0");

        registers[27] = new register("$k1");

        registers[28] = new register("$gp");

        registers[29] = new register("$sp");

        registers[30] = new register("$fp");

        registers[31] = new register("$ra");

    }

    public int getvalue(int registernumber){

        return registers[registernumber].getValue();

    }

}
 class register{

    public String name;

    public int value;

    public register(String name){

        this.name = name;

        this.value = 0;

    }

    public void setValue(int value) {

        if(this.name!="$zero")

            this.value = value;

    }



    public int getValue() {

        return value;

    }

}
 class Control {

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
