package com.ecit.sendbox;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.kohsuke.MetaInfServices;

import javax.annotation.Resource;
import java.util.logging.Logger;


@MetaInfServices(Module.class)
@Information(id = "my-sandbox-module")// 模块名,在指定挂载进程后通过-d指定模块,配合@Command注解来唯一确定方法
public class MySandBoxModule implements Module {
    //日志输出，默认采用logback，这里的日志输出到切入的服务日志中
    private Logger LOG = Logger.getLogger(MySandBoxModule.class.getName());

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    @Command("addLog")// 模块命令名
    public void addLog() {
        new EventWatchBuilder(moduleEventWatcher)
                .onClass("java.com.com.ecit.controller.HelloController")// 想要对 PackageServiceImpl 这个类进行切面
                .onBehavior("hello")// 想要对上面类的 bathSave 方法进行切面
                .onWatch(new AdviceListener() {
                    //对方法执行之前执行
                    @Override
                    protected void before(Advice advice) throws Throwable {
                        //获取方法的所有参数
                        Object[] parameterArray = advice.getParameterArray();
                        if (parameterArray != null) {
                            for (Object po : parameterArray) {
                                //方法参数可能为空，规避报错
                                if (po != null) {
                                    System.out.println(po.toString());
                                }
                            }
                        }
                    }
                });
    }
}