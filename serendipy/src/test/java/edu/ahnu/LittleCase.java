package edu.ahnu;

import java.util.Random;

public class LittleCase {
    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int num = random.nextInt(5);
            System.out.println(num + 1);
        }
    }
}
