package part2.common.service.impl;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:07
 *@Description:version1
 *@version:1.0
 */

import lombok.extern.slf4j.Slf4j;
import part2.common.pojo.User;
import part2.common.service.UserService;

import java.util.UUID;
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        log.info("getUserById is called...");
        return User.builder()
                .id(id)
                .userName(UUID.randomUUID().toString())
                .sex(true)
                .build();
    }
}
