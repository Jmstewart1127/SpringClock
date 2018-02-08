package com.timeclock.web.ClockBeta.service;

import java.util.ArrayList;
import java.util.Date;

import com.timeclock.web.ClockBeta.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.timeclock.web.ClockBeta.logistics.ClockLogic;
import com.timeclock.web.ClockBeta.logistics.UserAuthDetails;
import com.timeclock.web.ClockBeta.model.Clock;
import com.timeclock.web.ClockBeta.repository.ClockRepository;

@Service
public class ClockService {

    @Autowired
    ClockRepository clockRepository;

    @Autowired
    BusinessService businessService;

    @Autowired
    HistoryService historyService;

    @Autowired
    JobsService jobsService;

    @Autowired
    ClockLogic clockLogic;

    @Autowired
    UserAuthDetails userAuthDetails;

    public void handleClockInOut(int id) {
        if (this.findClockedInById(id)) {
            this.clockOut(id);
        } else {
            this.clockIn(id);
        }
    }

    public void clockIn(int id) {
        if (!this.findClockedInById(id)) {
            clockRepository.updateClock(id, new Date(), new Date());
        }
    }

    public void clockOut(int id) {
        clockOutIfClockedInWithoutJobId(id);
    }

    public void clockInAtJob(int id, int jobId) {
        clockInIfClockedOut(id, jobId);
    }

    private void clockInIfClockedOut(int employeeId, int jobId) {
        if (!this.findClockedInById(employeeId)) {
            clockRepository.clockIn(employeeId, jobId, new Date(), new Date());
        }
    }

    public void clockOutFromJob(int id, int jobId) {
        clockOutIfClockedIn(id, jobId);
    }

    private void handleClockOut(int employeeId, int jobId) {
        initializeClockLogicForEmployee(employeeId);
        updateEmployeeDataUponClockOut(employeeId, jobId);
        updateLaborCost(jobId);
        updateHistory(employeeId);
    }

    private void clockOutIfClockedIn(int employeeId, int jobId) {
        if (findClockedInById(employeeId)) {
            handleClockOut(employeeId, jobId);
        }
    }

    private void updateEmployeeDataUponClockOut(int employeeId, int jobId) {
        clockRepository.clockOut(
            employeeId,
            jobId,
            clockLogic.getEndTime(),
            clockLogic.getShiftTime(),
            clockLogic.getUpdatedWeekTime(),
            clockLogic.getUpdatedWeekTimeInHours(),
            clockLogic.getWeeklyPay()
        );
    }

    private void handleClockOutWithoutJobId(int employeeId) {
        initializeClockLogicForEmployee(employeeId);
        updateEmployeeDataUponClockOutWithoutUsingJobId(employeeId);
        updateHistory(employeeId);
    }

    private void clockOutIfClockedInWithoutJobId(int employeeId) {
        if (findClockedInById(employeeId)) {
            handleClockOutWithoutJobId(employeeId);
        }
    }

    private void updateEmployeeDataUponClockOutWithoutUsingJobId(int employeeId) {
        clockRepository.updateClock(
            employeeId,
            clockLogic.getEndTime(),
            clockLogic.getShiftTime(),
            clockLogic.getUpdatedWeekTime(),
            clockLogic.getUpdatedWeekTimeInHours(),
            clockLogic.getWeeklyPay()
        );
    }

    private void initializeClockLogicForEmployee(int employeeId) {
        clockLogic.initializeClockLogic(
            findLastRefreshTimeById(employeeId),
            newEndTime(),
            findCurrentWeekTimeById(employeeId),
            findPayRateById(employeeId)
        );
    }

    private Date newEndTime() {
        return new Date();
    }

    private void updateLaborCost(int jobId) {
        jobsService.updateLaborCost(jobId, clockLogic.getShiftPay());
    }

    private void updateHistory(int employeeId) {
        historyService.saveHistory(
            employeeId,
            clockLogic.getStartTime(),
            clockLogic.getEndTime(),
            clockLogic.getShiftTime()
        );
    }

    private void updateEmployeeDataUponRefresh(int employeeId, int jobId) {
        clockRepository.refreshClockWithJobId(
            employeeId,
            jobId,
            new Date(),
            clockLogic.getShiftTime(),
            clockLogic.getUpdatedWeekTime(),
            clockLogic.getUpdatedWeekTimeInHours(),
            clockLogic.getWeeklyPay()
        );
    }

    private void handleRefresh(int employeeId, int jobId) {
        initializeClockLogicForEmployee(employeeId);
        updateEmployeeDataUponRefresh(employeeId, jobId);
        updateLaborCost(jobId);
        updateHistory(employeeId);
    }

    public void refreshClockAndAddLabor(int id) {
        if (findClockedInById(id)) {
            handleRefresh(id, findClockedInAtById(id));
        }
    }

    public Iterable<Clock> findAllEmployeesByAdmin(Authentication auth) {
        Iterable<Business> usersBusinesses = businessService.findByCurrentUserId(auth);
        ArrayList<Clock> allEmployees = new ArrayList<Clock>();
        for (Business business : usersBusinesses) {
            Iterable<Clock> employeesFound = this.findByBizId(business.getId());
            for (Clock clocks : employeesFound) {
                allEmployees.add(clocks);
            }
        }
        return allEmployees;
    }

    public Iterable<Clock> findAllEmployeesByAdminId(int id) {
        Iterable<Business> usersBusinesses = businessService.findBusinessesByUserId(id);
        ArrayList<Clock> allEmployees = new ArrayList<Clock>();
        for (Business business : usersBusinesses) {
            Iterable<Clock> employeesFound = this.findByBizId(business.getId());
            for (Clock clocks : employeesFound) {
                allEmployees.add(clocks);
            }
        }
        return allEmployees;
    }

    public void deleteById(int id) {
        clockRepository.delete(findEmployeeById(id));
    }

    private Date findLastRefreshTimeById(int id) {
        return clockRepository.findLastRefreshById(id);
    }

    private long findCurrentWeekTimeById(int id) {
        return clockRepository.findWeekTimeById(id);
    }

    private double findPayRateById(int id) {
        return clockRepository.findPayRateById(id);
    }

    public void resetPayPeriod(int bizId) {
        clockRepository.resetClock(bizId);
    }

    public Iterable<Clock> findByBizId(int bizId) {
        return clockRepository.findByBizId(bizId);
    }

    public Boolean findClockedInById(int id) {
        return clockRepository.findClockedById(id);
    }

    public Iterable<Clock> findById(int id) {
        return clockRepository.findById(id);
    }

    public Clock findEmployeeById(int id) {
        return clockRepository.findUserById(id);
    }

    private int findClockedInAtById(int id) {
        return clockRepository.findClockedInAtById(id);
    }

    public void delete(Clock clock) {
        clockRepository.delete(clock);
    }

    public void saveClock(Clock clock) {
        clockRepository.save(clock);
    }

    public int findBizIdById(int id) {
        return clockRepository.findBizIdById(id);
    }
}
