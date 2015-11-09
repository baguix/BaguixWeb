package com.baguix.utils.doc;

import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * PropertiesTool Tester.
 *
 * @author Scott
 */
public class PropertiesToolTest {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesToolTest.class);
    private String filepath = "";
    private String temppath = "";

    @Before
    public void before() throws Exception {
        filepath = PathTool.getClassesPath() + "/files/test.properties";
        File f = new File(filepath);
        if (!f.exists()) {
            String content = FileManager.readStrFromFile(filepath,"UTF-8");
            if(content!=null && !"".equals(content)) {
                FileManager.newTextFile(filepath, "");
            }
            else{
                FileManager.newTextFile(filepath, "server=127.0.0.1\ncopyright=baguix\\u5DE5\\u4F5C\\u5BA4");
            }
        }
        temppath = PathTool.getClassesPath() + "/files/test.ftl";
        File tempf = new File(temppath);
        if (!tempf.exists()) {
            FileManager.newTextFile(temppath, "#测试模版\nserver=${server}\ncopyright=${copyright}");
        }
    }

    @After
    public void after() throws Exception {
        FileManager.cleanFolder(PathTool.getClassesPath()+"/files");
    }

    /**
     * Method: mapFile(String file)
     */
    @Test
    public void testMapFile() throws Exception {
        PropertiesTool pt = new PropertiesTool();
        Map<String, String> mapin = pt.mapFile(filepath);
        Map<String, String> map = new HashMap<>();
        map.put("server", "127.0.0.1");
        map.put("copyright", "baguix工作室");
        assertEquals(map, mapin);
    }
    /**
     * Method: mapFile(Map map, String file)
     */
    @Test
    public void testMapFileMap() throws Exception {

        // 不存在的文件，返回192.168.0.1
        Map<String, String> map = new HashMap<>();
        map.put("server", "192.168.0.1");
        map.put("copyright", "baguix.com");
        PropertiesTool pt = new PropertiesTool();
        Map<String, String> mapin = pt.mapFile(map, PathTool.getClassesPath() + "/files/test1.properties");
        assertEquals(map.get("server"), mapin.get("server"));
        assertEquals(map.get("copyright"), mapin.get("copyright"));

        Map<String, String> map1 = new HashMap<>();
        map1.put("server", "127.0.0.1");
        map1.put("copyright", "baguix工作室");
        Map<String, String> mapin1 = pt.mapFile(map, PathTool.getClassesPath() + "/files/test.properties");
        assertEquals(map1.get("server"), mapin1.get("server"));
        assertEquals(map1.get("copyright"), mapin1.get("copyright"));
    }
    /**
     * Method: map2File(String file, Map<String,String> map)
     */
    @Test
    public void testMap2File() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("server", "127.0.0.1");
        map.put("copyright", "baguix工作室");
        PropertiesTool pt = new PropertiesTool();
        pt.map2File(filepath, map, "测试文件");
    }

    /**
     * Method: map2FileByFreemarker(String file, Map<String,String> map)
     */
    @Test
    public void testMap2FileByFreemarker() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("server", "127.0.0.1");
        map.put("copyright", "baguix工作室");
        PropertiesTool pt = new PropertiesTool();
        pt.map2FileByFreemarker(filepath, temppath, map);
    }
}
