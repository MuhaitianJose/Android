package com.muhaitian.record;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.muhaitian.record.entity.TestLocalSocket;
import com.muhaitian.record.localsocket.BindService;
import com.muhaitian.record.localsocket.LocalSocketServer;
import com.muhaitian.record.localsocket.LocalSocketServerCallback;

/**
 * Created by Muhaitian on 09/07/2017.
 */

public class LocalSocketActivity extends AppCompatActivity implements LocalSocketServerCallback {
    private final String TAG = LocalSocketActivity.class.getSimpleName();
    private Button localsocket;
    private TextView showInfo;
    private Handler ShowHandler;
    private BindService mBindService;

    private final String LOCALSOCKET_SERVICE_COMPONENT = "com.muhaitian.record/com.muhaitian.record.service.LocalSocketService";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localsocket_test);

        Intent intent = new Intent();
        ComponentName Name = ComponentName.unflattenFromString(LOCALSOCKET_SERVICE_COMPONENT);
        intent.setComponent(Name);

        init(intent);
    }

    private void init(Intent intent) {
        if (intent != null) {
            mBindService = new BindService(getApplicationContext());
            boolean results = mBindService.startBindLocalSocketService(intent);
            Log.d(TAG, "init: results==" + results);
        }

        localsocket = (Button) findViewById(R.id.localsocketButton);
        ShowHandler = new Handler();
        LocalSocketServer.getInstance().startListenLocalSocket();
        LocalSocketServer.getInstance().setLocalSocketServerCallback(this);
        localsocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mBindService.getILocalSocketAidlInterface().getTestLocalSocket();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        showInfo = (TextView) findViewById(R.id.getInfoByLocalSocket);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void sendLocalSocket(final TestLocalSocket testLocalSocket) {
        if (testLocalSocket != null) {
            ShowHandler.post(new Runnable() {
                @Override
                public void run() {
                    showInfo.setText("Name:" + testLocalSocket.getName() + " | Description==" + testLocalSocket.getDescription());
                }
            });
        } else {
            Log.d(TAG, "sendLocalSocket: testLocalSocket is NULL");
        }
    }
}
