package com.ecit.premain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyTransformer implements ClassFileTransformer {
    private String targetClassName;
    private String targetMethodName;

    public MyTransformer(String targetClassName, String targetMethodName) {
        this.targetClassName = targetClassName;
        this.targetMethodName = targetMethodName;
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");

        if (className.equals(targetClassName)) {
            try {
                // 得到类信息
                CtClass ctclass = ClassPool.getDefault().get(className);
                // 得到方法信息
                CtMethod[] ctMethods = ctclass.getDeclaredMethods();
                for (CtMethod ctMethod: ctMethods) {
                    // 修改方法实现
                    ctMethod.insertAfter("System.out.println(\"" + ctMethod.getName() + " Agent!!!!!!!!!!!!!!!!!!!!!!!!\");");
                }
                return ctclass.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}