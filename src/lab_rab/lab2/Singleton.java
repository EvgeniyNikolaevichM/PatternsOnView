package lab_rab.lab2;

import java.io.*;
import java.util.Properties;

public class Singleton {
    private static volatile Singleton instance;
    static Properties properties;
    public static String email = null;
    public static String path = null;
    private Singleton() {
        try {
            InputStream in = new FileInputStream(new File("config.properties"));
            properties = new Properties();
            properties.load(in);
            email = properties.getProperty("EMAIL");
            path = properties.getProperty("PATH");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static void main (String[] args) throws IOException {
        Singleton propertyReader = Singleton.getInstance();
        String configuration = "Адрес электронной почты "+ email +"\n"+"Путь к файлу "+ path;
        System.out.println(configuration);
    }
}
