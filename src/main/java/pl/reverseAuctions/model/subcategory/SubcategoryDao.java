package pl.reverseAuctions.model.subcategory;

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
public class SubcategoryDao implements Entity<Subcategory> {

    private final String INSERT_QUERY = "INSERT INTO Subcategory (subcategoryName, categoryId) VALUES (?,?)";
    private final String UPDATE_QUERY = "UPDATE Subcategory SET subcategoryName = ?, categoryId = ? WHERE idSubcategory = ?";
    private final String DELETE_QUERY = "DELETE FROM Subcategory WHERE idSubcategory = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM Subcategory";
    private final String GET_BY_ID = "SELECT * FROM Subcategory WHERE idSubcategory = ?";

    @Override
    public void saveToDb(Subcategory model) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            if(model.getId() == null) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY)){
                    preparedStatement.setString(1, model.getSubcategoryName());
                    preparedStatement.setLong(2, model.getCategoryId());
                    preparedStatement.executeUpdate();
                }
            } else {
                try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY)){
                    preparedStatement.setString(1, model.getSubcategoryName());
                    preparedStatement.setLong(2, model.getCategoryId());
                    preparedStatement.setLong(3, model.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }

    }

    @Override
    public void delete(Subcategory model) throws  SQLException {
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
    public List<Subcategory> getAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()){
            ArrayList<Subcategory> subcategoryList = new ArrayList<>();
            try (ResultSet resultSet = conn.createStatement().executeQuery(GET_ALL_QUERY)){
                while(resultSet.next()){
                    Subcategory loadedSubcategory = new Subcategory();
                    loadedSubcategory.setId(resultSet.getLong("idSubcategory"));
                    loadedSubcategory.setSubcategoryName(resultSet.getString("subcategoryName"));
                    loadedSubcategory.setCategoryId(resultSet.getLong("categoryId"));
                    subcategoryList.add(loadedSubcategory);
                }
            }
            return subcategoryList;
        }
    }

    @Override
    public Subcategory getById(Long id) throws SQLException {
        try (Connection conn = DbUtil.getConn()){
            Subcategory loadedSubcategory = new Subcategory();
            try (PreparedStatement preparedStatement = conn.prepareStatement(GET_BY_ID)){
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    loadedSubcategory.setId(resultSet.getLong("idSubcategory"));
                    loadedSubcategory.setSubcategoryName(resultSet.getString("subcategoryName"));
                    loadedSubcategory.setCategoryId(resultSet.getLong("categoryId"));
                }
            }
            return loadedSubcategory;
        }
    }
}
