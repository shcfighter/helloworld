package com.ecit.service;

import java.math.BigDecimal;

public interface IAccountService {

    void debit(String userId, BigDecimal num);
}
