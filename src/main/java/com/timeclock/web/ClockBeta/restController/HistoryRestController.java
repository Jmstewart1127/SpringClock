package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.History;
import com.timeclock.web.ClockBeta.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryRestController {

    @Autowired
    HistoryService historyService;

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping("/rest/get/history/{bizId}")
    public Iterable<History> getAllHistoryByBusinessId(@PathVariable int bizId) {
        return historyService.findAllByBizId(bizId);
    }

}
