package getresource;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ResourceTest {
    public static void main(String[] args) throws IOException {
        String pkgName = "getresource";
        testGetResource(pkgName);
        testGetResources(pkgName);
    }

    public static void testGetResource(String pkgName) throws IOException {
        ClassLoader classLoader = ResourceTest.class.getClassLoader();
        URL url;
        if (classLoader != null) {
            url = classLoader.getResource(pkgName.replace(".", "/"));
        } else {
            url = ClassLoader.getSystemResource(pkgName.replace(".", "/"));
        }
        System.out.println(url);
    }

    public static void testGetResources(String pkgName) throws IOException {
        Enumeration<URL> enumeration;
        ClassLoader classLoader = ResourceTest.class.getClassLoader();
        if (classLoader != null) {
            enumeration = classLoader.getResources(pkgName.replace(".", "/"));
        } else {
            enumeration = ClassLoader.getSystemResources(pkgName.replace(".", "/"));
        }
        URL url;
        while (enumeration.hasMoreElements()) {
            url = enumeration.nextElement();
            System.out.println(url);
        }
    }
}
