package pl.reverseAuctions.model.userRole;

import pl.reverseAuctions.model.DbUtil;
import pl.reverseAuctions.model.Entity;

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
        try (Connection conn = DbUtil.getConn()){
            if(model.getId() == null){
                try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY)){
                    preparedStatement.setString(1, model.getUserRoleName());
                    preparedStatement.executeUpdate();
                }
            } else {
                try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY)){
                    preparedStatement.setString(1, model.getUserRoleName());
                    preparedStatement.setLong(2, model.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    @Override
    public void delete(UserRole model) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
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
    public List<UserRole> getAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            ArrayList<UserRole> userRoleList = new ArrayList<>();
            try (ResultSet resultSet = conn.createStatement().executeQuery(GET_ALL_QUERY)) {
                while (resultSet.next()) {
                    UserRole userRole = new UserRole();
                    userRole.setId(resultSet.getLong("id"));
                    userRole.setUserRoleName(resultSet.getString("userRoleName"));
                    userRoleList.add(userRole);
                }
            }
            return userRoleList;
        }
    }

    @Override
    public UserRole getById(Long id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            UserRole loadedUserRole = new UserRole();
            try (PreparedStatement preparedStatement = conn.prepareStatement(GET_BY_ID)){
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    loadedUserRole.setId(resultSet.getLong("id"));
                    loadedUserRole.setUserRoleName(resultSet.getString("userRoleName"));
                }
            }
            return loadedUserRole;
        }
    }

}
