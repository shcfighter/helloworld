package com.ecit.premain;

import java.lang.instrument.Instrumentation;

public class MyPremain {
    public static void premain(String agentOps, Instrumentation inst) {
        String[] args = agentOps.split("_");
        // 绑定ClassFileTransformer
        System.out.println(args.length);
        System.out.println(args[0]);
        System.out.println(args[1]);
        inst.addTransformer(new MyTransformer(args[0], args[1]));
    }
}