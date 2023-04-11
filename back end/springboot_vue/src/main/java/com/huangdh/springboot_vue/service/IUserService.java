package com.huangdh.springboot_vue.service;

import com.huangdh.springboot_vue.controller.dto.UserDTO;
import com.huangdh.springboot_vue.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jason852
 * @since 2023-04-08
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
