package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Schedule;
import com.timeclock.web.ClockBeta.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ClockService clockService;

    @Autowired
    JobsService jobsService;

    public Iterable<Schedule> getScheduleByClockId(int clockId) {
        return scheduleRepository.findScheduleByClockId(clockId);
    }

    public Iterable<Schedule> getScheduleByJobId(int jobId) {
        return scheduleRepository.findScheduleByClockId(jobId);
    }

    public Iterable<Schedule> getScheduleByBizId(int bizId) {
        return scheduleRepository.findScheduleByBizId(bizId);
    }

    public Iterable<Integer> getJobIdsByClockId(int id) {
        return scheduleRepository.findJobIdsByClockId(id);
    }

    public Iterable<Jobs> findJobsAssignedToEmployee(int id) {
        ArrayList<Jobs> jobs = new ArrayList<Jobs>();
        for (int jobId : getJobIdsByClockId(id)) {
            if (jobsService.findJobById(jobId) != null) {
                jobs.add(jobsService.findJobById(jobId));
            }
        }
        return jobs;
    }

    public Iterable<Clock> findAllEmployeesOnJob(int jobId) {
        ArrayList<Clock> clock = new ArrayList<Clock>();
        for (int employee : scheduleRepository.findClockIdsByJobId(jobId)) {
            clock.add(clockService.findEmployeeById(employee));
        }
        return clock;
    }

    public void addToJobs(List<Schedule> schedules) {
        for (Schedule s : schedules) {
            this.saveSchedule(s);
        }
    }

    public boolean checkIfExists(int clockId, int jobId) {
        return scheduleRepository.existsByClockIdAndJobId(clockId, jobId);
    }

    public void delete(Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

}
