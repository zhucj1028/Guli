package cn.xiaolimoon.common_util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zcj
 * @Description: 统一返回结果的类
 * @Date: 2022/1/8 22:44
 */
@Data
public class R {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 把构造方法私有
     */
    private R() {}

    /**
     * 成功静态方法
     * @return 成功 SUCCESS
     */
    public static cn.xiaolimoon.common_util.R ok() {
        cn.xiaolimoon.common_util.R r = new cn.xiaolimoon.common_util.R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    /**
     * 失败静态方法
     * @return 成功 ERROR
     */
    public static cn.xiaolimoon.common_util.R error() {
        cn.xiaolimoon.common_util.R r = new cn.xiaolimoon.common_util.R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public cn.xiaolimoon.common_util.R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public cn.xiaolimoon.common_util.R message(String message){
        this.setMessage(message);
        return this;
    }

    public cn.xiaolimoon.common_util.R code(Integer code){
        this.setCode(code);
        return this;
    }

    public cn.xiaolimoon.common_util.R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public cn.xiaolimoon.common_util.R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
