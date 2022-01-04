package com.mycompany.tomcatnativeimagetest.util;

import java.io.File;
import java.nio.file.Files;

public class FileUtil {

    {
        System.out.println("FileUtil lone block");
    }

    static {
        System.out.println("FileUtil static block");
    }

    public FileUtil() {
        super();
        System.out.println("FileUtil constructor.");
    }

    public static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (!Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }

}
