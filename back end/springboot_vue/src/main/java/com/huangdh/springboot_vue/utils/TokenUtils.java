package com.huangdh.springboot_vue.utils;

import cn.hutool.core.date.DateUtil;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.huangdh.springboot_vue.entity.User;
import com.huangdh.springboot_vue.service.IUserService;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;

@Component
public class TokenUtils {

    private static IUserService staticUserService;

    @Resource
    private IUserService userService;

    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

    /**
     * 生成Token
     *
     */
    public static String createToken(String userid,String sign){
        return JWT.create()
                .withAudience(userid)
//                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
//                .withClaim("id", userid)//userId
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //超时设置,两小时后token过期
//                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(sign)); //以password座位token的密匙
    }

    /**
     * 获取当前登录用户信息
     * @return user对象
     */
    public static User getCurrentUser(){
        try{
            HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
