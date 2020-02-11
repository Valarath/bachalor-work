package cz.honestcity.service.vote;

import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.model.user.User;
import cz.honestcity.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class VoteService {

    //TODO to config file
    private static final int LOWEST_VALUE_FOR_ACCEPTENCE = 5;

    @Autowired
    private VoteGateway voteGateway;

    private UserService userService;

    public abstract void upVote(Suggestion suggestion, long userId);

    protected void increaseSuggesterScore(User user){
        userService.increaseUserScore(user);
    }

    protected void recordVote(long suggestionId, long userId){
        voteGateway.recordVote(suggestionId,userId);
    }

    protected boolean isSuggestionAcceptable(long suggestionId, long userId) {
        return voteGateway.getNumberOfVotes(suggestionId)*calculateUserTrustworthiness(userId)> LOWEST_VALUE_FOR_ACCEPTENCE;
    }

    private double calculateUserTrustworthiness(long userId){
        return Math.atan(userService.getUserScore(userId)+Double.MIN_VALUE);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
