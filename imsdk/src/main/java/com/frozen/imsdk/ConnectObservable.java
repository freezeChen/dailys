package com.frozen.imsdk;

import com.frozen.imsdk.model.IMConnect;

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
        notifyObservers(new IMConnect(state));
    }
}
