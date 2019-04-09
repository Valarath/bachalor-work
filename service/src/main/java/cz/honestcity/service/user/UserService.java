package cz.honestcity.service.user;

import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.ClosedExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.suggestion.SuggestionService;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserGateway userGateway;

    @Autowired
    protected Map<String, SuggestionService> suggestionServices;

    public User getUser(long userId){
        return userGateway.getUser(userId);
    }

    public int getUserScore(long userId) {
        return userGateway.getUserScore(userId);
    }

    public Map<Class<? extends Suggestion>,List<? extends Suggestion>> getUserSuggestions(long userId) {
        Map<Class<? extends Suggestion>,List<? extends Suggestion>> userSuggestions = new HashMap<>();
        suggestionServices.entrySet().forEach( entry -> {
            if(!entry.getKey().equals(SuggestionServiceType.SuggestionServiceTypeNames.BASE_SERVICE))
                addUserSuggestions(userId, userSuggestions, entry);
        });
        return  userSuggestions;
    }

    private void addUserSuggestions(long userId, Map<Class<? extends Suggestion>, List<? extends Suggestion>> userSuggestions, Map.Entry<String, SuggestionService> entry) {
        List<? extends Suggestion> suggestions = entry.getValue().getUserSuggestions(userId);
        userSuggestions.put((Class<? extends Suggestion>) suggestions.getClass().arrayType(),suggestions);
    }

    public void increaseUserScore(User user) {
        userGateway.setUserScore(getIncreasedScore(user));
    }

    private User getIncreasedScore(User user) {
        return user.setScore(user.getScore()+1);
    }

    public void saveNewUser(User user){
        userGateway.saveNewUser(user);
    }

    public void updateUserData(User user){
        userGateway.updateUserData(user);
    }
}
