package ru.yusdm.jdbcvsr2dbc.common.jmeter

import org.apache.jmeter.config.Arguments
import org.apache.jmeter.config.gui.ArgumentsPanel
import org.apache.jmeter.control.LoopController
import org.apache.jmeter.control.gui.LoopControlPanel
import org.apache.jmeter.control.gui.TestPlanGui
import org.apache.jmeter.engine.StandardJMeterEngine
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy
import org.apache.jmeter.testelement.TestElement
import org.apache.jmeter.testelement.TestPlan
import org.apache.jmeter.threads.ThreadGroup
import org.apache.jmeter.threads.gui.ThreadGroupGui
import org.apache.jmeter.util.JMeterUtils
import org.apache.jorphan.collections.HashTree
import ru.yusdm.jdbcvsr2dbc.common.utils.FileUtils


object JMeterTestRunner {

    private const val JMETER_PROPERTIES = "/jmeter/configs/jmeter.properties"

    init {
        val jMeterProps = FileUtils.createFileFromResource(
            fileNamePrefix = "jmeter",
            fileNameSuffix = "jdbc_r2dbc",
            resourcePath = JMETER_PROPERTIES
        )
        JMeterUtils.loadJMeterProperties(jMeterProps.absolutePath)
    }

    fun runSuiteForGetRequest(getRequest: JMeterGetRequest) {

        val loopController = LoopController().apply {
            loops = 1
            setFirst(true)
            setProperty(TestElement.TEST_CLASS, LoopController::class.java.name)
            setProperty(TestElement.GUI_CLASS, LoopControlPanel::class.java.name)
        }.also {
            it.initialize()
        }

        val threadGroup = ThreadGroup().apply {
            name = getRequest.name + " ThreadGroup"
            numThreads = getRequest.numThreads
            rampUp = 1
            setSamplerController(loopController)
            setProperty(TestElement.TEST_CLASS, ThreadGroup::class.java.name)
            setProperty(TestElement.GUI_CLASS, ThreadGroupGui::class.java.name)
        }

        val testPlan = TestPlan(getRequest.name + " TestPlan").apply {
            setProperty(TestElement.TEST_CLASS, TestPlan::class.java.name)
            setProperty(TestElement.GUI_CLASS, TestPlanGui::class.java.name)
            setUserDefinedVariables(ArgumentsPanel().createTestElement() as Arguments)
        }

        val testPlanTree = HashTree().apply {
            this.add(testPlan)
            val threadGroupHashTree = this.add(testPlan, threadGroup)
            threadGroupHashTree.add(getRequest.toHTTPSamplerProxy())
        }

        val jmeter = StandardJMeterEngine()
        jmeter.configure(testPlanTree)
        jmeter.run()
    }

    fun tt2() {

        //JMeter Engine
        val jmeter = StandardJMeterEngine()
        JMeterUtils.loadJMeterProperties("/Users/dyusupov/Work/custom/new/jdbcvsr2dbc/common/src/main/resources/jmeter/configs/jmeter.properties");
        // JMeter Test Plan, basically JOrphan HashTree

        // JMeter Test Plan, basically JOrphan HashTree
        val testPlanTree = HashTree()

        // First HTTP Sampler - open example.com

        // First HTTP Sampler - open example.com
        val examplecomSampler = HTTPSamplerProxy()
        examplecomSampler.domain = "localhost"
        examplecomSampler.port = 8080
        examplecomSampler.path = "api/countries/1"
        examplecomSampler.method = "GET"
        examplecomSampler.name = "Open example.com"
        examplecomSampler.addArgument("fetch_cities", "true");
        examplecomSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy::class.java.name)
        examplecomSampler.setProperty(TestElement.GUI_CLASS, HttpTestSampleGui::class.java.name)


        // Loop Controller
        val loopController = LoopController()
        loopController.loops = 1
        loopController.setFirst(true)
        loopController.setProperty(TestElement.TEST_CLASS, LoopController::class.java.name)
        loopController.setProperty(TestElement.GUI_CLASS, LoopControlPanel::class.java.name)
        loopController.initialize()

        // Thread Group

        // Thread Group
        val threadGroup = ThreadGroup()
        threadGroup.name = "Example Thread Group"
        threadGroup.numThreads = 1
        threadGroup.rampUp = 1
        threadGroup.setSamplerController(loopController)
        threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup::class.java.name)
        threadGroup.setProperty(TestElement.GUI_CLASS, ThreadGroupGui::class.java.name)

        // Test Plan

        // Test Plan
        val testPlan = TestPlan("Create JMeter Script From Java Code")
        testPlan.setProperty(TestElement.TEST_CLASS, TestPlan::class.java.name)
        testPlan.setProperty(TestElement.GUI_CLASS, TestPlanGui::class.java.name)
        testPlan.setUserDefinedVariables(ArgumentsPanel().createTestElement() as Arguments)

        // Construct Test Plan from previously initialized elements

        // Construct Test Plan from previously initialized elements
        testPlanTree.add(testPlan)
        val threadGroupHashTree = testPlanTree.add(testPlan, threadGroup)
        threadGroupHashTree.add(examplecomSampler)


        // Store execution results into a .jtl file


        // Run Test Plan

        // Run Test Plan
        jmeter.configure(testPlanTree)
        jmeter.run()
    }
}