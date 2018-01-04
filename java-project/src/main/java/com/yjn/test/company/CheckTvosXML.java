package com.yjn.test.company;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/4/27  YuanJunNan 创建
 *          <p>1.01 2016/4/27 修改者姓名 修改内容说明</p>
 */
public class CheckTvosXML {
    public static void main(String[] args) throws IOException, DocumentException, InterruptedException {
        FileReader reader = new FileReader("D:\\1212.txt");
        String ecoding = reader.getEncoding();
        StringBuffer error = new StringBuffer();
        BufferedReader buffer = new BufferedReader(reader);
        String url;
        int i = 0;
        while ((url = buffer.readLine()) != null) {
//            System.out.println(">>>>>>>>>>>>" + url);
            String u = url.split("#")[2];
            String nnn = url.split("#")[1];
            String ddd = url.split("#")[0];
            String re = HttpUtils.URLGet(u, null);
            Element root = null;
            try {
//                root = XMLUtils.getRootElement("");

               /* Element category = root.element("category");
                List<Element> items = category.elements("item");
                for (Element item : items) {

                    Element cmd_action = item.element("cmd_action");
//                    Element detail = item.element("detail");
                    String action = XMLUtils.getAttributeByName(cmd_action, "value");
//                    String descid = XMLUtils.getAttributeByName(detail, "descid");*/

                if (re.contains("com.test.test.Start")) {
                    System.out.println(ddd + ":" + nnn + ":" + u);
                } else {
//                           System.out.println(action);
                }
                System.out.println(i++);

//                }

//                List<Element> pockets = root.elements("pockets");
//                if (pockets != null && pockets.size() > 1) {
//                    for (Element pocket : pockets) {
//                        List<Element> pocItems = pocket.elements("pocketitem");
//                        for (Element poc : pocItems) {
//                            Element detail = poc.element("detail");
//                            String mixicon = XMLUtils.getAttributeByName(detail, "mixicon");
//                            String background = XMLUtils.getAttributeByName(detail, "background");
//                            if ("".equals(background) && mixicon != "" && mixicon != null)
//                                continue;
//                            HttpClient httpclient = new DefaultHttpClient();
//                            HttpGet httpget = new HttpGet(background);
//                            HttpResponse response = httpclient.execute(httpget);
//                            System.out.println(background + ">>>>>>" + response.getStatusLine().getStatusCode());
//                            if (response.getStatusLine().getStatusCode() == 404) {
//                                error.append(url + "\n" + background + "\n");
//                            }
//                        }
//                    }
//                } else {
//                    System.out.println("######################4.0");
//                }
            } catch (Exception e) {
                error.append("报错" + url + "\n");
            }
            Thread.sleep(1000l * 1);

        }
        System.out.print(error.toString());
        System.out.println("over");
    }
}
