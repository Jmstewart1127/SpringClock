package com.timeclock.web.ClockBeta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "biz_id")
    private int bizId;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_address")
    private String jobAddress;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "category")
    private String category;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "amount_charged")
    private double amountCharged;
    @Column(name = "amount_due")
    private double amountDue;
    @Column(name = "amount_paid")
    private double amountPaid = 0;
    @Column(name = "is_paid")
    private Boolean isPaid = false;
    @Column(name = "material_cost")
    private double materialCost;
    @Column(name = "labor_cost")
    private double laborCost;
    @Column(name = "completion_date")
    private LocalDate completionDate;

    public Jobs() {
        super();
    }

    public Jobs(int bizId, String jobName, String jobAddress, double latitude, double longitude, String category,
                String customerName, double amountCharged, double amountDue, double amountPaid, Boolean isPaid,
                double materialCost, double laborCost, LocalDate completionDate) {
        this.bizId = bizId;
        this.jobName = jobName;
        this.jobAddress = jobAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.customerName = customerName;
        this.amountCharged = amountCharged;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.isPaid = isPaid;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.completionDate = completionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBizId() {
        return bizId;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(double amountCharged) {
        this.amountCharged = amountCharged;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(double materialCost) {
        this.materialCost = materialCost;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}
