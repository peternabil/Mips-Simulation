
package MIPS;

//
//import javax.swing.*;
//import java.awt.event.*;
//import java.awt.*;
//import java.util.*;
//
//abstract class Shape{
//    protected Color c;
//    protected int x;
//    protected int y;
//    public Shape(int x,int y,Color c){
//        this.x=x;
//        this.c=c;
//        this.y=y;
//    }
//    public abstract void paint(Graphics g);
//
//
//    }
//
// class Rec extends Shape{
//
//    private int w;
//    private int h;
//    public Rec(int w,int h,Color c,int x,int y){
//        super(x,y,c);
//        this.w=w;
//        this.h=h;
//    }
//    public  void paint(Graphics g){
//        g.setColor(c);
//        g.drawRect(x, y, w, h);
//
//    }
//}
//class Line extends Shape{
//
//    private int x2;
//    private int y2;
//    public Line(int x2,int y2,Color c,int x,int y){
//        super(x,y,c);
//        this.x2=x2;
//        this.y2=y2;
//    }
//    public  void paint(Graphics g){
//        g.setColor(c);
//        g.drawLine(x, y, x2, y2);
//
//    }
//}
//class ptpanel extends JPanel {
//
//    private ArrayList shapes = new ArrayList();
//
//    public void addShape(Shape s) {
//        if (s != null) {
//            shapes.add(s);
//        }
//    }
//
//    public void removeShape(Shape s) {
//        if (s != null) {
//            shapes.remove(s);
//        }
//    }
//
//    public void clearShape() {
//        shapes.clear();
//    }
//
//    public void paint(Graphics g) {
//        super.paint(g);
//        for (int i = 0; i < shapes.size(); i++) {
//            ((Shape) (shapes.get(i))).paint(g);
//        }
//    }
//}
//
//public class eduGui extends JFrame {
//
//    ptpanel draw = new ptpanel();
//    private JButton Simulate = new JButton("Simulate");
//    private JButton Next = new JButton("Next");
//    private JLabel rt = new JLabel("0");
//    private JLabel rs = new JLabel("0");
//    private JLabel rd = new JLabel("0");
//    private JLabel regwrite = new JLabel("0");
//    private JLabel alusrc = new JLabel("0");
//    private JLabel aluop = new JLabel("0");
//    private JLabel memtoreg = new JLabel("0");
//    private JLabel branch = new JLabel("0");
//    private JLabel jump = new JLabel("0");
//    private JLabel memoryread = new JLabel("0");
//    private JLabel memorywrite = new JLabel("0");
//    private JLabel regdst = new JLabel("0");
//    private JLabel offset = new JLabel("0");
//    private JLabel functioncode = new JLabel("0");
//    private JLabel im = new JLabel("");
//    JLabel label = new JLabel("center");
//    JPanel labels = new JPanel();
//    JPanel over = new JPanel();
//    private ImageIcon m;
//    private static int count;
//    private static int counti;
//    private Shape s;
//    private ArrayList Shapes = new ArrayList();
//    private ArrayList oldShapes = new ArrayList();
//    private Assembler a;
//    private InstructionMem[] instructions;
//    private String st = "";
//    private String[] splited;
//    private Control c;
//    private javax.swing.Timer timer;
//
//    public eduGui(String st) {
//        //add $s0,$s1.$s2
//        this.setBounds(0, 0, 850, 650);
//        this.setResizable(false);
//        this.st=st;
//        m = new ImageIcon(getClass().getResource("Mips.png"));
//        im = new JLabel(m);
//        im.setBounds(0, 0, 800, 600);
//        rt.setForeground(Color.RED);
//        rs.setForeground(Color.RED);
//        rd.setForeground(Color.RED);
//        regdst.setForeground(Color.RED);
//        regdst.setBounds(353, 136, 100, 20);
//        regwrite.setForeground(Color.RED);
//        alusrc.setForeground(Color.RED);
//        memtoreg.setForeground(Color.RED);
//        jump.setForeground(Color.RED);
//        jump.setBounds(427,150,100, 20);
//        branch.setForeground(Color.RED);
//        branch.setBounds(427,165,100, 20);
//        memoryread.setForeground(Color.RED);
//        offset.setForeground(Color.RED);
//        offset.setBounds(382, 510, 100, 20);
//        functioncode.setForeground(Color.RED);
//        functioncode.setBounds(230, 448, 100, 20);
//        memoryread.setBounds(427,179,100, 20);
//        memtoreg.setBounds(427,196,100, 20);
//        aluop.setBounds(427,209,100, 20);
//        memorywrite.setBounds(427,222,100, 20);
//        alusrc.setBounds(427,237,100, 20);
//        regwrite.setBounds(398,257,100, 20);
//        memorywrite.setForeground(Color.RED);
//        aluop.setForeground(Color.RED);
//        LayoutManager overlay = new OverlayLayout(over);
//        over.setLayout(overlay);
//        labels.setLayout(null);
//        rt.setBounds(200, 310, 120, 20);
//        rs.setBounds(200, 280, 120, 20);
//        rd.setBounds(200, 375, 120, 20);
//        Simulate.setBounds(200, 550, 100, 20);
//        labels.add(Simulate);
//        Next.setBounds(500, 550, 100, 20);
//        labels.add(Next);
//        label.setBounds(500, 300, 100, 20);
//        labels.add(regdst);
//        labels.add(regwrite);
//        labels.add(offset);
//        labels.add(functioncode);
//        labels.add(alusrc);
//        labels.add(jump);
//        labels.add(branch);
//        labels.add(memtoreg);
//        labels.add(memoryread);
//        labels.add(aluop);
//        labels.add(memorywrite);
//        labels.add(rt);
//        labels.add(rs);
//        labels.add(rd);
//        labels.add(label);
//        labels.setOpaque(false);
//        draw.add(im);
//        over.add(labels);
//        over.add(draw);
//        this.add(over);
//
//        instructions = new InstructionMem[1000];
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        draw.addMouseListener(new MouseAdapter(){
//            public void mouseClicked(MouseEvent e){
//                System.out.println(e.getX()+"\n"+e.getY());
//            }
//        });
//        Simulate.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                splited = st.split("\n");
//
//                Next.addActionListener(new ActionListener() {
//
//                    public void actionPerformed(ActionEvent e) {
//                        if(count< splited.length){
//                        a = new Assembler(splited[count]);
//                        String[] splitd = splited[count].split("\\s+");
//                        draw.setOpaque(false);
//                        for (int i = 0; i < Shapes.size(); i++) {
//                            draw.removeShape((Shape) Shapes.get(i));
//                        }
//                        Shapes.clear();
//                        draw.repaint();
//                        counti=0;
//                        switch (splitd[0]) {
//                            case "j":
//                                s = new Line(797, 15, Color.RED, 15, 15);
//                                Shapes.add(s);
//                                s = new Line(14, 295, Color.RED, 15, 15);
//                                Shapes.add(s);
//                                for (int i = 0; i < Shapes.size(); i++) {
//                                    draw.addShape((Shape) Shapes.get(i));
//                                }
//
//                                break;
//                            case "add":
//                                 c = new Control(a.opcode);
//                                regdst.setText("" + c.getRegDst());
//                                branch.setText("" + c.getBranch());
//                                memoryread.setText(c.getmemRead() + "");
//                                regwrite.setText(c.getRegWrite() + "");
//                                memtoreg.setText("" + c.getmemroReg());
//                                memorywrite.setText(c.getmemWrite() + "");
//                                jump.setText(c.getJump() + "");
//                                int[] n = c.getALUop();
//                                aluop.setText(n[0] + "" + n[1]);
//                                alusrc.setText(c.getALUsrc() + "");
//                                timer = new javax.swing.Timer(1500, new ActionListener() {
//                                    public void actionPerformed(ActionEvent e) {
//                                        if (counti < 2) {
//                                            if (counti == 0) {
//                                                s = new Line(326, 287, Color.RED, 184, 287);
//                                                Shapes.add(s);
//                                                s = new Line(289, 375, Color.RED, 185, 375);
//                                                Shapes.add(s);
//                                                s = new Line(326, 321, Color.RED, 183, 321);
//                                                Shapes.add(s);
//                                                s = new Line(367, 450, Color.RED, 184, 450);
//                                                Shapes.add(s);
//                                                rt.setText(a.getRt() + "");
//                                                rd.setText(a.getRd() + "");
//                                                rs.setText(a.getRs() + "");
//                                                functioncode.setText(a.functioncode + "");
//                                            }
//                                            if (counti == 1) {
//
//                                                for (int i = 0; i < Shapes.size(); i++) {
//                                                    draw.removeShape((Shape) Shapes.get(i));
//                                                }Shapes.clear();
//                                                s = new Line(524, 305, Color.RED, 448, 305);
//                                                Shapes.add(s);
//                                            }
//
//
//                                            for (int i = 0; i < Shapes.size(); i++) {
//                                                draw.addShape((Shape) Shapes.get(i));
//                                            }
//
//                                            draw.repaint();
//                                        }
//                                        counti++;
//
//                                    }
//
//                                });
//                                // s = new Line(326, 321, Color.RED, 183, 321);
//                                //Shapes.add(s);
//                                timer.start();
//
//                                break;
//                        }
//
//                        }
//                        count++;
//                    }
//
//                });
//            }
//        });
//
//    }
//}
