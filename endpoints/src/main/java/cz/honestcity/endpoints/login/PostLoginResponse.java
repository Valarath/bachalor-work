package cz.honestcity.endpoints.login;

import cz.honestcity.model.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author michal.keder
 */
@Data
@Accessors(chain = true)
public class PostLoginResponse {

    private User user;

}