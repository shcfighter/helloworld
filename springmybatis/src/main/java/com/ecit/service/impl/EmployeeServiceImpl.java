package com.ecit.service.impl;

import com.ecit.domain.Employee;
import com.ecit.mapper.EmployeeMapper;
import com.ecit.service.IEmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void save(Employee employee) {
        employeeMapper.insert(employee);
        //int i = 1/0;
    }
}
