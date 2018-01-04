package com.yjn.redis;

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
        String path = args[0];
        System.out.println("gz文件路径是>>" + path);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        List<String> dest = GzipUtil.unGzipFiles(path);
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
//                                    60427F61F898;(114, 118, 101, 166, 165, 374, 346, 372, 105, 267, 139, 391, 96, 313, 226, 115, 349, 169, 378, 100, 94, 161, 312, 191, 384, 187, 290, 222, 148, 393, 110, 266, 348, 144, 358, 350, 106, 143, 234, 265, 364, 295, 365, 220, 153, 296, 151, 111, 123, 113, 66, 174, 197, 76, 390, 355, 58, 394, 389, 299, 293, 84, 206, 201, 185, 122, 78, 155, 157, 120, 99, 60, 146, 202, 192, 89, 357, 328, 163, 61, 188, 269, 297, 298, 73, 159, 154, 186, 241, 375, 376, 46, 141, 86, 214, 244, 142, 221, 104, 147, 145, 172, 162, 329, 119, 138, 388, 327, 149, 95, 270, 62, 330, 112, 224, 121, 150, 279, 81, 173, 291, 370)-(130, 373, 160, 56, 379, 236, 238, 217, 392, 44, 170, 369, 300, 158, 319, 204, 90, 205, 218, 301, 219, 140, 83, 124, 63, 42, 134, 183, 367, 385, 352, 175, 371, 317, 316, 318, 194, 171, 59, 93, 381, 354, 366, 196, 189, 377, 380, 225, 181, 347, 363, 98, 190, 216, 235, 43, 215, 243, 227, 85, 131, 77, 223, 345, 360, 88, 135, 65, 386, 152, 109, 164, 102, 375, 116, 87, 168, 167, 40, 41, 332, 333, 331, 198, 253, 136, 137, 344, 242, 323, 324, 325, 322, 321, 200, 92, 280, 315, 228, 129, 64, 203, 326, 320, 362)

                                   // 6;(163, 502, 66, 221, 60, 186, 62, 469, 396, 523, 509, 407, 522, 174, 188, 220, 76, 349, 524, 146, 153, 450, 350, 226, 139, 497, 149, 123, 155, 157, 84, 61, 434, 111, 445, 355, 267, 122, 498, 327, 110, 112, 269, 295, 192, 329, 234, 141, 474, 290, 473, 145, 214, 94, 358, 376, 154, 144, 244, 486, 313, 378, 119, 399, 143, 96, 197, 99, 191, 465, 533, 508, 142, 78, 100, 390, 409, 428, 151, 384, 73, 121, 493, 514, 138, 357, 162, 518, 206, 46, 296, 148, 224, 538, 346, 86, 425, 458, 394, 147, 515, 372, 375, 414, 299, 293, 403, 441, 328, 477, 104, 120, 106, 365, 222, 393, 467, 297, 298, 452, 398, 388, 408, 391, 266, 187, 480, 435, 202, 432, 483, 422, 270, 169, 443, 348, 113, 172, 312, 95, 115, 161, 279, 374, 504, 330, 506, 265, 364, 389, 440, 457, 150, 370, 539, 166, 165)-(510, 171, 406, 527, 503, 175, 392, 235, 63, 379, 164, 194, 59, 300, 415, 217, 301, 65, 190, 236, 238, 517, 43, 490, 242, 228, 397, 419, 481, 402, 203, 521, 92, 421, 476, 362, 129, 205, 380, 405, 189, 98, 455, 93, 64, 181, 183, 471, 485, 451, 42, 438, 124, 366, 140, 442, 436, 410, 492, 367, 491, 85, 488, 447, 437, 400, 319, 347, 360, 377, 470, 511, 363, 223, 87, 489, 484, 131, 168, 352, 516, 371, 413, 478, 77, 444, 449, 482, 426, 468, 135)

                                    arrs = line.split(";");
                                    jedis.set(arrs[0], arrs[1]);
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
                        System.out.println("读取【" + filepath + "】文件,结束>>耗时:" + (end - start));
                    }
                });


            }
        }

    }

}
