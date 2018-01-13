/**
 * 
 */
package com.timeclock.web.ClockBeta.logistics;

import java.util.Date;

/**
 * @author Jacob Stewart
 * 
 * Calculates employee hours when clocking in and out.
 *
 */
public class ClockLogic {

	private Date startTime;
	private Date endTime;
	private long shiftTime;
	private long currentWeekTime;
	private long updatedWeekTime;
	private double updatedWeekTimeInHours;
	private double payRate;
	private double shiftPay;
	private double weeklyPay;
	
	public ClockLogic() {}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(long shiftTime) {
		this.shiftTime = shiftTime;
	}

	public long getCurrentWeekTime() {
		return currentWeekTime;
	}

	public void setCurrentWeekTime(long currentWeekTime) {
		this.currentWeekTime = currentWeekTime;
	}

	public long getUpdatedWeekTime() {
		return updatedWeekTime;
	}

	public void setUpdatedWeekTime(long updatedWeekTime) {
		this.updatedWeekTime = updatedWeekTime;
	}

	public double getUpdatedWeekTimeInHours() {
		return updatedWeekTimeInHours;
	}

	public void setUpdatedWeekTimeInHours(double updatedWeekTimeInHours) {
		this.updatedWeekTimeInHours = updatedWeekTimeInHours;
	}

	public double getPayRate() {
		return payRate;
	}

	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	public double getWeeklyPay() {
		return weeklyPay;
	}

	public double getShiftPay() {
		return shiftPay;
	}

	public void setShiftPay(double shiftPay) {
		this.shiftPay = shiftPay;
	}

	public void setWeeklyPay(double weeklyPay) {
		this.weeklyPay = weeklyPay;
	}

	public void initializeClockLogic(Date startTime, Date endTime, long currentWeekTime, double payRate) {
		this.setStartTime(startTime);
		this.setEndTime(endTime);
		this.setCurrentWeekTime(currentWeekTime);
		this.setPayRate(payRate);
		this.calculateAll();
	}

	private void calculateAll() {
		this.calculateWeeklyTime();
		this.calculateShiftTime();
		this.calculateShiftPay();
		this.convertWeekTimeToHours();
		this.calculateUpdatedWeeklyPay();
	}

	public void calculateWeeklyTime() {
		long shift = this.getShiftTime();
		shift += this.getCurrentWeekTime();
		setUpdatedWeekTime(shift);
	}
	
	private void calculateShiftTime() {
		setShiftTime(this.getEndTime().getTime() - this.getStartTime().getTime());
	}

	private void calculateShiftPay() {
		setShiftPay(this.getShiftTime() * this.getPayRate());
	}
	
	private void convertWeekTimeToHours() {
		double weekTimeInHours = (double)Math.round(((double)this.getUpdatedWeekTime() / 3600000) * 100d) / 100d;
		this.setUpdatedWeekTimeInHours(weekTimeInHours);
	}

	private void calculateUpdatedWeeklyPay() {
		double updatedWeeklyPay = this.getUpdatedWeekTime() * this.getPayRate();
		this.setWeeklyPay(updatedWeeklyPay);
	}

}
