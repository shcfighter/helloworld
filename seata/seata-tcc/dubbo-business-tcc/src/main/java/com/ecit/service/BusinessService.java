package com.ecit.service;

import com.ecit.FirstTccAction;
import com.ecit.SecondTccAction;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Reference
    private FirstTccAction firstTccAction;
    @Reference
    private SecondTccAction secondTccAction;

    @GlobalTransactional
    public String transfer(){
        //加钱参与者，一阶段执行
        boolean ret = secondTccAction.prepareAdd(null, "A", 20);

        if(!ret){
            throw new RuntimeException("账号:[A] 预收款失败");
        }

        //扣钱参与者，一阶段执行
        ret = firstTccAction.prepareMinus(null, "C", 20);

        if(!ret){
            //扣钱参与者，一阶段失败; 回滚本地事务和分布式事务
            throw new RuntimeException("账号:[C] 预扣款失败");
        }

        System.out.println(String.format("transfer amount[%s] from [%s] to [%s] finish.", String.valueOf(20), "A", "C"));
        return "success";
    }

    @GlobalTransactional
    public String transfer2(){
        //加钱参与者，一阶段执行
        boolean ret = secondTccAction.prepareAdd(null, "A", 20);

        if(!ret){
            throw new RuntimeException("账号:[A] 预收款失败");
        }

        //扣钱参与者，一阶段执行
        ret = firstTccAction.prepareMinus(null, "C", 20);

        if(true){
            //扣钱参与者，一阶段失败; 回滚本地事务和分布式事务
            throw new RuntimeException("账号:[C] 预扣款失败");
        }

        System.out.println(String.format("transfer amount[%s] from [%s] to [%s] finish.", String.valueOf(20), "A", "C"));
        return "success";
    }
}
