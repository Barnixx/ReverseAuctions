package pl.reverseAuctions.auction;


import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.offer.Offer;
import pl.reverseAuctions.offer.OfferService;
import pl.reverseAuctions.user.CurrentUser;
import pl.reverseAuctions.validator.NewAuctionValidationGroup;

import javax.validation.groups.Default;
import java.util.List;


@Controller
public class AuctionController {

    private final CategoryService categoryService;
    private final AuctionService auctionService;
    private final OfferService offerService;
    private final OfferService userService;

    public AuctionController(CategoryService categoryService, AuctionService auctionService, OfferService offerService, OfferService userService) {
        this.categoryService = categoryService;
        this.auctionService = auctionService;
        this.offerService = offerService;
        this.userService = userService;
    }

    @GetMapping("/auctionList")
    public String getAutions(Model model) {
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        model.addAttribute("auctionsList", auctionService.getAll());
        return "auctionList";
    }

    @GetMapping("/getAuction/{id}")
    public String allAuctions(Model model, @PathVariable("id") Long id){
        Auction auction = auctionService.getById(id);
        auction.setView(auction.getView() + 1);
        auctionService.save(auction);
        model.addAttribute("auction", auction);
        model.addAttribute("offerList", offerService.getOfferByAuctionId(id));
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        return "auction";
    }


    @GetMapping("/addAuction")
    public String addAuction(Model model, @AuthenticationPrincipal CurrentUser currentUser) {

        model.addAttribute("auction", new Auction());
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        return "addAuction";
    }

    @PostMapping("/addAuction")
    public String saveAuction(Model model, @Validated({NewAuctionValidationGroup.class, Default.class}) Auction auction, BindingResult bindingResult,
                              @AuthenticationPrincipal CurrentUser currentUser) {

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
        }

        auction.setUser(currentUser.getUser());
        if (bindingResult.hasErrors()) {
            model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
            System.out.println(auction);
            return "addAuction";
        }
        System.out.println(auction);
        Auction returnAuction = auctionService.save(auction);
        return "redirect:/getAuction/" + returnAuction.getId();
    }

    @GetMapping("/searchAuction")
    public String search(Model model, @RequestParam(required = false, defaultValue = "0") Long categoryId,
                         @RequestParam(required = false) String auctionName, @SortDefault("name") Pageable pageable) {

        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        if (categoryId == 0 && !auctionName.equals("")) {
            model.addAttribute("page", auctionService.getAuctionsByName(auctionName, pageable));
            System.out.println("PIERWSZY IF");
            return "auctionList";
        }
        if (!auctionName.equals("")) {
            model.addAttribute("page", auctionService.getAllByNameAndCategory_Id(auctionName, categoryId, pageable));
            System.out.println("drugi IF");
            return "auctionList";
        }

        return "redirect:/";

    }

    @GetMapping("/getAuction/{id}/addOffer")
    public String addOffer(@PathVariable("id") Long id, Model model){
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        //model.addAttribute("auction_id", auctionService.getById(id));
        model.addAttribute("offer", new Offer());
        return "addOffer";
    }

    @PostMapping("/getAuction/{id}/addOffer")
    public String addNewOffer(@ModelAttribute Offer offer, @PathVariable long id, @AuthenticationPrincipal CurrentUser currentUser){
//        offer.setAuction(auction);
        offer.setId(null);
        offer.setAuction(auctionService.getById(id));
        offer.setUser(currentUser.getUser());
        System.out.println(offer);
        offerService.save(offer);
        return "redirect:/getAuction/" + offer.getAuction().getId();

    }

    @GetMapping("/showOffer/{id}")
    public String showOffer(Model model, @PathVariable("id") Long id){
        model.addAttribute("offer", offerService.getById(id));
        model.addAttribute("user", offerService.getById(id).getUser());
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        return "showOffer";
    }
}
