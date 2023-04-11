package com.huangdh.springboot_vue.service.impl;

import com.huangdh.springboot_vue.entity.Student;
import com.huangdh.springboot_vue.mapper.StudentMapper;
import com.huangdh.springboot_vue.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jason852
 * @since 2023-03-29
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
