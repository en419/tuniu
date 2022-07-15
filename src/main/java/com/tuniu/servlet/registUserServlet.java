package com.tuniu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuniu.Pojo.ResultInfo;
import com.tuniu.Pojo.User;
import com.tuniu.service.impl.registerUserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registUserServlet")
public class registUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取数据
        Map<String, String[]> map=req.getParameterMap();
        User user = new User();
        try {
            //BeanUtils工具类的一个方法，该方法将map中的数据映射到JavaBean中的get和set方法中（数据封装到JavaBean）
            //之后取值直接从JavaBean中的get和set方法中取值就行了
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        registerUserServiceImpl registerUserService = new registerUserServiceImpl();
        boolean flag = registerUserService.registeruser(user);
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        //将info对象转换为json数据
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
