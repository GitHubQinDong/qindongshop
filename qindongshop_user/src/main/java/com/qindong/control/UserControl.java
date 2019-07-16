package com.qindong.control;

import com.qindong.model.User;
import com.qindong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author qind6
 * @date 2019/6/24
 */
@Controller
public class UserControl {
    @Autowired
    UserService userService;
    Logger logger= LoggerFactory.getLogger(UserControl.class);

    @RequestMapping(value = "/toRegister",method = RequestMethod.GET)
    public String toRegister(){
        return "user_register";
    }

    @RequestMapping(value = "/user_register",method = RequestMethod.POST)
    public String register(User user, Model model){
        int res=userService.insertUser(user);
        if(res==1){
            return toUserInfo(user.getId(),model);
        }
       return "user_register";
    }

    @RequestMapping(value = "/toUserInfo",method = RequestMethod.GET)
    public String toUserInfo(int id,Model model){
        model.addAttribute("user",userService.selectByPrimaryKey(id));
        return "user_info";
    }

    @RequestMapping(value = "/toUsers",method = RequestMethod.GET)
    public String toUsers(User user,Model model){
        model.addAttribute("userList",userService.selectUsers(user));
        return "user_list";
    }

    @RequestMapping(value = "/toUpdate",method = RequestMethod.GET)
    public String toUpdate(int id,Model model){
        model.addAttribute("user",userService.selectByPrimaryKey(id));
        return "user_update";
    }

    @RequestMapping(value = "/user_update",method = RequestMethod.POST)
    public void update(User user,Model model){
        model.addAttribute("user",user);
        userService.updateUser(user);
    }

    @RequestMapping(value = "/delUser",method = RequestMethod.GET)
    public String delUser(int id,Model model){
        userService.delUser(id);
        return toUsers(new User(),model);
    }

}
