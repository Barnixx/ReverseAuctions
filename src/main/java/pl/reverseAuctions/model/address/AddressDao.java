package pl.reverseAuctions.model.address;

import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.DbUtil;
import pl.reverseAuctions.model.Entity;
import pl.reverseAuctions.model.user.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDao implements Entity<Address> {

    private final String INSERT_QUERY = "INSERT INTO Address(country, city, postalCode, street, userId) VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE_QUERY = "UPDATE Address SET country = ?, city = ?, postalCode = ?, street = ?, userId = ? WHERE idAddress = ?";
    private final String DELETE_QUERY = "DELETE FROM Address WHERE idAddress = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM Address";
    private final String GET_BY_ID = "SELECT * FROM Address WHERE idAddress = ?";

    UserDao userDao;

    @Override
    public void saveToDb(Address model) throws SQLException {

        try (Connection conn = DbUtil.getConn()) {
            if (model.getId() == null) {
                String generatedColumns[] = {"ID"};
                try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY, generatedColumns)) {

                    preparedStatement.setString(1, model.getCountry());
                    preparedStatement.setString(2, model.getCity());
                    preparedStatement.setString(3, model.getPostalCode());
                    preparedStatement.setString(4, model.getStreet());
                    if (model.getUser() != null) {
                        preparedStatement.setLong(5, model.getUser().getId());
                    } else {
                        preparedStatement.setNull(5, java.sql.Types.INTEGER);
                    }
                    preparedStatement.executeUpdate();
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    while (resultSet.next()) {
                        model.setId(resultSet.getLong(1));
                    }
                }

            } else {
                try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY)) {

                    preparedStatement.setString(1, model.getCountry());
                    preparedStatement.setString(2, model.getCity());
                    preparedStatement.setString(3, model.getPostalCode());
                    preparedStatement.setString(4, model.getStreet());
                    if (model.getUser() != null) {
                        preparedStatement.setLong(5, model.getUser().getId());
                    } else {
                        preparedStatement.setNull(5, java.sql.Types.INTEGER);
                    }
                    preparedStatement.setLong(6, model.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }

    }

    @Override
    public void delete(Address model) throws SQLException {
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
    public List<Address> getAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            ArrayList<Address> addressList = new ArrayList<>();
            try (ResultSet resultSet = conn.createStatement().executeQuery(GET_ALL_QUERY)) {
                while (resultSet.next()) {
                    Address loadedAddress = new Address();
                    loadedAddress.setId(resultSet.getLong("idAddress"));
                    loadedAddress.setCountry(resultSet.getString("country"));
                    loadedAddress.setCity(resultSet.getString("city"));
                    loadedAddress.setPostalCode(resultSet.getString("postalCode"));
                    loadedAddress.setStreet(resultSet.getString("street"));
                    loadedAddress.setUser(userDao.getById(resultSet.getLong("idUser")));
                    addressList.add(loadedAddress);
                }
            }

            return addressList;
        }
    }

    @Override
    public Address getById(Long id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            Address loadedAddress = new Address();
            try (PreparedStatement preparedStatement = conn.prepareStatement(GET_BY_ID)) {

                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    loadedAddress.setId(resultSet.getLong("idAddress"));
                    loadedAddress.setCountry(resultSet.getString("country"));
                    loadedAddress.setCity(resultSet.getString("city"));
                    loadedAddress.setPostalCode(resultSet.getString("postalCode"));
                    loadedAddress.setStreet(resultSet.getString("street"));
                    loadedAddress.setUser(userDao.getById(resultSet.getLong("idUser")));
                }
            }
            return loadedAddress;
        }
    }
}
