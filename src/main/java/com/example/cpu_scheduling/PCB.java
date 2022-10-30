package com.example.cpu_scheduling;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PCB {
    private final StringProperty name = new SimpleStringProperty();

    private final IntegerProperty arrivalTime = new SimpleIntegerProperty();

    private final IntegerProperty priorityNum = new SimpleIntegerProperty();

    private final IntegerProperty runTime =new SimpleIntegerProperty();

    private final StringProperty status = new SimpleStringProperty();

    public PCB(String name){
        int arrivaltime = (int)(Math.random()*5+1);
        int priorityNum = (int)(Math.random()*8);
        int runTime = (int)(Math.random()*10+1);

        this.setName(name);
        this.setArrivalTime(arrivaltime);
        this.setPriorityNum(priorityNum);
        this.setRunTime(runTime);
        this.setStatus("Ready");
    }


    public StringProperty nameProperty(){
        return name;
    }
    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public IntegerProperty arrivalTimeProperty(){
        return arrivalTime;
    }
    public int getArrivalTime()
    {
        return arrivalTime.get();
    }

    public void setArrivalTime(int arrivalTime){
        this.arrivalTime.set(arrivalTime);
    }

    public IntegerProperty priorityNumProperty(){
        return priorityNum;
    }
    public int getPriorityNum(){ return priorityNum.get(); }

    public void setPriorityNum(int priorityNum){
        this.priorityNum.set(priorityNum);
    }

    public IntegerProperty runTimeProperty(){
        return runTime;
    }
    public int  getRunTime(){
        return runTime.get();
    }

    public void setRunTime(int runTime){
        this.runTime.set(runTime);
    }

    public StringProperty statusProperty(){
        return status;
    }
    public String getStatus(){
        return status.get();
    }

    public void setStatus(String status){
        this.status.set(status);
    }

    public void run(){
        int num = priorityNum.intValue();
        int time = runTime.intValue();
        if(num == 7)
            ;
        else{
            num++;
            priorityNum.set(num);
        }
        if(time == 0)
            ;
        else {
            time--;
            runTime.set(time);
        }
    }
}
