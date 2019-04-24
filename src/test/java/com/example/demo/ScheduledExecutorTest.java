package com.example.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

	public static void main(String[] args) {
		  ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
	        
	        long initialDelay = 1;
	        long period = 1;
	        service.scheduleAtFixedRate(new MyScheduledExecutor("job1"), 0, 10, TimeUnit.SECONDS);
	        
	        service.scheduleWithFixedDelay(new MyScheduledExecutor("job2"),0, 10, TimeUnit.SECONDS);
	}

}
class MyScheduledExecutor implements Runnable {
    
    private String jobName;
    
    MyScheduledExecutor() {
        
    }
    
    MyScheduledExecutor(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        
        System.out.println(jobName + " is running");
    }
}
