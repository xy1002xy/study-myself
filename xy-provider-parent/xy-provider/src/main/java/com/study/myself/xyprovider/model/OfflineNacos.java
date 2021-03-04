package com.study.myself.xyprovider.model;

import lombok.Data;

/**
 * @program: xy-parent
 * @description: 下线nacos
 * @author: wxy
 * @create: 2021-02-08 10:15
 **/
@Data
public class OfflineNacos {

    private Integer port;

    private String serviceName;

    private String ip;
}
