package com.baguix.utils.media;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.MogrifyCmd;

/**
* ImageMagick工具
* @author Scott
 */

public class Im4JavaTool {
	
	/* 
	  调试时可以用这些命令查看ImageMagick命令
	 op.getCommand();
	 composite.getCommand();
	*/ 
	 
	public static String imageMagickPath = null;
	
	public Im4JavaTool(String ImageMagickPath) {
		Im4JavaTool.imageMagickPath = ImageMagickPath;
	}

	/**
	 * 获得图片的尺寸
	 * @param imagePath 图片的路径
	 * @return int数组，int[0]宽度，int[1]高度，单位px
	 */
	public int[] getWidthHeight(String imagePath) {
		int[] wh = {0,0};
		try {
			File picture = new File(imagePath);
			BufferedImage sourceImg = ImageIO
					.read(new FileInputStream(picture));
			wh[0] = sourceImg.getWidth();
			wh[1] = sourceImg.getHeight();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wh;
	}
	
	/**
	 * 获得图片的宽度
	 * @param imagePath 图片的路径
	 * @return int类型，宽度，单位px
	 */
	public int getWidth(String imagePath) {
		int w = 0;
		try {
			File picture = new File(imagePath);
			BufferedImage sourceImg = ImageIO
					.read(new FileInputStream(picture));

			w = sourceImg.getWidth();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w;
	}
	
	/**
	 * 获得图片的高度
	 * @param imagePath 图片的路径
	 * @return int类型，高度，单位px
	 */
	public int getHeight(String imagePath) {
		int w = 0;
		try {
			File picture = new File(imagePath);
			BufferedImage sourceImg = ImageIO
					.read(new FileInputStream(picture));

			w = sourceImg.getHeight();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w;
	}
	
	
	/**
	 * 根据坐标裁剪图片
	 * 
	 * @param srcPath
	 *            要裁剪图片的路径
	 * @param newPath
	 *            裁剪图片后的路径
	 * @param x
	 *            起始横坐标
	 * @param y
	 *            起始挫坐标
	 * @param x1
	 *            结束横坐标
	 * @param y1
	 *            结束挫坐标
	 */
	public void cutImage(String srcPath, String newPath, int x, int y,
			int x1, int y1) throws Exception {
		int width = x1 - x;
		int height = y1 - y;
		IMOperation op = new IMOperation();
		op.addImage(srcPath);

		/**
		 * width：裁剪的宽度 height：裁剪的高度 x：裁剪的横坐标 y：裁剪的挫坐标
		 */
		op.crop(width, height, x, y);

		op.addImage(newPath);

		ConvertCmd convert = new ConvertCmd();
		
		// linux下不要设置此值，不然会报错
		String spl = System.getProperty("file.separator");
		if ((spl.equals("\\") || spl == "\\")) {
			convert.setSearchPath(imageMagickPath);
		}
		
		convert.run(op);
	}

	/**
	 * 根据限定尺寸缩放图片
	 * 
	 * 注: 缩到能放入限定尺寸中
	 * 
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @param srcPath
	 *            源图片路径
	 * @param newPath
	 *            缩放后图片的路径
	 */
	public void scaleImage(int width, int height, String srcPath,
			String newPath) throws Exception {
		IMOperation op = new IMOperation();
		op.addImage(srcPath);

		op.resize(width, height);
		op.addImage(newPath);

		ConvertCmd convert = new ConvertCmd();

		// linux下不要设置setSearchPath，不然会报错
		String spl = System.getProperty("file.separator");
		if ((spl.equals("\\") || spl == "\\")) {
			convert.setSearchPath(imageMagickPath);
		}

		convert.run(op);
	}

	/**
	 * 只根据宽度缩放图片
	 * 
	 * @param width
	 *            缩放后的图片宽度
	 * @param srcPath
	 *            源图片路径
	 * @param newPath
	 *            缩放后图片的路径
	 */
	public void cutImage(int width, String srcPath, String newPath)
			throws Exception {
		IMOperation op = new IMOperation();
		op.addImage(srcPath);

		op.resize(width, null);
		op.addImage(newPath);

		ConvertCmd convert = new ConvertCmd();

		// linux下不要设置此值，不然会报错
		String spl = System.getProperty("file.separator");
		if ((spl.equals("\\") || spl == "\\")) {
			convert.setSearchPath(imageMagickPath);
		}

		convert.run(op);
	}

	/**
	 * 给图片加英文水印
	 * convert src.jpg -gravity southeast -fill black -pointsize 16 -draw "text 5,5 'http://www.netingcn.com'" dest-c.jpg
	 * @param srcPath
	 *            源图片路径
	 */
	public void addImgTextEn(String srcPath, String color, int size, String text, String position) throws Exception {
		IMOperation op = new IMOperation();
		// position的值是，左上角：northwest、左下角：southwest、右上角：northeast、右下角：southeast、中间：center。
		op.font("verdana").gravity(position).pointsize(size).fill(color)
				.draw("text 5,5 "+text);
		op.addImage(srcPath);
		op.addImage(srcPath);
		ConvertCmd convert = new ConvertCmd();

		// linux下不要设置此值，不然会报错
		String spl = System.getProperty("file.separator");
		if ((spl.equals("\\") || spl == "\\")) {
			convert.setSearchPath(imageMagickPath);
		}
		convert.run(op);
	}
	
	/**
	 * 给图片加中英文文字水印
	 * mogrify -font msyh.ttf -pointsize 24 -fill black -weight bolder -gravity southeast -annotate +20+20 "您好" src.jpg
	 * @param srcPath
	 *            源图片路径
	 */
	public void addImgTextCn(String srcPath, String color, int size, String text, String position) throws Exception {
		IMOperation op = new IMOperation();
		//设置水印字体，Windows下用到
		String spl = System.getProperty("file.separator");
		if ((spl.equals("\\") || spl == "\\")) {
			op.font(imageMagickPath+"\\1.ttf");
		}
		else{
			op.font(imageMagickPath+"/1.ttf");
		}
		op.pointsize(size);
		op.fill(color);
		// 左上角：northwest、左下角：southwest、右上角：northeast、右下角：southeast、中间：center。
		op.gravity(position);
		op.annotate();
		op.addRawArgs("+5+5");
		op.addRawArgs("\""+text+"\"");
		op.addImage(srcPath);
		MogrifyCmd mogrify = new MogrifyCmd();

		// linux下不要设置此值，不然会报错
		if ((spl.equals("\\") || spl == "\\")) {
			mogrify.setSearchPath(imageMagickPath);
		}
		
		mogrify.run(op);
	}

	/**
	 * 给图片加图片水印 。
	 * 
	 * 在右下角加图片水印的命令：$ composite -gravity southeast 水印.png 原图.jpg 效果图.jpg
	 * 
	 * @param srcPath
	 *            源图片的位置
	 * @param waterImagePath
	 *            水印
	 * @param destPath
	 *            生成图片的位置
	 * @param position
	 *            生成水印的位置
	 * @param alpha
	 *            水印透明度
	 */
	public void addImgImg(String waterImagePath, String srcPath,
			String destPath, String position,  int alpha) {
		IMOperation op = new IMOperation();
		// 左上角：northwest、左下角：southwest、右上角：northeast、右下角：southeast、中间：center。
		String gravity = position;
		op.dissolve(alpha);
		op.gravity(gravity);
		op.addImage(waterImagePath);
		op.addImage(srcPath);
		op.addImage(destPath);
		
		//构造器有true，命令会加gm，估计是linux下用，未经验证。
		//CompositeCmd composite = new CompositeCmd(true);
		CompositeCmd composite = new CompositeCmd();
		String spl = System.getProperty("file.separator");
		if ((spl.equals("\\") || spl == "\\")) {
			composite.setSearchPath(imageMagickPath);
		}
		try {
			composite.run(op);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IM4JavaException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 增加图片边框：convert exam.jpg -bordercolor blue -border 5 border-1.jpg 
	 * @param srcImagePath 原图
	 * @param destImagePath 新图
	 * @param color 边框颜色
	 * @param width 边框粗细
	 */
	public void addBorder(String srcImagePath, String destImagePath, String color,  int width) {
		IMOperation op = new IMOperation();
		op.addImage(srcImagePath);
		op.bordercolor(color);
		op.border(width);
		op.addImage(destImagePath);
		
		ConvertCmd convert = new ConvertCmd();

		// linux下不要设置此值，不然会报错
		String spl = System.getProperty("file.separator");
		if ((spl.equals("\\") || spl == "\\")) {
			convert.setSearchPath(imageMagickPath);
		}
		
		try {
			convert.run(op);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IM4JavaException e) {
			e.printStackTrace();
		}
	}
}