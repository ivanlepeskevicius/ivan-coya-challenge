package funda.test.tasks;

import funda.test.model.Category;
import funda.test.ui.FundaHomePage;
import funda.test.ui.NavigationBar;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

public class NavigateTo implements Task {

    private final Category category;
    private FundaHomePage theFundaHomePage;

    public NavigateTo(Category category) {
        this.category = category;
    }

    @Override
    @Step("{0} navigates to the #category category")
    public <T extends Actor> void performAs(T actor){
        actor.attemptsTo(
                Open.browserOn(theFundaHomePage),
                Click.on(NavigationBar.category(category))
        );
    }

    public static Performable theCategory(Category category) {
        return Instrumented.instanceOf(NavigateTo.class).withProperties(category);
    }
}
