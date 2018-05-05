/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication37;

/**
 *
 * @author LENOVO
 */
public class JavaApplication37 {

    public static void main(String[] args) {
        Memory m1 = new Memory(20);
        String s = m1.getString();
        String s1 = m1.getValueasString();
        m1.write(-12);
        String s2 = m1.getValueasString();
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);

    }

}

class Memory {

    private int value;  // Value stored inside the memory object

    private byte b1; // Most significant byte

    private byte b2;

    private byte b3;

    private byte b4; // Least significant byte

    public Memory(int value) {

        this.value = value;

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

            String s = Integer.toBinaryString(value);

            sb = new StringBuffer(s);

            for (int i = 0; i < s.length(); i++) {

                sb.setCharAt(i, s.charAt(i));
            }

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

                if (s.charAt(i) == '1') {

                    sb.setCharAt(i, s.charAt(i));

                    break;

                }

                sb.setCharAt(i, s.charAt(i));

            }
            for (i = i + 1; i < s.length(); i++) {

                if (s.charAt(i) == '1') {

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

    public void updateValue() {

        String s1 = Byte.toString(b1);

        String s2 = Byte.toString(b2);

        String s3 = Byte.toString(b3);

        String s4 = Byte.toString(b4);

        String s5 = s1 + s2 + s3 + s4;

        value = Integer.parseInt(s5);

    }

    public void updateValueBytes() {

        if (value >= 0) {
            String s = Integer.toBinaryString(value);

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < s.length(); i++) {

                sb.setCharAt(i, s.charAt(i));
            }

            for (int i = s.length(); i < 32; i++) {

                sb.append("0");

            }

            sb = sb.reverse();

            String newValue = sb.toString();

            String s1 = newValue.substring(0, 7);

            String s2 = newValue.substring(8, 15);

            String s3 = newValue.substring(16, 23);

            String s4 = newValue.substring(24, 31);

            b1 = Byte.parseByte(s1);

            b2 = Byte.parseByte(s2);

            b3 = Byte.parseByte(s3);

            b4 = Byte.parseByte(s4);

        } else {

            int temp = -value;

            String s = Integer.toBinaryString(temp);

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < s.length(); i++) {

                sb.setCharAt(i, s.charAt(i));
            }

            for (int i = sb.length(); i < 32; i++) {

                sb.append("1");
            }

            sb = sb.reverse();

            String newValue = sb.toString();

            String s1 = newValue.substring(0, 7);

            String s2 = newValue.substring(8, 15);

            String s3 = newValue.substring(16, 23);

            String s4 = newValue.substring(24, 31);

            b1 = Byte.parseByte(s1);

            b2 = Byte.parseByte(s2);

            b3 = Byte.parseByte(s3);

            b4 = Byte.parseByte(s4);
        }
    }

    public String getString() {
        return Integer.toBinaryString(value);
    }
}
