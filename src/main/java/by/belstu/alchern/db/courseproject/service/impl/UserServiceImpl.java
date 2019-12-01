package by.belstu.alchern.db.courseproject.service.impl;

import by.belstu.alchern.db.courseproject.dao.DAOFactory;
import by.belstu.alchern.db.courseproject.dao.exception.DAOException;
import by.belstu.alchern.db.courseproject.dao.exception.impl.UserDAOException;
import by.belstu.alchern.db.courseproject.model.impl.Role;
import by.belstu.alchern.db.courseproject.model.impl.User;
import by.belstu.alchern.db.courseproject.service.UserService;
import by.belstu.alchern.db.courseproject.service.exception.UserServiceException;
import by.belstu.alchern.db.courseproject.view.dto.UserDTO;

public class UserServiceImpl implements UserService {
    private static final int ROLE_USER_ID = 2;

    @Override
    public User login(String login, String password) throws UserServiceException {
        User user;
        try {
            user = DAOFactory.getInstance().getUserDAO().login(login, password);
        } catch (UserDAOException e) {
            throw new UserServiceException(e);
        }
        return user;
    }

    @Override
    public User register(UserDTO userDTO) throws UserServiceException {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setTel(userDTO.getTel());
        user.setAddress(userDTO.getAddress());
        user.setDocumentNumber(userDTO.getDocumentNumber());
        try {
            user.setRole(DAOFactory.getInstance().getRoleDAO().get(ROLE_USER_ID));
            DAOFactory.getInstance().getUserDAO().insert(user);
        } catch (DAOException e) {
            throw new UserServiceException(e);
        }

        return user;
    }
}
