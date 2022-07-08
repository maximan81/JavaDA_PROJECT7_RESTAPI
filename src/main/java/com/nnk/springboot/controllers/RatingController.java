package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * RatingController. class that manage
 * request/response logic of rating.
 */
@Controller
public class RatingController {

    private static final Logger log = LoggerFactory.getLogger(CurveController.class);
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    /**
     * home. Method that display a rating home page.
     *
     * @param model a model
     * @return rating/list view
     */
    @RequestMapping("/user/rating/list")
    public String home(Model model)
    {
        Iterable<Rating> ratings = ratingService.getRatings();

        model.addAttribute("ratings", ratings);

        log.info("Request GET for displaying rating/list page "
                + " SUCCESS(200 OK)");

        return "rating/list";
    }

    /**
     * addRatingForm. Method that display an add rating form page.
     *
     * @param model a model
     * @param rating a rating
     * @return rating/add view
     */
    @GetMapping("/user/rating/add")
    public String addRatingForm(Model model, Rating rating) {

        model.addAttribute("rating", rating);

        log.info("Request GET for displaying rating/add page "
                + " SUCCESS(200 OK)");

        return "rating/add";
    }

    /**
     * validate. Method that validate an add rating form
     * and perform post request for adding new rating.
     *
     * @param rating a rating
     * @param result a BindingResult
     * @return rating/list view
     */
    @PostMapping("/user/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        if (result.hasErrors()){

            log.error("Request POST for validation of rating/add form, {}",
                    result);

            return "rating/add";
        }

        ratingService.saveRating(rating);

        log.info("Request POST for successful rating/add"
                + " SUCCESS(200 OK)");

        return "redirect:/user/rating/list";
    }

    /**
     * showUpdateForm. Method that display update rating page.
     *
     * @param id a rating id
     * @param model a model
     * @return rating/update view
     */
    @GetMapping("/user/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Rating> rating = ratingService.getRating(id);

        if (rating.isEmpty()) {

            log.error("Error for displaying rating/update page "
                    + " redirection to rating list page");

            return "redirect:/user/rating/list";
        }

        model.addAttribute("rating", rating.get());

        log.info("Request GET for displaying rating/update page "
                + " SUCCESS(200 OK)");

        return "rating/update";
    }

    /**
     * updateRating. Method that validate an add rating form
     * and perform post request for updating rating.
     *
     * @param rating a rating
     * @param result a BindingResult
     * @return rating/list view
     */
    @PostMapping("/user/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {

            log.error("Request POST for validation of rating/update form for rating "
                            +
                            "id: {}, errors: {}",
                    id, result);

            return "rating/update";
        }

        ratingService.saveRating(rating);

        log.info("Request POST for successful rating/update"
                + " SUCCESS(200 OK)");

        return "redirect:/user/rating/list";
    }

    /**
     * deleteRating. Method that delete rating in database.
     *
     * @param id a rating id
     * @return rating/list view
     */
    @GetMapping("/user/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

        Optional<Rating> rating = ratingService.getRating(id);

        if (rating.isEmpty()) {

            log.error("Error for deleting rating"
                    + "redirection to rating list page");

            return "redirect:/user/rating/list";
        }

        ratingService.deleteRating(rating.get());

        log.info("Request GET for successful rating/delete"
                + " SUCCESS(200 OK)");

        return "redirect:/user/rating/list";
    }
}
