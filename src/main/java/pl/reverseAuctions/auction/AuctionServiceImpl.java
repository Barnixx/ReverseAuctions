package pl.reverseAuctions.auction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.reverseAuctions.category.Category;
import pl.reverseAuctions.category.CategoryRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;

    public AuctionServiceImpl(AuctionRepository auctionRepository, CategoryRepository categoryRepository) {
        this.auctionRepository = auctionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
    }

    @Override
    public Auction getById(Long id) {
        return auctionRepository.findById(id);
    }

    @Override
    public List<Auction> getAll() {
        return auctionRepository.findAll();
    }

    @Override
    public void delete(Auction auction) {
        auctionRepository.delete(auction);
    }

    @Override
    public void delete(Long id) {
        auctionRepository.delete(id);
    }

    @Override
    public List<Auction> getAuctionsBySubcategoryId(Long id) {
        return auctionRepository.findByCategory_Id(id);
    }

    @Override
    public Page<Auction> getAuctionsBySubcategoryId(Long id, Pageable pageable) {
        return auctionRepository.findAllByCategory_IdAndEndTimeGreaterThanEqual(id, LocalDate.now(), pageable);
        //return auctionRepository.findBySubcategory_Id(id, pageable);
    }

    @Override
    public List<Auction> getAuctionsByCategoryId(Long id) {
        return auctionRepository.findByCategory_ParentCategory_Id(id);
    }

    @Override
    public Page<Auction> getAuctionsByCategoryId(Long id, Pageable pageable) {
        return auctionRepository.findAllByCategory_ParentCategory_IdAndEndTimeGreaterThanEqual(id, LocalDate.now(), pageable);

        //return auctionRepository.findBySubcategory_Category_Id(id, pageable);
    }

    @Override
    public List<Auction> getAuctionsByUser(Long id) {
        return auctionRepository.findAllByUser_Id(id);
    }

    @Override
    public Page<Auction> findAll(Pageable pageable) {
        return auctionRepository.findAll(pageable);
    }

    @Override
    public Page<Auction> getAuctionsByName(String name, Pageable pageable) {
        return auctionRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Page<Auction> getAllByNameAndCategory_Id(String name, Long id, Pageable pageable) {
        return auctionRepository.findAllByNameContainingAndCategory_ParentCategory_Id(name, id, pageable);
    }

    @Override
    public Page<Auction> getAllAuctionsByCategory(Long id, Pageable pageable) {

        Set<Category> categories = new HashSet<>();
        Category category = categoryRepository.findById(id);
        categories.add(category);
        if (category.getSubcategories() != null) {
            for (Category subcategory : category.getSubcategories()) {
                categories.add(subcategory);
                if (subcategory.getSubcategories() != null) {
                    categories.addAll(subcategory.getSubcategories());
                }
            }
        }
        return auctionRepository.findDistinctByCategoryIn(categories, pageable);
    }

    private Set<Category> createCategorySet(Category category) {
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        if (category.getSubcategories() != null) {
            for (Category subcategory : category.getSubcategories()) {
                categories.add(subcategory);
                categories.addAll(createCategorySet(subcategory));
            }
        }
        return categories;
    }
}
