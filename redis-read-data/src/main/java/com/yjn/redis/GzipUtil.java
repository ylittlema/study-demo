package com.yjn.redis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Created by huzhang on 2017/3/21.
 */
public class GzipUtil {


    public static List<String> unGzipFiles(String sourceDirs) {
        List<String> files = new ArrayList<String>();
        File dir = new File(sourceDirs);
        if (dir.isDirectory() && dir.listFiles().length > 0) {
            for (File file : dir.listFiles()) {
                if (file.getName().contains(".gz")) {
                    files.add(unGzipFile(file.getPath()));
                }
            }
        } else {
            files.add(unGzipFile(sourceDirs));
        }
        return files;
    }


    public static String unGzipFile(String sourcedir) {
        String ouputfile = "";
        try {
            //建立gzip压缩文件输入流
            FileInputStream fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            GZIPInputStream gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            ouputfile = sourcedir.substring(0, sourcedir.lastIndexOf('.'));
            if (ouputfile.lastIndexOf('.') > 0) {
                ouputfile = ouputfile.substring(0, ouputfile.lastIndexOf('.'));
            }
            FileOutputStream fout = new FileOutputStream(ouputfile);

            int num;
            byte[] buf = new byte[1024];

            while ((num = gzin.read(buf, 0, buf.length)) != -1) {
                fout.write(buf, 0, num);
            }

            gzin.close();
            fout.close();
            fin.close();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return ouputfile;
    }
}
