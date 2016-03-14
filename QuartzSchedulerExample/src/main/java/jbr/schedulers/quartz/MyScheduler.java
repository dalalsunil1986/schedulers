package jbr.schedulers.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class MyScheduler {
  // Set Job details
  public void scheduleJob() throws Exception {

    JobKey jobKey = new JobKey("RanjithJob", "RanjithGroup");
    JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(jobKey).build();

    /**
     * Set required values to the JobDetail object, so that it can be retrieved
     * when executing the job.
     */
    jobDetail.getJobDataMap().put("FirstName", "Ranjith");
    jobDetail.getJobDataMap().put("LastName", "Sekar");
    jobDetail.getJobDataMap().put("City", "Chennai");

    // Setting the start time of the job.
    MyJobListener.jobStartTime = System.currentTimeMillis();
    // Trigger the Job
    triggerJob(jobDetail);

  }

  // Trigger the job
  private void triggerJob(JobDetail jobDetail) throws Exception {
    TriggerKey triggerKey = new TriggerKey("MyTriggerKey", "MyTriggerGroup" + System.currentTimeMillis());

    /**
     * Create Trigger and mention schedule details like: when it should trigger
     * and how many times it should execute
     */
    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(SimpleScheduleBuilder
        .repeatSecondlyForTotalCount(MyJobConstants.JOB_REPEAT_COUNT, MyJobConstants.JOB_REPEAT_INTERVAL)).build();

    // Create Scheduler
    Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    scheduler.getListenerManager().addJobListener(new MyJobListener());
    scheduler.start();

    scheduler.scheduleJob(jobDetail, trigger);
  }
}
