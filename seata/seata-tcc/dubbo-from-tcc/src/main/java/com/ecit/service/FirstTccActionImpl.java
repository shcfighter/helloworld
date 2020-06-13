package com.ecit.service;

import com.ecit.FirstTccAction;
import com.ecit.domain.Account;
import com.ecit.mapper.AccountMapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * 扣钱参与者实现
 *
 * @author zhangsen
 */
@Service
public class FirstTccActionImpl implements FirstTccAction {

    /**
     * 扣钱账户 DAO
     */
    @Resource
    private AccountMapper accountMapper;

    /**
     * 扣钱数据源事务模板
     */
    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 一阶段准备，冻结 转账资金
     * @param businessActionContext
     * @param accountNo
     * @param amount
     * @return
     */
    @Override
    public boolean prepareMinus(BusinessActionContext businessActionContext, final String accountNo, final double amount) {
        //分布式事务ID
        final String xid = businessActionContext.getXid();

        return transactionTemplate.execute(new TransactionCallback<Boolean>(){

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try {
                    //校验账户余额
                    Account account = accountMapper.getAccountForUpdate(accountNo);
                    if(account == null){
                        throw new RuntimeException("账户不存在");
                    }
                    if (account.getAmount() - amount < 0) {
                        throw new RuntimeException("余额不足");
                    }
                    //冻结转账金额
                    double freezedAmount = account.getFreezedAmount() + amount;
                    account.setFreezedAmount(freezedAmount);
                    accountMapper.updateFreezedAmount(account);
                    System.out.println(String.format("prepareMinus account[%s] amount[%f], dtx transaction id: %s.", accountNo, amount, xid));
                    return true;
                } catch (Throwable t) {
                    t.printStackTrace();
                    status.setRollbackOnly();
                    return false;
                }
            }
        });
    }

    /**
     * 二阶段提交
     * @param businessActionContext
     * @return
     */
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        //分布式事务ID
        final String xid = businessActionContext.getXid();
        //账户ID
        final String accountNo = String.valueOf(businessActionContext.getActionContext("accountNo"));
        //转出金额
        final double amount = Double.valueOf(String.valueOf(businessActionContext.getActionContext("amount")));
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try{
                    Account account = accountMapper.getAccountForUpdate(accountNo);
                    //扣除账户余额
                    double newAmount = account.getAmount() - amount;
                    if (newAmount < 0) {
                        throw new RuntimeException("余额不足");
                    }
                    account.setAmount(newAmount);
                    //释放账户 冻结金额
                    account.setFreezedAmount(account.getFreezedAmount()  - amount);
                    accountMapper.updateAmount(account);
                    System.out.println(String.format("minus account[%s] amount[%f], dtx transaction id: %s.", accountNo, amount, xid));
                    return true;
                }catch (Throwable t){
                    t.printStackTrace();
                    status.setRollbackOnly();
                    return false;
                }
            }
        });
    }

    /**
     * 二阶段回滚
     * @param businessActionContext
     * @return
     */
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        //分布式事务ID
        final String xid = businessActionContext.getXid();
        //账户ID
        final String accountNo = String.valueOf(businessActionContext.getActionContext("accountNo"));
        //转出金额
        final double amount = Double.valueOf(String.valueOf(businessActionContext.getActionContext("amount")));
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {

            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                try{
                    Account account = accountMapper.getAccountForUpdate(accountNo);
                    if(account == null){
                        //账户不存在，回滚什么都不做
                        return true;
                    }
                    //释放冻结金额
                    account.setFreezedAmount(account.getFreezedAmount()  - amount);
                    accountMapper.updateFreezedAmount(account);
                    System.out.println(String.format("Undo prepareMinus account[%s] amount[%f], dtx transaction id: %s.", accountNo, amount, xid));
                    return true;
                }catch (Throwable t){
                    t.printStackTrace();
                    status.setRollbackOnly();
                    return false;
                }
            }
        });
    }

}
