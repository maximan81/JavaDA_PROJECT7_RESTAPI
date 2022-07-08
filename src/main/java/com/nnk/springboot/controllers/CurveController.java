package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
 * CurveController. class that manage
 * request/response logic of curvePoint.
 */
@Controller
public class CurveController {

  private static final Logger log = LoggerFactory.getLogger(CurveController.class);
  private final CurvePointService curvePointService;

  public CurveController(CurvePointService curvePointService) {
    this.curvePointService = curvePointService;
  }

  /**
   * home. Method that display a curvePoint home page.
   *
   * @param model a model
   * @return curvePoint/list view
   */
  @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        Iterable<CurvePoint> curvePoints = curvePointService.getCurvePoints();

        model.addAttribute("curvePoints", curvePoints);

        log.info("Request GET for displaying curvePoint/list page "
              + " SUCCESS(200 OK)");

        return "curvePoint/list";
    }

  /**
   * addCurvePointForm. Method that display an add curvePoint form page.
   *
   * @param model a model
   * @param curvePoint a bidListDto
   * @return curvePoint/add view
   */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model, CurvePoint curvePoint) {
        model.addAttribute("curvePoint", curvePoint);

      log.info("Request GET for displaying curvePoint/add page "
              + " SUCCESS(200 OK)");

        return "curvePoint/add";
    }

  /**
   * validate. Method that validate an add curvePoint form
   * and perform post request for adding new curvePoint.
   *
   * @param curvePoint a curvePoint
   * @param result a BindingResult
   * @return curvePoint/list view
   */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()){

          log.error("Request POST for validation of curvePoint/add form, {}",
                  result);

          return "curvePoint/add";
        }

        curvePointService.saveCurvePoint(curvePoint);

      log.info("Request POST for successful curvePoint/add"
              + " SUCCESS(200 OK)");

        return "redirect:/curvePoint/list";
    }

  /**
   * showUpdateForm. Method that display update curvePoint page.
   *
   * @param id a curvePoint id
   * @param model a model
   * @return curvePoint/update view
   */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<CurvePoint> curvePoint = curvePointService.getCurvePoint(id);

        if (curvePoint.isEmpty()) {

          log.error("Error for displaying curvePoint/update page "
                  + " redirection to curvePoint list page");

          return "redirect:/curvePoint/list";
        }

        model.addAttribute("curvePoint", curvePoint.get());

        log.info("Request GET for displaying curvePoint/update page "
              + " SUCCESS(200 OK)");

        return "curvePoint/update";
    }

  /**
   * updateCurvePoint. Method that validate an add curvePoint form
   * and perform post request for updating curvePoint.
   *
   * @param curvePoint a curvePoint
   * @param result a BindingResult
   * @return curvePoint/list view
   */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {

          log.error("Request POST for validation of curvePoint/update form for curvePoint id: {}, errors: {}",
                  id, result);

          return "curvePoint/update";
        }

        curvePointService.saveCurvePoint(curvePoint);

       log.info("Request POST for successful curvePoint/update"
              + " SUCCESS(200 OK)");

        return "redirect:/curvePoint/list";
    }

  /**
   * deleteCurvePoint. Method that delete curvePoint in database.
   *
   * @param id a curvePoint id
   * @return curvePoint/list view
   */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        Optional<CurvePoint> curvePoint = curvePointService.getCurvePoint(id);

        if (curvePoint.isEmpty()) {

          log.error("Error for deleting curvePoint"
                  + "redirection to curvePoint list page");

          return "curvePoint/list";
        }

        curvePointService.deleteCurvePoint(curvePoint.get());

      log.info("Request GET for successful curvePoint/delete"
              + " SUCCESS(200 OK)");

        return "curvePoint/list";
    }
}
