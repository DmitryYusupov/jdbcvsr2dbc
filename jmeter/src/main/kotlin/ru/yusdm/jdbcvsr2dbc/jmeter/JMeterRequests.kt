package ru.yusdm.jdbcvsr2dbc.jmeter

import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy
import org.apache.jmeter.testelement.TestElement

sealed class JmeterHttpRequest {

}

data class JMeterGetRequest(
    val host: String,
    val path: String,
    val port: Int,
    val numThreads: Int,
    val name: String = "$host/$path/test",
    val params: Map<String, String>? = null

)

internal fun JMeterGetRequest.toHTTPSamplerProxy(): HTTPSamplerProxy {
    return HTTPSamplerProxy().apply {
        domain = this@toHTTPSamplerProxy.host
        port = this@toHTTPSamplerProxy.port
        path = this@toHTTPSamplerProxy.path
        method = "GET"
        name = this@toHTTPSamplerProxy.name

        params?.let {
            it.forEach { argName, argValue ->
                addArgument(argName, argValue)
            }
        }

        setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy::class.java.name)
        setProperty(TestElement.GUI_CLASS, HttpTestSampleGui::class.java.name)
    }

}
