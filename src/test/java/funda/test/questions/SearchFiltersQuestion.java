package funda.test.questions;

import funda.test.model.SearchResults;
import funda.test.ui.SearchElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Value;

public class SearchFiltersQuestion implements Question<SearchResults> {

    @Override
    public SearchResults answeredBy(Actor actor) {

        String SearchField = Value.of(SearchElements.SEARCH_FIELD)
                .viewedBy(actor)
                .value();

        String DistanceField = Value.of(SearchElements.DISTANCE_FIELD)
                .viewedBy(actor)
                .value();

        String FromField = Value.of(SearchElements.FROM_FIELD)
                .viewedBy(actor)
                .value();

        String ToField = Value.of(SearchElements.TO_FIELD)
                .viewedBy(actor)
                .value();

        return new SearchResults(
                SearchField,
                DistanceField,
                FromField,
                ToField
                );
    }
}
