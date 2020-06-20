package com.ecit.domain;

public class Employee {
    private Integer id;

    private String name;

    private Integer gz;

    private String dept;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getGz() {
        return gz;
    }

    public void setGz(Integer gz) {
        this.gz = gz;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }
    
}