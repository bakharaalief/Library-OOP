package dao;

import java.util.ArrayList;

public interface Dao<T> {
    ArrayList<T> getAll();
    void create(T t);
    void update(T t);
    void delete(T t);
}
