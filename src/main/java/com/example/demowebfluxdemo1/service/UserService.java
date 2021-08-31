package com.example.demowebfluxdemo1.service;

import com.example.demowebfluxdemo1.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Intellij IDEA.
 *
 * @author ZhuZhaoMing
 * @Description 用户操作接口
 * @date 2021/8/31-9:07 下午
 * @motto: Never say die Never give up
 */
public interface UserService {
    //    根据id查询用户
    Mono<User> getUserById(int id);

    //    查询所有用户
    Flux<User> getAllUser();

    //    添加用户
    Mono<Void> saveUserInfo(Mono<User> user);


}
