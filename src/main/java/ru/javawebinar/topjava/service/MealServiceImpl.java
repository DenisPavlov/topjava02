package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return new ArrayList<>(repository.getAll(userId));
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void update(Meal meal, int userId) {
        checkNotFoundWithId(repository.save(meal), userId);
    }
}