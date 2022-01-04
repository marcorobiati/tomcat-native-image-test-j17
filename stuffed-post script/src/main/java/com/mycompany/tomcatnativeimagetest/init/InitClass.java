package com.mycompany.tomcatnativeimagetest.init;

import com.mycompany.tomcatnativeimagetest.util.FileUtil;
import java.io.File;
import java.nio.file.Files;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitClass implements ServletContextListener {

    public static int aNumberStatic = 111;
    public int aNumber = 111;
    public static InitClass ICReference;

    {
        System.out.println("InitClass lone block");
    }

    static {
        System.out.println("InitClass static block");
    }

    public InitClass() {
        super();
        System.out.println("InitClass constructor.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        String contextPath = arg0.getServletContext().getContextPath();

        File appxml = new File(System.getProperty("catalina.base") + "\\conf\\Catalina\\localhost\\" + contextPath + ".xml");
        if (appxml.exists()) {
            System.out.println("Deleting file " + appxml);
            appxml.delete();
        } else {
            System.out.println(appxml + "not found.");
        }

        File m2 = new File(System.getProperty("user.home") + "\\.m2\\repository\\com\\mycompany\\" + contextPath);
        if (m2.exists()) {
            System.out.println("Deleting .m2 " + contextPath + " directory.");
            FileUtil.deleteDir(m2);
        } else {
            System.out.println("m2 " + contextPath + " directory not found.");
        }

        File appCache = new File(System.getProperty("catalina.base") + "\\work\\Catalina\\localhost\\" + contextPath);
        if (appCache.exists()) {
            System.out.println("Deleting app cache.");
            FileUtil.deleteDir(appCache);
        } else {
            System.out.println("app cache non trovata.");
        }

        File tomcatLogs = new File(System.getProperty("catalina.base") + "\\logs");
        if (tomcatLogs.exists()) {
            System.out.println("Deleting Tomcat logs.");
            File[] contents = tomcatLogs.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    if (!Files.isSymbolicLink(f.toPath())) {
                        f.delete();
                    }
                }
            }
        } else {
            System.out.println("Tomcat logs not found.");
        }

        System.out.println("contextDestroyed -------------------------------------------------------------------");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        aNumberStatic = 222;
        aNumber = 222;
        System.out.println("aNumberStatic set to: " + aNumberStatic);
        System.out.println("aNumber set to: " + aNumber);

        ICReference = this;
        System.out.println("I'm object: " + this);
        System.out.println("contextInitialized -------------------------------------------------------------------");
    }

}
