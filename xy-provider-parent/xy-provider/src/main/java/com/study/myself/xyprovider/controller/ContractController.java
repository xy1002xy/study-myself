package com.study.myself.xyprovider.controller;

import com.study.myself.xycommon.enums.ErrorCodeEnum;
import com.study.myself.xycommon.exception.XyException;
import com.study.myself.xycommon.model.IdRequest;
import com.study.myself.xycommon.model.PageResult;
import com.study.myself.xycommon.utils.HttpUtils;
import com.study.myself.xyprovider.model.ContractDataBO;
import com.study.myself.xyprovider.model.ContractDetailBO;
import com.study.myself.xyprovider.model.request.ContractDetailDTO;
import com.study.myself.xyprovider.model.request.ListContractDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: xy-parent
 * @description: 合同controller
 * @author: wxy
 * @create: 2021-05-10 13:37
 **/
@RequestMapping("/v1/rest/contract")
@RestController
@Api(tags = "远程调用的相关接口")
@Slf4j
public class ContractController {

    @PostMapping("/contractDetail")
    @ApiOperation(value = "根据合同主键id获取合同详情", notes = "根据合同主键id获取合同详情")
    public ContractDetailBO contractDetail(@RequestBody IdRequest idRequest) {
        throw new XyException(ErrorCodeEnum.EXCEPTION, "异常原因不明");
        //     return null;
    }

    @PostMapping("/pageContract")
    @ApiOperation(value = "根据条件分页查询合同数据", notes = "根据条件分页查询合同数据")
    public PageResult<ContractDataBO> pageContract(@RequestBody @Validated ListContractDTO listContractDTO) {
        return null;
    }

    @PostMapping("/approvalPass")
    @ApiOperation(value = "提交备案-审核通过", notes = "提交备案-审核通过")
    public Boolean approvalPass(@RequestBody @Validated ContractDetailDTO contractDetailDTO) {
        String param =
            "{\"cardfrom\":\"\",\"cardto\":\"01\",\"cardtypeto\":\"01\",\"ce_id\":\"ce67a609c8679ada51214b9e76e262b9931a1a09a76b879a7ba94d13f53161b6\",\"education\":\"\",\"entcode\":\"913301087458306077\",\"entname\":\"杭州天谷信息科技有限公司\",\"file\":\"1281齐汝潇-劳动合同_17.pdf\",\"finish_time\":\"2020-12-30 16:35:18\",\"legal_person\":\"\",\"name\":\"1281齐汝潇-保密及竞业限制协议_12\",\"num\":\"1696907442803198484\",\"phoneto\":\"18510118567\",\"probation_time\":\"\",\"scale\":\"\",\"sign_end_time\":\"\",\"start_time\":\"2020-12-08 11:59:50\",\"type\":\"01\",\"userfrom\":\"\",\"userto\":\"齐汝潇\",\"validity_end\":\"2023-11-03 23:59:59\",\"validity_start\":\"2020-11-04 00:00:00\",\"work_content\":\"\",\"work_hour\":\"\",\"work_place\":\"\"}";
        String syncUrl = "http://192.168.7.5:8087/collect/v2/api/reportContractSyncc";
        // String syncUrl = "http://192.168.7.5:8087/rest/sign/constractSync";
        //String param = JSON.toJSONString(reportContractSyncDTO);
        String syncResult = HttpUtils.post(param, syncUrl);
        return null;
    }

    @PostMapping("/approvalRefuse")
    @ApiOperation(value = "提交备案-审核拒绝", notes = "提交备案-审核拒绝")
    public Boolean approvalRefuse(@RequestBody @Validated IdRequest idRequest) {

        return null;
    }

}
