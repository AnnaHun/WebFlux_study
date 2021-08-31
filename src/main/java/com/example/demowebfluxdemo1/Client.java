package com.example.demowebfluxdemo1;

import com.example.demowebfluxdemo1.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Created by Intellij IDEA.
 *
 * @author ZhuZhaoMing
 * @Description
 * @date 2021/8/31-10:49 下午
 * @motto: Never say die Never give up
 */
public class Client {
    public static void main(String[] args) {
//        调用服务器地址
        WebClient webClient = WebClient.create("http://127.0.0.1:52175");

//        根据id查询
        String id = "1";
        User userResult = webClient.get().uri("/users/{id}", id).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(User.class).block();
        System.out.println(userResult.getName());

//        查询所有
        Flux<User> results = webClient.get().uri("/users").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(User.class);

        results.map(stu -> stu.getName()).buffer().doOnNext(System.out::print).blockFirst();

    }
}
