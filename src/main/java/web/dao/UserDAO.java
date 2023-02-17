package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    void saveUser(User user);

    User getUserById(Long id);

    void update(User user);

    void delete(Long id);


}
