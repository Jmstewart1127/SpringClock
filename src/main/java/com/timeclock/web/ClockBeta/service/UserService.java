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
        User userExists = findUserByUserName(user.getUserName());
        if (userExists == null) {
            user.setEnabled(true);
            createUserRole(user.getUserName(), user.getRole());
            userRepository.save(user);
        }
    }

    private void createUserRole(String username, String role) {
        UserRole userRole = new UserRole();
        userRole.setUserName(username);
        userRole.setRole(role);
        saveUserRole(userRole);
    }

    private void saveUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    private User findUserByUserName(String username) {
        return userRepository.findUserByUserName(username);
    }

    public int getIdByUserName(String userName) {
        return userRepository.findIdByUserName(userName);
    }

    public User findIdByCredentials(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }
}
