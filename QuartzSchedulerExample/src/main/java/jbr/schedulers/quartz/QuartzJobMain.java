package jbr.schedulers.quartz;

public class QuartzJobMain {

  public static void main(String[] args) throws Exception {
    MyScheduler myScheduler = new MyScheduler();
    myScheduler.scheduleJob();
  }
}
