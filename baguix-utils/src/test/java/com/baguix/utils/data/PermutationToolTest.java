package com.baguix.utils.data;

import org.apache.commons.lang3.StringUtils;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/** 
* ValueTool Tester. 
* 
* @author Scott(Su Guang) 
* @version 1.0 
*/

public class PermutationToolTest {

    private static final Logger logger = LoggerFactory.getLogger(PermutationToolTest.class);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    public void test(){
        StringUtils.isEmpty("");
    }

    /**
     * Method: Permutation(java.util.List<Integer> arr, int k)
     */
    @Test
    public void testPermutation(){
        PermutationTool p  = new PermutationTool();
        List<Object[]> list  =  new ArrayList<>();
        List<Object> olist;
        p.Permutation(list, Arrays.asList("A","B","C","D"), 0);
        for (Object[] oa: list){
            olist = Arrays.asList(oa);
            for (Object o :olist){
                String str = String.valueOf(o);
                System.out.print(str);
            }
            System.out.println("\n");
        }
    }

    /**
     * Method: Permutation(java.util.List<Integer> arr, int k)
     */
    @Test
    public void testPermutationStr(){
        PermutationTool p  = new PermutationTool();
        List<String>slist = p.Permutation("A,B,C,D");
        for (String s: slist){
            System.out.println(s);
        }
    }

} 
