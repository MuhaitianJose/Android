package com.muhaitian.record;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.muhaitian.record.entity.Student;
import com.muhaitian.record.localsocket.BindService;

/**
 * Created by Muhaitian on 09/07/2017.
 */

public class AidlTestActivity extends AppCompatActivity {
    private final String TAG = AidlTestActivity.class.getSimpleName();
    private Button InTest, OutTest, InOutTest,bindservice;
    private TextView InBeforInfo, InAfterInfo, OutBeforeInfo, OutAfterInfo, InOutBeforeInfo, InOutAfterInfo;
    private Student InBeforeSt, InAfterSt, OutBeforeSt, OutAfterSt, InOutBeforeSt, InOutAfterSt;
    private BindService mBindService;

    private final String AIDL_SERVICE_COMPONENT = "com.muhaitian.record/com.muhaitian.record.service.TestAidlService";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aidl_test);

        initData(null);
        Initwidget();
    }

    private void initData(Intent intent) {

        InBeforeSt = new Student();
        InBeforeSt.setName("wangkang");
        InBeforeSt.setAge(24);
        InBeforeSt.setMale(true);
        InBeforeSt.setStudentID("001");
        InAfterSt = new Student();

        OutBeforeSt = new Student();
        OutBeforeSt.setName("muhaitian");
        OutBeforeSt.setAge(26);
        OutBeforeSt.setMale(true);
        OutBeforeSt.setStudentID("002");
        OutAfterSt = new Student();

        InOutBeforeSt = new Student();
        InOutBeforeSt.setName("lizihai");
        InOutBeforeSt.setAge(27);
        InOutBeforeSt.setMale(true);
        InOutBeforeSt.setStudentID("003");
        InOutAfterSt = new Student();
    }

    private void Initwidget() {
        InBeforInfo = (TextView) findViewById(R.id.InBeforeInfo);
        InAfterInfo = (TextView) findViewById(R.id.InAfterInfo);
        OutBeforeInfo = (TextView) findViewById(R.id.OutBeforeInfo);
        OutAfterInfo = (TextView) findViewById(R.id.OutAfterInfo);
        InOutBeforeInfo = (TextView) findViewById(R.id.InOutBeforeInfo);
        InOutAfterInfo = (TextView) findViewById(R.id.InOutAfterInfo);

        InBeforInfo.setText("Name==" + InBeforeSt.getName() + " | Student==" + InBeforeSt.getStudentID() + " | Age==" + InBeforeSt.getAge());
        OutBeforeInfo.setText("Name==" + OutBeforeSt.getName() + " | Student==" + OutBeforeSt.getStudentID() + " | Age==" + OutBeforeSt.getAge());
        InOutBeforeInfo.setText("Name==" + InOutBeforeSt.getName() + " | Student==" + InOutBeforeSt.getStudentID() + " | Age==" + InOutBeforeSt.getAge());

        InTest = (Button) findViewById(R.id.InTest);
        InTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InAfterSt = mBindService.getITestAidlInterface().sendInStudent(InBeforeSt);
                    InAfterInfo.setText("InBeforeSt: Name=" + InBeforeSt.getName() + " | ID==" + InBeforeSt.getStudentID() + " | Age=" + InBeforeSt.getAge() + "\n" +
                            "InAfterSt: Name=" + InAfterSt.getName() + " | ID==" + InAfterSt.getStudentID() + " | Age=" + InAfterSt.getAge() + "\n"
                    );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        OutTest = (Button) findViewById(R.id.OutTest);
        OutTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutAfterSt = mBindService.getITestAidlInterface().sendOutStudent(OutBeforeSt);
                    OutAfterInfo.setText("OutBeforeSt: Name=" + OutBeforeSt.getName() + " | ID==" + OutBeforeSt.getStudentID() + " | Age=" + OutBeforeSt.getAge() + "\n" +
                            "OutAfterSt: Name=" + OutAfterSt.getName() + " | ID==" + OutAfterSt.getStudentID() + " | Age=" + OutAfterSt.getAge() + "\n"
                    );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        InOutTest = (Button) findViewById(R.id.InOutTest);
        InOutTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InOutAfterSt = mBindService.getITestAidlInterface().sendInOutStudent(InOutBeforeSt);
                    InOutAfterInfo.setText("InOutBeforeSt: Name=" + InOutBeforeSt.getName() + " | ID==" + InOutBeforeSt.getStudentID() + " | Age=" + InOutBeforeSt.getAge() + "\n" +
                            "InOutAfterSt: Name=" + InOutAfterSt.getName() + " | ID==" + InOutAfterSt.getStudentID() + " | Age=" + InOutAfterSt.getAge() + "\n"
                    );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        bindservice = (Button) findViewById(R.id.bindservice);
        bindservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName Name = ComponentName.unflattenFromString(AIDL_SERVICE_COMPONENT);
                intent.setComponent(Name);
                mBindService = new BindService(getApplicationContext());
                boolean results = mBindService.startBindAidlService(intent);
                Log.d(TAG, "initData: results=="+results);
            }
        });
    }

}
