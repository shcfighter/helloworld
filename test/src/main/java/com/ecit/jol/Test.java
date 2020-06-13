package com.ecit.jol;

import org.openjdk.jol.info.ClassLayout;

public class Test {
    public static void main(String[] args) {
        //System.out.println(ClassLayout.parseClass(Object.class).toPrintable());
        System.out.println(ClassLayout.parseInstance(new Integer(1)).toPrintable());
        System.out.println(ClassLayout.parseInstance(new String()).toPrintable());
    }

}
