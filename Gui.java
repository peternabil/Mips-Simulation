package guim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui extends javax.swing.JFrame {
    private Assembler a;
    private InstructionMem[] instructions;
    private String s;
    private String[] splited;
    private Control c;
    public Gui() {
        initComponents();
        instructions = new InstructionMem[1000];
        simulate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s = program.getText();
                splited = s.split("\n");
                for (int i = 0; i < splited.length; i++) {
                a = new Assembler(splited[i]);
                instructions[i] = new InstructionMem(s);
                instructions[i].machinecode = a.toString();
                machineCode.setText(instructions[i].machinecode + "");
                functioncode.setText(""+a.functioncode);
                opcode.setText(""+a.opcode);
                rs.setText(a.rs+"");
                rt.setText(""+a.rt);
                rd.setText(""+a.rd);
                c=new Control(a.opcode);
                regdst.setText(""+c.getRegDst());
                branch.setText(""+c.getBranch());
                memread.setText(c.getmemRead()+"");
                regwrite.setText(c.getRegWrite()+"");
                memtoreg.setText(""+c.getmemroReg());
                memwrite.setText(c.getmemWrite()+"");
                int []n=c.getALUop();
                aluop.setText(n[0]+""+n[1]);
                alusrc.setText(c.getALUsrc()+"");
               
                }
                
            }
        });

            
        

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        program = new javax.swing.JTextArea();
        machineCode = new javax.swing.JTextField();
        functionCode = new javax.swing.JTextField();
        simulate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rs = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        rt = new javax.swing.JTextField();
        rd = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        RsField = new javax.swing.JLabel();
        RdField = new javax.swing.JLabel();
        RegDestinationMuxoutput = new javax.swing.JLabel();
        RtField = new javax.swing.JLabel();
        SignExtenderOutput = new javax.swing.JLabel();
        OffsetField = new javax.swing.JLabel();
        opcode = new javax.swing.JTextField();
        Read1 = new javax.swing.JLabel();
        DataMemory = new javax.swing.JLabel();
        Read2 = new javax.swing.JLabel();
        pc = new javax.swing.JLabel();
        Shift2 = new javax.swing.JLabel();
        ZeroFlag = new javax.swing.JLabel();
        FunctionCode = new javax.swing.JLabel();
        AluOutput = new javax.swing.JLabel();
        ALUSecondInput = new javax.swing.JLabel();
        MemWrite = new javax.swing.JLabel();
        ALUOp = new javax.swing.JLabel();
        Branch = new javax.swing.JLabel();
        MemtoReg = new javax.swing.JLabel();
        MemRead = new javax.swing.JLabel();
        RegDest = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        ANDGate = new javax.swing.JLabel();
        MemtoRegMuxOutput = new javax.swing.JLabel();
        regdst = new javax.swing.JTextField();
        branch = new javax.swing.JTextField();
        functioncode = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        memread = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        regwrite = new javax.swing.JTextField();
        aluop = new javax.swing.JTextField();
        memwrite = new javax.swing.JTextField();
        alusrc = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        ALUSrc = new javax.swing.JLabel();
        RegWrite = new javax.swing.JLabel();
        ALUControlOutput = new javax.swing.JLabel();
        alucontoloutput = new javax.swing.JTextField();
        memtoreg = new javax.swing.JTextField();
        RegDest1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        program.setColumns(20);
        program.setRows(5);
        jScrollPane1.setViewportView(program);

        machineCode.setText("0");

        functionCode.setText("0");
        functionCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                functionCodeActionPerformed(evt);
            }
        });

        simulate.setText("simulate");
        simulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulateActionPerformed(evt);
            }
        });

        jLabel1.setText("Op code");

        jLabel2.setText("Machine Code");

        rs.setText("0");
        rs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rsActionPerformed(evt);
            }
        });

        jTextField2.setText("0");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setText("0");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        rt.setText("0");

        rd.setText("0");
        rd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdActionPerformed(evt);
            }
        });

        jTextField6.setText("0");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        RsField.setText("Rs field");

        RdField.setText("RdField");

        RegDestinationMuxoutput.setText("RegDestination Mux output");

        RtField.setText("RtField");

        SignExtenderOutput.setText("Sign-extender Output");

        OffsetField.setText("Offset field of instruction");

        opcode.setText("0");
        opcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcodeActionPerformed(evt);
            }
        });

        Read1.setText("Read data 1");

        DataMemory.setText("Data memory output");

        Read2.setText("Read data 2");

        pc.setText("PC");

        Shift2.setText("Shift-2 output");

        ZeroFlag.setText("Zero flag");

        FunctionCode.setText("Function code of instruction");

        AluOutput.setText("ALU Output");

        ALUSecondInput.setText("ALU second input");

        MemWrite.setText("MemWrite");

        ALUOp.setText("ALUOp");

        Branch.setText("Branch");

        MemtoReg.setText("MemtoReg");

        MemRead.setText("MemRead");

        RegDest.setText("RegDest");

        jLabel25.setText("jLabel3");

        ANDGate.setText("AND gate output");

        MemtoRegMuxOutput.setText("MemtoReg Mux output");

        regdst.setText("0");
        regdst.setBorder(null);
        regdst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regdstActionPerformed(evt);
            }
        });

        branch.setText("0");
        branch.setBorder(null);
        branch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchActionPerformed(evt);
            }
        });

        functioncode.setText("0");
        functioncode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                functioncodeActionPerformed(evt);
            }
        });

        jTextField11.setText("0");
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jTextField12.setText("0");
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jTextField13.setText("0");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField14.setText("0");
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jTextField15.setText("0");
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });

        jTextField16.setText("0");
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        memread.setText("0");
        memread.setBorder(null);
        memread.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memreadActionPerformed(evt);
            }
        });

        jTextField18.setText("0");
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        regwrite.setText("0");
        regwrite.setBorder(null);
        regwrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regwriteActionPerformed(evt);
            }
        });

        aluop.setText("0");
        aluop.setBorder(null);
        aluop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluopActionPerformed(evt);
            }
        });

        memwrite.setText("0");
        memwrite.setBorder(null);
        memwrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memwriteActionPerformed(evt);
            }
        });

        alusrc.setText("0");
        alusrc.setBorder(null);
        alusrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alusrcActionPerformed(evt);
            }
        });

        jTextField23.setText("0");
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jTextField24.setText("0");
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        ALUSrc.setText("ALUSrc");

        RegWrite.setText("RegWrite");

        ALUControlOutput.setText("ALU control output");

        alucontoloutput.setText("0");
        alucontoloutput.setBorder(null);
        alucontoloutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alucontoloutputActionPerformed(evt);
            }
        });

        memtoreg.setText("0");
        memtoreg.setBorder(null);
        memtoreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memtoregActionPerformed(evt);
            }
        });

        RegDest1.setText("Control Signals:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(OffsetField)
                                .addGap(845, 845, 845))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(21, 21, 21)
                                                        .addComponent(pc)
                                                        .addGap(157, 157, 157))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jLabel2)
                                                        .addGap(101, 101, 101)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(opcode, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(machineCode, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(rs, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(ANDGate)
                                                        .addGap(86, 86, 86)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(RegDestinationMuxoutput)
                                                        .addGap(27, 27, 27)
                                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(62, 62, 62)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Read1)
                                            .addComponent(FunctionCode)
                                            .addComponent(Read2)
                                            .addComponent(ALUSecondInput)
                                            .addComponent(AluOutput)
                                            .addComponent(ZeroFlag)
                                            .addComponent(DataMemory)
                                            .addComponent(Shift2)
                                            .addComponent(MemtoRegMuxOutput)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(SignExtenderOutput)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(RdField)
                                                    .addComponent(jLabel1)
                                                    .addComponent(RsField)
                                                    .addComponent(RtField))
                                                .addGap(134, 134, 134)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(rd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(functioncode, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(functionCode, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(ALUSrc)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(alusrc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(MemWrite)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(memwrite, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(MemRead)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(MemtoReg)
                                        .addComponent(RegWrite)
                                        .addComponent(Branch)
                                        .addComponent(RegDest)
                                        .addComponent(ALUOp))
                                    .addGap(92, 92, 92)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(regdst, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                        .addComponent(branch, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(memread, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(regwrite, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(memtoreg, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(aluop, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)))
                                .addComponent(RegDest1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ALUControlOutput)
                                .addGap(55, 55, 55)
                                .addComponent(alucontoloutput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(580, 580, 580)
                        .addComponent(simulate)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(830, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(420, 420, 420)))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {alucontoloutput, aluop, alusrc, branch, memread, memtoreg, memwrite, regdst, regwrite});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(simulate)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pc)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FunctionCode)
                    .addComponent(functioncode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RegDest1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(machineCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Read1)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(opcode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Read2)
                                    .addComponent(functionCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(Branch))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(regdst, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RegDest))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(branch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(MemRead))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(memread, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(regwrite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RegWrite))
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(DataMemory)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ALUOp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(memwrite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MemWrite))))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Shift2)
                            .addComponent(alusrc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ALUSrc))
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(ALUSecondInput)
                                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(AluOutput)
                                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rs, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(34, 34, 34)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(RtField)
                                                    .addComponent(rt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(RsField))))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RdField)
                                    .addComponent(rd, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RegDestinationMuxoutput)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ZeroFlag)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(MemtoReg)
                                            .addComponent(memtoreg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)))
                                .addComponent(aluop, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(OffsetField)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SignExtenderOutput)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ANDGate)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MemtoRegMuxOutput)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ALUControlOutput)
                            .addComponent(alucontoloutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(601, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(124, 124, 124)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {alucontoloutput, aluop, alusrc, branch, memread, memtoreg, memwrite, regdst, regwrite});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simulateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_simulateActionPerformed

    private void functionCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_functionCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_functionCodeActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void rsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rsActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void opcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcodeActionPerformed

    private void regdstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regdstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regdstActionPerformed

    private void branchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_branchActionPerformed

    private void functioncodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_functioncodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_functioncodeActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void memreadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memreadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memreadActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void regwriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regwriteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regwriteActionPerformed

    private void aluopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aluopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aluopActionPerformed

    private void memwriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memwriteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memwriteActionPerformed

    private void alusrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alusrcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alusrcActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void alucontoloutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alucontoloutputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alucontoloutputActionPerformed

    private void memtoregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memtoregActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_memtoregActionPerformed

    private void rdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdActionPerformed

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ALUControlOutput;
    private javax.swing.JLabel ALUOp;
    private javax.swing.JLabel ALUSecondInput;
    private javax.swing.JLabel ALUSrc;
    private javax.swing.JLabel ANDGate;
    private javax.swing.JLabel AluOutput;
    private javax.swing.JLabel Branch;
    private javax.swing.JLabel DataMemory;
    private javax.swing.JLabel FunctionCode;
    private javax.swing.JLabel MemRead;
    private javax.swing.JLabel MemWrite;
    private javax.swing.JLabel MemtoReg;
    private javax.swing.JLabel MemtoRegMuxOutput;
    private javax.swing.JLabel OffsetField;
    private javax.swing.JLabel RdField;
    private javax.swing.JLabel Read1;
    private javax.swing.JLabel Read2;
    private javax.swing.JLabel RegDest;
    private javax.swing.JLabel RegDest1;
    private javax.swing.JLabel RegDestinationMuxoutput;
    private javax.swing.JLabel RegWrite;
    private javax.swing.JLabel RsField;
    private javax.swing.JLabel RtField;
    private javax.swing.JLabel Shift2;
    private javax.swing.JLabel SignExtenderOutput;
    private javax.swing.JLabel ZeroFlag;
    private javax.swing.JTextField alucontoloutput;
    private javax.swing.JTextField aluop;
    private javax.swing.JTextField alusrc;
    private javax.swing.JTextField branch;
    private javax.swing.JTextField functionCode;
    private javax.swing.JTextField functioncode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField machineCode;
    private javax.swing.JTextField memread;
    private javax.swing.JTextField memtoreg;
    private javax.swing.JTextField memwrite;
    private javax.swing.JTextField opcode;
    private javax.swing.JLabel pc;
    private javax.swing.JTextArea program;
    private javax.swing.JTextField rd;
    private javax.swing.JTextField regdst;
    private javax.swing.JTextField regwrite;
    private javax.swing.JTextField rs;
    private javax.swing.JTextField rt;
    private javax.swing.JButton simulate;
    // End of variables declaration//GEN-END:variables
}
