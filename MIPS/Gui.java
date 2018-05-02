package guim;

import java.awt.*;
import javax.swing.AbstractAction.*;
import java.awt.event.*;
import java.util.*;

public class Gui extends javax.swing.JFrame {
    private Assembler a;
    private String s;
    public RegistersFile f=new RegistersFile();
    private String[] splited;
    private Control c;
    private static int counter=0;
    private ALU alu;
    private register r1,r2,r3;
    private AluControl alucontrol; 
    private DataMem Mem=new DataMem();
    private String offset;
    private String offsetex;
    
    public Gui() {
        initComponents();
        registers.addItem("$zer0");
        registers.addItem("$at");
        registers.addItem("$v0");
        registers.addItem("$v1");
        registers.addItem("$a0");
        registers.addItem("$a1");
        registers.addItem("$a2");
        registers.addItem("$a3");
        registers.addItem("$t0");
        registers.addItem("$t1");
        registers.addItem("$t2");
        registers.addItem("$t3");
        registers.addItem("$t4");
        registers.addItem("$t5");
        registers.addItem("$t6");
        registers.addItem("$t7");
        registers.addItem("$s0");
        registers.addItem("$s1");
        registers.addItem("$s2");
        registers.addItem("$s3");
        registers.addItem("$s4");
        registers.addItem("$s5");
        registers.addItem("$s6");
        registers.addItem("$s7");
        registers.addItem("$t8");
        registers.addItem("$t9");
        registers.addItem("$k0");
        registers.addItem("$k1");
        registers.addItem("$gp");
        registers.addItem("$sp");
        registers.addItem("$fp");
        registers.addItem("$ra");
        registers.setSelectedItem(null);
        
    
     
  simulate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                s = program.getText();
                a = new Assembler(); 
                a.assemble(s);
                splited = s.split("\n");
                eduGui n =new eduGui(s);
                n.setVisible(true);
              
                next.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
               
                        if (counter < splited.length) {
                            machineCode.setText(a.instructionMem.instructions[counter].machinecode);
                            offset=a.instructionMem.instructions[counter].machinecode.substring(15,31);
                            functioncode.setText(a.instructionMem.instructions[counter].functioncodestr);
                            opcode.setText(a.instructionMem.instructions[counter].opcodestr);
                            rs.setText(a.instructionMem.instructions[counter].rsstr);
                            rt.setText(a.instructionMem.instructions[counter].rtstr);
                            rd.setText(a.instructionMem.instructions[counter].rdstr);
                            c = new Control(a.instructionMem.instructions[counter].opcode);
                            regdst.setText("" + c.getRegDst());
                            branch.setText("" + c.getBranch());
                            memread.setText(c.getmemRead() + "");
                            regwrite.setText(c.getRegWrite() + "");
                            memtoreg.setText("" + c.getmemroReg());
                            memwrite.setText(c.getmemWrite() + ""); 
                            aluop.setText(c.getALUop());
                            alusrc.setText(c.getALUsrc() + "");
                            
                            offsettxt.setText(offset);
                            offsettxt.setText(a.instructionMem.instructions[counter].immediatestr);
                            if(a.instructionMem.instructions[counter].rtstr!=null)
                                regdsttxt.setText(a.instructionMem.instructions[counter].rtstr);
                            else
                                regdsttxt.setText(a.instructionMem.instructions[counter].rdstr);
                            alucontrol =new AluControl(c.getALUop(),a.instructionMem.instructions[counter].functioncodestr);
                            r1 =f.getRegister(Integer.parseInt(a.instructionMem.instructions[counter].rsstr,2)); 
                            r2 =f.getRegister(Integer.parseInt(a.instructionMem.instructions[counter].rdstr,2));
                            read1.setText(r1.getValue()+"");
                            if(a.instructionMem.instructions[counter].instructionType=='r')
                                read2.setText(r2.getValue()+"");
                           
                            if(a.instructionMem.instructions[counter].instructionType=='i')
                                alu =new ALU(r1,r2 ,c.getALUsrc(),a.instructionMem.instructions[counter].immediate,alucontrol.getAluSignal());
                            else
                                alu =new ALU(r1,r2 ,c.getALUsrc(),a.instructionMem.instructions[counter].offset,alucontrol.getAluSignal());
                            aluout.setText(alu.getResult()+"");
                            if(c.getRegWrite()==1){
                            if(c.getRegDst()==1)
                                f.setvalue(Integer.parseInt(a.instructionMem.instructions[counter].rtstr,2), alu.getResult());
                            else if(c.getRegDst()==0)
                                f.setvalue(Integer.parseInt(a.instructionMem.instructions[counter].rdstr,2), alu.getResult());
                            }
                            if(alu.getResult()==0)
                                flag.setText("1");
                            else
                                flag.setText("0");
                            if(c.getBranch()==1&&alu.getResult()==0)
                                and.setText("1");
                               else
                                and.setText("0");      
                            alucontoloutput.setText(alucontrol.getAluSignal());
                            
                            if(offset.charAt(0)=='0'){
                                offsetex="0000000000000000"+offset;
                                signextend.setText(offsetex);
                                
                            }
                            else{
                                offsetex="1111111111111111"+offset;
                                signextend.setText(offsetex);
                            }
                            if((offsetex).charAt(0)=='0')
                                shift2.setText("00000000000000"+offset+"00");
                            else
                                shift2.setText("11111111111111"+offset+"00");
                            
                             if(c.getmemRead()==1){
                                int address=alu.getResult();
                                r2.setValue(Mem.read(address));
                                datamemout.setText(Mem.read(address)+"");
                            }
                            if(c.getmemWrite()==1){
                                int address=alu.getResult();
                                Mem.write(r2.getValue(),address);
                            }
                            
                            registers.addItemListener(new ItemListener() {
                                @Override
                                public void itemStateChanged(ItemEvent ie) {
                                    for (int i = 0; i < 32; i++) {
                                        if (registers.getSelectedIndex() == i) {
                                            regValue.setText("" + f.getvalue(i));
                                        }
                                    }
                                }
                            });

                        }
                        counter++;
                    }
                });

            }
        });
  
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                counter=0;
                
            }
        });

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        program = new javax.swing.JTextArea();
        machineCode = new javax.swing.JTextField();
        read2 = new javax.swing.JTextField();
        simulate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rs = new javax.swing.JTextField();
        offsettxt = new javax.swing.JTextField();
        regdsttxt = new javax.swing.JTextField();
        rt = new javax.swing.JTextField();
        rd = new javax.swing.JTextField();
        signextend = new javax.swing.JTextField();
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
        datamemout = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        and = new javax.swing.JTextField();
        aluout = new javax.swing.JTextField();
        read1 = new javax.swing.JTextField();
        shift2 = new javax.swing.JTextField();
        memread = new javax.swing.JTextField();
        flag = new javax.swing.JTextField();
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
        registers = new javax.swing.JComboBox<>();
        regValue = new javax.swing.JTextField();
        next = new javax.swing.JButton();
        reset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        program.setColumns(20);
        program.setRows(5);
        jScrollPane1.setViewportView(program);

        machineCode.setText("0");

        read2.setText("0");
        read2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                read2ActionPerformed(evt);
            }
        });

        simulate.setText("Simulate");
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

        offsettxt.setText("0");
        offsettxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offsettxtActionPerformed(evt);
            }
        });

        regdsttxt.setText("0");
        regdsttxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regdsttxtActionPerformed(evt);
            }
        });

        rt.setText("0");

        rd.setText("0");
        rd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdActionPerformed(evt);
            }
        });

        signextend.setText("0");
        signextend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signextendActionPerformed(evt);
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

        datamemout.setText("0");
        datamemout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datamemoutActionPerformed(evt);
            }
        });

        jTextField12.setText("0");
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        and.setText("0");
        and.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                andActionPerformed(evt);
            }
        });

        aluout.setText("0");
        aluout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aluoutActionPerformed(evt);
            }
        });

        read1.setText("0");
        read1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                read1ActionPerformed(evt);
            }
        });

        shift2.setText("0");
        shift2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shift2ActionPerformed(evt);
            }
        });

        memread.setText("0");
        memread.setBorder(null);
        memread.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memreadActionPerformed(evt);
            }
        });

        flag.setText("0");
        flag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flagActionPerformed(evt);
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

        regValue.setText("0");

        next.setText("Next Instruction");

        reset.setText("Reset");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel2)
                                        .addGap(101, 101, 101))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(registers, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(pc))
                                        .addGap(52, 52, 52)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(opcode, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(machineCode, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rs, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(regValue, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(OffsetField)
                                            .addComponent(Shift2))
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(signextend, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(offsettxt, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                            .addComponent(shift2)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(RegDestinationMuxoutput)
                                        .addGap(27, 27, 27)
                                        .addComponent(regdsttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(simulate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(next))
                            .addComponent(Read1)
                            .addComponent(FunctionCode)
                            .addComponent(Read2)
                            .addComponent(ALUSecondInput)
                            .addComponent(AluOutput)
                            .addComponent(ZeroFlag)
                            .addComponent(DataMemory)
                            .addComponent(MemtoRegMuxOutput)
                            .addComponent(ANDGate)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(functioncode, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(read1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(read2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aluout, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(flag, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datamemout, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(and, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(270, 270, 270)
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
                                .addComponent(RegDest1)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(MemtoReg)
                                        .addComponent(RegWrite)
                                        .addComponent(Branch)
                                        .addComponent(RegDest)
                                        .addComponent(ALUOp)
                                        .addComponent(MemRead))
                                    .addGap(92, 92, 92)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(memread, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(regdst, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                        .addComponent(branch, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(regwrite, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(memtoreg, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(aluop, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ALUControlOutput)
                                .addGap(55, 55, 55)
                                .addComponent(alucontoloutput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(919, Short.MAX_VALUE)
                    .addComponent(jLabel25)
                    .addGap(420, 420, 420)))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {alucontoloutput, aluop, alusrc, branch, memread, memtoreg, memwrite, regdst, regwrite});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simulate)
                    .addComponent(registers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(next)
                    .addComponent(reset))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pc)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(read1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(opcode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Read2)
                                    .addComponent(read2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(Branch))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(regdst, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RegDest))
                        .addGap(18, 33, Short.MAX_VALUE)
                        .addComponent(branch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MemRead)
                            .addComponent(memread, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(regwrite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RegWrite))
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(DataMemory)
                                    .addComponent(datamemout, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ALUOp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(memwrite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MemWrite))))
                        .addGap(37, 37, 37))
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
                                            .addComponent(aluout, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                    .addComponent(regdsttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ZeroFlag)
                                        .addComponent(flag, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(MemtoReg)
                                            .addComponent(memtoreg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)))
                                .addComponent(aluop, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(OffsetField)
                            .addComponent(offsettxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(alusrc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ALUSrc)
                            .addComponent(ANDGate)
                            .addComponent(and, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SignExtenderOutput)
                            .addComponent(signextend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MemtoRegMuxOutput)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Shift2)
                            .addComponent(shift2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ALUControlOutput)
                            .addComponent(alucontoloutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(638, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(124, 124, 124)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {alucontoloutput, aluop, alusrc, branch, memread, memtoreg, memwrite, regdst, regwrite});

        pack();
    }// </editor-fold>                        

    private void simulateActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void read2ActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void regdsttxtActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void rsActionPerformed(java.awt.event.ActionEvent evt) {                                   
        // TODO add your handling code here:
    }                                  

    private void offsettxtActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void signextendActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void opcodeActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void regdstActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void branchActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void functioncodeActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void datamemoutActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void andActionPerformed(java.awt.event.ActionEvent evt) {                                    
        // TODO add your handling code here:
    }                                   

    private void aluoutActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void read1ActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void shift2ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void memreadActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void flagActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void regwriteActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void aluopActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void memwriteActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void alusrcActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void alucontoloutputActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void memtoregActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void rdActionPerformed(java.awt.event.ActionEvent evt) {                                   
        // TODO add your handling code here:
    }                                  

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
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
    private javax.swing.JTextField aluout;
    private javax.swing.JTextField alusrc;
    private javax.swing.JTextField and;
    private javax.swing.JTextField branch;
    private javax.swing.JTextField datamemout;
    private javax.swing.JTextField flag;
    private javax.swing.JTextField functioncode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField machineCode;
    private javax.swing.JTextField memread;
    private javax.swing.JTextField memtoreg;
    private javax.swing.JTextField memwrite;
    private javax.swing.JButton next;
    private javax.swing.JTextField offsettxt;
    private javax.swing.JTextField opcode;
    private javax.swing.JLabel pc;
    public javax.swing.JTextArea program;
    private javax.swing.JTextField rd;
    private javax.swing.JTextField read1;
    private javax.swing.JTextField read2;
    private javax.swing.JTextField regValue;
    private javax.swing.JTextField regdst;
    private javax.swing.JTextField regdsttxt;
    private javax.swing.JComboBox<String> registers;
    private javax.swing.JTextField regwrite;
    private javax.swing.JButton reset;
    private javax.swing.JTextField rs;
    private javax.swing.JTextField rt;
    private javax.swing.JTextField shift2;
    private javax.swing.JTextField signextend;
    public javax.swing.JButton simulate;
    // End of variables declaration                   
}

