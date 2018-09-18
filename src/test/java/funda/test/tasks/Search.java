package funda.test.tasks;

import funda.test.ui.SearchElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Search implements Task {


    private final String searchTerm;

    protected Search(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Step("Enter a valid term for search")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Enter.theValue(searchTerm).into(SearchElements.SEARCH_FIELD)
        );
    }

    public static Performable forTheTerm(String searchTerm) {
        return instrumented(Search.class, searchTerm);
    }
}
