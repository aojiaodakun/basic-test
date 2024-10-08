package com.hzk.java.jmx.connector;

import com.hzk.java.jmx.mbean.standard.HelloService;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

/**
 * https://blog.csdn.net/wzngzaixiaomantou/article/details/128274953
 */
public class RmiServerTest {

    public static void main(String[] args) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("com.hzk:type=hello");
        //create mbean and register mbean
        server.registerMBean(new HelloService(), helloName);

        //这个步骤很重要，注册一个端口，绑定url后用于客户端通过rmi方式连接JMXConnectorServer
        LocateRegistry.createRegistry(9999);
        //URL路径的结尾可以随意指定，但如果需要用Jconsole来进行连接，则必须使用jmxrmi
        JMXServiceURL url = new JMXServiceURL
                ("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
        System.out.println("begin rmi start");
        jcs.start();
        System.out.println("rmi start");
    }

}
