package pl.reverseAuctions.model.subcategory;

public class Subcategory {

    private Long id;
    private String subcategoryName;
    private Long categoryId;

    public Subcategory() {
    }

    public Subcategory(String subcategoryName, Long categoryId) {
        this.subcategoryName = subcategoryName;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
