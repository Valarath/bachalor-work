package cz.honestcity.database.suggestion.exchange.rate;

import cz.honestcity.database.suggestion.SuggestionPostgresGateway;
import cz.honestcity.database.suggestion.SuggestionPostgresMapper;
import cz.honestcity.model.suggestion.ExchangeRateSuggestion;
import cz.honestcity.model.suggestion.Suggestion;
import cz.honestcity.service.suggestion.exchange.rate.ExchangeRateSuggestionGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateSuggestionPostgresGateway extends SuggestionPostgresGateway implements ExchangeRateSuggestionGateway {

    private final ExchangeRateSuggestionPostgresMapper mapper;

    public ExchangeRateSuggestionPostgresGateway(SuggestionPostgresMapper suggestionPostgresMapper, ExchangeRateSuggestionPostgresMapper mapper) {
        super(suggestionPostgresMapper);
        this.mapper = mapper;
    }

    @Override
    public List<? extends ExchangeRateSuggestion> getExchangePointSuggestions(String exchangePointId) {
        List<ExchangeRatePostgresSuggestion> suggestions = mapper.getExchangePointSuggestions(exchangePointId);
        suggestions.forEach(this::setRates);
        return suggestions;
    }

    @Override
    public void suggests(List<? extends Suggestion> suggestions) {
        suggestions.forEach(suggestion ->{
            suggestion.setId(suggestionPostgresMapper.suggest(suggestion));
            mapper.suggestsExchangeRateChange((ExchangeRateSuggestion) suggestion);
        });
    }

    @Override
    public ExchangeRateSuggestion getSuggestion(String suggestionId) {
        ExchangeRatePostgresSuggestion exchangeRateSuggestion = mapper.getExchangeRateSuggestion(suggestionId);
        setRates(exchangeRateSuggestion);
        return exchangeRateSuggestion;
    }

    @Override
    public List<? extends ExchangeRateSuggestion> getUserSuggestions(String userId) {
        List<ExchangeRatePostgresSuggestion> suggestions = mapper.getUserExchangeRateSuggestions(userId);
        suggestions.forEach(this::setRates);
        return suggestions;
    }

    private void setRates(ExchangeRatePostgresSuggestion exchangeRateSuggestion) {
        exchangeRateSuggestion.getSuggestedExchangeRate().setRates(mapper.getSuggestedRates(exchangeRateSuggestion.getSuggestedExchangeRate().getId()));
    }

}