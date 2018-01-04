package com.yjn.springdemo.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

/**
 * Created by YJF on 2016-01-20.
 */
public class ZipUtils {

    public static void main(String[] args) {
        String str = "{\"finalPage\":\"false\",\"flag\":0,\"result\":[{\"result\":{\"actor\":\"黄奕|刘桦|邓家佳|韦炜|黄艺馨|李湘|汪涵|杨建平|李昌元|施宁|立威廉\",\"id\":\"1555\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g3.ykimg.com/0516000051F2448D67583941440BAF54\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g3.ykimg.com/0516000051F2448D67583941440BAF54\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g3.ykimg.com/0516000051F2448D67583941440BAF54\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g3.ykimg.com/0516000051F2448D67583941440BAF54\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g3.ykimg.com/0516000051F2448D67583941440BAF54\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g3.ykimg.com/0516000051F2448D67583941440BAF54\"}],\"is_pay\":0,\"score\":\"7.2\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g3.ykimg.com/0516000051F2448D67583941440BAF54\",\"title\":\"十全九美\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"\",\"id\":\"22985\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/0516000051D263C667583930A104F066\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/0516000051D263C667583930A104F066\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/0516000051D263C667583930A104F066\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/0516000051D263C667583930A104F066\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/0516000051D263C667583930A104F066\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/0516000051D263C667583930A104F066\"}],\"is_pay\":0,\"score\":\"暂无\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g1.ykimg.com/0516000051D263C667583930A104F066\",\"title\":\"锁锁美同学@提不起劲\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"香里奈|藤谷太辅|北山宏光|市川知宏|田中圭|大政绚\",\"id\":\"16073\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/051600005192FB59670C4A1ADE0C583F\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/051600005192FB59670C4A1ADE0C583F\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/051600005192FB59670C4A1ADE0C583F\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/051600005192FB59670C4A1ADE0C583F\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/051600005192FB59670C4A1ADE0C583F\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/051600005192FB59670C4A1ADE0C583F\"}],\"is_pay\":0,\"score\":\"8.0\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g1.ykimg.com/051600005192FB59670C4A1ADE0C583F\",\"title\":\"美咲NO.1\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"\",\"id\":\"23122\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/051600005062B32697927316CC00164A\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/051600005062B32697927316CC00164A\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/051600005062B32697927316CC00164A\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/051600005062B32697927316CC00164A\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/051600005062B32697927316CC00164A\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/051600005062B32697927316CC00164A\"}],\"is_pay\":0,\"score\":\"8.3\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g2.ykimg.com/051600005062B32697927316CC00164A\",\"title\":\"吸血姬美夕\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"\",\"id\":\"1812953\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/05160000563301CA67BC3C55EE0C8283\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/05160000563301CA67BC3C55EE0C8283\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/05160000563301CA67BC3C55EE0C8283\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/05160000563301CA67BC3C55EE0C8283\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/05160000563301CA67BC3C55EE0C8283\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/05160000563301CA67BC3C55EE0C8283\"}],\"is_pay\":0,\"score\":\"暂无\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g1.ykimg.com/05160000563301CA67BC3C55EE0C8283\",\"title\":\"醉美 2015\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"\",\"id\":\"1101620\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/050D0000551A5F5A67379F1D7B0D0FB7\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/050D0000551A5F5A67379F1D7B0D0FB7\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/050D0000551A5F5A67379F1D7B0D0FB7\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/050D0000551A5F5A67379F1D7B0D0FB7\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/050D0000551A5F5A67379F1D7B0D0FB7\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/050D0000551A5F5A67379F1D7B0D0FB7\"}],\"is_pay\":0,\"score\":\"8.9\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g2.ykimg.com/050D0000551A5F5A67379F1D7B0D0FB7\",\"title\":\"i-EVER美课美妆 2015\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"格格|李健仁\",\"id\":\"33572\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g4.ykimg.com/05160000516F4B5F97927358CA0AD4C9\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g4.ykimg.com/05160000516F4B5F97927358CA0AD4C9\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g4.ykimg.com/05160000516F4B5F97927358CA0AD4C9\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g4.ykimg.com/05160000516F4B5F97927358CA0AD4C9\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g4.ykimg.com/05160000516F4B5F97927358CA0AD4C9\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g4.ykimg.com/05160000516F4B5F97927358CA0AD4C9\"}],\"is_pay\":0,\"score\":\"暂无\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g4.ykimg.com/05160000516F4B5F97927358CA0AD4C9\",\"title\":\"貌美如花\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"\",\"id\":\"1085056\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/0516000053843EEB67379F506A0E2F34\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/0516000053843EEB67379F506A0E2F34\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/0516000053843EEB67379F506A0E2F34\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/0516000053843EEB67379F506A0E2F34\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/0516000053843EEB67379F506A0E2F34\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/0516000053843EEB67379F506A0E2F34\"}],\"is_pay\":0,\"score\":\"暂无\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g2.ykimg.com/0516000053843EEB67379F506A0E2F34\",\"title\":\"大家想得美 2014\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"\",\"id\":\"32455\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/0516000051ADA7026758390BCB0B8111\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/0516000051ADA7026758390BCB0B8111\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/0516000051ADA7026758390BCB0B8111\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/0516000051ADA7026758390BCB0B8111\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/0516000051ADA7026758390BCB0B8111\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/0516000051ADA7026758390BCB0B8111\"}],\"is_pay\":0,\"score\":\"暂无\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g2.ykimg.com/0516000051ADA7026758390BCB0B8111\",\"title\":\"甜美旋律 Rainbow Live\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"\",\"id\":\"1098801\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/05160000545079D867379F19B00AA2A5\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/05160000545079D867379F19B00AA2A5\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/05160000545079D867379F19B00AA2A5\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/05160000545079D867379F19B00AA2A5\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/05160000545079D867379F19B00AA2A5\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/05160000545079D867379F19B00AA2A5\"}],\"is_pay\":0,\"score\":\"暂无\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g1.ykimg.com/05160000545079D867379F19B00AA2A5\",\"title\":\"大家想得美 2015\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"金超群|何家劲\",\"id\":\"1082876\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://i2.letvimg.com/vrs/201403/04/5d14f91dfaf143aca7c3bff25a861d57.jpg\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://i2.letvimg.com/vrs/201403/04/5d14f91dfaf143aca7c3bff25a861d57.jpg\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://i2.letvimg.com/vrs/201403/04/5d14f91dfaf143aca7c3bff25a861d57.jpg\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://i2.letvimg.com/vrs/201403/04/5d14f91dfaf143aca7c3bff25a861d57.jpg\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://i2.letvimg.com/vrs/201403/04/5d14f91dfaf143aca7c3bff25a861d57.jpg\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://i2.letvimg.com/vrs/201403/04/5d14f91dfaf143aca7c3bff25a861d57.jpg\"}],\"is_pay\":0,\"score\":\"6.699999809265137\",\"sourceWebsites\":{\"letv\":\"乐视\"},\"superscript\":[],\"thumb\":\"http://i2.letvimg.com/vrs/201403/04/5d14f91dfaf143aca7c3bff25a861d57.jpg\",\"title\":\"铡美案\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"阿旺仁青|江疏影|陈瑾|巴登西饶\",\"id\":\"1093319\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/051600005398072A67379F14D10ECAB2\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/051600005398072A67379F14D10ECAB2\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/051600005398072A67379F14D10ECAB2\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/051600005398072A67379F14D10ECAB2\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/051600005398072A67379F14D10ECAB2\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/051600005398072A67379F14D10ECAB2\"}],\"is_pay\":0,\"score\":\"6.8\",\"sourceWebsites\":{\"letv\":\"乐视\"},\"superscript\":[],\"thumb\":\"http://g1.ykimg.com/051600005398072A67379F14D10ECAB2\",\"title\":\"天上的菊美\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"\",\"id\":\"21858\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g4.ykimg.com/051600005245257F6758393F7F042F0A\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g4.ykimg.com/051600005245257F6758393F7F042F0A\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g4.ykimg.com/051600005245257F6758393F7F042F0A\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g4.ykimg.com/051600005245257F6758393F7F042F0A\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g4.ykimg.com/051600005245257F6758393F7F042F0A\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g4.ykimg.com/051600005245257F6758393F7F042F0A\"}],\"is_pay\":0,\"score\":\"暂无\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g4.ykimg.com/051600005245257F6758393F7F042F0A\",\"title\":\"达菲鸭救美\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"葛晓倩|万蒂妮\",\"id\":\"34619\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/0516000051C11D666758393EF20D55EC\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/0516000051C11D666758393EF20D55EC\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g1.ykimg.com/0516000051C11D666758393EF20D55EC\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/0516000051C11D666758393EF20D55EC\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/0516000051C11D666758393EF20D55EC\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g1.ykimg.com/0516000051C11D666758393EF20D55EC\"}],\"is_pay\":0,\"score\":\"8.7\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g1.ykimg.com/0516000051C11D666758393EF20D55EC\",\"title\":\"淘最可思美 2013\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"\",\"id\":\"1093765\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g3.ykimg.com/0516000053FD682867379F151E0DAF1B\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g3.ykimg.com/0516000053FD682867379F151E0DAF1B\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g3.ykimg.com/0516000053FD682867379F151E0DAF1B\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g3.ykimg.com/0516000053FD682867379F151E0DAF1B\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g3.ykimg.com/0516000053FD682867379F151E0DAF1B\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g3.ykimg.com/0516000053FD682867379F151E0DAF1B\"}],\"is_pay\":0,\"score\":\"8.0\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g3.ykimg.com/0516000053FD682867379F151E0DAF1B\",\"title\":\"美妈问问团 2014\"},\"type\":\"0001\"},{\"result\":{\"actor\":\"刘梦瑶|火旺|张东\",\"id\":\"26556\",\"images\":[{\"size\":\"b\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/0516000051AD985B6758390701074DEB\"},{\"size\":\"m\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/0516000051AD985B6758390701074DEB\"},{\"size\":\"s\",\"style\":\"v\",\"type\":\"c\",\"url\":\"http://g2.ykimg.com/0516000051AD985B6758390701074DEB\"},{\"size\":\"b\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/0516000051AD985B6758390701074DEB\"},{\"size\":\"m\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/0516000051AD985B6758390701074DEB\"},{\"size\":\"s\",\"style\":\"h\",\"type\":\"b\",\"url\":\"http://g2.ykimg.com/0516000051AD985B6758390701074DEB\"}],\"is_pay\":0,\"score\":\"8.1\",\"sourceWebsites\":{},\"superscript\":[],\"thumb\":\"http://g2.ykimg.com/0516000051AD985B6758390701074DEB\",\"title\":\"食全食美 2013\"},\"type\":\"0001\"}],\"total\":119}\n";
        String zipStr =  gzip(str);
        String unzip = gunzip(zipStr);

        System.out.print(unzip);


    }

    /**
     * 使用gzip进行压缩
     */
    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }

    /**
     * <p>Description:使用gzip进行解压缩</p>
     *
     * @param compressedStr
     * @return
     */
    public static String gunzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }

        return decompressed;
    }

    /**
     * 使用zip进行压缩
     *
     * @param str 压缩前的文本
     * @return 返回压缩后的文本
     */
    public static final String zip(String str) {
        if (str == null)
            return null;
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        String compressedStr = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
            compressedStr = new sun.misc.BASE64Encoder().encodeBuffer(compressed);
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return compressedStr;
    }

    /**
     * 使用zip进行解压缩
     *
     * @param compressedStr 压缩后的文本
     * @return 解压后的字符串
     */
    public static final String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }
}
