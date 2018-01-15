package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobsRestController {

    @Autowired
    JobsService jobsService;


    @CrossOrigin(origins = {"http://localhost:4200", "https://spring-clock-ui.herokuapp.com"})
    @RequestMapping("/rest/jobs/address/{bizId}")
    public Iterable<Jobs> showJobAddressByBizId(@PathVariable int bizId) {
        return jobsService.findAddressByBizId(bizId);
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://spring-clock-ui.herokuapp.com"})
    @RequestMapping("/rest/jobs/all/{bizId}")
    public Iterable<Jobs> showJobsByBizId(@PathVariable int bizId) {
        return jobsService.findByBizId(bizId);
    }

}
