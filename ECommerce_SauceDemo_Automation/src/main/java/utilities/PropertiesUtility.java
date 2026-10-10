package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

    private Properties prop;

    public PropertiesUtility() throws IOException {

        FileInputStream fis = new FileInputStream("src/test/resources/projectData/config.properties");
        prop = new Properties();
        prop.load(fis);
    }
    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
