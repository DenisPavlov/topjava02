package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    List<Meal> getAll(int userId) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    Meal create(Meal meal);

    void update(Meal meal, int userId);
}