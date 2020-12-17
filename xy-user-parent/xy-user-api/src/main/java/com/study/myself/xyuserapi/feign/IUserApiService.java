package com.study.myself.xyuserapi.feign;

import com.study.myself.xycommon.common.ResultModel;
import com.study.myself.xycommon.model.IdRequest;
import com.study.myself.xyuserapi.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: xy-user-parent
 * @description: 用户对外提供的接口
 * @author: wxy
 * @create: 2020-12-04 16:31
 **/
@FeignClient(value = "xy-user")
public interface IUserApiService {

    /**
     * 返回用户信息
     *
     * @param idRequest 用户id
     * @return 返回用户信息
     */
    @PostMapping("/user/getUser")
    ResultModel<UserVo> getUser(@RequestBody IdRequest idRequest);
}
