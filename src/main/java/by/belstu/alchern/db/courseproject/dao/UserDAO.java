package by.belstu.alchern.db.courseproject.dao;

import by.belstu.alchern.db.courseproject.dao.exception.impl.UserDAOException;
import by.belstu.alchern.db.courseproject.model.impl.User;

import java.util.List;

public interface UserDAO extends EntityDAO<User> {
    User login(String login, String password) throws UserDAOException;

    List<User> getByRange(int x1, int x2) throws UserDAOException;
}
