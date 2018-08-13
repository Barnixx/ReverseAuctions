package pl.reverseAuctions.model.userRole;

import pl.reverseAuctions.model.DbUtil;
import pl.reverseAuctions.model.Entity;
import pl.reverseAuctions.model.category.Category;
import pl.reverseAuctions.model.category.CategoryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDao implements Entity<UserRole> {

    private final String INSERT_QUERY = "INSERT INTO UserRole (userRoleName) VALUES (?)";
    private final String UPDATE_QUERY = "UPDATE UserRole SET userRoleName = ? WHERE idUserRole = ?";
    private final String DELETE_QUERY = "DELETE FROM UserRole WHERE idUserRole = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM UserRole";
    private final String GET_BY_ID = "SELECT * FROM UserRole WHERE idUserRole = ?";

    @Override
    public void saveToDb(UserRole model) throws SQLException {

    }

    @Override
    public void delete(UserRole model) throws SQLException {

    }

    @Override
    public List<UserRole> getAll() throws SQLException {
        return null;
    }

    @Override
    public UserRole getById(int id) throws SQLException {
        return null;
    }

}
