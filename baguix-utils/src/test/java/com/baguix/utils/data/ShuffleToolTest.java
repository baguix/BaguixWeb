package com.baguix.utils.data;

import com.baguix.test.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/** 
* ShuffleTool Tester.
* 
* @author Scott(Su Guang) 
* @version 1.0 
*/

public class ShuffleToolTest {

    private static final Logger logger = LoggerFactory.getLogger(ShuffleToolTest.class);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: sufInt(int[] in)
     */
    @Test
    public void testShuffleInt(){
        ShuffleTool s = new ShuffleTool();
        int[] ri = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int[] a = s.sufInt(ri);
        logger.info("0,1,2,3,4,5,6,7,8,9");
        String str = Converter.intArray2Str(a);
        logger.info(str);
    }

    /**
     * Method: sufStr(String s)
     */
    @Test
    public void testShuffleStr(){
        ShuffleTool s = new ShuffleTool();
        String str = "abcdefghijklmnopqrstuvwxyz";
        String result = s.sufStr(str);
        logger.info(str);
        logger.info(result);
    }

    /**
     * Method: sufList(List<T> list)
     */
    @Test
    public void testShuffleList(){
        List<User> users = new ArrayList<User>();
        init(users, 10);
        ShuffleTool<User> s = new ShuffleTool<User>();
        List<User> ulist = s.sufList(users);
        print(ulist);
    }

    // 初始化10个User
    private static void init(List users, int num) {
        for (int i = 0; i < num; i++) {
            User u = new User();
            u.setId("" + i);
            u.setName("用户" + i);
            users.add(u);
        }
    }

    // 打印
    private static void print(List<User> users) {
        for(User u: users){
            System.out.println(u.toString());
        }
    }
} 
