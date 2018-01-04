package com.wd.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Test1 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double start = System.currentTimeMillis() ;
        System.out.println("Starts...");
        readFile("D:\\WorkSpace\\edu-git");
        double end = System.currentTimeMillis() ;
        System.out.println("Successed!");
        System.out.println("Time used : " + (end - start)/1000+"s");
    }

    public static void readFile(String path) {
        File file = new File(path);
        File[] arr = file.listFiles();
        File file1;
        File file2;

        if(arr!=null) {
            for (int i = 0; i < arr.length; i++) {
                if(arr[i].getName().indexOf(".idea") != -1 || arr[i].getName().indexOf("target") != -1)
                {
                    continue;
                }
//                if (arr[i].getName().indexOf("edu") != -1) {
                // 判断是否是文件夹，如果是的话，再调用一下read方法
                if (arr[i].isDirectory()) {
                    if (arr[i].getName().indexOf("edu") != -1) {
                        String pa=arr[i].getPath();
                        file1 = new File(pa.substring(0, pa.lastIndexOf("\\"))+"\\"+arr[i].getName().replace("edu", "sport"));
                        arr[i].renameTo(file1);
                        readFile(file1.getAbsolutePath());
                    } else {
                        readFile(arr[i].getAbsolutePath());
                    }
                } else { //如果是文件，则替换内容
                    BufferedReader reader;
                    try {
                        reader = new BufferedReader(new InputStreamReader(new FileInputStream(arr[i].getAbsolutePath())));
                        StringBuffer buffer = new StringBuffer();
                        String str = null;
                        while ((str = reader.readLine()) != null) {
                            str += '\n';
                            buffer.append(str);
                        }
                        FileOutputStream outputStream = new FileOutputStream(arr[i].getAbsolutePath());
                        outputStream.write(buffer.toString().replaceAll("edu", "sport").getBytes());
                        reader.close();
                        outputStream.close();
                        if (arr[i].getName().indexOf("edu") != -1) {
                            String pa=arr[i].getPath();
                            file2 = new File(pa.substring(0, pa.lastIndexOf("\\"))+"\\"+arr[i].getName().replace("edu", "sport"));
//                                file2 = new File(arr[i].getAbsolutePath().replace("edu", "sport"));
                            arr[i].renameTo(file2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

//                }
            }
        }
    }

}
