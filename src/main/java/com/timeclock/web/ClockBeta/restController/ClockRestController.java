package com.timeclock.web.ClockBeta.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.service.ClockService;

@RestController
public class ClockRestController {

    @Autowired
    ClockService clockService;

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/employees/{id}")
    public Iterable<Clock> showAllEmployeesByBizId(@PathVariable int id) {
        return clockService.findByBizId(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/get/employee/{id}")
    public Iterable<Clock> getEmployeeByIdForMobileApp(@PathVariable int id) {
        return clockService.findById(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/employee/{id}")
    public Clock getEmployeeById(@PathVariable int id) {
        return clockService.findEmployeeById(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/get/all/employees/{id}")
    public Iterable<Clock> getEmployeesByAdminId(@PathVariable int id) {
        return clockService.findAllEmployeesByAdminId(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/web/clock/in/out/{id}")
    public void handleClockInAndOut(@PathVariable int id) {
        clockService.handleClockInOut(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/clock/in/{id}")
    public void clockInForMobileApp(@PathVariable int id) {
        clockService.clockIn(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/clock/out/{id}")
    public void clockOutForMobileApp(@PathVariable int id) {
        clockService.clockOut(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/clock/in/{id}/{jobId}")
    public void clockInAtJobSiteForMobileApp(@PathVariable int id, @PathVariable int jobId) {
        clockService.clockInAtJob(id, jobId);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/clock/out/{id}/{jobId}")
    public void clockOutFromJobSiteForMobileApp(@PathVariable int id, @PathVariable int jobId) {
        clockService.clockOutFromJob(id, jobId);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/status/refresh/{id}")
    public void refreshEmployeeStatusWithJobId(@PathVariable int id) {
        clockService.refreshClockAndAddLabor(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/clock/delete/{id}")
    public void deleteEmployeeById(@PathVariable int id) {
        clockService.deleteById(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/employee/add", method = RequestMethod.POST)
    public ResponseEntity<String> addNewEmployee(@RequestBody Clock clock) {
        clockService.saveClock(clock);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
