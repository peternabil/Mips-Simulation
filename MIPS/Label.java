package MIPS;

public class Label {
    Instruction labelname = new Instruction();     // the name given by the user
    PC position;          // the corresponding position to that label
    public Label(Instruction labelname,PC position){
        this.labelname = labelname;
        this.position = position;
    }
    public int findlabel(String s){
        if (this.labelname.equals(s))
            return this.position.getPc();
        return -1000000;
    }
}
