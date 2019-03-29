package cz.honestcity.service.gateway;

import cz.honestcity.model.user.User;

public interface UserGateway {
    User getUser(long userId);

    void increaseUserScore(long id, int increasedScore);

    int getUserScore(long userId);
}
