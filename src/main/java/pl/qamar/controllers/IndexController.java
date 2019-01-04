package pl.qamar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.qamar.domain.Category;
import pl.qamar.domain.UnitOfMeasure;
import pl.qamar.repositories.CategoryRepository;
import pl.qamar.repositories.UnitOfMeasureRepository;
import pl.qamar.services.RecipeService;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
