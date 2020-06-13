package com.ecit.agentmain;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class AgentmainAttach {
    public static void main(String[] args) throws IOException, AttachNotSupportedException,
            AgentLoadException, AgentInitializationException {
        VirtualMachine vm = VirtualMachine.attach("8436");//7997是待绑定的jvm进程的pid号
        vm.loadAgent("D:\\IdeaProjects\\HelloWorld\\javaagent\\target/javaagent-1.0-SNAPSHOT.jar", "java.com.com.ecit.MyAgentTest&hello");
    }
}