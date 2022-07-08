package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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

@Controller
public class RuleNameController {

    private static final Logger log = LoggerFactory.getLogger(RuleNameController.class);
    private final RuleNameService ruleNameService;

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    /**
     * home. Method that display a ruleName home page.
     *
     * @param model a model
     * @return ruleName/list view
     */
    @RequestMapping("/user/ruleName/list")
    public String home(Model model)
    {
        Iterable<RuleName> ruleNames = ruleNameService.getRuleNames();

        model.addAttribute("ruleNames", ruleNames);

        log.info("Request GET for displaying ruleName/list page "
                + " SUCCESS(200 OK)");

        return "ruleName/list";
    }

    /**
     * addRuleNameForm. Method that display an add ruleName form page.
     *
     * @param model a model
     * @param ruleName a ruleName
     * @return ruleName/add view
     */
    @GetMapping("/user/ruleName/add")
    public String addRuleNameForm(Model model, RuleName ruleName) {
        model.addAttribute("ruleName", ruleName);

        log.info("Request GET for displaying ruleName/add page "
                + " SUCCESS(200 OK)");

        return "ruleName/add";
    }

    /**
     * validate. Method that validate an add ruleName form
     * and perform post request for adding new ruleName.
     *
     * @param ruleName a ruleName
     * @param result a BindingResult
     * @return ruleName/list view
     */
    @PostMapping("/user/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

        if (result.hasErrors()){

            log.error("Request POST for validation of ruleName/add form, {}",
                    result);

            return "ruleName/add";
        }

        ruleNameService.saveRuleName(ruleName);

        log.info("Request POST for successful ruleName/add"
                + " SUCCESS(200 OK)");

        return "redirect:/user/ruleName/list";
    }

    /**
     * showUpdateForm. Method that display update ruleName page.
     *
     * @param id a ruleName id
     * @param model a model
     * @return ruleName/update view
     */
    @GetMapping("/user/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<RuleName> ruleName = ruleNameService.getRuleName(id);

        if (ruleName.isEmpty()) {

            log.error("Error for displaying ruleName/update page "
                    + " redirection to ruleName list page");

            return "redirect:/user/ruleName/list";
        }

        model.addAttribute("ruleName", ruleName.get());

        log.info("Request GET for displaying ruleName/update page "
                + " SUCCESS(200 OK)");

        return "ruleName/update";
    }

    /**
     * updateRuleName. Method that validate an add ruleName form
     * and perform post request for updating ruleName.
     *
     * @param ruleName a ruleName
     * @param result a BindingResult
     * @return ruleName/list view
     */
    @PostMapping("/user/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {

            log.error("Request POST for validation of ruleName/update form for ruleName id: {}, errors: {}",
                    id, result);

            return "ruleName/update";
        }

        ruleNameService.saveRuleName(ruleName);

        log.info("Request POST for successful ruleName/update"
                + " SUCCESS(200 OK)");

        return "redirect:/user/ruleName/list";
    }

    /**
     * deleteRuleName. Method that delete ruleName in database.
     *
     * @param id a ruleName id
     * @return ruleName/list view
     */
    @GetMapping("/user/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

        Optional<RuleName> ruleName = ruleNameService.getRuleName(id);

        if (ruleName.isEmpty()) {

            log.error("Error for deleting ruleName"
                    + "redirection to ruleName list page");

            return "redirect:/user/ruleName/list";
        }

        ruleNameService.deleteRuleName(ruleName.get());

        log.info("Request GET for successful ruleName/delete"
                + " SUCCESS(200 OK)");

        return "redirect:/user/ruleName/list";
    }
}
