package pcb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DPS {
    public static final int CPUTIME = 2;//CPU时间片取固定值2

    public static void main(String[] args) throws InterruptedException {
        //一、初始化进程
        System.out.println("===========动态优先级调度算法=============");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入进程数量：");
        int input = sc.nextInt();
        sc.close();
        Queue<PCB> queue = new LinkedList<PCB>();
        for (int i = 1; i <= input; i++) {
            queue.offer(new PCB("进程" + i));
        }

        //二、进程入队并工作
        System.out.println("=======================================");
        System.out.println("名字\t" + "到达时间\t" + "要求运行时间\t" + "优先级数\t" + "状态");
        System.out.println("=======================================");

        ArrayList<PCB> sequence = new ArrayList<PCB>();

        while (!queue.isEmpty()) {
            //按照PCB优先级与到达时间排序
            if (queue.size() > 1) {
                getMax(queue);
            }
            if (!queue.isEmpty()) {
                //遍历队列，设置进程状态（增强For循环）
                for (PCB q : queue) {
                    if (q.equals(queue.peek())) {
                        //队首：进入工作状态
                        q.setState("W");
                    } else {
                        //非队首：进入就绪状态
                        if (q.getState().equals("W")) {
                            q.setState("R");
                        }
                    }
                    //打印信息
                    q.printInformation();
                }
                //进程运行
                queue.peek().run();
            }

            //若剩余运行时间小于等于0，进程状态转换为E(End)
            for (PCB q : queue) {
                if (q.getRunTime() <= 0) {
                    q.setState("E");
                }

                if (q.getState().equals("E")) {
                    System.out.println();
                    //打印信息
                    System.out.println("==============" + q.getName() + "进程结束================");
                    q.printInformation();
                    System.out.println("=======================================");
                    sequence.add(q);
                    queue.poll();
                    Thread.sleep(1500);
                    break;
                }
            }
            System.out.println();
        }
        //三、打印PCB完成顺序
        System.out.println("PCB结束工作的顺序为：");
        for (PCB q : sequence) {
            System.out.printf(q.getName() + "\t");
        }
    }

    //getMax()方法：找到最先抵达且优先级最高的PCB
    public static void getMax(Queue<PCB> queue) {
        int maxPriorityNum = 0;
        int minArrivalTime = 999;
        //找出最高优先级
        for (PCB q : queue) {
            if (q.getPriorityNum() > maxPriorityNum) {
                maxPriorityNum = q.getPriorityNum();
            }
        }
        //找出最高优先级中最早到达时间
        for (PCB q : queue) {
            if (q.getPriorityNum() == maxPriorityNum && q.getArrivalTime() < minArrivalTime) {
                minArrivalTime = q.getArrivalTime();
            }
        }

        PCB first = queue.peek();
        for (int i = 0; i < queue.size(); i++) {
            //将优先级最高的PCB移动到队首
            while (queue.peek().getPriorityNum() < maxPriorityNum) {
                queue.offer(queue.peek());
                queue.poll();
            }

            PCB q = ((LinkedList<PCB>) queue).get(i);//queue强转为LinkedList类型
            //跳过队首frist的判定
            if (!q.equals(first)) {
                //如果q的优先级等于队首优先级 且 到达时间比队首早
                if ((q.getPriorityNum() == queue.peek().getPriorityNum())
                        && q.getArrivalTime() < queue.peek().getArrivalTime()) {
                    //当队首到达时间不等于最早到达时间
                    while (queue.peek().getArrivalTime() != minArrivalTime) {
                        queue.offer(queue.peek());
                        queue.remove(queue.peek());
                        queue.remove(0);
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
    }
}


