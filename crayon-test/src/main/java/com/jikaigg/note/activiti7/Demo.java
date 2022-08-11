package com.jikaigg.note.activiti7;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;

public class Demo {
    public static void main(String[] args) {
        // 1. 创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3. 使用service进行流程部署，定义一个流程的名字，把bpmn和png部署到数据库中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程")
                .addClasspathResource("bpmn/chuchai.bpmn")
                .addClasspathResource("bpmn/chuchai.png")
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
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("chuchai");
        // 4. 输出内容
        System.out.println("流程定义id" + instance.getProcessDefinitionId());
        System.out.println("流程实例id" + instance.getId());
        System.out.println("当前活动的id" + instance.getActivityId());
    }

    /**
     * 查询个人待执行的任务
     */
    @Test
    public void findPersonalTaskListTest() {
        // 1. 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 获取TaskService
        TaskService taskService = processEngine.getTaskService();
        // 3. 根据流程key和任务的负责人查询任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("chuchai") //流程key
                .taskAssignee("zhangsan").list();  // 要查询的负责人
        // 4. 输出内容
        for (Task task : taskList) {
            System.out.println("流程实力id=" + task.getProcessInstanceId());
            System.out.println("任务id=" + task.getId());
            System.out.println("任务负责人=" + task.getAssignee());
            System.out.println("任务名称=" + task.getName());
        }
    }

    /**
     * 完成个人任务
     */
    @Test
    public void completTaskTest() {
        // 1. 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 获取TaskService
        TaskService taskService = processEngine.getTaskService();
        // 3. 根据任务完成任务
        taskService.complete("2505");
    }

    /**
     * 完整完成jerry个人任务
     */
    @Test
    public void completTaskOkTest() {
        // 1. 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 获取TaskService
        TaskService taskService = processEngine.getTaskService();
        // 3. 获取jerry待处理的出差任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("chuchai")
                .taskAssignee("jack")
                .singleResult();
        System.out.println("流程实力id=" + task.getProcessInstanceId());
        System.out.println("任务id=" + task.getId());
        System.out.println("任务负责人=" + task.getAssignee());
        System.out.println("任务名称=" + task.getName());
        // 完成jerry的个人任务
        taskService.complete(task.getId());
    }

    /**
     * 完整完成jack个人任务
     */
    @Test
    public void completTaskOkTest1() {
        // 1. 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 获取TaskService
        TaskService taskService = processEngine.getTaskService();
        // 3. 获取jerry待处理的出差任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("chuchai")
                .taskAssignee("jerry")
                .singleResult();
        System.out.println("流程实力id=" + task.getProcessInstanceId());
        System.out.println("任务id=" + task.getId());
        System.out.println("任务负责人=" + task.getAssignee());
        System.out.println("任务名称=" + task.getName());
        // 完成jerry的个人任务
        taskService.complete(task.getId());
    }

    /**
     * 完整完成rose个人任务
     */
    @Test
    public void completTaskOkTest2() {
        // 1. 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2. 获取TaskService
        TaskService taskService = processEngine.getTaskService();
        // 3. 获取jerry待处理的出差任务
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("chuchai")
                .taskAssignee("rose")
                .singleResult();
        System.out.println("流程实力id=" + task.getProcessInstanceId());
        System.out.println("任务id=" + task.getId());
        System.out.println("任务负责人=" + task.getAssignee());
        System.out.println("任务名称=" + task.getName());
        // 完成jerry的个人任务
        taskService.complete(task.getId());
    }

    /**
     * 使用zip包进行批量的部署
     */
    @Test
    public void deployByZip() {
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //流程部署
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("");
        // 使用压缩包进行流程部署
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deploy = repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();


    }

    /**
     * 下载资源文件
     * 从数据库中服务png和bpmn文件
     */
    @Test
    public void getBpmnAndPng() throws IOException {
        // 获取执行引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取流程定义和部署service
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 获取查询对象ProcessDeginitionQuery查询流程定义信息
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("chuchai")
                .singleResult();
        // 通过流程定义信息获取部署ID
        String deploymentId = processDefinition.getDeploymentId();
        // 通过Repositoryservice传递部署id参数，读取资源信息（png和bpmn文件）
        // 获取png文件
        String pngName = processDefinition.getDiagramResourceName();
        InputStream pngInput = repositoryService.getResourceAsStream(deploymentId, pngName);
        //获取bpmn文件
        String bpmnName = processDefinition.getResourceName();
        InputStream bpmnInput = repositoryService.getResourceAsStream(deploymentId, bpmnName);
        // 构造OutputStream
        File pngFile = new File("./src/main/resources/download/chuchai.png");
        File bpmnFile = new File("./src/main/resources/download/chuchai.bpmn");
        FileOutputStream pngFileOutputStream = new FileOutputStream(pngFile);
        FileOutputStream bpmnFileOutputStream = new FileOutputStream(bpmnFile);
        IOUtils.copy(pngInput, pngFileOutputStream);
        IOUtils.copy(bpmnInput, bpmnFileOutputStream);
        //  关闭流
        pngInput.close();
        pngFileOutputStream.close();
        bpmnInput.close();
        bpmnFileOutputStream.close();
    }

    /**
     * 查看历史信息
     */
    @Test
    public void getHistoryInfo() {
        // 获取执行引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取历史信息Service
        HistoryService historyService = processEngine.getHistoryService();
        // 获取actinst表的查询对象
        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();
        // 查询actinst表,条件是InstanceId
        // instanceQuery.processInstanceId("2501");
        // 查询actinst表,条件也可以是InstanceId，和条件是InstanceId效果一样
        instanceQuery.processDefinitionId("chuchai:1:4");
        // 增加排序操作，根据开始时间排序，升序
        instanceQuery.orderByHistoricActivityInstanceStartTime().asc();
        // chaxun 查询所有内容
        List<HistoricActivityInstance> list = instanceQuery.list();
        for (HistoricActivityInstance instance : list) {
            System.out.println(instance);
        }

    }

}
