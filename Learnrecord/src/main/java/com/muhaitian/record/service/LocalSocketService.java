package com.muhaitian.record.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.muhaitian.record.ILocalSocketAidlInterface;
import com.muhaitian.record.entity.TestLocalSocket;

/**
 * Created by wangkang001 on 2017/7/8.
 */

public class LocalSocketService extends Service{

    private TestLocalSocket mTestLocalSocket;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return ibind;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTestLocalSocket = new TestLocalSocket();
        mTestLocalSocket.setName("Muhaitian_Jose");
        mTestLocalSocket.setDescription("transmit data to Client by LocalSocket");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private final ILocalSocketAidlInterface.Stub ibind = new ILocalSocketAidlInterface.Stub() {
        @Override
        public void getTestLocalSocket() throws RemoteException {

        }
    };

}
