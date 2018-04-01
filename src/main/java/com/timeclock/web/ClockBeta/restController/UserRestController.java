package com.timeclock.web.ClockBeta.restController;

import com.timeclock.web.ClockBeta.model.User;
import com.timeclock.web.ClockBeta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value="/rest/login/{username}/{password}")
    public User loginByRetrievingUserId(@PathVariable String username, @PathVariable String password) {
        return userService.findIdByCredentials(username, password);
    }

    @CrossOrigin(origins = {"https://spring-clock-ui.herokuapp.com", "http://localhost:3000"})
    @RequestMapping(value = "/rest/user/create", method = RequestMethod.POST)
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
