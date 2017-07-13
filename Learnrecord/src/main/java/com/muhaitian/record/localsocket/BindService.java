package com.muhaitian.record.localsocket;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.muhaitian.record.ILocalSocketAidlInterface;
import com.muhaitian.record.ITestAidlInterface;

/**
 * Created by Muhaitian on 09/07/2017.
 */

public class BindService {

    private final String TAG = "BindService";

    private Context mContext;
    private ILocalSocketAidlInterface mILocalSocketAidlInterface;
    private ITestAidlInterface mITestAidlInterface;
    private LocalsocketConnection mLocalsocketConnection;
    private AidlConnection mAidlConnection;
    private MyHander myHander;
    public BindService(Context context){
        HandlerThread mm = new HandlerThread("bind Aidl");
        mm.start();
        myHander = new MyHander(mm.getLooper());
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
        if(getITestAidlInterface()==null){
            myHander.sendEmptyMessageDelayed(99,500);
        }
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
            Log.d(TAG, "onServiceConnected: service=="+service);
            mITestAidlInterface = ITestAidlInterface.Stub.asInterface(service);
            Log.d(TAG, "onServiceConnected: mITestAidlInterface=="+mITestAidlInterface);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
            mITestAidlInterface = null;
        }
    }

    public ILocalSocketAidlInterface getILocalSocketAidlInterface() {
        return mILocalSocketAidlInterface;
    }

    public ITestAidlInterface getITestAidlInterface() {
        Log.d(TAG, "getITestAidlInterface: mITestAidlInterface=="+mITestAidlInterface);
        return mITestAidlInterface;
    }
    
    class MyHander extends Handler{
        public MyHander(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==99){
                if(getITestAidlInterface()==null){
                    Log.d(TAG, "handleMessage: myHander.sendEmptyMessageDelayed(99,300);");
                    myHander.sendEmptyMessageDelayed(99,3000);
                }else {
                    myHander.removeMessages(99);
                }
            }
        }
    }
}
