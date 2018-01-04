package com.yjn.springdemo.main;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <p>类的说明</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/1/16 YuanJunNan 创建
 *          <p>1.01 2016/1/16 修改者姓名 修改内容说明</p>
 */
public class TestMian {
    public static void main(String[] args) throws IOException {
        try {
            //读取原图
            BufferedImage image = ImageIO.read(new File("D:\\abc\\dis.png"));
            BufferedImage src = ImageIO.read(new File("D:\\abc\\123.png"));

            int w = image.getWidth();
            int h = image.getHeight();

            BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();
            output = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = output.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

           // g2.fillRoundRect(0, 0,w, h, 30, 30);
            g2.setComposite(AlphaComposite.SrcOver);
            g2.drawImage(image, 0, 0, w, h, null);
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(src, 0, 0, w, h, null);
            g2.dispose();
            ImageIO.write(output, "png", new File("D:\\abc\\hh.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    * 圆角处理
    * @param BufferedImage
    * @param cornerRadius
    * */
    public static String makeRoundedCorner(String srcImageFile, String result, String type, int cornerRadius) {
        try {
            //读取原图
            BufferedImage image = ImageIO.read(new File(srcImageFile));

            int w = image.getWidth();
            int h = image.getHeight();
            BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();
            output = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = output.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillRoundRect(0, 0,w, h, cornerRadius, cornerRadius);
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(image, 0, 0, w, h, null);
            g2.dispose();
            ImageIO.write(output, type, new File(result));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   /* private Bitmap clipBitmap(Bitmap bitmapDis, Bitmap bitmapSrc)
    {
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        // 创建一个和原始图片一样大小位图
        Bitmap roundConcerImage = Bitmap.createBitmap(bitmapSrc.getWidth(), bitmapSrc.getHeight(), Config.ARGB_8888);
        // 创建带有位图roundConcerImage的画布
        Canvas canvas = new Canvas(roundConcerImage);
        // 先绘制dis目标图
        if (bitmapDis != null && !bitmapDis.isRecycled())
            canvas.drawBitmap(bitmapDis, 0, 0, mPaint);
        // 设置混合模式
        mPaint.setXfermode(porterDuffXfermode);
        // 再绘制src源图
        canvas.drawBitmap(bitmapSrc, 0, 0, mPaint);
        // 还原混合模式
        mPaint.setXfermode(null);
        bitmapDis = roundConcerImage;
        return bitmapDis;
    }*/


   /* public static void main(String[] args) throws IOException {
        //首先jpg格式的图片，支持RGB，无法实现背景透明
        //      png与gif支持RGB和alpha属性,可以生成透明图片

        int width = 400;
        int height = 300;

// 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
// 获取Graphics2D
        Graphics2D g2d = image.createGraphics();

// ---------- 增加下面的代码使得背景透明 -----------------
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
// ---------- 背景透明代码结束 -----------------


// 画图
        *//*g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(1));
        g2d.draw*//*
//释放对象
        g2d.dispose();
// 保存文件
        ImageIO.write(image, "png", new File("D:/test.png"));
    }*/

    public static BufferedImage setBorderRadius(BufferedImage srcImage, int radius) {
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, width, height, radius, radius);
        g2d.setComposite(AlphaComposite.SrcIn);
        g2d.drawImage(srcImage, 0, 0, width, height, null);
        return image;
    }
}
