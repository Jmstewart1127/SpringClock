package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Schedule;
import com.timeclock.web.ClockBeta.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleRestController {

    @Autowired
    ScheduleService scheduleService;

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/jobs/employee/schedule/{clockId}")
    public Iterable<Schedule> showJobsAssignedToEmployeeById(@PathVariable int clockId) {
        return scheduleService.getScheduleByClockId(clockId);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/job/schedule/{jobId}")
    public Iterable<Schedule> showSchedulesByJobId(@PathVariable int jobId) {
        return scheduleService.getSchedulesByJobId(jobId);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/mobile/jobs/assigned/employee/{clockId}")
    public Iterable<Jobs> showJobAddressesAssignedToEmployee(@PathVariable int clockId) {
        return scheduleService.findJobsAssignedToEmployee(clockId);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/jobs/employee/schedule/jobs/{clockId}")
    public Iterable<Integer> showJobIdsByEmployeeId(@PathVariable int clockId) {
        return scheduleService.getJobIdsByClockId(clockId);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/jobs/assigned/employees/{jobId}")
    public Iterable<Clock> showAllEmployeesAssignedToJob(@PathVariable int jobId) {
        return scheduleService.findAllEmployeesOnJob(jobId);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value="/rest/jobs/assign/employees", method = RequestMethod.POST)
    public ResponseEntity<String> assignEmployeesToNewJobs(@RequestBody List<Schedule> schedules) {
        scheduleService.addToJobs(schedules);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value="/rest/jobs/assign/single/employee", method = RequestMethod.POST)
    public ResponseEntity<String> assignOneEmployeeToNewJob(@RequestBody Schedule schedule) {
        scheduleService.saveSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value="/rest/schedule/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateSchedule(@RequestBody Schedule schedule) {
        scheduleService.saveSchedule(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value="/rest/schedule/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSchedule(@RequestBody Schedule schedule) {
        scheduleService.delete(schedule);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value="/rest/schedule/delete/{clockId}/{jobId}")
    public void deleteScheduleByClockIdAndJobId(
        @PathVariable int clockId, @PathVariable int jobId) {
        scheduleService.deleteByClockIdAndJobId(clockId, jobId);
    }


}
