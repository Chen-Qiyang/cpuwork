package com.example.cpu_scheduling;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.util.Comparator;

public class sort {
    public static void getMax(ObservableList<PCB> queue) {
        //找出优先级最高(优先级数最小)的进程
        int minPriorityNum = 100;
        int minArrivalTime = 100;
        for(PCB q:queue){
            if(q.getPriorityNum()<minPriorityNum){
                minPriorityNum = q.getPriorityNum();
            }
        }
        //找出优先级最高且到达时间最早的进程,减少忙等
        for(PCB q:queue){
            if(q.getPriorityNum() == minPriorityNum&&q.getArrivalTime()<minArrivalTime) {
                minArrivalTime = q.getArrivalTime();
            }
        }

        PCB first = queue.get(0);
        for(int i=0;i<queue.size();i++){
            while (queue.get(0).getPriorityNum()>minPriorityNum){
                queue.add(queue.get(0));
                queue.remove(queue.get(0));
            }
            PCB q = queue.get(i);

            if(!q.equals(first)){
                if((q.getPriorityNum() == queue.get(0).getPriorityNum() && q.getArrivalTime()<queue.get(0).getArrivalTime())){
                    while(queue.get(0).getArrivalTime() != minArrivalTime){
                        queue.add(queue.get(0));
                        queue.remove(queue.get(0));
                    }
                }
                else {
                    continue;
                }
            }
            else {
                continue;
            }
        }
    }
}
