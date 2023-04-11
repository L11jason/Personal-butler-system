package com.huangdh.springboot_vue.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangdh.springboot_vue.common.Constants;
import com.huangdh.springboot_vue.controller.dto.UserDTO;
import com.huangdh.springboot_vue.entity.User;
import com.huangdh.springboot_vue.exception.ServiceException;
import com.huangdh.springboot_vue.mapper.UserMapper;
import com.huangdh.springboot_vue.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangdh.springboot_vue.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jason852
 * @since 2023-04-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG= Log.get();

    @Override
    public UserDTO login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try{
             one = getOne(queryWrapper);//数据库中查询的用户信息
        }catch (Exception e){//查询到多条匹配数据
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }

        //判断是否匹配成功
        if(one!=null){
            BeanUtil.copyProperties(one,userDTO,true);
            //设置token
            String token = TokenUtils.createToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }

    }

    @Override
    public User register(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        //queryWrapper.eq("password",userDTO.getPassword());
        User one ;
        try{
            one = getOne(queryWrapper);//数据库中查询的用户信息
        }catch (Exception e){//查询到多条匹配数据
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        //判断用户是否已存在
        if(one ==null){
            one=new User();//避免空指针异常
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);//copy完后的用户对象储存到数据库
        }else{
            throw new ServiceException(Constants.CODE_600,"用户已存在");
        }
        return one;
    }

//    private User getUserInfo(){
//
//    }
}
