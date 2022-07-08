package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(CurveController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * home. Method that display a user home page.
     *
     * @param model a model
     * @return user/list view
     */
    @RequestMapping("/admin/user/list")
    public String home(Model model)
    {
        Iterable<User> users = userService.getUsers();

        model.addAttribute("users", users);

        log.info("Request GET for displaying user/list page "
                + " SUCCESS(200 OK)");

        return "user/list";
    }

    /**
     * addUser. Method that display an add user form page.
     *
     * @param model a model
     * @param user a user
     * @return user/add view
     */
    @GetMapping("/admin/user/add")
    public String addUser(Model model, User user) {

        model.addAttribute("user", user);

        log.info("Request GET for displaying user/add page "
                + " SUCCESS(200 OK)");

        return "user/add";
    }

    /**
     * validate. Method that validate an add user form
     * and perform post request for adding new user.
     *
     * @param user a user
     * @param result a BindingResult
     * @return user/list view
     */
    @PostMapping("/admin/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {

            log.error("Request POST for validation of user/add form, {}",
                    result);

            return "user/add";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        userService.saveUser(user);

        log.info("Request POST for successful user/add"
                + " SUCCESS(200 OK)");

        return "redirect:/admin/user/list";
    }

    /**
     * showUpdateForm. Method that display update user page.
     *
     * @param id a user id
     * @param model a model
     * @return user/update view
     */
    @GetMapping("/admin/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<User> user = userService.getUser(id);

        if (user.isEmpty()) {

            log.error("Error for displaying user/update page "
                    + " redirection to user list page");

            return "redirect:/admin/user/list";
        }

        user.get().setPassword("");

        model.addAttribute("user", user.get());

        log.info("Request GET for displaying user/update page "
                + " SUCCESS(200 OK)");

        return "user/update";
    }

    /**
     * updateUser. Method that validate an update user form
     * and perform post request for updating user.
     *
     * @param user a user
     * @param result a BindingResult
     * @return user/list view
     */
    @PostMapping("/admin/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {

            log.error("Request POST for validation of user/update form for user id: {}, errors: {}",
                    id, result);

            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        userService.saveUser(user);

        log.info("Request POST for successful user/update"
                + " SUCCESS(200 OK)");

        return "redirect:/admin/user/list";
    }

    /**
     * deleteUser. Method that delete user in database.
     *
     * @param id a user id
     * @return user/list view
     */
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        Optional<User> user = userService.getUser(id);

        if (user.isEmpty()) {

            log.error("Error for deleting user"
                    + "redirection to user list page");

            return "redirect:/admin/user/list";
        }

        userService.deleteUser(user.get());

        log.info("Request GET for successful user/delete"
                + " SUCCESS(200 OK)");

        return "redirect:/admin/user/list";
    }
}
