package com.dx.mapper.user;

import com.dx.model.user.UserMain;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    UserMain getUserInfoByLoginNumber(String loginNumber);


}
