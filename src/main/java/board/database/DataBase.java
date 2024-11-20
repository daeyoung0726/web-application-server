package board.database;

import java.util.List;

public interface DataBase<T> {
    void add(T o);
    T findById(Object id);
    List<T> findAll();
    void updateById(Object id, T o);
    void deleteById(Object id);
}
