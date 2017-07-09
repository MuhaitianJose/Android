package com.muhaitian.record.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.muhaitian.record.ITestAidlInterface;
import com.muhaitian.record.entity.Student;

/**
 * Created by wangkang001 on 2017/7/8.
 */

public class TestAidlService extends Service{

    private final String TAG = TestAidlService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

   private final ITestAidlInterface iBind = new ITestAidlInterface.Stub() {
       @Override
       public Student sendInStudent(Student student) throws RemoteException {
           Log.d(TAG, "sendInStudent: "+student.getName()+" | "+student.getStudentID()+" | "+student.getAge());
           student.setStudentID("004");
           return student;
       }

       @Override
       public Student sendOutStudent(Student student) throws RemoteException {
           Log.d(TAG, "sendOutStudent: "+student.getName()+" | "+student.getStudentID()+" | "+student.getAge());
           student.setStudentID("005");
           return student;
       }

       @Override
       public Student sendInOutStudent(Student student) throws RemoteException {
           Log.d(TAG, "sendInOutStudent: "+student.getName()+" | "+student.getStudentID()+" | "+student.getAge());
           student.setStudentID("006");
           return student;
       }
   };

}
