package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class UserUtil {
    public static List<User> USERS = Arrays.asList(
            new User(null, "Denis", "denis@mail.com", "123qwe", Role.ROLE_ADMIN),
            new User(null, "Anna", "anna@mail.ru", "321ewq", 2000, true,
                    new HashSet<>(Collections.singletonList(Role.ROLE_USER)))
    );


}
