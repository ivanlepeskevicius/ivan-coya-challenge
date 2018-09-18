package funda.test.tasks;

import funda.test.ui.SearchElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class SelectSuggestion implements Task {

    @Step("Select the first suggestion for the search term")
    public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    WaitUntil.the(SearchElements.FIRST_SUGGESTION, isPresent()).forNoMoreThan(15).seconds(),
                    WaitUntil.the(SearchElements.FIRST_SUGGESTION, isCurrentlyVisible()).forNoMoreThan(15).seconds(),
                    WaitUntil.the(SearchElements.FIRST_SUGGESTION, isCurrentlyEnabled()).forNoMoreThan(15).seconds()
            );
            actor.attemptsTo(
                    Click.on(SearchElements.FIRST_SUGGESTION),
                    WaitUntil.the(SearchElements.FIRST_SUGGESTION, isNotCurrentlyVisible())
            );
    }

    public static Performable SelectSuggestion() {
        return instrumented(SelectSuggestion.class);
    }
}
