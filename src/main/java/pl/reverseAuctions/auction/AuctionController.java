package pl.reverseAuctions.auction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.offer.OfferService;
import pl.reverseAuctions.user.CurrentUser;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping("/list")
    public String listAction(Model model, @SortDefault("id") Pageable pageable) {
        model.addAttribute("subcategoriesMap", categoryService.getAllCategoriesWithSubcategories());
        model.addAttribute("page", auctionService.findAll(pageable));
        return "testPage";
    }
}
