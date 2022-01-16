package cn.xiaolimoon.service_edu.controller;

import cn.xiaolimoon.common_util.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zcj
 * @Description: 登录
 * @Date: 2022/1/15 9:43
 * @CrossOrigin 解决跨域
 */

@CrossOrigin
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://blog-moon.oss-cn-chengdu.aliyuncs.com/blog//tx_1640061721724.jpg");
    }
}
