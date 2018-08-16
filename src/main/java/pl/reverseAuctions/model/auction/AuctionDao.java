package pl.reverseAuctions.model.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.reverseAuctions.model.DbUtil;
import pl.reverseAuctions.model.Entity;
import pl.reverseAuctions.model.offer.OfferDao;
import pl.reverseAuctions.model.subcategory.SubcategoryDao;
import pl.reverseAuctions.model.user.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuctionDao implements Entity<Auction> {

    private final String INSERT_QUERY = "INSERT INTO Auction(auctionName, auctionDescription, subcategoryId, userId, auctionStartTime, auctionEndTime, auctionIsActive, auctionView, auctionWinOfferID) " +
            "VALUE (?, ?, ?, ?, ? ,? ,? ,? ,?)";
    private final String UPDATE_QUERY = "UPDATE Auction SET auctionName = ?, auctionDescription = ?, subcategoryId = ?, userId = ?, auctionStartTime = ?, auctionEndTime = ?, auctionIsActive = ?, auctionView = ?, auctionWinOfferID = ? " +
            "WHERE idAuction = ?";
    private final String DELETE_QUERY = "DELETE FROM Auction WHERE idAuction = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM Auction";
    private final String GET_BY_ID = "SELECT * FROM Auction WHERE idAuction = ?";

    @Autowired
    SubcategoryDao subcategoryDao;
    @Autowired
    UserDao userDao;
    @Autowired
    OfferDao offerDao;

    @Override
    public void saveToDb(Auction model) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            if (model.getId() == null) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY)) {
                    preparedStatement.setString(1, model.getAuctionName());
                    preparedStatement.setString(2, model.getAuctionDescription());
                    if (model.getSubcategory() != null) preparedStatement.setLong(3, model.getSubcategory().getId());
                    else preparedStatement.setNull(3, java.sql.Types.INTEGER);

                    if (model.getUser() != null) preparedStatement.setLong(4, model.getUser().getId());
                    else preparedStatement.setNull(4, java.sql.Types.INTEGER);

                    preparedStatement.setTimestamp(5, Timestamp.valueOf(model.getAuctionStartTime()));
                    preparedStatement.setTimestamp(6, Timestamp.valueOf(model.getAuctionEndTime()));
                    preparedStatement.setTimestamp(7, Timestamp.valueOf(model.getAuctionEndTime()));
                    preparedStatement.setInt(8, model.isAuctionIsActive());
                    preparedStatement.setLong(9, model.getAuctionWinOffer().getId());
                    preparedStatement.executeUpdate();
                }
            } else {
                try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY)) {
                    preparedStatement.setString(1, model.getAuctionName());
                    preparedStatement.setString(2, model.getAuctionDescription());
                    if (model.getSubcategory() != null) preparedStatement.setLong(3, model.getSubcategory().getId());
                    else preparedStatement.setNull(3, java.sql.Types.INTEGER);

                    if (model.getUser() != null) preparedStatement.setLong(4, model.getUser().getId());
                    else preparedStatement.setNull(4, java.sql.Types.INTEGER);

                    preparedStatement.setTimestamp(5, Timestamp.valueOf(model.getAuctionStartTime()));
                    preparedStatement.setTimestamp(6, Timestamp.valueOf(model.getAuctionEndTime()));
                    preparedStatement.setTimestamp(7, Timestamp.valueOf(model.getAuctionEndTime()));
                    preparedStatement.setInt(8, model.isAuctionIsActive());
                    preparedStatement.setLong(9, model.getAuctionWinOffer().getId());
                    preparedStatement.setLong(10, model.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    @Override
    public void delete(Auction model) throws SQLException {
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
    public List<Auction> getAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            List<Auction> auctionList = new ArrayList<>();
            try (ResultSet resultSet = conn.createStatement().executeQuery(GET_ALL_QUERY)) {
                while (resultSet.next()) {
                    Auction loadedAuction = new Auction();
                    loadedAuction.setId(resultSet.getLong("idAuction"));
                    loadedAuction.setAuctionName(resultSet.getString("auctionName"));
                    loadedAuction.setAuctionDescription(resultSet.getString("auctionDescription"));
                    loadedAuction.setSubcategory(subcategoryDao.getById(resultSet.getLong("subcategoryId")));
                    loadedAuction.setUser(userDao.getById(resultSet.getLong("userId")));
                    loadedAuction.setAuctionStartTime(resultSet.getTimestamp("auctionStartTime").toLocalDateTime());
                    loadedAuction.setAuctionEndTime(resultSet.getTimestamp("auctionEndTime").toLocalDateTime());
                    loadedAuction.setAuctionIsActive(resultSet.getByte("auctionIsActive"));
                    loadedAuction.setAuctionView(resultSet.getLong("auctionView"));
                    loadedAuction.setAuctionWinOffer(offerDao.getById(resultSet.getLong("auctionWinOfferID")));
                    auctionList.add(loadedAuction);
                }
            }

            return auctionList;
        }
    }

    @Override
    public Auction getById(Long id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            Auction loadedAuction = new Auction();
            try (PreparedStatement preparedStatement = conn.prepareStatement(GET_BY_ID)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    loadedAuction.setId(resultSet.getLong("idAuction"));
                    loadedAuction.setAuctionName(resultSet.getString("auctionName"));
                    loadedAuction.setAuctionDescription(resultSet.getString("auctionDescription"));
                    loadedAuction.setSubcategory(subcategoryDao.getById(resultSet.getLong("subcategoryId")));
                    loadedAuction.setUser(userDao.getById(resultSet.getLong("userId")));
                    loadedAuction.setAuctionStartTime(resultSet.getTimestamp("auctionStartTime").toLocalDateTime());
                    loadedAuction.setAuctionEndTime(resultSet.getTimestamp("auctionEndTime").toLocalDateTime());
                    loadedAuction.setAuctionIsActive(resultSet.getByte("auctionIsActive"));
                    loadedAuction.setAuctionView(resultSet.getLong("auctionView"));
                    loadedAuction.setAuctionWinOffer(offerDao.getById(resultSet.getLong("auctionWinOfferID")));
                }
            }

            return loadedAuction;
        }
    }
}
