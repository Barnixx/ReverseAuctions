package pl.reverseAuctions.user;

import lombok.extern.java.Log;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
@Log
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
    public String saveNewUser(@Valid User user, BindingResult bindingResult) {
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

    @GetMapping("/user/edit-profile")
    private String editProfile(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        return "user-profile-edit";
    }

    @PostMapping("/user/edit-profile")
    private String alternate(@ModelAttribute User user, @AuthenticationPrincipal CurrentUser currentUser){
        currentUser.getUser().setFirstName(user.getFirstName());
        currentUser.getUser().setLastName(user.getLastName());
        currentUser.getUser().setMail(user.getMail());
        currentUser.getUser().setPhoneNumber(user.getPhoneNumber());
        currentUser.getUser().setBirth(user.getBirth());
        userService.update(currentUser.getUser());
        return "user-profile-confirm";
    }

    @GetMapping("/user/delete-address/{id}")
    private String editAddress(Model model, @PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        addressService.delete(id);
        return "user-profile-confirm";
    }

    @GetMapping("/user/add-address")
    private String addAddress(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        model.addAttribute("user", user);
        Address address = new Address();
        model.addAttribute("address", address);
        return "user-address-add";
    }

    @PostMapping("/user/add-address")
    private String createAddress(@ModelAttribute Address address, @AuthenticationPrincipal CurrentUser currentUser) {
        address.setUser(currentUser.getUser());
        addressService.save(address);
        return "user-profile-confirm";
    }


}
