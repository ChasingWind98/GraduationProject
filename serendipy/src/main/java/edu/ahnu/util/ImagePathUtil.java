package edu.ahnu.util;

import sun.jvm.hotspot.oops.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ImagePathUtil {
    private static String path1 = "/image/biaoqing1.jpg";
    private static String path2 = "/image/biaoqing2.jpg";
    private static String path3 = "/image/biaoqing3.jpg";
    private static String path4 = "/image/biaoqing4.jpg";
    private static String path5 = "/image/biaoqing5.jpg";


    public static String getImagePath() {
        List<String> list = new ArrayList<>();
        list.add(path1);
        list.add(path2);
        list.add(path3);
        list.add(path4);
        list.add(path5);

        Random random = new Random();

        int num = random.nextInt(5);


        return list.get(num);

    }
}
