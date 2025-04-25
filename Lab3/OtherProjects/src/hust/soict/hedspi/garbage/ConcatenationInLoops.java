package hust.soict.hedspi.garbage;


import java.util.Random;

public class ConcatenationInLoops {

    public static void main(String[] args) {
        Random r = new Random(123);
        long start;
        int loopCount = 65536;
        String s;

        start = System.currentTimeMillis();
        String sPlus = "";
        for (int i = 0; i < loopCount; i++) {
            sPlus += r.nextInt(2);
        }
        s = sPlus;
        System.out.printf("Using + operator:      %d ms%n", System.currentTimeMillis() - start);

        r = new Random(123);
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < loopCount; i++) {
            sb.append(r.nextInt(2));
        }
        s = sb.toString();
        System.out.printf("Using StringBuilder:   %d ms%n", System.currentTimeMillis() - start);

        r = new Random(123);
        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < loopCount; i++) {
            sbf.append(r.nextInt(2));
        }
        s = sbf.toString();
        System.out.printf("Using StringBuffer:    %d ms%n", System.currentTimeMillis() - start);
    }
}