package cz.honestcity.service.suggestion.exchange.create;

import cz.honestcity.model.suggestion.NewExchangePointSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.suggestion.base.BaseSuggestionGateway;
import cz.honestcity.service.suggestion.base.BaseSuggestionService;
import cz.honestcity.service.vote.exchange.create.UpVoteNewExchangePointService;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

//@Service(SuggestionServiceType.SuggestionServiceTypeNames.NEW_EXCHANGE_POINT)
@HonestCityService(beanId = NewExchangePointSuggestion.class)
public class NewExchangePointSuggestionService extends BaseSuggestionService {

    private final NewExchangePointSuggestionGateway gateway;
    private final UpVoteNewExchangePointService upVoteNewExchangePointService;

    public NewExchangePointSuggestionService(@Qualifier("SuggestionPostgresGateway") BaseSuggestionGateway suggestionGateway, NewExchangePointSuggestionGateway gateway, UpVoteNewExchangePointService upVoteNewExchangePointService) {
        super(suggestionGateway);
        this.gateway = gateway;
        this.upVoteNewExchangePointService = upVoteNewExchangePointService;
    }

    @Override
    public void suggest(List<? extends Suggestion> suggestions) {
        gateway.suggests(suggestions);
        suggesterVotesForHisSuggestions(suggestions, upVoteNewExchangePointService);
    }

    @Override
    public Suggestion getSuggestion(String suggestionId) {
        return gateway.getSuggestion(suggestionId);
    }

    @Override
    public List<? extends Suggestion> getUserSuggestions(String userId) {
        return gateway.getUserSuggestions(userId);
    }
}