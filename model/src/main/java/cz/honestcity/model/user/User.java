package cz.honestcity.model.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class User {
    private long userId;
    private int score;
}
