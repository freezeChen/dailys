package com.frozens.daily.component.netty.observable;

import java.util.Observable;

public class ConnectObservable extends Observable {

    private ConnectObservable() {
    }

    private static ConnectObservable instance;


    public synchronized static ConnectObservable getInstance() {
        if (instance == null) {
            instance = new ConnectObservable();
        }
        return instance;
    }

    public void change(int state) {
        setChanged();
        notifyObservers(state);
    }
}
