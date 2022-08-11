package com.jikaigg.note.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DemoUel {


    @Test
    public void creataActivitiByUel(){
        // 1. 创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 使用service进行流程部署，定义一个流程的名字，把bpmn和png部署到数据库中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程-uel")
                .addClasspathResource("bpmn/chuchai-uel.bpmn")
                .addClasspathResource("bpmn/chuchai-uel.png")
                .deploy();
        // 4. 输出部署信息
        System.out.println("流程部署id=" + deploy.getId());
        System.out.println("流程部署名字name=" + deploy.getName());
    }

    @Test
    public void startProcessTest() {
        // 1. 创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3. 根据流程定义的id启动流程
        // 创建map用来替换uel表达式
        Map<String,Object> assginess = new HashMap<>();
        assginess.put("assignee0","yaojikai");
        assginess.put("assignee1","shimengyu");
        assginess.put("assignee2","zhangdapao");
        assginess.put("assignee3","zhangwuji");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("chuchai-uel",assginess);
        // 4. 输出内容
        System.out.println("流程定义id" + instance.getProcessDefinitionId());
        System.out.println("流程实例id" + instance.getId());
        System.out.println("当前活动的id" + instance.getActivityId());
    }
}
