package cse116;

public class RegistersFile {
    public register[] registers;

    public RegistersFile() {
        registers = new register[32];
        registers[0] = new register("$zero");
        registers[1] = new register("$at");
        registers[2] = new register("$v0");
        registers[3] = new register("$v1");
        registers[4] = new register("$$a0");
        registers[5] = new register("$a1");
        registers[6] = new register("$a2");
        registers[7] = new register("$a3");
        registers[8] = new register("$t0");
        registers[9] = new register("$t1");
        registers[10] = new register("$t2");
        registers[11] = new register("$t3");
        registers[12] = new register("$t4");
        registers[13] = new register("$t5");
        registers[14] = new register("$t6");
        registers[15] = new register("$t7");
        registers[16] = new register("$s0");
        registers[17] = new register("$s1");
        registers[18] = new register("$s2");
        registers[19] = new register("$s3");
        registers[20] = new register("$s4");
        registers[21] = new register("$s5");
        registers[22] = new register("$s6");
        registers[23] = new register("$s7");
        registers[24] = new register("$t8");
        registers[25] = new register("$t9");
        registers[26] = new register("$k0");
        registers[27] = new register("$k1");
        registers[28] = new register("$gp");
        registers[29] = new register("$sp");
        registers[30] = new register("$fp");
        registers[31] = new register("$ra");
    }
    public int getvalue(int registernumber){
        return registers[registernumber].getValue();
    }
}
