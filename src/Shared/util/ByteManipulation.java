package Shared.util;

public class ByteManipulation {
    public static void main(String[] args) {
        byte x = 0x01;
        System.out.println(x);
        x = (byte)(x << 1);
        System.out.println(x & 0x02);
    }

    public static boolean[] getBits(byte input) {
        boolean[] output = new boolean[8];
        for(int i = 0; i < output.length; i++) {
            output[7 - i] = ((input >> i) & 0x01) == 1;
        }
        return output;
    }

    public static byte getByte(boolean[] input) {
        if (input.length != 8) {
            throw new IllegalArgumentException("Input must be a byte!");
        }

        byte output = -128;

        for(int i = 0; i < 8; i++) {
            output = (byte)((output << 1) & (input[i] ? 0x01 : 0x00));
        }

        return output;
    }
}
