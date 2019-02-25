package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.createWithExcess;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExcess;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

public class MealRestController {

    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    private MealService service;

    public List<MealTo> getAll() {
        log.debug("getAll");
        return getFilteredWithExcess(service.getAll(authUserId()), authUserCaloriesPerDay(), LocalTime.MIN, LocalTime.MAX);
    }

    public MealTo get(int id){
        log.debug("get {}", id);
        return createWithExcess(service.get(id, authUserId()), null);
    }

    public void delete(int id){
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public MealTo create(Meal meal){
        log.info("create {}", meal);
        Meal created = service.create(meal);
        return getAll().stream().filter(mealTo -> mealTo.getId().equals(created.getId())).findFirst().get();
    }

    public void update(Meal meal){
        log.info("update {}", meal);
        service.update(meal, authUserId());
    }


}