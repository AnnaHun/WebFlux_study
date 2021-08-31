package com.example.demowebfluxdemo1;

import com.example.demowebfluxdemo1.handler.UserHandler;
import com.example.demowebfluxdemo1.service.UserService;
import com.example.demowebfluxdemo1.service.impl.UserServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * Created by Intellij IDEA.
 *
 * @author ZhuZhaoMing
 * @Description
 * @date 2021/8/31-10:31 下午
 * @motto: Never say die Never give up
 */
public class Server {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();

    }

    //    创建router路由
    public RouterFunction<ServerResponse> routerFunction() {
//        创建handler对象
        UserService userService = new UserServiceImpl();
        UserHandler handler = new UserHandler(userService);
//        设置路由
        return RouterFunctions.route(GET("/users/{id}").and(accept(APPLICATION_JSON)), handler::getUserById)
                .andRoute(GET("/users").and(accept(APPLICATION_JSON)), handler::getAllUsers);
    }


    //    创建服务器完成适配
    public void createReactorServer() {
//        路由器和handler适配
        RouterFunction<ServerResponse> route = routerFunction();
        HttpHandler httpHandler = toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

//        创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(adapter).bindNow();

    }
}
