package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.Business;
import com.timeclock.web.ClockBeta.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusinessRestController {

    @Autowired
    BusinessService businessService;

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/user/{id}/businesses")
    public Iterable<Business> getBusinessesByUserId(@PathVariable int id) {
        return businessService.findBusinessesByUserId(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/user/businesses")
    public Iterable<Business> getBusinessesByCurrentLogin(Authentication auth) {
        System.out.println(":: " + businessService.findByCurrentUserId(auth));
        return businessService.findByCurrentUserId(auth);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/business/{id}")
    public Business getBusinessById(@PathVariable int id) {
        return businessService.findBusinessById(id);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/business/add", method = RequestMethod.POST)
    public ResponseEntity<String> addNewBusiness(@RequestBody Business business) {
        businessService.saveBusiness(business);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/business/update", method = RequestMethod.PUT)
    public ResponseEntity<String> updateBusiness(@RequestBody Business business) {
        businessService.saveBusiness(business);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/business/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBusiness(@RequestBody Business business) {
        businessService.delete(business);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
