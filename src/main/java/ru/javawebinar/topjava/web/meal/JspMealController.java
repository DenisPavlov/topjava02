package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class JspMealController extends MealBaseController {
    private static final Logger log = LoggerFactory.getLogger(JspMealController.class);

    protected JspMealController(MealService service) {
        super(service);
    }

    @GetMapping("/meals")
    public String meals(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/meals/add")
    public String addMeal(Model model){
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("/meals")
    public String setMeal(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        int userId = SecurityUtil.authUserId();
        checkNew(meal);
        log.info("create {} for user {}", meal, userId);
        service.create(meal, userId);
        return "redirect:meals";
    }

}
