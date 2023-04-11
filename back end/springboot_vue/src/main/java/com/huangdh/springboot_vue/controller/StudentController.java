package com.huangdh.springboot_vue.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.huangdh.springboot_vue.service.IStudentService;
import com.huangdh.springboot_vue.entity.Student;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jason852
 * @since 2023-03-29
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;

    //新增或更新
    @PostMapping
    public  boolean save(@RequestBody Student student){
        //新增或更新
        return studentService.saveOrUpdate(student);
    }

    //删除
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return studentService.removeById(id); /*${table.entityPath}=student  ${entity}=Student*/
    }

    //批量删除数据
    @DeleteMapping("/delBatch")
    public boolean delbatch(@RequestBody List<Integer> ids){
        return studentService.removeBatchByIds(ids);
    }

    //查询所有数据
    @GetMapping
    public List<Student> findAll() {
        return studentService.list();
    }

    //根据id查询
    @GetMapping("/{id}")
    public Student findOne(@PathVariable Integer id) {
        return studentService.getById(id);
    }

    //分页
    @GetMapping("/page")
    public Page<Student> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String vcStudentName,
                                  @RequestParam(defaultValue = "") String vcStudentCode,
                                  @RequestParam(defaultValue = "") String nGrade) {
        QueryWrapper<Student> queryWrapper= new QueryWrapper<>();
        if(!"".equals(vcStudentName)){
            queryWrapper.like("VC_STUDENT_NAME",vcStudentName);
        }
        if(!"".equals(vcStudentCode)){
            queryWrapper.like("VC_STUDENT_CODE",vcStudentCode);
        }
        if(!"".equals(nGrade)){
            queryWrapper.like("N_GRADE",nGrade);
        }
        queryWrapper.orderByDesc("N_STUDENT_ID");
        return studentService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }


    //导入导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        List<Student> List= studentService.list();//查出所有数

    }


}

