package com.study.myself.xyuser.feign;

import com.study.myself.xycommon.common.ResultModel;
import com.study.myself.xyuser.model.IdRequest;
import com.study.myself.xyuser.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: xy-parent
 * @description: 对外暴露的接口
 * @author: wxy
 * @create: 2020-12-04 10:56
 **/
@FeignClient(value = "xy-user")
public interface UserFeignApi {

    /**
     * 返回用户信息
     *
     * @param idRequest 用户id
     * @return 返回用户信息
     */
    @PostMapping("/getUser")
    ResultModel<User> getUser(@RequestBody IdRequest idRequest);
}
