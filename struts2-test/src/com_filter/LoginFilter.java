package com_filter;

import com_ActionMapper.ActionAbstract;
import com_ActionMapper.ActionMapper;
import com_ActionMapper.ActionProxy;
import com_ActionMapper.RealAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * struts2的入口就是过滤器，最后也是通过过滤器返回给用户，所以在模拟的时候，把一些处理的代码都在过滤器中进行调用
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/11
 * Time:9:12
 */

public class LoginFilter implements javax.servlet.Filter {
    @Override
    public void destroy() {
    }

    /**
     * 在过滤器中判断，由于每次访问页面过滤器都会判断
     *
     * @param req
     * @param resp
     * @param chain
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        System.out.println("过滤器");
        //必须带着这句，否则页面不显示
//        chain.doFilter(req, resp);
        //获得url
        String url = request.getRequestURI();
        System.out.println(request.getRequestURL());
        //可以获得访问的信息
        String path = request.getServletPath();
        System.out.println(request.getServletPath());
        System.out.println("path" + path);
        ActionMapper actionMapper = new ActionMapper();
        System.out.println(request.getQueryString());
        System.out.println(path.substring(path.lastIndexOf(".") + 1));
        if (path.substring(path.lastIndexOf(".") + 1).equals("jsp") == false) {
            if (actionMapper.result(path)) {
                System.out.println("----");
                String name = request.getQueryString();
                System.out.println("表单数据："+name);
                String username = name.substring(0, name.indexOf("="));
                String password = name.substring(name.indexOf("&") + 1, name.lastIndexOf("="));
                System.out.println("=下标："+name.indexOf("="));
                String usernamevlaue = name.substring(name.indexOf("=") + 1,name.indexOf("&"));
                String passwordvalue = name.substring(name.lastIndexOf("=") + 1);
                System.out.println("usernmae="+username);
                System.out.println("password="+password);
                System.out.println("usernamevlaue=" + usernamevlaue);
                System.out.println("passwordvalue=" + passwordvalue);
                RealAction realAction = new RealAction();
                ActionProxy actionProxy = new ActionProxy(realAction);
                ActionAbstract a = (ActionAbstract) Proxy.newProxyInstance(actionProxy.getClass().getClassLoader(),
                        realAction.getClass().getInterfaces(), actionProxy);
                String xinxi=a.doSomething(username, password, usernamevlaue, passwordvalue);
                System.out.println("提交");
                System.out.println("代理对象返回的信息:"+xinxi);
                if (xinxi.equals("success")) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
                    dispatcher.forward(request, response);
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {

    }

}
