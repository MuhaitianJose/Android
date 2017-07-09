package com.muhaitian.record.localsocket;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

import com.muhaitian.record.ILocalSocketAidlInterface;
import com.muhaitian.record.ITestAidlInterface;

/**
 * Created by Muhaitian on 09/07/2017.
 */

public class BindService {

    private Context mContext;
    private ILocalSocketAidlInterface mILocalSocketAidlInterface;
    private ITestAidlInterface mITestAidlInterface;
    private LocalsocketConnection mLocalsocketConnection;
    private AidlConnection mAidlConnection;

    public BindService(Context context){
        mContext = context;
    }

    public boolean startBindLocalSocketService(Intent intent){
        boolean results = false;
        mLocalsocketConnection = new LocalsocketConnection();
        results = mContext.bindService(intent,mLocalsocketConnection,Context.BIND_AUTO_CREATE);
        return results;
    }

    public boolean startBindAidlService(Intent intent){
        boolean results = false;
        mAidlConnection  =new AidlConnection();
        results = mContext.bindService(intent,mAidlConnection,Context.BIND_AUTO_CREATE);
        return results;
    }

    class LocalsocketConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mILocalSocketAidlInterface = ILocalSocketAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mILocalSocketAidlInterface = null;
        }
    }

    class AidlConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mITestAidlInterface = ITestAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mITestAidlInterface = null;
        }
    }

    public ILocalSocketAidlInterface getILocalSocketAidlInterface() {
        return mILocalSocketAidlInterface;
    }

    public ITestAidlInterface getITestAidlInterface() {
        return mITestAidlInterface;
    }
}
