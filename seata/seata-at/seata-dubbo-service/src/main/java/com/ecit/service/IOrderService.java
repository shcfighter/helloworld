package com.ecit.service;

public interface IOrderService {

    void create(String userId, String commodityCode, Integer count);
}
