package zk;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>();
        al.add("a");
        al.add("b");
        System.out.println(al);

        System.out.println("==========================================");
        System.out.println("主线程开始了");

        Thread thread = new Thread(new Runnable() {

            public void run() {
                System.out.println("线程开始了");
                while(true){

                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }
}