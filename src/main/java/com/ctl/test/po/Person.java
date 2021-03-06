package com.ctl.test.po;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: Person</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-05 10:20
 */
public class Person{
    public Person() {
    }

    public Person(int id, String name) {
        Random random = new Random();
        int nextInt = random.nextInt(1000);
        try {
            TimeUnit.MILLISECONDS.sleep(nextInt);
            this.id = id;
            this.name = name;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Thread thread = new Thread(() -> {
//            try {
//                TimeUnit.MILLISECONDS.sleep(nextInt);
//                this.id = id;
//                this.name = name;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
    }
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}