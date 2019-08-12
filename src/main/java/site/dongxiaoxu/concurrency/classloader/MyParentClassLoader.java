package site.dongxiaoxu.concurrency.classloader;

import java.io.*;
import java.text.MessageFormat;

/**
 * Created by dongxiaoxu on 2019/8/11.
 */
public class MyParentClassLoader extends ClassLoader {

    private final String CURRENT_PROJECT_PATH = System.getProperty("user.dir");

    private final String CLASS_PATH = CURRENT_PROJECT_PATH + "\\classpath\\";

    private final String CLASS_FILE_SUFFIX = ".class";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        if (name.startsWith("java")) {
            super.findClass(name);
        }
        String classFilePath = CLASS_PATH + name.replace(".", "\\") + CLASS_FILE_SUFFIX;
        byte[] bufferData;
        try(InputStream inputStream = new FileInputStream(classFilePath);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[2048];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            bufferData = outputStream.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException(MessageFormat.format("目录{0}中不存在类文件{1}！", CLASS_PATH, name));
        }
        return this.defineClass(name, bufferData, 0 ,bufferData.length);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.startsWith("java")) {
            super.loadClass(name);
        }
        return this.findClass(name);
    }
}
