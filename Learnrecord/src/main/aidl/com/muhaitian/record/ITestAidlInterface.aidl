// ITestAidlInterface.aidl
package com.muhaitian.record;
// Declare any non-default types here with import statements

import com.muhaitian.record.entity.Student;

interface ITestAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    void sendInStudent(in Student student);
    void sendOutStudent(out Student student);
    void sendInOutStudent(inout Student studend);
}
