package pl.reverseAuctions.auction;


import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.offer.Offer;
import pl.reverseAuctions.offer.OfferService;
import pl.reverseAuctions.user.CurrentUser;


import javax.validation.Valid;
import java.util.List;
import pl.reverseAuctions.user.User;


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
    public String saveAuction(Model model, @Valid Auction auction, BindingResult bindingResult,
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



    @GetMapping("/getAuction/{id}/addOffer")
    public String addOffer(@PathVariable("id") Long id, Model model){
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        model.addAttribute("auction", auctionService.getById(id));
        model.addAttribute("offer", new Offer());
        return "addOffer";
    }

    @PostMapping("/getAuction/{id}/addOffer")
    public String addNewOffer(@ModelAttribute Offer offer, @ModelAttribute Auction auction, @AuthenticationPrincipal CurrentUser currentUser){
        offer.setAuction(auction);
        offer.setUser(currentUser.getUser());
        offerService.save(offer);
        return "redirect:/getAuction/" + offer.getAuction().getId();

    }
}
