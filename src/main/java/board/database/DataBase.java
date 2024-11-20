package board.database;

import java.util.List;

public interface DataBase<T> {
    void add(T o);
    T findById(Long id);
    List<T> findAll();
    void updateById(Long id, T o);
    void deleteById(Long id);
}
