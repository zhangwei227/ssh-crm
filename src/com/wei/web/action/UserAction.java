package com.wei.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wei.domain.User;
import com.wei.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven {

    private User user = new User();
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String login() throws Exception {

        //1.调用service执行登录逻辑
        User user = userService.getUserByCodePassword(this.user);
        //2.将返回的User对象放入session域中
        ActionContext.getContext().getSession().put("user", user);
        //3.重定向到项目首页
        return "toHome";
    }

    @Override
    public Object getModel() {
        return user;
    }
}
