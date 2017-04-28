package costumetrade.common.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.PropertyPlaceholderHelper;



/**
 * properties工具类 支持占位符替换
 * 
 * @author dante
 * @Date 2015年11月11日
 */
public class PropertiesUtils {

    private static final Log logger = LogFactory.getLog(PropertiesUtils.class);

    /** Default placeholder prefix: {@value} */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /** Default placeholder suffix: {@value} */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private static final PropertyPlaceholderHelper helper =
            new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX);

    /**
     * 读取类路径下下面的配置文件 传入的地址为相对类路径的地址 比如classpath下面的config存放配置文件，就传值"config/"
     * 
     * @author dante
     * @Date 2015年11月11日
     * @param pathRelativeToClasspath
     * @return
     */
    public static Properties getProperties(String pathRelativeToClasspath) {
        Properties props = new Properties();
        URL url = Thread.currentThread().getContextClassLoader().getResource(pathRelativeToClasspath);
        File dir;
        try {
            dir = new File(url.toURI());
        } catch (URISyntaxException e) {
            logger.error("配置文件路径有问题:" + url, e);
            return props;
        }
        return getProperties(dir);
    }

    /**
     * 读取配置文件 如果是目录读取目录下面的properties文件 如果是文件直接读取文件
     * 
     * @param file
     * @return
     */
    public static Properties getProperties(File file) {

        File[] propertiesFiles = listPropertiesFile(file);

        Properties props = loadProperties(propertiesFiles);

        replacePlaceHolder(props);

        return props;

    }


    /**
     * 占位符替换
     * 
     * @param props
     */
    public static void replacePlaceHolder(Properties props) {

        if (props == null || props.isEmpty()) {
            return;
        }

        Enumeration<?> propertiesNames = props.propertyNames();

        while (propertiesNames.hasMoreElements()) {

            String key = (String) propertiesNames.nextElement();
            String value = props.getProperty(key);
            String replacedVal = helper.replacePlaceholders(value, props);

            props.setProperty(key, StringUtils.trim(replacedVal));
        }
    }


    /**
     * @param propertiesFiles
     * @return Properties
     */
    private static Properties loadProperties(File[] propertiesFiles) {
        Properties props = new Properties();
        if (propertiesFiles != null && propertiesFiles.length > 0) {
            for (File file : propertiesFiles) {
                InputStream is = null;
                Reader reader = null;
                try {
                    is = new FileInputStream(file);
                    reader = new InputStreamReader(is, "UTF-8");
                    props.load(reader);
                } catch (IOException e) {
                    logger.error("读取配置文件失败:" + file.getAbsolutePath(), e);
                } finally {
                    IOUtils.closeQuietly(reader);
                    IOUtils.closeQuietly(is);
                }
            }
        }

        return props;

    }

    /**
     * @return
     */
    private static File[] listPropertiesFile(File file) {

        if (file.isDirectory()) {
            return file.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {

                    return pathname.getName().endsWith(".properties");
                }
            });
        }

        File[] files = new File[1];
        files[0] = file;

        return files;
    }


    public static Properties getClasspathProperties(Class<?> clazz,String filename) {
        Properties properties = new Properties();
        InputStream inputStream = null;
            try {
                inputStream = clazz.getClassLoader().getResourceAsStream(filename);
                if(inputStream == null){
                    return properties;
                }
                properties.load(inputStream);
            } catch (IOException e) {
                logger.error("读取文件失败", e);
            }finally {
                IOUtils.closeQuietly(inputStream);
            }
        return properties;

    }

}
