package com.taozi.testjava.test.controller;

import com.alibaba.fastjson.JSON;
import com.taozi.testjava.test.pojo.UserInfo;
import com.taozi.testjava.test.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.US_ASCII;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Controller 控制层需要的注解
//@RestController 使用这个也是可以的，但是使用后他里面所有请求返回的都是字符串！
//一般只需要作为接口放回JSON格式数据的话推荐使用@RestController
//@Controller这个是可以与Thymeleaf模板引擎使用时可以返回一个页面的
@Controller
//@RequestMapping指定路径名
//@RequestMapping("/test")用这个来指定路径也是可以的
@RequestMapping(value = "/test")
public class UserInfoController {
    //获取到UserInfoService
    @Autowired
    private UserInfoService userInfoService;

    //Get请求
    @GetMapping
    //@ResponseBody 注释后表示放回的是字符串
    @ResponseBody
    public String queryAll(){
        List<UserInfo> userInfoList = userInfoService.queryAll();
        return JSON.toJSONString(userInfoList);
    }

    //使用了RestFull风格
    @GetMapping("/{id}")
    @ResponseBody
    public String query(@PathVariable(value = "id")Integer id){
        UserInfo userInfo = userInfoService.queryById(id);
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList.add(userInfo);
        return JSON.toJSONString(userInfoList);
    }

    //post请求
    //@RequestBody 表示接受请求的JSON格式的数据
    @PostMapping
    @ResponseBody
    public String add(@RequestBody UserInfo userInfo){
        userInfoService.add(userInfo);
        return "添加OK";
    }

    //Delete请求
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public String delete(@PathVariable("id")Integer id){
        userInfoService.delete(id);
        return "删除成功";
    }

    //Put请求
    @PutMapping("/{id}")
    @ResponseBody
    public String update(@PathVariable("id")Integer id,
                         @RequestBody UserInfo userInfo){
        userInfo.setId(id);
        userInfoService.update(userInfo);
        return "修改成功";
    }
}
