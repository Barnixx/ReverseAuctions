package pl.reverseAuctions.model.offer;

import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.DbUtil;
import pl.reverseAuctions.model.Entity;
import pl.reverseAuctions.model.user.User;
import pl.reverseAuctions.model.user.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfferDao implements Entity<Offer> {

    private final String INSERT_QUERY = "INSERT INTO offer(offerTitle, offerDescription, offerPrice, idUser, idAuction, offerAddTime) VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_QUERY = "UPDATE offer SET offerTitle = ?, offerDescription = ?, offerPrice = ?, idUser = ?, idAuction = ?, offerAddTime = ? WHERE idUser = ?";
    private final String DELETE_QUERY = "DELETE FROM offer WHERE idOffer = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM offer";
    private final String GET_BY_ID = "SELECT * FROM offer WHERE idOffer = ?";

    @Override
    public void saveToDb(Offer model) throws SQLException {

        try (Connection conn = DbUtil.getConn()) {
            if (model.getId() == null) {
                String generatedColumns[] = {"ID"};
                try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY, generatedColumns)) {

                    preparedStatement.setString(1, model.getTitle());
                    preparedStatement.setString(2, model.getDescription());
                    preparedStatement.setDouble(3, model.getPrice());
                    preparedStatement.setLong(4, model.getUser().getIdUser());
                    preparedStatement.setLong(5, model.getAuction().getId());
                    preparedStatement.setString(6, model.getAddTime());

                    preparedStatement.executeUpdate();
                    ResultSet resultSet = preparedStatement.getGeneratedKeys();
                    while (resultSet.next()) {
                        model.setId(resultSet.getLong(1));
                    }
                }
            } else {
                try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY)) {

                    preparedStatement.setString(1, model.getTitle());
                    preparedStatement.setString(2, model.getDescription());
                    preparedStatement.setDouble(3, model.getPrice());
                    preparedStatement.setLong(4, model.getUser().getIdUser());
                    preparedStatement.setLong(5, model.getAuction().getId());
                    preparedStatement.setString(6, model.getAddTime());
                    preparedStatement.setLong(7, model.getId());

                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    @Override
    public void delete(Offer model) throws SQLException {
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
    public List<Offer> getAll() throws SQLException{
        try (Connection conn = DbUtil.getConn()) {

            ArrayList<Offer> offerList = new ArrayList<>();
            try (ResultSet resultSet = conn.createStatement().executeQuery(GET_ALL_QUERY)) {

                while (resultSet.next()) {
                    Offer loadedOffer = new Offer();
                    loadedOffer.setId(resultSet.getLong("idOffer"));
                    loadedOffer.setTitle(resultSet.getString("offerTitle"));
                    loadedOffer.setDescription(resultSet.getString("offerDescription"));
                    loadedOffer.setPrice(resultSet.getDouble("offerPrice"));
                    loadedOffer.setUser(userDao.getById(resultSet.getLong("idUser")));
                    loadedOffer.setAuction(auctionDao.getById(resultSet.getLong("idAuction")));
                    loadedOffer.setAddTime(resultSet.getString("offerAddTime"));
                    offerList.add(loadedOffer);
                }
            }
            return offerList;
        }
    }

    @Override
    public Offer getById(int id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {

            Offer loadedOffer = new Offer();
            try (PreparedStatement preparedStatement = conn.prepareStatement(GET_BY_ID)) {

                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    loadedOffer.setId(resultSet.getLong("idOffer"));
                    loadedOffer.setTitle(resultSet.getString("offerTitle"));
                    loadedOffer.setDescription(resultSet.getString("offerDescription"));
                    loadedOffer.setPrice(resultSet.getDouble("offerPrice"));
                    loadedOffer.setUser(userDao.getById(resultSet.getLong("idUser")));
                    loadedOffer.setAuction(auctionDao.getById(resultSet.getLong("idAuction")));
                    loadedOffer.setAddTime(resultSet.getString("offerAddTime"));
                }
            }
            return loadedOffer;
        }
    }
}
