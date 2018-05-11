package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.timeclock.web.ClockBeta.logistics.PaymentLogic;
import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.repository.JobsRepository;
import java.util.ArrayList;

@Service
public class JobsService {

    @Autowired
    BusinessService businessService;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    ScheduleService scheduleService;

    public Jobs findJobById(int id) {
        return jobsRepository.findById(id);
    }

    public Iterable<Jobs> findAll() {
        return jobsRepository.findAll();
    }

    public Iterable<Jobs> findByBizId(int bizId) {
        return jobsRepository.findByBizId(bizId);
    }

    public Iterable<Jobs> findAddressByBizId(int bizId) {
        return jobsRepository.findAddressByBizId(bizId);
    }

    public Jobs findByCategory(String category) {
        return jobsRepository.findByCategory(category);
    }

    public Jobs findByCustomerName(String customer) {
        return jobsRepository.findByCustomerName(customer);
    }

    private double findTotalAmountChargedById(int id) {
        return jobsRepository.findAmountChargedById(id);
    }

    private double findBalanceDueById(int id) {
        return jobsRepository.findAmountDueById(id);
    }

    private double findAmountPaidById(int id) {
        return jobsRepository.findAmountPaidById(id);
    }

    private void updateAmountDueById(int id, double amountPaid, double amountDue) {
        jobsRepository.updateAmountDue(id, amountPaid, amountDue);
    }

    private int findIdByCustomerName(String customerName) {
        return jobsRepository.findIdByCustomerName(customerName);
    }

    public void deleteJob(int id) {
        scheduleService.deleteSchedulesByJobId(id);
        jobsRepository.deleteJob(id);
    }

    private void isPaid(int id, Boolean bool) {
        jobsRepository.isPaid(id, bool);
    }

    public double findMaterialCostById(int id) {
        return jobsRepository.findMaterialCostById(id);
    }

    public void updateMaterialCost(int id, double materialCost) {
        materialCost += findMaterialCostById(id);
        jobsRepository.updateMaterialCost(id, materialCost);
    }

    public double findLaborCostById(int id) {
        return jobsRepository.findLaborCostById(id);
    }

    public void updateLaborCost(int id, double laborAmount) {
        laborAmount += findLaborCostById(id);
        jobsRepository.updateLaborCost(id, laborAmount);
    }

    private void checkIfPaid(int id, double totalPaid) {
        if (findTotalAmountChargedById(id) == totalPaid) {
            isPaid(id, true);
        }
    }

    public void addPayment(String customerName, double amountPaid) {
        PaymentLogic pl = new PaymentLogic();
        int id = findIdByCustomerName(customerName);
        double totalAmountPaid = findAmountPaidById(id) + amountPaid;
        pl.makePayment(findBalanceDueById(id), amountPaid);
        double newAmountDue = pl.getBalanceDue();
        updateAmountDueById(id, totalAmountPaid, newAmountDue);
        checkIfPaid(id, totalAmountPaid);
    }

    public ArrayList<Jobs> findAllJobsByUserId(int userId) {
        Iterable<Business> allBusinesses = businessService.findBusinessesByUserId(userId);
        ArrayList<Jobs> allJobs = new ArrayList<>();
        for (Business business : allBusinesses) {
            Iterable<Jobs> jobsByBusiness = findByBizId(business.getId());
            for (Jobs job : jobsByBusiness) {
                allJobs.add(job);
            }
        }
        return allJobs;
    }

    public Jobs saveJob(Jobs job) {
        return jobsRepository.save(job);
    }

}
