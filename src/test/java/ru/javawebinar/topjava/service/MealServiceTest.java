package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.of(2019, Month.MARCH, 1, 10, 0, 0),
                "Завтрак", 500);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), USER_MEAL1, USER_MEAL2, USER_MEAL3, USER_MEAL4, USER_MEAL5, USER_MEAL6, newMeal);
    }

    @Test
    public void get() {
        Meal actual = service.get(USER_MEAL1_ID, USER_ID);
        assertMatch(actual, USER_MEAL1);
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), USER_MEAL2, USER_MEAL3, USER_MEAL4, USER_MEAL5, USER_MEAL6);
    }

    @Test
    public void getBetweenDates() {
        LocalDate startDate = LocalDateTime.of(2015, Month.MAY, 30, 0, 0, 0).toLocalDate();
        LocalDate endDate = LocalDateTime.of(2015, Month.MAY, 30, 0, 0, 0).toLocalDate();
        assertMatch(service.getBetweenDates(startDate, endDate, USER_ID), USER_MEAL1, USER_MEAL2, USER_MEAL3);
    }

    @Test
    public void getBetweenDateTimes() {
        LocalDateTime startDateTime = LocalDateTime.of(2015, Month.MAY, 30, 8, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2015, Month.MAY, 30, 21, 0, 0);
        assertMatch(service.getBetweenDateTimes(startDateTime, endDateTime, USER_ID), USER_MEAL1, USER_MEAL2, USER_MEAL3);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL1, ADMIN_MEAL2);
    }

    @Test
    public void update() {
        Meal updated = new Meal(USER_MEAL1);
        updated.setDescription("updated description");
        updated.setCalories(1234);
        updated.setDateTime(LocalDateTime.now());
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL1_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(USER_MEAL1_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(USER_MEAL1_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        service.update(USER_MEAL1, ADMIN_ID);
    }

}