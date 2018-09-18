package funda.test.features.navigation;

import funda.test.questions.SearchFiltersQuestion;
import funda.test.tasks.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static funda.test.model.Category.ForSale;
import static funda.test.tasks.SelectSuggestion.SelectSuggestion;
import static funda.test.ui.SearchElements.*;
import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.matchers.ConsequenceMatchers.displays;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotPresent;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SerenityRunner.class)
public class WhenSearchingForSale {

    @Managed(driver = "chrome")
    WebDriver theBrowser;
    Actor theUser = Actor.named("User");
    SearchFiltersQuestion results = new SearchFiltersQuestion();

    @Steps
    OpenTheFundaHomePage openTheFundaHomePage;

    @Before
    public void userCanBrowseTheWeb() {
        theUser.can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    public void searchWithNoParameters(){
        givenThat(theUser).wasAbleTo(openTheFundaHomePage);

        when(theUser).attemptsTo(
                ClickOn.theSearchButton(),
                WaitUntil.the(SEARCH_RESULTS, isVisible()).forNoMoreThan(10).seconds()
        );

        then(theUser).should(seeThat(TheWebPage.title(),
                containsString("Homes for sale Nederland - Houses for sale in Nederland [funda]")));

        andThat(theUser).should(
                seeThat(results, displays("SearchField",equalTo("Nederland"))),
                seeThat(results, displays("DistanceField",equalTo("0"))),
                seeThat(results, displays("FromField",equalTo("0"))),
                seeThat(results, displays("ToField",equalTo("ignore_filter")))
        );
    }

    @Test
    public void searchUsingOnlyASearchTerm(){
        givenThat(theUser).wasAbleTo(openTheFundaHomePage);

        when(theUser).attemptsTo(
                Search.forTheTerm("Amsterdam"),
                SelectSuggestion(),
                ClickOn.theSearchButton(),
                WaitUntil.the(SEARCH_RESULTS, isVisible()).forNoMoreThan(10).seconds()
        );

        then(theUser).should(
                seeThat(TheWebPage.title(),
                containsString("Homes for sale Amsterdam - Houses for sale in Amsterdam [funda]"))
                );

        andThat(theUser).should(
                seeThat(results, displays("SearchField",equalTo("Amsterdam"))),
                seeThat(results, displays("DistanceField",equalTo("0"))),
                seeThat(results, displays("FromField",equalTo("0"))),
                seeThat(results, displays("ToField",equalTo("ignore_filter")))
        );
    }

    @Test
    public void searchUsingAnInvalidSearchTerm(){
        givenThat(theUser).wasAbleTo(openTheFundaHomePage);

        when(theUser).attemptsTo(
                Search.forTheTerm("qwerty"),
                ClickOn.theSearchButton()
        );

        then(theUser).should(eventually(seeThat(the(NO_SUGGESTION), isVisible())));
    }

    @Test
    public void selectAToValueBiggerThanTheFromValue(){
        givenThat(theUser).wasAbleTo(openTheFundaHomePage);

        when(theUser).attemptsTo(
                SelectFromOptions.byValue("100000").from(FROM_FIELD),
                SelectFromOptions.byValue("50000").from(TO_FIELD),
                ClickOn.theSearchButton()
        );

        then(theUser).should(eventually(seeThat(the(INVALID_TO), isVisible())));
        and(theUser).should(eventually(seeThat(the(NO_RESULTS_WARNING), isVisible())));
    }

    @Test
    public void performASearchUsingAllTheParameters(){
        givenThat(theUser).wasAbleTo(openTheFundaHomePage);

        when(theUser).attemptsTo(
                Search.forTheTerm("Amsterdam"),
                SelectSuggestion(),
                SelectFromOptions.byValue("5").from(DISTANCE_FIELD),
                SelectFromOptions.byValue("50000").from(FROM_FIELD),
                SelectFromOptions.byValue("100000").from(TO_FIELD),
                ClickOn.theSearchButton(),
                WaitUntil.the(SEARCH_RESULTS, isVisible()).forNoMoreThan(10).seconds()
        );

        then(theUser).should(
                seeThat(TheWebPage.title(),
                        containsString("Homes for sale Amsterdam - Houses for sale in Amsterdam [funda]"))
        );

        andThat(theUser).should(
                seeThat(results, displays("SearchField",equalTo("Amsterdam"))),
                seeThat(results, displays("DistanceField",equalTo("5"))),
                seeThat(results, displays("FromField",equalTo("50000"))),
                seeThat(results, displays("ToField",equalTo("100000")))
        );
    }


    @Test
    public void checkTheLastSearchedCriteriaIsDisplayed(){
        givenThat(theUser).wasAbleTo(openTheFundaHomePage);
        and(theUser).wasAbleTo(
                Search.forTheTerm("Amsterdam"),
                SelectSuggestion(),
                SelectFromOptions.byValue("5").from(DISTANCE_FIELD),
                SelectFromOptions.byValue("50000").from(FROM_FIELD),
                SelectFromOptions.byValue("100000").from(TO_FIELD),
                ClickOn.theSearchButton(),
                WaitUntil.the(SEARCH_RESULTS, isVisible()).forNoMoreThan(10).seconds()
        );

        when(theUser).attemptsTo(NavigateTo.theCategory(ForSale));

        then(theUser).should(seeThat(the(LAST_SEARCH), isVisible()));
    }

    @Test
    public void checkTheDefaultValuesForAllTheParameters(){
        givenThat(theUser).wasAbleTo(openTheFundaHomePage);

        then(theUser).should(
                seeThat(TheWebPage.title(),
                        containsString("Search homes for sale in the Netherlands [funda]"))
        );

        andThat(theUser).should(
                seeThat(results, displays("SearchField",equalTo(""))),
                seeThat(results, displays("DistanceField",equalTo("0"))),
                seeThat(results, displays("FromField",equalTo("0"))),
                seeThat(results, displays("ToField",equalTo("ignore_filter"))),
                seeThat(the(LAST_SEARCH), isNotPresent())
        );
    }
}

