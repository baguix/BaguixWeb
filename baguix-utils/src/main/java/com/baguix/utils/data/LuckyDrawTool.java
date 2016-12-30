package com.baguix.utils.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <b>抽奖（抽签）工具</b><br>
 * @author Scott(SG)
 */
public class LuckyDrawTool<T> {
    private Class<T> EntityClass;

    /**
     * 按中奖率计算出应该抽出几个
     * @param total 总数
     * @param rate  抽取比率
     * @return 人数
     */
    public int luckerNumber(long total, float rate){
        int result = 0;
        result = (int) (total*rate);
        return result;
    }

    /**
     * 从集合里抽出num个中奖者
     * @param list 列表
     * @param idname id名
     * @param num 人数
     * @return
     */
    public Map getLucky(List list, String idname,  int num){
        if(num>list.size()){
            num=list.size();
        }
        Map<String, T> result = new HashMap<String, T>();
        int i = 0;
        while(result.size()<num){
            Random rd = new Random();
            int pos = rd.nextInt(list.size());
            T u = (T) list.get(pos);
            try {
                Method method = u.getClass().getMethod("get"+ValueTool.captureName(idname));
                String id = method.invoke(u).toString();
                result.put(id, u);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
            list.remove(pos);
        }
        return result;
    }
}
