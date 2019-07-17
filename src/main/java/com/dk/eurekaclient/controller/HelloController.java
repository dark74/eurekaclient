package com.dk.eurekaclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: eurekaclient
 * @description: 客户端Hello类
 * @author: DongKe
 * @create: 2019-07-17 16:26
 **/
@Slf4j
@RestController
public class HelloController {
    @Autowired
    private Registration registration;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello")
    public String helloResp() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(registration.getServiceId());
        for (int i = 0; i < serviceInstanceList.size(); i++) {
            System.out.println("主机名host:" + serviceInstanceList.get(i).getHost() + ", service_id:" + serviceInstanceList.get(i).getServiceId());
        }
        return "Hello, I am Client Service Provider";
    }
}

