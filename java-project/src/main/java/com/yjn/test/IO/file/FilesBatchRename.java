package com.yjn.test.IO.file;

import java.io.*;

/**
 * <p>描述</p>
 *
 * @author YuanJunNan
 * @author 其他作者姓名
 * @version 1.00  2016/6/8  YuanJunNan 创建
 *          <p>1.01 2016/6/8 修改者姓名 修改内容说明</p>
 */
public class FilesBatchRename {
    public static void main(String[] args) throws IOException {
        File dir = new File("E:\\workspace\\IntellijIDEA-Project\\baseframework\\CodeGeneration\\src\\main\\resources\\template\\interfaces-core");
        String fileSuffix = "ftl";
        fileRename(dir, fileSuffix);
    }

    private static void fileRename(File dir, final String suffix) throws IOException {
        if (dir.exists()) {
            File[] fs = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    boolean isfile = new File(dir, name).isFile();
                    if ((isfile && name.endsWith(suffix)) || !isfile)
                        return true;
                    return false;
                }
            });
            for (File item : fs) {
                if (!item.isFile()) {
                    fileRename(item, suffix);
                }
                if (item.isFile()) {
                    String oldName = item.getAbsolutePath();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(oldName)));
                    StringBuffer buffer = new StringBuffer();
                    String str;
                    while ((str = reader.readLine()) != null) {
                        if (str.contains("package"))
                            buffer.append("package ${packageName};");
                        else
                            buffer.append(str);
                        buffer.append('\n');
                    }
                    FileOutputStream outputStream = new FileOutputStream(oldName);
                    outputStream.write(buffer.toString().getBytes());
                    reader.close();
                    outputStream.close();
                    /*File file = new File(oldName + ".ftl");
                    item.renameTo(file);*/
                    System.out.println("文件" + "\"" + oldName + "\"已重名.");
                }

            }
        }
    }
}
