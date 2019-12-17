package com.frozens.daily.component.netty;


import org.jetbrains.annotations.Contract;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MessageDecoder extends ChannelInboundHandlerAdapter {
    private static int PackSize = 4;       //总长度
    private static int HeaderSize = 2;      //头部协议长度
    private static int VerSize = 2;         //版本信息长度
    private static int OperationSize = 4;   //操作类型长度
    private static int SeqIdSize = 4;       //
    private static int RawHeaderSize = PackSize + HeaderSize + VerSize + OperationSize + SeqIdSize;



    public static Protocol decode(ByteBuf buf) {
        ByteBuf rawHead = buf.readBytes(RawHeaderSize);

        byte[] packBuf = new byte[PackSize];
        byte[] headerBuf = new byte[HeaderSize];
        byte[] versionBuf = new byte[VerSize];
        byte[] operationBuf = new byte[OperationSize];
        byte[] seqBuf = new byte[SeqIdSize];

        rawHead.readBytes(packBuf);
        rawHead.readBytes(headerBuf);
        rawHead.readBytes(versionBuf);
        rawHead.readBytes(operationBuf);
        rawHead.readBytes(seqBuf);


        int packLen = byteArrayToInt(packBuf);
        int headerLen = byteArrayToInt(headerBuf);
        int version = byteArrayToInt(versionBuf);
        int operation = byteArrayToInt(operationBuf);
        int seq = byteArrayToInt(seqBuf);

        Protocol p = new Protocol(operation);
        if (packLen > headerLen) {
            byte[] bodyBytes = new byte[packLen - headerLen];
            buf.readBytes(bodyBytes);
            p.setMsg(bodyBytes);
        }


        System.out.println(new String(packBuf) + "\n"
                + new String(headerBuf) + "\n"
                + new String(versionBuf) + "\n"
                + new String(operationBuf) + "\n"
                + new String(seqBuf));

        return p;
    }

    /**
     * @param Oper 操作类型
     * @param msg  信息
     * @return
     */
    public static ByteBuf encode(int Oper, String msg) {

        byte[] msgBytes = msg.getBytes();

        ByteBuf buffer = Unpooled.buffer();

        //总长度
        buffer.writeBytes(IntToByte4Array((msgBytes.length + 16)));
        //头部协议长度
        buffer.writeBytes(IntToByte2Array(RawHeaderSize));
        //版本信息
        buffer.writeBytes(IntToByte2Array(2));
        //操作类型
        buffer.writeBytes(IntToByte4Array(Oper));
        //id
        buffer.writeBytes(IntToByte4Array(12));
        //内容
        buffer.writeBytes(msgBytes);


        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    private static byte[] IntToByte4Array(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    private static byte[] IntToByte2Array(int value) {
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

    private static int byteArrayToInt(byte[] b) {
        int value = 0;
        for (byte aB : b) {
            value = (value << 8) | aB;
        }
        return value;

    }

}
