package com.baguix.utils.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 全排列
 * Created by Scott on 2016/5/1.
 */
public class PermutationTool {
    public void Permutation(List<Object[]> list, List<String> arr, int k){
        for(int i = k; i < arr.size(); i++){
            Collections.swap(arr, i, k);
            Permutation(list, arr, k+1);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            list.add(arr.toArray());
        }
    }

    /**
     * 全排列
     * @param arr 例如：“a,b,c,d”
     * @return 排列字符串列表
     */
    public List<String> Permutation(String arr){
        List<String> result  =  new ArrayList<>();

        List<Object[]> list  =  new ArrayList<>();
        Permutation(list,  Arrays.asList(arr.split(",")), 0);

        for (Object[] oa: list){
            String s = Arrays.toString(oa);
            String str = s.substring(1,s.length()-1);
            result.add(str);
        }

        return result;
    }
}
