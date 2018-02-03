/**
 * Created by Vincent on 2017/12/13 0013.
 */
package org.trump.vincent.algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Conclusion for List iterate style
     */
    public void IterateStyle() {
        List<Student> students = new ArrayList<>();
        Iterator<Student> iterator = students.iterator();
        /**
         *  Iterable interface & Interator
         */
        for (; iterator.hasNext(); ) {
            Student student = iterator.next();
            //TODO
        }

        /**
         * foreach loop
         */
        for (Student student : students) {
            //TODO
        }

        /**
         * nature loop style
         */
        for (int i = 0; students != null && i < students.size(); i++) {
            //TODO
        }

        /**
         * funtional programming
         */
        students.forEach(new Consumer<Student>() {
            @Override
            public void accept(Student student) {
                //TODO
            }
        });
    }
}