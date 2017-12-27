package com.timeclock.web.ClockBeta.repository;

import com.timeclock.web.ClockBeta.model.Jobs;
import com.timeclock.web.ClockBeta.model.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Iterable<Schedule> findScheduleByBizId(int bizId);

    Iterable<Schedule> findScheduleByClockId(int clockId);

    Iterable<Schedule> findScheduleByClockIdAndJobId(int clockId, int jobId);

    boolean existsByClockIdAndJobId(int clockId, int jobId);

    @Query("SELECT jobId FROM com.timeclock.web.ClockBeta.model.Schedule"
            + " WHERE clockId= :clockId")
    Iterable<Integer> findJobIdsByClockId(@Param("clockId")int clockId);



}
