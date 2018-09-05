package pl.reverseAuctions.category;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.reverseAuctions.auction.AuctionService;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final AuctionService auctionService;
    private final CategoryService categoryService;

    public CategoryController(AuctionService auctionService, CategoryService categoryService) {
        this.auctionService = auctionService;
        this.categoryService = categoryService;
    }

    @GetMapping("{category_id}")
    public String getCategory(Model model, @PathVariable long category_id, @SortDefault("id") Pageable pageable) {
        model.addAttribute("categories", categoryService.getRootCategory());
        model.addAttribute("page", auctionService.getAllAuctionsByCategory(category_id, pageable));
        return "auctionList";
    }

    @GetMapping("{category_id}/{subcategory_id}")
    public String getCategory(Model model, @PathVariable long category_id,
                              @PathVariable long subcategory_id, @SortDefault("id") Pageable pageable) {
        model.addAttribute("categories", categoryService.getRootCategory());
        model.addAttribute("page", auctionService.getAuctionsBySubcategoryId(subcategory_id, pageable));
        return "auctionList";
    }
}
