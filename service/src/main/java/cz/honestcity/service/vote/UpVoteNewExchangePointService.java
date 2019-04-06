package cz.honestcity.service.vote;

import cz.honestcity.model.exchange.ExchangePoint;
import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.vote.VoteType;
import cz.honestcity.service.suggestion.SuggestionServiceType;
import org.springframework.stereotype.Service;

@Service(VoteType.VoteConstants.NEW_EXCHANGE_POINT)
public class UpVoteNewExchangePointService extends VoteService{

    public void upVote(long suggestionId, long userId) {
        if(isSuggestionAcceptable(suggestionId, userId))
            acceptNewExchangePoint(suggestionId);
        recordVote(suggestionId,userId);
    }

    private void acceptNewExchangePoint(long suggestionId){
        NewExchangePointSuggestion suggestion = (NewExchangePointSuggestion) suggestionServices.get(SuggestionServiceType.NEW_EXCHANGE_POINT.name()).getSuggestion(suggestionId);
        exchangeService.createExchange(getNewExchangePoint(suggestion));
        increaseSuggesterScore(suggestion.getSuggestedBy());
    }

    private ExchangePoint getNewExchangePoint(NewExchangePointSuggestion suggestion) {
        return (ExchangePoint) new ExchangePoint().setPosition(suggestion.getPosition());
    }
}
