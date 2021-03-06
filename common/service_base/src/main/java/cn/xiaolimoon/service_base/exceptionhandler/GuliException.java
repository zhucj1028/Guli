package cn.xiaolimoon.service_base.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zcj
 * @Description: 统一异常
 * @Date: 2022/1/10 22:33
 */

@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class GuliException extends RuntimeException {
    // 状态码
    private Integer code;
    // 异常信息
    private String msg;

}
