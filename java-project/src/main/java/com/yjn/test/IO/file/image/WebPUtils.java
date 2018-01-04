package com.yjn.test.IO.file.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2017/3/16/0016  YuanJunNan 创建
 *          <p>1.01 2017/3/16/0016 修改者姓名 修改内容说明</p>
 */
public class WebPUtils {

    public static void main(String[] args) throws IOException {

        BufferedImage b = ImageIO.read(new FileInputStream("d:\\6.jpg"));//读入源图片
        BufferedImage k = new BufferedImage(b.getWidth(), b.getHeight(),BufferedImage.TYPE_INT_RGB);//输出目标图片
//        BufferedImage r = new ChromeFilter().filter(b,k);//调用滤镜
//        ImageIO.write(r, "jpg", new File("d:\\u.jpg"));//生成图片文件
    }

}
