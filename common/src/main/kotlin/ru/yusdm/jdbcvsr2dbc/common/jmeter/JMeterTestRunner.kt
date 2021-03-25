package ru.yusdm.jdbcvsr2dbc.common.jmeter

import org.apache.jmeter.config.Arguments
import org.apache.jmeter.config.gui.ArgumentsPanel
import org.apache.jmeter.control.LoopController
import org.apache.jmeter.control.gui.LoopControlPanel
import org.apache.jmeter.control.gui.TestPlanGui
import org.apache.jmeter.engine.StandardJMeterEngine
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

}