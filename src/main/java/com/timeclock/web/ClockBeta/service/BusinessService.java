package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.logistics.UserAuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.repository.BusinessRepository;

@Service
public class BusinessService {

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    UserAuthDetails userAuthDetails;

    public Business findBusinessById(int id) {
        return businessRepository.findById(id);
    }

    public Business findByBizName(String bizName) {
        return businessRepository.findByBizName(bizName);
    }

    public Iterable<Business> findByCurrentUserId(Authentication auth) {
        return businessRepository.findByAdminId(userAuthDetails.getUserId(auth));
    }

    public Iterable<Business> findBusinessesByUserId(int id) {
        return businessRepository.findByAdminId(id);
    }

    public void saveBusiness(Business business) {
        businessRepository.save(business);
    }

    public void updateYtdLaborCost(int businessId, double additionalLaborCost) {
        double currentLaborCost = businessRepository.findYtdLaborCostById(businessId);
        double newLaborCost = currentLaborCost + additionalLaborCost;
        businessRepository.updateYtdLaborCost(businessId, newLaborCost);
    }

    public void updateYtdMaterialCost(int businessId, double additionalMaterialCost) {
        double currentMaterialCost = businessRepository.findYtdMaterialCostById(businessId);
        double newMaterialCost = currentMaterialCost + additionalMaterialCost;
        businessRepository.updateYtdMaterialCost(businessId, newMaterialCost);
    }

    public void delete(Business business) {
        businessRepository.delete(business);
    }

}
