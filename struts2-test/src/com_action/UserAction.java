package com_action;

/**
 * Created with IDEA
 * author:DuzhenTong
 * Date:2017/11/10
 * Time:20:44
 */
public class UserAction {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        System.out.println("类中属性;"+username+password);
        if ("aaa".equals(username) && "sss".equals(password)) {
            return "success";
        }
        return "error";
    }
}
