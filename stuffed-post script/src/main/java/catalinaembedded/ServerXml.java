package catalinaembedded;
public class ServerXml implements org.apache.catalina.startup.Catalina.ServerXml {
public void load(org.apache.catalina.startup.Catalina tc_Catalina_0) {


org.apache.catalina.core.StandardServer tc_StandardServer_1 = new org.apache.catalina.core.StandardServer();
tc_StandardServer_1.setPort(Integer.valueOf("8005"));
tc_StandardServer_1.setShutdown("SHUTDOWN");


org.apache.catalina.deploy.NamingResourcesImpl tc_NamingResourcesImpl_2 = new org.apache.catalina.deploy.NamingResourcesImpl();


org.apache.tomcat.util.descriptor.web.ContextResource tc_ContextResource_3 = new org.apache.tomcat.util.descriptor.web.ContextResource();
tc_ContextResource_3.setName("UserDatabase");
tc_ContextResource_3.setAuth("Container");
tc_ContextResource_3.setType("org.apache.catalina.UserDatabase");
tc_ContextResource_3.setDescription("User database that can be updated and saved");
tc_ContextResource_3.setProperty("factory", "org.apache.catalina.users.MemoryUserDatabaseFactory");
tc_ContextResource_3.setProperty("pathname", "conf/tomcat-users.xml");
tc_NamingResourcesImpl_2.addResource(tc_ContextResource_3);
tc_StandardServer_1.setGlobalNamingResources(tc_NamingResourcesImpl_2);


org.apache.catalina.core.StandardService tc_StandardService_4 = new org.apache.catalina.core.StandardService();
tc_StandardService_4.setName("Catalina");

org.apache.catalina.connector.Connector tc_Connector_5 = new org.apache.catalina.connector.Connector(new org.apache.coyote.http11.Http11NioProtocol());
tc_Connector_5.setPort(Integer.valueOf("8080"));
tc_Connector_5.setProperty("connectionTimeout", "20000");
tc_Connector_5.setRedirectPort(Integer.valueOf("8443"));
tc_Connector_5.setPortOffset(tc_StandardServer_1.getPortOffset());
tc_StandardService_4.addConnector(tc_Connector_5);


org.apache.catalina.core.StandardEngine tc_StandardEngine_6 = new org.apache.catalina.core.StandardEngine();
tc_StandardEngine_6.setName("Catalina");
tc_StandardEngine_6.setDefaultHost("localhost");
tc_StandardEngine_6.addLifecycleListener(new org.apache.catalina.startup.EngineConfig());
tc_StandardEngine_6.setParentClassLoader(tc_Catalina_0.getParentClassLoader());


org.apache.catalina.realm.LockOutRealm tc_LockOutRealm_7 = new org.apache.catalina.realm.LockOutRealm();


org.apache.catalina.realm.UserDatabaseRealm tc_UserDatabaseRealm_8 = new org.apache.catalina.realm.UserDatabaseRealm();
tc_UserDatabaseRealm_8.setResourceName("UserDatabase");
tc_LockOutRealm_7.addRealm(tc_UserDatabaseRealm_8);
tc_StandardEngine_6.setRealm(tc_LockOutRealm_7);


org.apache.catalina.core.StandardHost tc_StandardHost_9 = new org.apache.catalina.core.StandardHost();
tc_StandardHost_9.setName("localhost");
tc_StandardHost_9.setAppBase("webapps");
tc_StandardHost_9.setUnpackWARs(Boolean.valueOf("true"));
tc_StandardHost_9.setAutoDeploy(Boolean.valueOf("true"));
tc_StandardHost_9.setParentClassLoader(tc_StandardEngine_6.getParentClassLoader());
tc_StandardHost_9.addLifecycleListener(new org.apache.catalina.startup.HostConfig());


org.apache.catalina.valves.AccessLogValve tc_AccessLogValve_10 = new org.apache.catalina.valves.AccessLogValve();
tc_AccessLogValve_10.setDirectory("logs");
tc_AccessLogValve_10.setPrefix("localhost_access_log");
tc_AccessLogValve_10.setSuffix(".txt");
tc_AccessLogValve_10.setPattern("%h %l %u %t \"%r\" %s %b");
tc_StandardHost_9.addValve(tc_AccessLogValve_10);
tc_StandardEngine_6.addChild(tc_StandardHost_9);
tc_StandardService_4.setContainer(tc_StandardEngine_6);
tc_StandardServer_1.addService(tc_StandardService_4);
tc_Catalina_0.setServer(tc_StandardServer_1);
}
}
