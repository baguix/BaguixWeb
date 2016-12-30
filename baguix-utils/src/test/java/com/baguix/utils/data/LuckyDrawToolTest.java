package com.baguix.utils.data;

import com.baguix.test.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/** 
* LuckyDrawTool Tester.
* 
* @author Scott(Su Guang) 
* @version 1.0 
*/

public class LuckyDrawToolTest {

    private static final Logger logger = LoggerFactory.getLogger(LuckyDrawToolTest.class);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getLucky(String exp, Object[] values)
     */
    @Test
    public void testGetLucky(){
        // 抽奖程序
        List<User> users = new ArrayList<User>();
        // 初始化1000个用户
        init(users, 1000);
        Random rd = new Random();
        LuckyDrawTool ld = new LuckyDrawTool();
        // 一等奖2人
        // 二等奖2%
        int edj = ld.luckerNumber(users.size(), 0.02f);
        Map luck1 = ld.getLucky(users, "id", 2);
        print(luck1);
        System.out.println(edj + "人");
        // 将幸运用户打印出来
        Map luck2 = ld.getLucky(users, "id", edj);
        print(luck2);
    }

    // 初始化
    private static void init(List users, int num) {
        for (int i = 0; i < num; i++) {
            User u = new User();
            u.setId("" + i);
            u.setName("用户" + i);
            users.add(u);
        }
    }
    // 打印
    private static void print(Map map) {
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, User> entry = (Map.Entry<String, User>) iter.next();
            System.out.println(entry.toString());
        }
    }
} 
