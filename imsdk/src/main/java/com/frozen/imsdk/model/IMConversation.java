package com.frozen.imsdk.model;

import java.util.ArrayList;
import java.util.List;

public class IMConversation {
    private List<IMMessage> messageList = new ArrayList<>();


    public List<IMMessage> getMessage() {
        return messageList;
    }

    public void setMessage(IMMessage message) {
        messageList.add(message);
    }
}
