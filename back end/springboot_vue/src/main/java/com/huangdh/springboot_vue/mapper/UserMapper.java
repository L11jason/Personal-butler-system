package com.huangdh.springboot_vue.mapper;

import com.huangdh.springboot_vue.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jason852
 * @since 2023-04-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
