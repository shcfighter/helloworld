package com.ecit.service.impl;

import com.ecit.service.IJdbcService;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service("jdbcService")
public class JdbcServiceImpl implements IJdbcService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Transactional()
    @Override
    public void save() {
        /*HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();*/
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList("select * from t_user;");
        resultList.forEach(result -> {
            result.forEach((k, v) -> {
                System.out.println(k + " --> " + v);
            });
        });
        System.out.println("===============================================");
        int value = new Random().nextInt(10000);
        jdbcTemplate.execute("insert into t_user(name) values(" + value + ")");
        resultList = jdbcTemplate.queryForList("select * from t_user;");
        resultList.forEach(result -> {
            result.forEach((k, v) -> {
                System.out.println(k + " --> " + v);
            });
        });
        int i = 1/0;
    }


    @Transactional()
    @Override
    public void save2() {
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(0, 0)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(1, 1)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(2,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(3,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(4,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(5,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(6,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(7,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(8,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(9,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(10,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(11,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(12,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(13,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(14,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(15,2)");
        jdbcTemplate.execute("insert into t_order(order_id, user_id ) values(16,2)");
    }

    @Override
    public List query() {
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList("select * from t_order order by order_id limit 5, 10;");
        resultList.forEach(result -> {
            System.out.println("===============================");
            result.forEach((k, v) -> {
                System.out.println(k + " --> " + v);
            });
        });
        return resultList;
    }
}
