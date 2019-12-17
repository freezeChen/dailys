package com.frozens.daily.component.netty;

public class ByteUtil {


    public static byte[] IntToByte4Array(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    public static byte[] IntToByte2Array(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);

        byte[] result = new byte[2];
        result[0] = src[2];
        result[1] = src[3];

        return result;
    }

    public static int byteArrayToInt(byte[] b) {
        int value = 0;
        for (byte aB : b) {
            value = (value << 8) | aB;
        }
        return value;

    }

}
