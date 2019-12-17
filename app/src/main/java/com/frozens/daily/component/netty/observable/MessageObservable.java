package com.frozens.daily.component.netty.observable;

import com.frozens.daily.component.netty.Protocol;

import java.util.Observable;

public class MessageObservable extends Observable {

    public MessageObservable() {
    }

    private static MessageObservable instance;


    public synchronized static MessageObservable getInstance() {
        if (instance == null) {
            instance = new MessageObservable();
        }
        return instance;
    }

    public void change(Protocol state) {
        setChanged();
        notifyObservers(state);
    }
}
