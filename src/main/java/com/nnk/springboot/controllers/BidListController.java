package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.service.BidListService;
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

    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    /**
     * home. Method that display a home page.
     *
     * @param model a model
     * @return bidList/list view
     */

    @GetMapping("/bidList/list")
    public String home(Model model)
    {
        Iterable<BidList> bidLists = bidListService.getAllBidList();

        model.addAttribute("bidLists", bidLists);

        return "bidList/list";
    }

    /**
     * addBidForm. Method that display a add bidList form page.
     *
     * @param model a model
     * @param bidListDto a bidListDto
     * @return bidList/add view
     */
    @GetMapping("/bidList/add")
    public String addBidForm(Model model, BidListDto bidListDto) {
        model.addAttribute("bidListDto", bidListDto);
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
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidListDto bidListDto, BindingResult result) {
        if (result.hasErrors()) {
            return "bidList/add";
        }

        bidListService.saveBidList(bidListDto);

        return "redirect:/bidList/list";
    }

    /**
     * showUpdateForm. Method that display update bidList page.
     *
     * @param id a bidList id
     * @param model a model
     * @return bidList/update view
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<BidList> bidList = bidListService.getOneBidList(id);

        if (bidList.isEmpty()) {
            return "redirect:/bidList/list";
        }

        model.addAttribute("bidListDto", new BidListDto(
                bidList.get().getBidListId(),
                bidList.get().getAccount(),
                bidList.get().getType(),
                bidList.get().getBidQuantity())
        );

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
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidListDto bidListDto,
                             BindingResult result, Model model) {


        if (result.hasErrors()) {
            return "bidList/update";
        }

        bidListService.saveBidList(bidListDto);

        return "redirect:/bidList/list";
    }

    /**
     * deleteBid. Method that delete bidList in database.
     *
     * @param id a bidList id
     * @return bidList/list view
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        Optional<BidList> bidList = bidListService.getOneBidList(id);

        if (bidList.isEmpty()) {
            return "redirect:/bidList/list";
        }

        bidListService.deleteBidList(bidList.get());

        return "bidList/list";
    }
}
