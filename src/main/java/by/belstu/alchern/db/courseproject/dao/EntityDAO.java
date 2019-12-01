package by.belstu.alchern.db.courseproject.dao;

import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.PositionDAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.RoleDAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.TicketDAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.UserDAOException;

import java.util.List;

public interface EntityDAO<T> {
    T get(int id) throws DAOException;

    List<T> getAll() throws DAOException;

    void insert(T t) throws DAOException;

    void update(T t) throws DAOException;

    void delete(T t) throws DAOException;
}
