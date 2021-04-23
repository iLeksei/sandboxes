package com.example.demo.activiti;

import com.example.demo.entities.User;
import org.springframework.stereotype.Component;

@Component("ActivitiUtils")
public class ActivitiUtils {

    public boolean needToCallSubFlow(User user) {
        return Long.valueOf(1).equals(user.getId());
    }

}
