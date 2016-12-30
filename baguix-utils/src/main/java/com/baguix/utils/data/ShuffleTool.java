package com.baguix.utils.data;

import java.util.List;
import java.util.Random;

/**
 * <b>洗牌工具</b><br>
 * @author Scott(SG)
 */
public class ShuffleTool<T> {

    public String sufStr (String str) {
        char[] c = str.toCharArray();
        Random rd = new Random();
        int l = c.length;
        if (l > 100) {
            l = 100;
        }
        for (int i = 0; i < l; i++) {
            int j = rd.nextInt(l);
            char temp = c[i];
            c[i] = c[j];
            c[j] = temp;
        }
        return String.copyValueOf(c);
    }

    public int[] sufInt(int[] in) {
        Random rd = new Random();
        int l = in.length;
        if (l > 100) {
            l = 100;
        }
        for (int i = 0; i < l; i++) {
            int j = rd.nextInt(l);
            int temp = in[i];
            in[i] = in[j];
            in[j] = temp;
        }
        return in;
    }

    public List<T> sufList(List<T> list) {
        Random rd = new Random();
        int l = list.size();
        if (l > 100) {
            l = 100;
        }
        for (int i = 0; i < l; i++) {
            int j = rd.nextInt(l);
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
        return list;
    }
}
