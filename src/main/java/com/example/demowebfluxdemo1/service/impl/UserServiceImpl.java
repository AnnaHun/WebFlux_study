package com.example.demowebfluxdemo1.service.impl;

import com.example.demowebfluxdemo1.entity.User;
import com.example.demowebfluxdemo1.service.UserService;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 *
 * @author ZhuZhaoMing
 * @Description
 * @date 2021/8/31-9:09 下午
 * @motto: Never say die Never give up
 */
@Repository
public class UserServiceImpl implements UserService {

    //    创建一个map集合存储数据
    private final Map<Integer, User> users = new HashMap<>();

    public UserServiceImpl() {
        this.users.put(1, new User("lucy", "男", 20));
        this.users.put(2, new User("lili", "女", 30));
        this.users.put(3, new User("jacky", "男", 40));
    }

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.justOrEmpty(this.users.get(id));
    }

    @Override
    public Flux<User> getAllUser() {
        return Flux.fromIterable(this.users.values());
    }

    @Override
    public Mono<Void> saveUserInfo(Mono<User> userMono) {
        return userMono.doOnNext(person -> {
//            向map集合里面放值
            int id = users.size() + 1;
            users.put(id, person);
        }).thenEmpty(Mono.empty());
    }
}
