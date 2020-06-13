package com.ecit.io;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        User person = new User("刘力", 21);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d://output.txt"));
        //objectOutputStream.writeObject("string");
        objectOutputStream.writeObject(person);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d://output.txt"));
        User p = (User) objectInputStream.readObject();
        System.out.println(p.getName());
    }
}
