package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Override
    public List<Meal> getAll(int userId) throws NotFoundException {
        return null;
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {

    }

    @Override
    public Meal create(Meal meal) {
        return null;
    }

    @Override
    public void update(Meal meal, int userId) {

    }
}