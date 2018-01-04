package com.yjn.test.IO.file.redis;

import org.apache.commons.collections.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huzhang on 2017/3/21.
 */
public class ImportFileToRedis {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        List<String> dest = GzipUtil.unGzipFiles("D:\\usr\\123\\111");
        if (CollectionUtils.isNotEmpty(dest)) {
            for (final String filepath : dest) {
                fixedThreadPool.execute(new Runnable() {
                    public void run() {
                        Jedis jedis = RedisPool.getJedis();
                        System.out.println("读取【" + filepath + "】文件,开始...");
                        long start = System.currentTimeMillis();
                        try {
                            FileReader fr = null;
                            BufferedReader bf = null;
                            fr = new FileReader(filepath);
                            bf = new BufferedReader(fr);
                            String line = "";
                            String[] arrs = null;
                            while ((line = bf.readLine()) != null) {
                                if (line != null && !"".equals(line)) {
                                    arrs = line.split(";");
                                    jedis.set(arrs[0], arrs[1] + arrs[2]);
                                }
                            }
                            bf.close();
                            fr.close();
                            File temp = new File(filepath);
                            temp.delete();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            RedisPool.returnResource(jedis);
                        }
                        long end = System.currentTimeMillis();
                        System.out.println("读取【" + filepath + "】文件,结束。耗时" + (end - start));
                    }
                });


            }
        }

    }

}
