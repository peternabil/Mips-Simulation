package cse116;

public class register{
    public String name;
    public int value;
    public register(String name){
        this.name = name;
        this.value = 0;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
