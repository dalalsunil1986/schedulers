package jbr.schedulers.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    // Write/call your actual business functions.
    for (int i = 0; i <= 1000; i++) {
      // just to show some delay for job.
    }
    System.out.println("****My Details*******");
    JobDataMap jobDetails = jobExecutionContext.getJobDetail().getJobDataMap();
    System.out.println("First Name: " + jobDetails.getString("FirstName"));
    System.out.println("Last Name: " + jobDetails.getString("LastName"));
    System.out.println("City Name: " + jobDetails.getString("City"));
  }

}