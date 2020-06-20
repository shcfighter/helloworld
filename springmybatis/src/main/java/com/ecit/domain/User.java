package com.ecit.domain;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private Integer id;

    private String name;

    private String idcard;

    private Integer age;

    private Byte sex;

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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(idcard, user.idcard) &&
                Objects.equals(age, user.age) &&
                Objects.equals(sex, user.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idcard, age, sex);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}