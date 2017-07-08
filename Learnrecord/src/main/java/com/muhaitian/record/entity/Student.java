package com.muhaitian.record.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangkang001 on 2017/7/8.
 */

public class Student implements Parcelable{
    private String Name;
    private String StudentID;
    private int Age;
    private boolean Male;

    public Student(){}

    protected Student(Parcel in) {
        Name = in.readString();
        StudentID = in.readString();
        Age = in.readInt();
        Male = in.readByte() != 0;
    }

    public void readFromParcel(Parcel parcel){
        Name = parcel.readString();
        StudentID = parcel.readString();
        Age = parcel.readInt();
        Male = parcel.readByte() != 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public boolean isMale() {
        return Male;
    }

    public void setMale(boolean male) {
        Male = male;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(StudentID);
        dest.writeInt(Age);
        dest.writeByte((byte) (Male ? 1 : 0));
    }
}
