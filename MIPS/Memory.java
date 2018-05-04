package MIPS;

public class Memory {

    private int value;  // Value stored inside the memory object
    private int location; // Location of the memory object 
    private byte b1; // Most significant byte
    private byte b2;
    private byte b3;
    private byte b4; // Least significant byte

    public Memory(int value, int location) {
        this.value = value;
        this.location = location;
    }  // Initialize a memory object with a location and a value

    public int read() {
        return value;  // Returns the value stored inside a memory object with specified location
    }

    public void write(int value) {
        this.value = value;  // Overwrites the value of a memory object
    }

    public String getValueasString() {
        StringBuffer sb;
        if (value >= 0) {
            return Integer.toBinaryString(value);
        } else {
            String s = Integer.toBinaryString(value);
            sb = new StringBuffer();
            int i;
            for (i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    sb.setCharAt(i, s.charAt(i));
                    break;
                }
                sb.setCharAt(i, s.charAt(i));
            }
            for (; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    sb.setCharAt(i, '0');
                } else {
                    sb.setCharAt(i, '1');
                }
            }
        }
        return sb.toString();
        // Make sign extension after calling this method!
    }

    public void setbyte1(byte b) {
        b1 = b;
    }

    public void setbyte2(byte b) {
        b2 = b;
    }

    public void setbyte3(byte b) {
        b3 = b;
    }

    public void setbyte4(byte b) {
        b4 = b;
    }
    public void updateValue(){
        String s1= Byte.toString(b1);
        String s2= Byte.toString(b2);
        String s3= Byte.toString(b3);
        String s4= Byte.toString(b4);
        String s5= s1+s2+s3+s4;
        value = Integer.parseInt(s5);
    }
    public void updateValueBytes(){
        String s = Integer.toBinaryString(value);
        // Call "Sign extend" method, then divide it to the four bytes
    }
}