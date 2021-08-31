package com.example.demowebfluxdemo1.handler;

import com.example.demowebfluxdemo1.entity.User;
import com.example.demowebfluxdemo1.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * Created by Intellij IDEA.
 *
 * @author ZhuZhaoMing
 * @Description
 * @date 2021/8/31-10:10 下午
 * @motto: Never say die Never give up
 */
public class UserHandler  {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    //    根据id查询
    public Mono<ServerResponse> getUserById(ServerRequest request) {
//        获取id值
        int userId = Integer.valueOf(request.pathVariable("id"));
//        空值处理
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
//        调用service方法得到数据
        Mono<User> userMono = this.userService.getUserById(userId);
//        吧usermomo进行转换返回
//        使用reactor操作符flatMap
        return userMono.flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(person))).switchIfEmpty(notFound);
    }


    //    查询所有
    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
//        调用server得到结果
        Flux<User> users = this.userService.getAllUser();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(users, User.class);


    }

    //    添加
    public Mono<ServerResponse> saveUser(ServerRequest request) {
//        得到user对象
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok().build(this.userService.saveUserInfo(userMono));
    }
}
