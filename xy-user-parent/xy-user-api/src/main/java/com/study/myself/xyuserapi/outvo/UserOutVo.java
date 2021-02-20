package com.study.myself.xyuserapi.outvo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: xy-parent
 * @description: 用户
 * @author: wxy
 * @create: 2020-12-04 16:33
 **/
@Data
@Accessors(chain = true)
public class UserOutVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Integer sex;
    private String mobile;
}
