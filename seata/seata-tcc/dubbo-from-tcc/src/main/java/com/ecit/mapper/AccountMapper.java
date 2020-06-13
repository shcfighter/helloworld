package com.ecit.mapper;

import com.ecit.domain.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(String accountNo);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String accountNo);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    Account getAccountForUpdate(String accountNo);

    int updateFreezedAmount(Account record);

    int updateAmount(Account record);
}