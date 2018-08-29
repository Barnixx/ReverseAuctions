package pl.reverseAuctions.auction;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.offer.OfferService;

@Controller
public class AuctionController {

    private final CategoryService categoryService;
    private final AuctionService auctionService;
    private final OfferService offerService;

    public AuctionController(CategoryService categoryService, AuctionService auctionService, OfferService offerService) {
        this.categoryService = categoryService;
        this.auctionService = auctionService;
        this.offerService = offerService;
    }

    @GetMapping("/auctionList")
    public String getAutions(Model model) {
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        model.addAttribute("auctionsList", auctionService.getAll());
        return "auctionList";
    }

    @GetMapping("/getAuction/{id}")
    public String allAuctions(Model model, @PathVariable("id") Long id){
        model.addAttribute("auction", auctionService.getById(id));
        model.addAttribute("offerList", offerService.getOfferByAuctionId(id));
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        return "auction";
    }
}
