package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Schedule;
import com.timeclock.web.ClockBeta.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleRestController {

    @Autowired
    ScheduleService scheduleService;


    @CrossOrigin(origins = {"http://localhost:4200", "https://spring-clock-ui.herokuapp.com"})
    @RequestMapping("/rest/jobs/employee/schedule/{clockId}")
    public Iterable<Schedule> showJobsAssignedToEmployeeById(@PathVariable int clockId) {
        return scheduleService.getScheduleByClockId(clockId);
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://spring-clock-ui.herokuapp.com"})
    @RequestMapping("/rest/jobs/assigned/employee/{clockId}")
    public Iterable<Jobs> showJobAddressesAssignedToEmployee(@PathVariable int clockId) {
        return scheduleService.findJobsAssignedToEmployee(clockId);
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://spring-clock-ui.herokuapp.com"})
    @RequestMapping("/rest/jobs/employee/schedule/jobs/{clockId}")
    public Iterable<Integer> showJobIdsByEmployeeId(@PathVariable int clockId) {
        return scheduleService.getJobIdsByClockId(clockId);
    }


    @CrossOrigin(origins = {"http://localhost:4200", "https://spring-clock-ui.herokuapp.com"})
    @RequestMapping("/rest/jobs/assigned/employees/{jobId}")
    public Iterable<Clock> showAllEmployeesAssignedToJob(@PathVariable int jobId) {
        return scheduleService.findAllEmployeesOnJob(jobId);
    }
}
