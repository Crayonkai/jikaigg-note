package com.jikaigg.note.activiti7;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class ActivitiBusinessDemo {
    /**
     * 添加业务key到Activiti表
     * BusinessKey
     */
    @Test
    public void test1() {
        // 获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 获取RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // 启动流程的过程中添加BusinessKey
        // 第一个参数：流程定义key ; 第二个参数：BusinessKey，存出差申请单的id
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("chuchai", "1001");

        // 输出
        System.out.println(processInstance.getBusinessKey());
    }

    /**
     * 全部流程实例的挂起和激活
     * suspend 暂停
     */
    @Test
    public void test2() {
        // 获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 查询流程定义的信息
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("chuchai")
                .singleResult();
        // 获取当前流程定义的实例是否都是挂起的状态
        boolean suspended = processDefinition.isSuspended();

        // 获取流程定义的id
        String id = processDefinition.getId();

        // 如果是挂起状态，改为激活
        if (suspended) {
            // 参数1是流程定义的id，参数2是传是否激活，参数3是激活的时间
            repositoryService.activateProcessDefinitionById(id, true, null);
            System.out.println("流程定义 " + id + " 已激活！");
        } else {
            // 如果是正常状态，改为挂起
            // 参数1是流程定义的id，参数2是传是否暂停/挂起，参数3是激活的时间
            repositoryService.suspendProcessDefinitionById(id, true, null);
            System.out.println("流程定义 " + id + " 已挂起！");
        }

    }

    /**
     * 挂起和激活 单个实例
     * suspend 暂停
     */
    @Test
    public void test3() {
        // 获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 获取RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // 获取RuntimeService获取流程实力对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("15001")
                .singleResult();
        // 获取当前实例是否都是挂起的状态 true-已暂停,false-激活
        boolean suspended = processInstance.isSuspended();

        // 获取流程实例的id
        String id = processInstance.getId();

        // 如果是挂起状态，改为激活
        if (suspended) {
            // 参数1是流程实例的id，参数2是传是否激活，参数3是激活的时间
            runtimeService.activateProcessInstanceById(id);
            System.out.println("流程实例 " + id + " 已激活！");
        } else {
            // 如果是正常状态，改为挂起
            // 参数1是流程定义的id，参数2是传是否暂停/挂起，参数3是激活的时间
            runtimeService.suspendProcessInstanceById(id);
            System.out.println("流程实例 " + id + " 已挂起！");
        }

    }

    /**
     * 测试被挂起的实例 完成任务是否能成功
     * suspend 暂停
     */
    @Test
    public void test4() {
        // 获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 获取TaskService
        TaskService taskService = processEngine.getTaskService();

        // 获取任务
        Task task = taskService.createTaskQuery().processInstanceId("15001")
                .taskAssignee("jerry")
                .singleResult();
        System.out.println("实例id: " + task.getProcessInstanceId());
        System.out.println("任务id: " + task.getId());
        System.out.println("负责人: " + task.getAssignee());
        System.out.println("任务名称: " + task.getName());

        // 根据任务id完成任务
        taskService.complete(task.getId());


    }

}
