package org.asanka.bam.beans;

/**
 * Created by asanka on 7/25/14.
 */
public class Student {
    private String name;
    private String subject;
    private int marks;

    public Student(String line){
        String [] data = line.split(",");
        this.name=data[0];
        this.subject=data[1];
        this.marks=Integer.valueOf(data[2]);
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ("Student Name:"+this.name+"-- Subject:"+this.subject+"-- Marks:"+this.marks);
    }
}
