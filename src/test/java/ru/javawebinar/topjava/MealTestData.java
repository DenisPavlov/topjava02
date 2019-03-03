package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL1_ID = START_SEQ + 2;
    public static final int USER_MEAL2_ID = START_SEQ + 3;
    public static final int USER_MEAL3_ID = START_SEQ + 4;
    public static final int USER_MEAL4_ID = START_SEQ + 5;
    public static final int USER_MEAL5_ID = START_SEQ + 6;
    public static final int USER_MEAL6_ID = START_SEQ + 7;
    public static final int ADMIN_MEAL1_ID = START_SEQ + 8;
    public static final int ADMIN_MEAL2_ID = START_SEQ + 9;

    public static final Meal USER_MEAL1 = new Meal(USER_MEAL1_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 10,0,0), "Завтрак", 500);
    public static final Meal USER_MEAL2 = new Meal(USER_MEAL2_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 13,0,0), "Обед", 1000);
    public static final Meal USER_MEAL3 = new Meal(USER_MEAL3_ID,
            LocalDateTime.of(2015, Month.MAY, 30, 20,0,0), "Ужин", 500);
    public static final Meal USER_MEAL4 = new Meal(USER_MEAL4_ID,
            LocalDateTime.of(2015, Month.MAY, 31, 10,0,0), "Завтрак", 500);
    public static final Meal USER_MEAL5 = new Meal(USER_MEAL5_ID,
            LocalDateTime.of(2015, Month.MAY, 31, 13,0,0), "Обед", 1000);
    public static final Meal USER_MEAL6 = new Meal(USER_MEAL6_ID,
            LocalDateTime.of(2015, Month.MAY, 31, 20,0,0), "Ужин", 510);
    public static final Meal ADMIN_MEAL1 = new Meal(ADMIN_MEAL1_ID,
            LocalDateTime.of(2015, Month.JUNE, 1, 14,0,0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL2 = new Meal(ADMIN_MEAL2_ID,
            LocalDateTime.of(2015, Month.JUNE, 1, 21,0,0), "Админ ужин", 1500);

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}