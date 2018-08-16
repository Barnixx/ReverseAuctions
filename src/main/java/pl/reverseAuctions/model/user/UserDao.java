package pl.reverseAuctions.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.DbUtil;
import pl.reverseAuctions.model.Entity;
import pl.reverseAuctions.model.userRole.UserRoleDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements Entity<User> {

    private final String INSERT_QUERY = "INSERT INTO User(userLogin, userFirstName, userLastName, userBirth, userMail, userRoleId) VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_QUERY = "UPDATE User SET userLogin = ?, userFirstName = ?, userLastName = ?, userBirth = ?, userMail = ?, userRoleId = ? WHERE idUser = ?";
    private final String DELETE_QUERY = "DELETE FROM User WHERE idUser = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM user";
    private final String GET_BY_ID = "SELECT * FROM User WHERE idUser = ?";

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public void saveToDb(User model) throws SQLException {

        try (Connection conn = DbUtil.getConn()) {
            if (model.getId() == null) {
                String generatedColumns[] = {"ID"};
                try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY, generatedColumns)) {

                    preparedStatement.setString(1, model.getLogin());
                    preparedStatement.setString(2, model.getFirstName());
                    preparedStatement.setString(3, model.getLastName());
                    preparedStatement.setDate(4, model.getBirth());
                    preparedStatement.setString(5, model.getMail());
                    if (model.getUserRole() != null) {
                        preparedStatement.setLong(6, model.getUserRole().getId());
                    } else {
                        preparedStatement.setNull(6, java.sql.Types.INTEGER);
                    }
                    preparedStatement.executeUpdate();
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    while (resultSet.next()) {
                        model.setId(resultSet.getLong(1));
                    }
                }
            } else {
                try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY)) {

                    preparedStatement.setString(1, model.getLogin());
                    preparedStatement.setString(2, model.getFirstName());
                    preparedStatement.setString(3, model.getLastName());
                    preparedStatement.setDate(4, model.getBirth());
                    preparedStatement.setString(5, model.getMail());
                    if (model.getUserRole() != null) {
                        preparedStatement.setLong(6, model.getUserRole().getId());
                    } else {
                        preparedStatement.setNull(6, java.sql.Types.INTEGER);
                    }
                    preparedStatement.setLong(7, model.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    @Override
    public void delete(User model) throws SQLException {
        try (Connection connection = DbUtil.getConn()) {

            if (model.getId() != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

                    preparedStatement.setLong(1, model.getId());
                    preparedStatement.executeUpdate();
                    model.setId(null);
                }
            }
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {

            ArrayList<User> userList = new ArrayList<>();
            try (ResultSet resultSet = conn.createStatement().executeQuery(GET_ALL_QUERY)) {

                while (resultSet.next()) {
                    User loadedUser = new User();
                    loadedUser.setId(resultSet.getLong("idUser"));
                    loadedUser.setLogin(resultSet.getString("userLogin"));
                    loadedUser.setFirstName(resultSet.getString("userFirstName"));
                    loadedUser.setLastName(resultSet.getString("userLastName"));
                    loadedUser.setBirth(resultSet.getDate("userBirth"));
                    loadedUser.setMail(resultSet.getString("userMail"));
                    loadedUser.setUserRole(userRoleDao.getById(resultSet.getLong("userRoleId")));
                    userList.add(loadedUser);
                }
            }
            return userList;
        }
    }

    @Override
    public User getById(Long id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {

            User loadedUser = new User();
            try (PreparedStatement preparedStatement = conn.prepareStatement(GET_BY_ID)) {

                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    loadedUser.setId(resultSet.getLong("idUser"));
                    loadedUser.setLogin(resultSet.getString("userLogin"));
                    loadedUser.setFirstName(resultSet.getString("userFirstName"));
                    loadedUser.setLastName(resultSet.getString("userLastName"));
                    loadedUser.setBirth(resultSet.getDate("userBirth"));
                    loadedUser.setMail(resultSet.getString("userMail"));
                    loadedUser.setUserRole(userRoleDao.getById(resultSet.getLong("userRoleId")));
                }
            }
            return loadedUser;
        }
    }
}