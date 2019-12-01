package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.exception.UserServiceException;
import by.belstu.alchern.db.courseproject.view.dto.UserDTO;

public interface UserService {
    User login(String login, String password) throws UserServiceException;

    User register(UserDTO userDTO) throws UserServiceException;
}
