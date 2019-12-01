package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.exception.UserServiceException;
import by.belstu.alchern.db.courseproject.view.dto.UserDTO;

import java.util.List;

public interface UserService {
    User login(String login, String password) throws UserServiceException;

    User register(UserDTO userDTO) throws UserServiceException;

    User editProfile(User user, UserDTO userDTO) throws UserServiceException;

    List<User> getByPageNum(int pageNum) throws UserServiceException;
}
