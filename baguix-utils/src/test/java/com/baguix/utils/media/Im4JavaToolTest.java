package com.baguix.utils.media;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Im4JavaTool Tester.
 *
 * @author Scott(SG)
 */
public class Im4JavaToolTest {
    private static final Logger logger = LoggerFactory.getLogger(Im4JavaToolTest.class);
    private String imageMagickPath = "c:/im";
    private String imagePath = "c:/im/test.jpg";
    private Im4JavaTool Im;

    @Before
    public void before() throws Exception {
        Im = new Im4JavaTool(imageMagickPath);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getWidthHeight(String imagePath)
     */
    @Test
    public void testGetWidthHeight() throws Exception {
        int[] wh = Im.getWidthHeight(imagePath);
        logger.info("Width:" + wh[0] + "; Height:" + wh[1]);
    }

    /**
     * Method: getWidth(String imagePath)
     */
    @Test
    public void testGetWidth() throws Exception {
        logger.info("Width:" + Im.getWidth(imagePath));
    }

    /**
     * Method: getHeight(String imagePath)
     */
    @Test
    public void testGetHeight() throws Exception {
        logger.info("Height:" + Im.getHeight(imagePath));
    }

    /**
     * Method: cutImage(String srcPath, String newPath, int x, int y, int x1, int y1)
     */
    @Test
    public void testCutImageForSrcPathNewPathXYX1Y1() throws Exception {
        String newImage = imageMagickPath + "/cut1.jpg";
        Im.cutImage(imagePath, newImage, 50, 50, 500, 400);
        int[] wh = Im.getWidthHeight(newImage);
        assertEquals(wh[0],450);
        assertEquals(wh[1],350);
        logger.info("Width:" + wh[0] + "; Height:" + wh[1]);
    }

    /**
     * Method: cutImage(int width, int height, String srcPath, String newPath)
     */
    @Test
    public void testCutImageForWidthHeightSrcPathNewPath() throws Exception {
        String newImage = imageMagickPath + "/cut2.jpg";
        Im.scaleImage(400,500,imagePath,newImage);
        int[] wh = Im.getWidthHeight(newImage);
        assertEquals(wh[0],400);
        assertEquals(wh[1],400);
    }

    /**
     * Method: cutImage(int width, String srcPath, String newPath)
     */
    @Test
    public void testCutImageForWidthSrcPathNewPath() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: addImgTextEn(String srcPath, String color, int size, String text, String position)
     */
    @Test
    public void testAddImgTextEn() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: addImgTextCn(String srcPath, String color, int size, String text, String position)
     */
    @Test
    public void testAddImgTextCn() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: addImgImg(String waterImagePath, String srcPath, String destPath, String position, int alpha)
     */
    @Test
    public void testAddImgImg() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: addBorder(String srcImagePath, String destImagePath, String color, int width)
     */
    @Test
    public void testAddBorder() throws Exception {
        //TODO: Test goes here... 
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
        //TODO: Test goes here... 
    }


}
