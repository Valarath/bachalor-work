package cz.honestcity.service.gateway;

import cz.honestcity.model.user.User;

public interface UserGateway {

    User getUser(long userId);

    void setUserScore(User user);

    int getUserScore(long userId);

    void saveNewUser(User user);

    void updateUserData(User user);
}
