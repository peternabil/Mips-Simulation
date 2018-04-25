package MIPS;

public class Label {
    String labelname;     // the name given by the user
    PC position;          // the corresponding position to that label
    public Label(String labelname,PC position){
        this.labelname = labelname;
        this.position = position;
    }
    public int findlabel(String s){
        if (this.labelname.equals(s))
            return this.position.getPc();
        else
            return -1000000;
    }
}
