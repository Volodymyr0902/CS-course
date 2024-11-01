package com.shpp.p2p.cs.vtaboranskyi.assignment11;

public class Calc2Tests {
    public static void main(String[] args) throws ValidationException {
        String expression = "3*(5+(64/2))";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = "7 + (100 + 4) / (4*8)";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = "(7^a / 12 * (9/b) + (c/3)) a=2 b=65 c=326";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = "sqrt(10)^atan(-4)/2*18";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = "2^8+tan(45-23.5*(cos14/1.37))/a a=22";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = "log1015";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = "1+(2+3*(4+5-sin(45*cos(a))))/7a=-7";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = "4^log218.7 - log10(a) a=98";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = "(2^-a / atan(-b))^c*sqrt150 a=7.12 -b=9 c=0.64";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = "";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        expression = null;
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        Assignment11Part1.main(null);
        System.out.println();

        expression = "(5+16(4/1.5)";
        Assignment11Part1.main(new String[]{expression});
        System.out.println();

        //-----------------------------------------------//

        Assignment11Part1.main(new String[]{"14^2+18-a*ca = 4c=8"});
        System.out.println();
        Assignment11Part1.main(new String[]{"-11^-v/-a + 9/-3 a=-2 -v=-3"});
        System.out.println();
        Assignment11Part1.main(new String[]{"-a^b*3 -a=-7 b=2"});
        System.out.println();
        Assignment11Part1.main(new String[]{"-5^-2 *16/9"});
        System.out.println();
        Assignment11Part1.main(new String[]{"-13+a - 18^-b * c/5 a = 7 b = 4 c = 22"});
        System.out.println();
        Assignment11Part1.main(new String[]{"-5--a a = 3"});
        System.out.println();
        Assignment11Part1.main(new String[]{"-256.2/0+12/26 - 10000"});
        System.out.println();
        Assignment11Part1.main(new String[]{"836^-15 * 72 - 154/a + c a = - 4 c = 184"});
        System.out.println();
        Assignment11Part1.main(new String[]{"a/b^-c + d- c/e a = 13 b = 42 c = 128 -d = 7 e = 12.4"});
        System.out.println();
        Assignment11Part1.main(new String[]{"12--a*3^b-50+c/4+d a=7 b = 5 -c = -3.72"});
        System.out.println();
        Assignment11Part1.main(new String[]{"-3.45*-a+b/12 a = 4 b = 146"});
    }
}
