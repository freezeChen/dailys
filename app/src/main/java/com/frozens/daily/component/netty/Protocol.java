package com.frozens.daily.component.netty;

public class Protocol {

    //heartbeat
    public static final int Op_Heartbeat = 2;
    //heartbeat reply
    public static final int getOp_Heartbeat_reply = 3;
    //auth connect
    public static final int OP_AUTH = 7;
    //auth connect reply
    public static final int Op_AuthReply = 8;


    public static final int PackSize = 4;       //总长度
    public static final int HeaderSize = 2;      //头部协议长度
    public static final int VerSize = 2;         //版本信息长度
    public static final int OperationSize = 4;   //操作类型长度
    public static final int SeqIdSize = 4;       //
    public static final int RawHeaderSize = PackSize + HeaderSize + VerSize + OperationSize + SeqIdSize;


    private int protocol;
    private byte[] msg;

    public Protocol(int protocol) {
        this.protocol = protocol;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public byte[] getMsg() {
        return msg;
    }

    public void setMsg(byte[] msg) {
        this.msg = msg;
    }
}

