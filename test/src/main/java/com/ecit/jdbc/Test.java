package com.ecit.jdbc;

import java.util.HashMap;
import java.util.Objects;

public class Test {

    public static void main(String[] args) {
        HashMap<Student, Student> map = new HashMap();
        Student student = null;
        /*for (int i = 0; i < 1000; i++) {
            student = new Student(i, String.valueOf(i));
            map.put(student, student);
        }*/

        Student student1 = new Student(1, "1");
        //System.out.println(student1.hashCode() + ", " + student1.toString());
        /*Student student2 = new Student(1, "1");
        System.out.println(student2.hashCode() + ", " + student2.toString());
        System.out.println(student1 == student2);
        System.out.println(student1.equals(student2));*/

        map.put(student1, student1);
        System.out.println(map.get(student1));
        System.out.println(student1.hashCode());
        student1.setId(2);
        student1.setName("2");
        System.out.println(student1.hashCode());
        System.out.println(map.get(student1));



    }


    static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id &&
                    Objects.equals(name, student.name);
        }

        /*@Override
        public int hashCode() {
            return Objects.hash(id, name);
        }*/
    }

}