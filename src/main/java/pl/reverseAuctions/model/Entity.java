package pl.reverseAuctions.model;

import java.sql.SQLException;
import java.util.List;

public interface Entity<T> {

    void saveToDb(T model) throws SQLException;

    void delete(T model) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;
}
