package pl.reverseAuctions.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.reverseAuctions.address.Address;
import pl.reverseAuctions.address.AddressService;
import pl.reverseAuctions.auction.Auction;
import pl.reverseAuctions.auction.AuctionService;
import pl.reverseAuctions.category.Category;
import pl.reverseAuctions.category.CategoryService;
import pl.reverseAuctions.offer.Offer;
import pl.reverseAuctions.offer.OfferService;
import pl.reverseAuctions.subcategory.Subcategory;
import pl.reverseAuctions.validator.UserRegisterValidationGroup;

import javax.validation.groups.Default;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final CategoryService categoryService;
    private final UserService userService;
    private final AddressService addressService;
    private final OfferService offerService;
    private final AuctionService auctionService;

    public UserController(CategoryService categoryService, UserService userService,
                          AddressService addressService, OfferService offerService, AuctionService auctionService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.addressService = addressService;
        this.offerService = offerService;
        this.auctionService = auctionService;
    }

    @GetMapping("/signIn")
    public String signIn(Model model) {
        model.addAttribute("user", new User());
        return "signInPage";
    }

    @GetMapping("/signUp")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public String saveNewUser(@Validated({UserRegisterValidationGroup.class, Default.class}) User user,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signUpPage";
        }
        userService.save(user);
        return "redirect:/";
    }

    @ModelAttribute("subcategoriesMap")
    private Map<Category, List<Subcategory>> categoryListMap() {
        return categoryService.getAllCategoriesWithSubcategories();
    }

    @GetMapping("/user")
    private String userProfile(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        List<Address> addressList = addressService.getAllByUser_Id(user.getId());
        model.addAttribute("addressList", addressList);
        return "user-profile";
    }

    @GetMapping("/user/my-auctions")
    private String userAuctions(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        List<Auction> auctionList = auctionService.getAuctionsByUser(user.getId());
        model.addAttribute("auctionList", auctionList);
        return "user-my-auctions";
    }

    @GetMapping("/user/my-offers")
    private String userOffers(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        List<Offer> offerList = offerService.getAllByUser(user.getId());
        model.addAttribute("offerList", offerList);
        return "user-my-offers";
    }

    @GetMapping("/user/new-auction")
    private String createAuction(){
        return "redirect:/new-auction";
    }

}
