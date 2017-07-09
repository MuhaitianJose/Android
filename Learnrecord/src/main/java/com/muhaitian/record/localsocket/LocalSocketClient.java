package com.muhaitian.record.localsocket;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;

import com.muhaitian.record.entity.TestLocalSocket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangkang001 on 2017/7/8.
 */

public class LocalSocketClient {

    private ExecutorService sendTestThread;

    public LocalSocketClient() {
        sendTestThread = Executors.newSingleThreadExecutor();
    }

    private static LocalSocketClient mLocalSocketClient;

    public static LocalSocketClient getInstance() {
        if (mLocalSocketClient == null) {
            mLocalSocketClient = new LocalSocketClient();
        }
        return mLocalSocketClient;
    }

    public void startSendInfo(TestLocalSocket testLocalSocket){
        sendTestThread.execute(new sendTestRunnable(testLocalSocket));
    }

    class sendTestRunnable implements Runnable {
        private TestLocalSocket testLocalSocket;

        public sendTestRunnable(TestLocalSocket testLocalSocket) {
            this.testLocalSocket = testLocalSocket;
        }

        @Override
        public void run() {
            LocalSocket localSocket = new LocalSocket();
            ObjectOutputStream objectOutputStream = null;
            if (!localSocket.isConnected()) {
                try {
                    localSocket.connect(new LocalSocketAddress(LocalSocketServer.LOCALSOCKET_ADDRESS, LocalSocketAddress.Namespace.ABSTRACT), 3000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                objectOutputStream = new ObjectOutputStream(localSocket.getOutputStream());
                objectOutputStream.writeObject(testLocalSocket);
                objectOutputStream.flush();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (localSocket != null) {
                    localSocket.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
