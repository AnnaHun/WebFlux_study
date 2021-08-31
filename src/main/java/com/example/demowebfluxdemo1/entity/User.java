package com.example.demowebfluxdemo1.entity;

/**
 * Created by Intellij IDEA.
 *
 * @author ZhuZhaoMing
 * @Description
 * @date 2021/8/31-9:05 下午
 * @motto: Never say die Never give up
 */
public class User {
    private String name;
    private String gender;
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }

    public User() {
    }

    public User(String name, String gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
