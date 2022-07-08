package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.service.BidListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * BidListController. class that manage
 * request/response logic of bidList.
 */

@Controller
public class BidListController {

    private static final Logger log = LoggerFactory.getLogger(BidListController.class);
    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    /**
     * home. Method that display a bid home page.
     *
     * @param model a model
     * @return bidList/list view
     */

    @GetMapping("/user/bidList/list")
    public String home(Model model)
    {
        Iterable<BidList> bidLists = bidListService.getAllBidList();

        model.addAttribute("bidLists", bidLists);

        log.info("Request GET for displaying bidList/list page "
                + " SUCCESS(200 OK)");

        return "bidList/list";
    }

    /**
     * addBidForm. Method that display a add bidList form page.
     *
     * @param model a model
     * @param bidListDto a bidListDto
     * @return bidList/add view
     */
    @GetMapping("/user/bidList/add")
    public String addBidForm(Model model, BidListDto bidListDto) {
        model.addAttribute("bidListDto", bidListDto);

        log.info("Request GET for displaying bidList/add page "
                + " SUCCESS(200 OK)");

        return "bidList/add";
    }

    /**
     * validate. Method that validate an add bidList form
     * and perform post request for adding new bidList.
     *
     * @param bidListDto a bidListDto
     * @param result a BindingResult
     * @return bidList/list view
     */
    @PostMapping("/user/bidList/validate")
    public String validate(@Valid BidListDto bidListDto, BindingResult result) {
        if (result.hasErrors()) {

            log.error("Request POST for validation of bidList/add form, {}",
                    result);

            return "bidList/add";
        }

        bidListService.saveBidList(bidListDto);

        log.info("Request POST for successful bidList/add"
                + " SUCCESS(200 OK)");

        return "redirect:/user/bidList/list";
    }

    /**
     * showUpdateForm. Method that display update bidList page.
     *
     * @param id a bidList id
     * @param model a model
     * @return bidList/update view
     */
    @GetMapping("/user/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<BidList> bidList = bidListService.getOneBidList(id);

        if (bidList.isEmpty()) {

            log.error("Error for displaying bidList/update page "
                    + " redirection to bidList list page");

            return "redirect:/user/bidList/list";
        }

        model.addAttribute("bidListDto", new BidListDto(
                bidList.get().getBidListId(),
                bidList.get().getAccount(),
                bidList.get().getType(),
                bidList.get().getBidQuantity())
        );

        log.info("Request GET for displaying bidList/update page "
                + " SUCCESS(200 OK)");

        return "bidList/update";
    }

    /**
     * updateBid. Method that validate an add bidList form
     * and perform post request for updating bidList.
     *
     * @param bidListDto a bidListDto
     * @param result a BindingResult
     * @return bidList/list view
     */
    @PostMapping("/user/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidListDto bidListDto,
                             BindingResult result, Model model) {


        if (result.hasErrors()) {

            log.error("Request POST for validation of bidList/update "
                            +
                            "form for bidList id: {}, errors: {}",
                    id, result);

            return "bidList/update";
        }

        bidListService.saveBidList(bidListDto);

        log.info("Request POST for successful bidList/update"
                + " SUCCESS(200 OK)");

        return "redirect:/user/bidList/list";
    }

    /**
     * deleteBid. Method that delete bidList in database.
     *
     * @param id a bidList id
     * @return bidList/list view
     */
    @GetMapping("/user/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        Optional<BidList> bidList = bidListService.getOneBidList(id);

        if (bidList.isEmpty()) {

            log.error("Error for deleting bidList"
                    + "redirection to bidList list page");

            return "redirect:/user/bidList/list";
        }

        bidListService.deleteBidList(bidList.get());

        log.info("Request GET for successful bidList/delete"
                + " SUCCESS(200 OK)");

        return "redirect:/user/bidList/list";
    }
}
