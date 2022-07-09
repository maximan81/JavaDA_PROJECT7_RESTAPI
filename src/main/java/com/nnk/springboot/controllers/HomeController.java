package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController. class that manage
 * request/response logic of home.
 */
@Controller
public class HomeController
{

	private static final Logger log = LoggerFactory.getLogger(CurveController.class);

	/**
	 * home. Method that display a home page.
	 *
	 * @param model a model
	 * @return home view
	 */
	@RequestMapping("/")
	public String home(Model model)
	{

		log.info("Request GET for displaying home page "
						+ " SUCCESS(200 OK)");

		return "home";
	}

	/**
	 * adminHome. Method that display a admin home page.
	 *
	 * @param model a model
	 * @return home user/list
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{

		log.info("Request GET for displaying user/list page "
						+ " SUCCESS(200 OK)");

		return "redirect:/admin/user/list";
	}


}
