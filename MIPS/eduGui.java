
package guim;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

abstract class Shape{
    protected Color c;
    protected int x;
    protected int y;
    public Shape(int x,int y,Color c){
        this.x=x;
        this.c=c;
        this.y=y;
    }
    public abstract void paint(Graphics g);


    }

class Line extends Shape{
    
    private int x2;
    private int y2;
    public Line(int x2,int y2,Color c,int x,int y){
        super(x,y,c);
        this.x2=x2;
        this.y2=y2;
    }
    public  void paint(Graphics g){
        g.setColor(c);
        g.drawLine(x, y, x2, y2);
        
    }
}
class ptpanel extends JPanel {

    private ArrayList shapes = new ArrayList();

    public void addShape(Shape s) {
        if (s != null) {
            shapes.add(s);
        }
    }

    public void removeShape(Shape s) {
        if (s != null) {
            shapes.remove(s);
        }
    }

    public void clearShape() {
        shapes.clear();
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < shapes.size(); i++) {
            ((Shape) (shapes.get(i))).paint(g);
        }
    }
}

public class eduGui extends JFrame {

    ptpanel draw = new ptpanel();
    private int PC;
    private RegistersFile f=new RegistersFile();
    private JButton Simulate = new JButton("Simulate");
    private JButton Next = new JButton("Next");
    private JLabel rt = new JLabel("0");
    private JLabel rs = new JLabel("0");
    private JLabel rd = new JLabel("0");
    private JLabel regwrite = new JLabel("0");
    private JLabel alusrc = new JLabel("0");
    private JLabel aluop = new JLabel("0");
    private JLabel memtoreg = new JLabel("0");
    private JLabel branch = new JLabel("0");
    private JLabel jump = new JLabel("0");
    private JLabel memoryread = new JLabel("0");
    private JLabel memorywrite = new JLabel("0");
    private JLabel regdst = new JLabel("0");
    private JLabel memtoregout = new JLabel("0");
    private JLabel alucontrolout = new JLabel("0");
    private JLabel datamem = new JLabel("0");
    private JLabel aluout = new JLabel("0");
    private JLabel offset = new JLabel("0");
    private JLabel functioncode = new JLabel("0");
    private JLabel read1 = new JLabel("0");
    private JLabel read2 = new JLabel("0");
    private JLabel im = new JLabel("");
    JLabel and = new JLabel("0");
    JLabel zero = new JLabel("0");
     JLabel shift2 = new JLabel("0");
    JLabel signextend = new JLabel("0");
    JLabel machineCode = new JLabel("0");
     JLabel branchaddress = new JLabel("0");
    JPanel labels = new JPanel();
    JPanel over = new JPanel();
    private ImageIcon m;
    private static int counter;
    private Assembler a;
    private InstructionMem[] instructions;
    private String st = "";
    private String[] splited;
    private Control c;
    private ALU alu;
    private register r1,r2,r3;
    private AluControl alucontrol; 
    private DataMem Mem=new DataMem();
    private String offsetex;
    private String shift2txt;
    private int offsetd;
    private int And=0;
    private int datamemoryoutput;
    private String[] splited2;
    private int value;
    private int address;
    private int shamt=0;
    private JLabel alusecond =new JLabel("0");
    private String offset2;
    private JLabel pc =new JLabel("0");
    public eduGui(String st,int Pc) {
        pc.setBounds(51, 439, 100, 20);
        pc.setForeground(Color.red);
        this.PC=Pc;
        pc.setText(PC+"");
        this.setBounds(0, 0, 850, 750);
        this.setResizable(false);
        this.st=st;
        m = new ImageIcon(getClass().getResource("IMG.jpg"));
        im = new JLabel(m);
        im.setBounds(0, 0, 800, 600);
        rt.setForeground(Color.RED);
        rs.setForeground(Color.RED);
        rd.setForeground(Color.RED);
        regdst.setForeground(Color.RED);
        regdst.setBounds(357, 265, 100, 20);
        regwrite.setForeground(Color.RED);
        alusrc.setForeground(Color.RED);
        memtoreg.setForeground(Color.RED);
        jump.setForeground(Color.RED);
        jump.setBounds(404,277,100, 20);
        alusecond.setBounds(541, 482, 100,20);
        alusecond.setForeground(Color.red);
        branch.setForeground(Color.RED);
        branch.setBounds(404,290,100, 20);
        memoryread.setForeground(Color.RED);
        offset.setForeground(Color.RED);
        offset.setBounds(370, 533, 100, 20);
        functioncode.setForeground(Color.RED);
        functioncode.setBounds(404, 597, 100, 20);
        memoryread.setBounds(404,304,100, 20);
        memtoreg.setBounds(404,316,100, 20);
        aluop.setBounds(404,332,100, 20);
        memorywrite.setBounds(404,346,100, 20);
        alusrc.setBounds(404,359,100, 20);
        regwrite.setBounds(404,372,100, 20);
        memorywrite.setForeground(Color.RED);
        aluop.setForeground(Color.RED);
        zero.setBounds(624, 420, 100, 20);
        datamem.setBounds(758, 445, 100, 20);
        aluout.setBounds(665, 542, 100, 20);
        and.setBounds(679, 292, 100, 20);
        machineCode.setBounds(135, 510, 100, 20);
        branchaddress.setBounds(627, 242, 100, 20);
        LayoutManager overlay = new OverlayLayout(over);
        over.setLayout(overlay);
        labels.setLayout(null);
        rt.setBounds(314, 426, 120, 20);
        rs.setBounds(316, 397, 120, 20);
        rd.setBounds(254, 474, 120, 20);
        read2.setBounds(480, 445,100, 20);
        read1.setBounds(484, 414,100, 20);
        alucontrolout.setBounds(568,541,100,20);
        memtoregout.setBounds(797, 578, 100, 20);
        Simulate.setBounds(200, 650, 100, 20);
        labels.add(Simulate);
        Next.setBounds(500, 650, 100, 20);
        labels.add(Next);
        and.setBounds(680, 285, 100, 20);
        signextend.setBounds(487, 541, 100, 20);
        shift2.setBounds(545, 281, 100,20);
        labels.add(zero);
        labels.add(regdst);
        labels.add(regwrite);
        labels.add(offset);
        labels.add(functioncode);
        labels.add(alusrc);
        labels.add(jump);
        labels.add(branch);
        labels.add(read1);
        labels.add(read2);
        labels.add(and);
        labels.add(alusecond);
        labels.add(memtoreg);
        labels.add(memoryread);
        labels.add(aluop);
        labels.add(branchaddress);
        labels.add(shift2);
        labels.add(signextend);
        labels.add(alucontrolout);
        labels.add(memtoregout);
        labels.add(memorywrite);
        labels.add(rt);
        labels.add(rs);
        labels.add(machineCode);
        labels.add(rd);
        labels.add(pc);
        labels.add(aluout);labels.add(rd);
        labels.add(datamem);
        labels.setOpaque(false);
        draw.add(im);
        over.add(labels);
        over.add(draw);
        this.add(over);

        instructions = new InstructionMem[1000];
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        draw.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.out.println(e.getX()+"\n"+e.getY());
            }
        });
        Simulate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
      
                
                a = new Assembler(); 
                a.assemble(st);
                splited = st.split("\n");
                
                Next.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        if (counter < splited.length) {
                            
                            draw.setOpaque(false);
                            machineCode.setText(a.instructionMem.instructions[counter].machinecode);
                            offset2=a.instructionMem.instructions[counter].machinecode.substring(16,32);
                            offset.setText(Integer.parseInt(offset2,2)+"");
                            offsetd=Integer.parseInt(offset2,2);
                            if(a.instructionMem.instructions[counter].instructionType=='r')
                                shamt=a.instructionMem.instructions[counter].shiftamount;
                            functioncode.setText(a.instructionMem.instructions[counter].functioncodestr);
                            rs.setText(a.instructionMem.instructions[counter].rsstr);
                            rt.setText(a.instructionMem.instructions[counter].rtstr);
                            rd.setText(a.instructionMem.instructions[counter].rdstr);
                            c = new Control(a.instructionMem.instructions[counter].opcode);
                            regdst.setText(c.getRegDst());
                            branch.setText("" + c.getBranch());
                            memoryread.setText(c.getmemRead() + "");
                            regwrite.setText(c.getRegWrite() + "");
                            memtoreg.setText(c.getmemroReg());
                            memorywrite.setText(c.getmemWrite() + ""); 
                            aluop.setText(c.getALUop());
                            alusrc.setText(c.getALUsrc() + "");
                            jump.setText(c.getJump()+"");
                            
                           
                            if(a.instructionMem.instructions[counter].rdstr!=null)
                                regdst.setText(a.instructionMem.instructions[counter].rdstr);
                            else
                                regdst.setText(a.instructionMem.instructions[counter].rtstr);
                            alucontrol =new AluControl(c.getALUop(),a.instructionMem.instructions[counter].functioncodestr,a.instructionMem.instructions[counter].opcodestr);
                            if(a.instructionMem.instructions[counter].rsstr!=null)
                                r1 =f.getRegister(Integer.parseInt(a.instructionMem.instructions[counter].rsstr,2)); 
                            if(a.instructionMem.instructions[counter].rtstr!=null)
                                r2 =f.getRegister(Integer.parseInt(a.instructionMem.instructions[counter].rtstr,2));
                            if (c.getJump() == 1) {
                                read1.setText("0");
                                read2.setText("0");
                            } else {
                                read1.setText(r1.getValue() + "");
                                read2.setText(r2.getValue() + "");
                            }
                            if(a.instructionMem.instructions[counter].instructionType=='r'){
                               if(shamt==0)
                                    alusecond.setText(r2.getValue()+"");
                               else
                                   alusecond.setText(shamt+"");
                            }
                            else
                                alusecond.setText(Integer.parseInt(offset2,2)+"");    
                            if(counter==0&&c.getJump()==1){
                                r1=f.getRegister(0); 
                                r2=f.getRegister(0); 
                            }
                            alu =new ALU(r1.getValue(),r2.getValue() ,c.getALUsrc(),a.instructionMem.instructions[counter].offset,alucontrol.getAluSignal(),shamt);
                           
                            if (c.getJump() == 1) {
                                aluout.setText("0");
                            }
                            else
                                aluout.setText(alu.getResult()+"");
                            if(c.getRegWrite()==1){
                            if(c.getRegDst().equals("01"))
                                f.setvalue(Integer.parseInt(a.instructionMem.instructions[counter].rdstr,2), alu.getResult());
                            else if(c.getRegDst().equals("00"))
                                f.setvalue(Integer.parseInt(a.instructionMem.instructions[counter].rtstr,2), alu.getResult());
                            
                            else{
                                f.setvalue(31,counter+1); 
                            }
                            }
                            if(alu.getResult()==0)
                                zero.setText("1");
                            else
                                zero.setText("0");
                            if(c.getBranch()==1&&alu.getResult()==0){
                                and.setText("1");
                                And=1;
                            }
                            else{
                                and.setText("0"); 
                                And=0;
                            }
                            alucontrolout.setText(alucontrol.getAluSignal());
                            
                            if(offset2.charAt(0)=='0'){
                                offsetex="0000000000000000"+offset2;
                                signextend.setText(Integer.parseInt(offsetex,2)+"");
                                
                            }
                            else{
                                offsetex="1111111111111111"+offset2;
                                signextend.setText(Integer.parseInt(offsetex,2)+"");
                            }
                            if((offsetex).charAt(0)=='0'){
                                shift2txt="00000000000000"+offset2+"00";
                                shift2.setText(Integer.parseInt(offsetex,2)+"");
                            }
                            else{
                                shift2txt="11111111111111"+offset2+"00";
                                shift2.setText(Integer.parseInt(offsetex,2)+"");
                            }
                            
                             branchaddress.setText(PC+(counter)*4+"");
                             if(c.getmemRead()==1){
                                int address=alu.getResult();
                                if(a.instructionMem.instructions[counter].opcodestr.equals("100000")){
                                    if(address%4==0){ 
                                     r2.setValue(Mem.getByte1(address));
                                     datamemoryoutput=Mem.getByte1(address);
                                    }
                                    else if((address-1)%4==0){
                                     r2.setValue(Mem.getByte2(address));
                                     datamemoryoutput=Mem.getByte2(address); 
                                    }
                                    else if ((address - 2) % 4 == 0) {
                                        r2.setValue(Mem.getByte3(address));
                                        datamemoryoutput = Mem.getByte3(address);
                                    }
                                    else{
                                        r2.setValue(Mem.getByte4(address));
                                        datamemoryoutput = Mem.getByte4(address);
                                    }
                                }
                                else{
                                   r2.setValue(Mem.read(address));
                                   datamemoryoutput=Mem.read(address); 
                                }
                                    
                                datamem.setText(datamemoryoutput+"");
                            }
                            if(c.getmemWrite()==1){
                                int address=alu.getResult();
                                if(a.instructionMem.instructions[counter].opcodestr.equals("101000")){
                                    if(address%4==0)
                                         Mem.setByte1(r2.getValue(),address);
                                    else if((address-1)%4==0)
                                        Mem.setByte2(r2.getValue(),address);
                                    else if((address-2)%4==0)
                                        Mem.setByte3(r2.getValue(),address);
                                    else
                                        Mem.setByte4(r2.getValue(),address);
                                }
                                else
                                    Mem.write(r2.getValue(),address);
                            }
                            if(c.getmemroReg().equals("01"))
                                memtoregout.setText(datamemoryoutput+"");
                            else if(c.getmemroReg().equals("00"))
                                 memtoregout.setText(alu.getResult()+"");
                            else{
                                memtoregout.setText((PC+(counter+1)*4)+"");
                            }
                                
                            pc.setText((PC+counter*4)+"");
                            if (c.getJump() == 0 &&And==1) {
                                if(offset2.charAt(0)=='1')
                                    offsetd=-(a.instructionMem.instructions[counter].offset);
                               else
                                    offsetd=(a.instructionMem.instructions[counter].offset);
                                
                                counter += offsetd;
                                
                            } 
                            else if(c.getJump() == 1){
                                if(offset2.charAt(0)=='1')
                                    offsetd=-(a.instructionMem.instructions[counter].offset);
                               else
                                    offsetd=(a.instructionMem.instructions[counter].offset);
                                
                                if(a.instructionMem.instructions[counter].opcode==2){
                                    counter = (offsetd);
                                }
                                else if(a.instructionMem.instructions[counter].opcode==3){
                                    counter = (offsetd);
                                    
                                        }
                                else if(alucontrol.getjrsignal().equals("1")){
                                   counter = (r1.getValue());
                                } 
                                
                            }
                            
                            else
                            {
                                counter++;
                            }
                        }
                    }

                });
            }
        });

    }
}
