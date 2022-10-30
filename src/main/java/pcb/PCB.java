package pcb;

public class PCB {
    private String name;
    //要求运行时间：1-5取随机数
    private int runTime;
    //优先级：1-10取随机数
    private int priorityNum;
    //进程状态:就绪W（Wait）、准备（Ready）或完成E（End）
    private String state ="R";
    //到达时间：1-5取随机数
    private int arrivalTime;

    //无参构造
    public PCB() {
    }
    public PCB(String name) {
        this.name = name;
        this.setRunTime(runTime);
        this.setPriorityNum(priorityNum);
        this.setState(state);
        this.setArrivalTime(arrivalTime);
    }

    //打印信息
    public void printInformation() {
        System.out.println(
                this.getName() + "\t\t"+this.getArrivalTime()+ "\t\t" + this.getRunTime() + "\t\t" +
                        this.getPriorityNum() + "\t\t" + this.getState());
    }

    //PCB工作
    public void run() {
        if(priorityNum < 7)
          this.priorityNum++;
        if(runTime > 0)
        this.runTime--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public int getRunTime() {
        if(runTime<0){
            return 0;
        } else{
            return runTime;
        }
    }

    public void setRunTime(int runTime) {
        runTime = (int) (Math.random() * 9 + 1);
        this.runTime = runTime;
    }

    public int getPriorityNum() {return priorityNum;}

    public void setPriorityNum(int priorityNum) {
        priorityNum = (int) (Math.random()*8);
        this.priorityNum = priorityNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        arrivalTime = (int)(Math.random()*5+1);
        this.arrivalTime = arrivalTime;
    }
}
