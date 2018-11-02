package com.frozen.imsdk;

import com.frozen.daily.imsdk.R;
import com.frozen.imsdk.model.IMMessage;
import com.google.gson.Gson;

import java.net.PasswordAuthentication;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.ByteBufUtil;
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

    /**
     * 登录认证
     */
    public static final int OPER_CHECK = 0000;
    /**
     * 信息发送
     */
    public static final int OPER_MSG = 1;

    public static IMMessage decode(ByteBuf buf) {
        ByteBuf rawHead = buf.readBytes(RawHeaderSize);

        byte[] packLen = new byte[PackSize];
        byte[] rawHeader = new byte[HeaderSize];
        byte[] version = new byte[VerSize];
        byte[] operation = new byte[OperationSize];
        byte[] seqId = new byte[SeqIdSize];

        rawHead.readBytes(packLen);
        rawHead.readBytes(rawHeader);
        rawHead.readBytes(version);
        rawHead.readBytes(operation);
        rawHead.readBytes(seqId);


        System.out.println(byteArrayToInt(packLen));
        System.out.println(byteArrayToInt(rawHeader));
        System.out.println(byteArrayToInt(version));
        System.out.println(byteArrayToInt(operation));
        System.out.println(byteArrayToInt(seqId));


        System.out.println(byteArrayToInt(packLen) - byteArrayToInt(rawHeader));
        byte[] bodyBytes = new byte[byteArrayToInt(packLen) - byteArrayToInt(rawHeader)];
        buf.readBytes(bodyBytes);

        System.out.println(new String(bodyBytes));


        System.out.println(new String(packLen) + "\n"
                + new String(rawHeader) + "\n"
                + new String(version) + "\n"
                + new String(operation) + "\n"
                + new String(seqId));
//        byte[] detail = new byte[new string-rawHeader];
//        System.arraycopy(bytes,0,);

        Gson gson = new Gson();
        IMMessage message = gson.fromJson(new String(bodyBytes), IMMessage.class);


//        System.out.println(new String(bytes));
        return message;
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
