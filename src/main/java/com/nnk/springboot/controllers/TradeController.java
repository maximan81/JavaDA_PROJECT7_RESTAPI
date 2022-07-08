package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {

    private static final Logger log = LoggerFactory.getLogger(CurveController.class);
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    /**
     * home. Method that display a trade home page.
     *
     * @param model a model
     * @return trade/list view
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        Iterable<Trade> trades = tradeService.getTrades();

        model.addAttribute("trades", trades);

        log.info("Request GET for displaying trade/list page "
                + " SUCCESS(200 OK)");

        return "trade/list";
    }

    /**
     * addTradeForm. Method that display an add trade form page.
     *
     * @param model a model
     * @param trade a trade
     * @return trade/add view
     */
    @GetMapping("/trade/add")
    public String addTradeForm(Model model, Trade trade) {
        model.addAttribute("trade", trade);

        log.info("Request GET for displaying trade/add page "
                + " SUCCESS(200 OK)");

        return "trade/add";
    }

    /**
     * validate. Method that validate an add trade form
     * and perform post request for adding new trade.
     *
     * @param trade a trade
     * @param result a BindingResult
     * @return trade/list view
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()){

            log.error("Request POST for validation of trade/add form, {}",
                    result);

            return "trade/add";
        }

        tradeService.saveTrade(trade);

        log.info("Request POST for successful trade/add"
                + " SUCCESS(200 OK)");

        return "redirect:/trade/list";
    }

    /**
     * showUpdateForm. Method that display update trade page.
     *
     * @param id a trade id
     * @param model a model
     * @return trade/update view
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Optional<Trade> trade = tradeService.getTrade(id);

        if (trade.isEmpty()) {

            log.error("Error for displaying trade/update page "
                    + " redirection to trade list page");

            return "redirect:/trade/list";
        }

        model.addAttribute("trade", trade.get());

        log.info("Request GET for displaying trade/update page "
                + " SUCCESS(200 OK)");

        return "trade/update";
    }

    /**
     * updateTrade. Method that validate an add trade form
     * and perform post request for updating trade.
     *
     * @param trade a trade
     * @param result a BindingResult
     * @return trade/list view
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {

            log.error("Request POST for validation of trade/update form for trade id: {}, errors: {}",
                    id, result);

            return "trade/update";
        }

        tradeService.saveTrade(trade);

        log.info("Request POST for successful trade/update"
                + " SUCCESS(200 OK)");

        return "redirect:/trade/list";
    }

    /**
     * deleteTrade. Method that delete trade in database.
     *
     * @param id a trade id
     * @return trade/list view
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        Optional<Trade> trade = tradeService.getTrade(id);

        if (trade.isEmpty()) {

            log.error("Error for deleting trade"
                    + "redirection to trade list page");

            return "trade/list";
        }

        tradeService.deleteTrade(trade.get());

        log.info("Request GET for successful trade/delete"
                + " SUCCESS(200 OK)");

        return "trade/list";
    }
}
