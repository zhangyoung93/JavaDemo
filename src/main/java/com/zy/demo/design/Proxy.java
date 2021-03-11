package com.zy.demo.design;

/**
 * 代理模式
 * @author zy
 */
public class Proxy {
    public static void main(String[] args){
        HttpService httpService = new HttpServiceProxy();
        httpService.register();
        httpService.login();
        httpService.logout();
    }
}

/**
 * HTTP服务
 */
interface HttpService{

    /**
     * 注册
     */
    void register();

    /**
     * 登录
     */
    void login();

    /**
     * 登出
     */
    void logout();
}

/**
 * HTTP服务实现类(被代理类)
 */
class HttpServiceImpl implements HttpService {

    /**
     * 注册
     */
    @Override
    public void register() {
        System.out.println("register.");
    }

    /**
     * 登录
     */
    @Override
    public void login() {
        System.out.println("login.");
    }

    /**
     * 登出
     */
    @Override
    public void logout() {
        System.out.println("logout.");
    }
}

/**
 * HTTP服务代理类
 */
class HttpServiceProxy implements HttpService {

    //实例化被代理类
    private HttpService httpService = new HttpServiceImpl();

    /**
     * 代理注册
     */
    @Override
    public void register() {
        System.out.println("request");
        this.httpService.register();
        System.out.println("response");
    }

    /**
     * 代理登录
     */
    @Override
    public void login() {
        System.out.println("request");
        this.httpService.login();
        System.out.println("response");
    }

    /**
     * 代理登出
     */
    @Override
    public void logout() {
        System.out.println("request");
        this.httpService.logout();
        System.out.println("response");
    }
}