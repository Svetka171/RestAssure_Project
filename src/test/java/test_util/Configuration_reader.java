package test_util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration_reader {
    private static Properties properties = new Properties();
    static {

        try{
            FileInputStream file = new FileInputStream("configuration.properties");

            properties.load(file);

            file.close();
        }catch(IOException e ){
            e.printStackTrace();
        }
    }
    public static String getProperties(String keyWord){
        return properties.getProperty(keyWord);
    }
}
