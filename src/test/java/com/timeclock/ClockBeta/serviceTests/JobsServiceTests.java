package com.timeclock.ClockBeta.serviceTests;

import com.timeclock.web.ClockBeta.ClockBetaApplication;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.repository.JobsRepository;
import com.timeclock.web.ClockBeta.service.JobsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClockBetaApplication.class)
public class JobsServiceTests {

    @Autowired
    JobsService jobsService;

    @Autowired
    JobsRepository jobsRepository;

    @Test
    public void testDeleteJob() {
        LocalDate localDate = LocalDate.now();
        Jobs job = new Jobs(1, "abc", "abc", 123.1, 123.1, "abc", "abc", 123.11, 123.11, 123.11, false, 123.11, 123.11, localDate);
        jobsService.saveJob(job);
        jobsRepository.removeJobsById(job.getId());
        Assert.assertTrue(jobsService.findJobById(job.getId()) == null);
    }
}
