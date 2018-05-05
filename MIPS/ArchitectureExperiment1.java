package architectureexperiment1;

public class ArchitectureExperiment1 {

    public static void main(String[] args) {
        Memory m1 = new Memory(20);
        String s1 = m1.getValueasBinaryString();
        m1.write(-20);
        String s2 = m1.getValueasBinaryString();
        System.out.println(s1);
        System.out.println(s2);
        m1.updateValueBytes();
        String b1 = m1.getB1String();
        String b2 = m1.getB2String();
        String b3 = m1.getB3String();
        String b4 = m1.getB4String();
        System.out.println(b1 + b2 + b3 + b4);
        int i1 = m1.getB1();
        int i2 = m1.getB2();
        int i3 = m1.getB3();
        int i4 = m1.getB4();
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
//        System.out.println((byte)-128);
//        System.out.println((byte)-129);
        m1.setbyte1((byte)0);
        m1.setbyte2((byte)0);
        m1.setbyte3((byte)0);
        m1.setbyte4((byte)0);
        m1.updateValue();
        System.out.println(m1.read());
    }

}

class Memory {

    /** Value stored inside the memory object**/
    private int value;  

    /** Most significant byte**/
    private int b1; 
    /**Second byte from the left**/
    private int b2;
    /**Second byte from the right**/
    private int b3;

    /** Least significant byte**/
    private int b4; 

    /** Initialize a memory object with a value**/
    public Memory(int value) {

        this.value = value;

    }
/** Returns the value stored inside a memory object**/
    public int read() {

        return value;

    }
/** Overwrites the value of a memory object**/
    public void write(int value) {

        this.value = value;  

    }
/** Returns a string representing the value inside the memory word in binary**/
    public String getValueasBinaryString() {

        StringBuffer sb;

        if (value >= 0) {

            String s = Integer.toBinaryString(value);

            sb = new StringBuffer(s);

//            for (int i = 0; i < s.length(); i++) {
//
//                sb.setCharAt(i, s.charAt(i));
//            }
            sb = sb.reverse();

            for (int i = s.length(); i < 32; i++) {

                sb.append("0");

            }
            sb = sb.reverse();
            String newValue = sb.toString();

            return newValue;
        } else {

            int temp = -value;

            String s = Integer.toBinaryString(temp);

            sb = new StringBuffer(s);

            sb = sb.reverse();

            int i;

            for (i = 0; i < s.length(); i++) {

                if (sb.charAt(i) == '1') {

                    sb.setCharAt(i, sb.charAt(i));

                    break;

                }

                sb.setCharAt(i, sb.charAt(i));

            }
            for (i = i + 1; i < s.length(); i++) {

                if (sb.charAt(i) == '1') {

                    sb.setCharAt(i, '0');

                } else {

                    sb.setCharAt(i, '1');

                }

            }

            for (i = sb.length(); i < 32; i++) {

                sb.append("1");
            }
            sb = sb.reverse();
            return sb.toString();
        }
    }
/**Set the most significant byte to a certain value**/
    public void setbyte1(byte b) {

        b1 = b;

    }
/**Set the second most significant byte to a certain value**/
    public void setbyte2(byte b) {

        b2 = b;

    }
/**Set the second least significant byte to a certain value**/
    public void setbyte3(byte b) {

        b3 = b;

    }
/**Set the least significant byte to a certain value**/
    public void setbyte4(byte b) {

        b4 = b;

    }
/**Returns the most significant byte**/
    public int getB1() {
        return b1;
    }
/**Returns the second most significant byte**/
    public int getB2() {
        return b2;
    }
/**Returns the second least significant byte**/
    public int getB3() {
        return b3;
    }
/**Returns the least significant byte**/
    public int getB4() {
        return b4;
    }
/**Returns the most significant byte as a string**/
    public String getB1String() {
        return Integer.toString(b1);
    }
/**Returns the second most significant byte as a string**/
    public String getB2String() {
        return Integer.toString(b2);
    }
/**Returns the second least significant byte as a string**/
    public String getB3String() {
        return Integer.toString(b3);
    }
/**Returns the least significant byte as a string**/
    public String getB4String() {
        return Integer.toString(b4);
    }
/**Update the value inside the memory word when a byte is changed using sb instruction**/
    public void updateValue() {

        String s1 = Integer.toString(b1);

        String s2 = Integer.toString(b2);

        String s3 = Integer.toString(b3);

        String s4 = Integer.toString(b4);

        String s5 = s1 + s2 + s3 + s4;

        value = Integer.parseInt(s5);

    }
/**Update the bytes inside the memory word when the value is overwritten using sw instruction
 or using write() method**/
    public void updateValueBytes() {

        if (value >= 0) {
            String s = Integer.toBinaryString(value);

            StringBuffer sb = new StringBuffer(s);

//            for (int i = 0; i < s.length(); i++) {
//
//                sb.setCharAt(i, s.charAt(i));
//            }
            for (int i = s.length(); i < 32; i++) {

                sb.append("0");

            }

            sb = sb.reverse();

            String newValue = sb.toString();

            String s1 = newValue.substring(0, 7);

            String s2 = newValue.substring(8, 15);

            String s3 = newValue.substring(16, 23);

            String s4 = newValue.substring(24, 31);

            b1 = Integer.parseInt(s1);

            b2 = Integer.parseInt(s2);

            b3 = Integer.parseInt(s3);

            b4 = Integer.parseInt(s4);

        } else {

            int temp = -value;

            String s = Integer.toBinaryString(temp);

            StringBuffer sb = new StringBuffer(s);

            sb = sb.reverse();

            int i;

            for (i = 0; i < s.length(); i++) {

                if (sb.charAt(i) == '1') {

                    sb.setCharAt(i, sb.charAt(i));

                    break;

                }

                sb.setCharAt(i, sb.charAt(i));

            }
            for (i = i + 1; i < s.length(); i++) {

                if (sb.charAt(i) == '1') {

                    sb.setCharAt(i, '0');

                } else {

                    sb.setCharAt(i, '1');

                }

            }

            for (i = sb.length(); i < 32; i++) {

                sb.append("1");
            }

            sb = sb.reverse();

            String newValue = sb.toString();

            String s1 = newValue.substring(0, 8);

            String s2 = newValue.substring(8, 16);

            String s3 = newValue.substring(16, 24);

            String s4 = newValue.substring(24, 32);

            b1 = Integer.parseInt(s1);

            b2 = Integer.parseInt(s2);

            b3 = Integer.parseInt(s3);

            b4 = Integer.parseInt(s4);
        }
    }

}
