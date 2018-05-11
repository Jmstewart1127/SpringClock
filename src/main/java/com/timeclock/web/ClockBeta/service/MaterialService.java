package com.timeclock.web.ClockBeta.service;

import com.timeclock.web.ClockBeta.logistics.MaterialCostLogic;
import com.timeclock.web.ClockBeta.model.Material;
import com.timeclock.web.ClockBeta.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {

    @Autowired
    JobsService jobsService;

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    MaterialCostLogic materialCostLogic;

    public Iterable<Material> findByJobId(int jobId) {
        return materialRepository.findByJobId(jobId);
    }

    public void saveMaterial(Material material) {
        jobsService.updateMaterialCost(material.getJobId(), material.getTotalPrice());
        materialRepository.save(material);
    }

    public void deleteMaterial(Material material) {
        materialRepository.delete(material);
    }

    public double calculateTotalPrice(int quantity, double price) {
        return materialCostLogic.storeTotalPrice(quantity, price);
    }

    public double totalPriceOfAllJobMaterials(int jobId) {
        Iterable<Material> materials = materialRepository.findByJobId(jobId);
        double totalCost = 0;
        for (Material material : materials) {
            totalCost += material.getTotalPrice();
        }
        return Math.round(totalCost * 100d) / 100d;
    }
}
