package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public List<User> getUsers() {

        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {

        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {

        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void update(User user) {

        userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        userDao.delete(id);
    }
}