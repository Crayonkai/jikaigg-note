package com.jikaigg.note.activiti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

public class CreateActivitiTable {

    /**
     * 两种方式创建mysql的表
     */
    public static void main(String[] args) {
        // 默认方式
        // getDefaultProcessEngine默认从resouces文件夹下读取activiti.cfg.xml文件
        // 创建ProcessEnging，创建MySQL表
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 自定义方式
        // createProcessEngineConfigurationFromResource中参数可自定义，不用默认方式中的固定命名
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");
        ProcessEngine buildProcessEngine = processEngineConfiguration.buildProcessEngine();

        System.out.println(processEngine);
        System.out.println(buildProcessEngine);

    }
}
