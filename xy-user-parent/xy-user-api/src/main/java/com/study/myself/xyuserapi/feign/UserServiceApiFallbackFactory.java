package com.study.myself.xyuserapi.feign;

import com.study.myself.xycommon.common.ResultModel;
import com.study.myself.xycommon.model.IdRequest;
import com.study.myself.xyuserapi.vo.UserVo;
import org.springframework.stereotype.Component;

/**
 * @program: xy-parent
 * @description: 用户模块-feign接口
 * @author: wxy
 * @create: 2021-03-04 17:22
 **/
@Component
public class UserServiceApiFallbackFactory implements IUserApiService{
    @Override
    public ResultModel<UserVo> getUser(IdRequest idRequest) {
       // return new ResultModel<>();
        return ResultModel.error(1001, "服务已经停止");
    }
}
