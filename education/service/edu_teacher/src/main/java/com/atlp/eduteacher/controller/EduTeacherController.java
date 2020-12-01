package com.atlp.eduteacher.controller;


import com.atlp.commonutils.R;
import com.atlp.eduteacher.entity.EduTeacher;
import com.atlp.eduteacher.entity.vo.TeacherQuery;
import com.atlp.eduteacher.service.EduTeacherService;
import com.atlp.servicebase.handler.CustomException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 刘鹏
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(description = "讲师管理")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("findAll")
    @ApiOperation(value = "查询所有讲师信息")
    public R findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        try {
            int i = 19 / 0;
        } catch (Exception e) {
            throw new CustomException(200001, "执行了自定义异常处理...");
        }
        return R.ok().data("items", list);
    }

    @DeleteMapping("deleteTeacher/{id}") // 标识路径传递参数注解
    @ApiOperation(value = "逻辑删除讲师")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if(flag) {
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师列表信息")
    public R pageListTeacher(@PathVariable @ApiParam(name = "current", value = "当前页", required = true) long current,
                             @PathVariable @ApiParam(name = "limit", value = "页记录数", required = true) long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("rows",records);
    }

    @PostMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation(value = "多条件分页查询讲师列表信息")
    public R pageTeacherCondition(@PathVariable @ApiParam(name = "current", value = "当前页", required = true) long current,
                                  @PathVariable @ApiParam(name = "limit", value = "页记录数", required = true) long limit,
                                  // 标识使用JSON传递数据注解
                                  @RequestBody(required = false) @ApiParam(name = "teacherQuery", value = "条件对象") TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String gmtCreate = teacherQuery.getGmtCreate();
        String gmtModified = teacherQuery.getGmtModified();
        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if(!StringUtils.isEmpty(gmtCreate)) {
            wrapper.ge("gmt_create", gmtCreate);
        }
        if(!StringUtils.isEmpty(gmtModified)) {
            wrapper.le("gmt_modified", gmtModified);
        }
        eduTeacherService.page(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if(save) {
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "根据讲师ID查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag) {
            return R.ok();
        }
        return R.error();
    }
}

