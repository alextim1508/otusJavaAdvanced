package ru.otus.timofeev.task8;


import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.ListedHashTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Main.runPT();
    }

    private static void runPT() throws IOException {
        initJMeter();

        var jmeter = new StandardJMeterEngine();
        var threadGroup = getThreadGroup();
        var testPlan = getTestPlan(threadGroup);

        var testPlanTree = new ListedHashTree();
        var threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);
        var sampler = getHttpSamplerProxy("""
                         {
                             "name" : "${__RandomString(10, abcdefghijklmnopqrstuvwxyz,)}",
                             "email" : "${__RandomString(10, abcdefghijklmnopqrstuvwxyz,)}@gmail.com",
                             "password" : "${__RandomString(10, abcdefghijklmnopqrstuvwxyz,)}"
                        }
        """);
        threadGroupHashTree.add(sampler);

        saveTree(testPlanTree);

        ResultCollector logger = getResultLogger();

        testPlanTree.add(testPlanTree.getArray()[0], logger);

        jmeter.configure(testPlanTree);

        jmeter.run();
    }

    private static void initJMeter() {
        var jmeterHome = System.getenv("JMETER_HOME");
        if (jmeterHome == null) {
            throw new RuntimeException("JMETER_HOME environment variable is not set.");
        }
        JMeterUtils.loadJMeterProperties(jmeterHome + "\\bin\\jmeter.properties");
        JMeterUtils.setJMeterHome(jmeterHome);
        JMeterUtils.initLocale();
    }

    private static ThreadGroup getThreadGroup() {
        var loopController = new LoopController();
        loopController.setLoops(1);
        loopController.setFirst(true);
        loopController.setProperty(TestElement.TEST_CLASS, LoopController.class.getName());
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel.class.getName());
        loopController.initialize();

        var threadGroup = new ThreadGroup();
        threadGroup.setName("User Creating Service");
        threadGroup.setNumThreads(5000);
        threadGroup.setRampUp(10);
        threadGroup.setSamplerController(loopController);
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui.class.getName());
        return threadGroup;
    }

    private static TestPlan getTestPlan(ThreadGroup threadGroup) {
        var testPlan = new TestPlan("User Creating Test Plan");
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui.class.getName());
        testPlan.addThreadGroup(threadGroup);
        return testPlan;
    }

    private static HTTPSamplerProxy getHttpSamplerProxy(String body) {
        var httpSampler = new HTTPSamplerProxy();
        httpSampler.setName("hash");
        httpSampler.setDomain("localhost");
        httpSampler.setPort(8080);
        httpSampler.setPath("/user");
        httpSampler.setMethod("GET");
        httpSampler.setFollowRedirects(true);
        httpSampler.setUseKeepAlive(true);
        httpSampler.setPostBodyRaw(true);
        httpSampler.addNonEncodedArgument("", body, "");
        httpSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
        httpSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui.class.getName());
        return httpSampler;
    }

    private static void saveTree(ListedHashTree testPlanTree) throws IOException {
        SaveService.saveTree(testPlanTree, Files.newOutputStream(Paths.get("./task8JMeter/jmeter/script.jmx")));
    }

    private static ResultCollector getResultLogger() {
        Summariser summer = null;
        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
        if (summariserName.length() > 0) {
            summer = new Summariser(summariserName);
        }
        var logger = new ResultCollector(summer);
        logger.setFilename("./task8JMeter/jmeter/pt-logs.jtl");
        return logger;
    }
}