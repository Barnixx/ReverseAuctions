package pl.reverseAuctions.model.category;

import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.DbUtil;
import pl.reverseAuctions.model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDao implements Entity<Category> {

    private final String INSERT_QUERY = "INSERT INTO Category (categoryName, categoryDescription) VALUES (?,?)";
    private final String UPDATE_QUERY = "UPDATE Category SET categoryName = ?, categoryDescription = ? WHERE idCategory = ?";
    private final String DELETE_QUERY = "DELETE FROM Category WHERE idCategory = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM Category";
    private final String GET_BY_ID = "SELECT * FROM Category WHERE idCategory = ?";

    @Override
    public void saveToDb(Category model) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            if(model.getId() == null) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY)){
                    preparedStatement.setString(1, model.getCategoryName());
                    preparedStatement.setString(2, model.getCategoryDescription());
                    preparedStatement.executeUpdate();
                }
            } else {
                try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY)){
                    preparedStatement.setString(1, model.getCategoryName());
                    preparedStatement.setString(2, model.getCategoryDescription());
                    preparedStatement.setLong(3, model.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    @Override
    public void delete(Category model) throws SQLException {
        try (Connection conn = DbUtil.getConn()){
            if(model.getId() != null){
                try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_QUERY)){
                    preparedStatement.setLong(1, model.getId());
                    preparedStatement.executeUpdate();
                    model.setId(null);
                }
            }
        }
    }

    @Override
    public List<Category> getAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()){
            ArrayList<Category> categoryList = new ArrayList<>();
            try (ResultSet resultSet = conn.createStatement().executeQuery(GET_ALL_QUERY)){
                while(resultSet.next()){
                    Category loadedCategory = new Category();
                    loadedCategory.setId(resultSet.getLong("idCategory"));
                    loadedCategory.setCategoryName(resultSet.getString("categoryName"));
                    loadedCategory.setCategoryDescription(resultSet.getString("categoryDescription"));
                    categoryList.add(loadedCategory);
                }
            }
            return categoryList;
        }
    }

    @Override
    public Category getById(Long id) throws SQLException {
        try (Connection conn = DbUtil.getConn()){
            Category loadedCategory = new Category();
            try (PreparedStatement preparedStatement = conn.prepareStatement(GET_BY_ID)){
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    loadedCategory.setId(resultSet.getLong("idCategory"));
                    loadedCategory.setCategoryName(resultSet.getString("categoryName"));
                    loadedCategory.setCategoryDescription(resultSet.getString("categoryDescription"));
                }
            }
            return loadedCategory;
        }
    }
}
