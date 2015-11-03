package com.baguix.utils.media;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FontTool {
	public static void main(String[] args) {
		// 在内存中创建图象
		BufferedImage image = new BufferedImage(500, 500,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 设定背景色
		g.setColor(new Color(0x0066CC));
		g.fillRect(0, 0, 500, 500);
		// 设定文字颜色
		g.setColor(new Color(0xffffff));
		String text = "张三";
		Font font = getFont("c:\\1.TTF");
		Font newf  = font.deriveFont(Font.PLAIN, 50);
		g.setFont(newf);
		Font fff = new Font("宋体",Font.PLAIN,14);
		
		g.drawString(text, 250, 250);
		g.dispose();
		
		File f = new File("c:\\test.jpg");
		try {
			ImageIO.write(image, "jpg", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从path导入一个Font
	 * 
	 * @param path
	 *            如："/font/xxx.ttf"
	 * @return
	 */
	public static Font getFont(String path) {
		try {
			File font_file = new File(path);
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, font_file);
			return dynamicFont;
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
