package com.muhaitian.record.localsocket;

import android.net.LocalServerSocket;
import android.net.LocalSocket;

import com.muhaitian.record.entity.TestLocalSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangkang001 on 2017/7/8.
 */

public class LocalSocketServer {

    public static final String LOCALSOCKET_ADDRESS = "muhaitian_localsocket";
    private LocalServerSocket mLocalServerSocket;
    private ExecutorService ListenLocalSocketClient;
    private LocalSocketServerCallback mLocalSocketServerCallback;

    public LocalSocketServer() {
        try {
            mLocalServerSocket = new LocalServerSocket(LOCALSOCKET_ADDRESS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListenLocalSocketClient = Executors.newSingleThreadExecutor();

    }

    private static LocalSocketServer mLocalSocketServer;

    public static LocalSocketServer getInstance() {
        if (mLocalSocketServer == null) {
            mLocalSocketServer = new LocalSocketServer();
        }
        return mLocalSocketServer;
    }

    public void setLocalSocketServerCallback(LocalSocketServerCallback mLocalSocketServerCallback) {
        this.mLocalSocketServerCallback = mLocalSocketServerCallback;
    }

    class ListeningRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                LocalSocket localSocket = null;
                try {
                    localSocket = mLocalServerSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (localSocket != null) {
                    ObjectInputStream objectInputStream = null;
                    try {
                        objectInputStream = new ObjectInputStream(localSocket.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (objectInputStream != null) {
                        try {
                            TestLocalSocket testLocalSocket = (TestLocalSocket) objectInputStream.readObject();
                            if (mLocalSocketServerCallback != null) {
                                mLocalSocketServerCallback.sendLocalSocket(testLocalSocket);
                            }
                            objectInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}
