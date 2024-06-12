package com.lqq.springcloud;

import java.util.ArrayList;

public class GC {
    byte[] bytes = new byte[1024 * 100];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<GC> list = new ArrayList<>();
        while (true) {
            list.add(new GC());
            Thread.sleep(10);
        }
    }
}
