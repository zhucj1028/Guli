package cn.xiaolimoon.service_edu.controller;

import cn.xiaolimoon.common_util.R;
import cn.xiaolimoon.service_edu.entity.EduTeacher;
import cn.xiaolimoon.service_edu.entity.vo.TeacherQuery;
import cn.xiaolimoon.service_edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Zcj
 * @Description: 讲师 前端控制器
 * @Date: 2022/1/01/08 9:43
 * @CrossOrigin 解决跨域
 */

@CrossOrigin
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    /**
     * 把service注入
     */
    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询讲师表所有数据
     *
     * @return 返回教师列表
     */
    @ApiOperation(value = "查询所有讲师")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    /**
     * 讲师逻辑删除
     *
     * @return 返回删除结果true\false
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 分页查询讲师列表
     *
     * @param current 当前页
     * @param limit   每页记录数
     * @return 分页list数组
     */
    @ApiOperation(value = "分页查询讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current, @PathVariable long limit) {
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        // 调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher, null);
        // 总记录数
        long total = pageTeacher.getTotal();
        // 数据list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

    /**
     * 条件查询带分页的方法
     *
     * @param current      当前页
     * @param limit        每页记录数
     * @param teacherQuery 查询条件
     * @return 条件查询分页list数组
     */
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  // @RequestBody(required = false) 接收json数据,需要和@PostMapping一起使用,required=false可以为空
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        // 构建查询条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        // 多条件组合查询request
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        // 调用方法实现条件查询分页
        eduTeacherService.page(pageTeacher, queryWrapper);
        // 总记录数
        long total = pageTeacher.getTotal();
        // 数据list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

    /**
     * 讲师添加
     *
     * @param eduTeacher 讲师列表数据
     * @return 成功与否
     */
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据ID查询讲师
     *
     * @param id 讲师ID
     * @return 讲师数据
     */
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher", eduTeacher);
    }

    /**
     * 修改讲师
     *
     * @param eduTeacher 讲师修改数据
     * @return 成功与否
     */
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

