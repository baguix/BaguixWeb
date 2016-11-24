package com.baguix.web.common.kaptcha;

import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DotNoice extends Configurable implements NoiseProducer {

    public void makeNoise(BufferedImage image, float factorOne, float factorTwo, float factorThree, float factorFour) {
        // 读取线条颜色配置
        Color color = this.getConfig().getNoiseColor();
        // 在图片中绘制内容
        Graphics g = image.getGraphics();
        int width = image.getWidth();
        int height = image.getHeight();
        Random random = new Random();
        // 随机生成线条，让图片看起来更加杂乱

        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(width - 1);// 起点的x坐标
            int y = random.nextInt(height - 1);// 起点的y坐标
            int x1 = random.nextInt(6) + 1;// x轴偏移量
            int y1 = random.nextInt(12) + 1;// y轴偏移量
            g.setColor(getRandColor());
            g.drawLine(x, y, x + x1, y + y1);
        }
        // 随机生成线条，让图片看起来更加杂乱
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(height - 1);
            int x1 = random.nextInt(12) + 1;
            int y1 = random.nextInt(6) + 1;
            g.setColor(getRandColor());
            g.drawLine(x, y, x - x1, y - y1);
        }
    }

    // 生成随机颜色
    Color getRandColor() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }
}