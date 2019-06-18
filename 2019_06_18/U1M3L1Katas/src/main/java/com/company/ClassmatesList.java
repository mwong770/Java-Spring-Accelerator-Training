package com.company;
import java.util.ArrayList;

public class ClassmatesList {

    ArrayList<Classmate> classmates = new ArrayList<>();

    public void add(Classmate student){
        classmates.add(student);
    }

    public Classmate get(int idx) {
        return classmates.get(idx);
    }

    public ArrayList getAll() {
        return classmates;
    }

}
