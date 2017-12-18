package org.trump.vincent.algorithm;

public class ReferenceTest {
    public void printStu(){
        Student student = new Student("vin");
//        Student student = null;
        modify(student);
        System.out.print(student.getName());
    }
    public void modify(Student stu){
        stu = new Student("vincent");
//        student = null;
    }
    public static void main(String[] args) {
        new ReferenceTest().printStu();

        Student student = new Student("vin");
        Student stu = student;
        stu = new Student("vincent");

    }
}
class Student{
    private String name;

    public Student(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}