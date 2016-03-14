package jbr.schedulers.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;

public class MyJobListener implements JobListener {
  static int completedJobCount;
  public static long jobStartTime;

  @Override
  public String getName() {
    // define your own job name.
    return "MYJOB";
  }

  @Override
  public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
    // Not sure user of this method.
    System.out.println("Entering - jobExecutionVetoed()");
  }

  @Override
  public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
    // Entry point for every Job
    System.out.println("\nEntering - jobToBeExecuted()");

    System.out.println("Job Name: " + jobExecutionContext.getJobDetail().getKey().getName());

    System.out.println("Exiting - jobToBeExecuted()");

  }

  @Override
  public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException jobExecutionException) {
    // Exit point for every job
    System.out.println("Entering - jobWasExecuted()");
    String jobName = jobExecutionContext.getJobDetail().getKey().getName();

    System.out.println("Job Name: " + jobName);

    // Stop the scheduler once its completes all the scheduled jobs.
    completedJobCount = completedJobCount + 1;

    System.out
        .println("Job: " + jobName + " completed in " + (System.currentTimeMillis() - jobStartTime) + " milliseconds.");

    if (completedJobCount == MyJobConstants.JOB_REPEAT_COUNT) {

      try {
        jobExecutionContext.getScheduler().shutdown();
      } catch (SchedulerException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    System.out.println("Exiting - jobWasExecuted()");

  }
}