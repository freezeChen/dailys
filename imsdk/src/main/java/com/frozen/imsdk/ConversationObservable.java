package com.frozen.imsdk;

import java.util.Observable;

public class ConversationObservable extends Observable {

    private ConversationObservable() {
    }

    private static volatile ConversationObservable instance;

    public synchronized static ConversationObservable getInstance() {
        if (instance == null) {
            instance = new ConversationObservable();
        }
        return instance;
    }
}
