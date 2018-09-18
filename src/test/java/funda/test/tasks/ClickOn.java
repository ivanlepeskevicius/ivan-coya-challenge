package funda.test.tasks;

import funda.test.ui.SearchElements;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;


import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class ClickOn implements Task {


    @Step("Press the Search button")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(SearchElements.SEARCH_BTN, isCurrentlyEnabled()).forNoMoreThan(10).seconds(),
                WaitUntil.the(SearchElements.SEARCH_BTN, isCurrentlyVisible()),
                Hover.over(SearchElements.SEARCH_BTN),
                Click.on(SearchElements.SEARCH_BTN)
        );
    }

    public static ClickOn theSearchButton(){
        return instrumented(ClickOn.class);
    }

}
