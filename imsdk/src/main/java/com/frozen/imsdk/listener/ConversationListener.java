package com.frozen.imsdk.listener;

import com.frozen.imsdk.model.IMMessage;

public interface ConversationListener {
    public void newMessage(IMMessage message);
}
