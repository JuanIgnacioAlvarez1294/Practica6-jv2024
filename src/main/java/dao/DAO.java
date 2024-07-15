package dao;

import java.util.List;

public interface DAO<T> {
    void crear(T t);
    List<T> listar();
}
