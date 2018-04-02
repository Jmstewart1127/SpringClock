package com.timeclock.web.ClockBeta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.timeclock.web.ClockBeta.model.User;
import com.timeclock.web.ClockBeta.model.UserRole;
import com.timeclock.web.ClockBeta.repository.UserRepository;
import com.timeclock.web.ClockBeta.repository.UserRoleRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public void saveUser(User user) {
        User userExists = findById(user.getId());
        if (userExists == null) {
            user.setEnabled(true);
            userRepository.save(user);
        }
    }

    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public int getIdByUserName(String userName) {
        return userRepository.findIdByUserName(userName);
    }

    public User findIdByCredentials(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }
}
