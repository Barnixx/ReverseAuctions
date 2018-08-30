package pl.reverseAuctions.auction;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.offer.Offer;
import pl.reverseAuctions.offer.OfferService;
import pl.reverseAuctions.user.CurrentUser;
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
    @GetMapping("/showOffer/{id}")
    public String showOffer(Model model, @PathVariable("id") Long id){
        model.addAttribute("offer", offerService.getById(id));
        model.addAttribute("user", userService.getUserByOfferId(id).getUsername());
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        return "showOffer";
    }
}
