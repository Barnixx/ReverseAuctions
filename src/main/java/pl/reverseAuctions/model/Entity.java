package pl.reverseAuctions.model;

import java.util.List;

public interface Entity<T> {

    void saveToDb(T model);

    void delete(T model);

    List<T> getAll();

    T getById(int id);
}
