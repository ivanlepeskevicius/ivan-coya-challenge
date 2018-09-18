package funda.test.features.navigation;

import funda.test.tasks.NavigateTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.thucydides.core.annotations.Managed;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static funda.test.model.Category.*;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.CoreMatchers.containsString;


@RunWith(SerenityRunner.class)
public class WhenBrowsingSearchCategories {

    @Managed(driver = "chrome")
    WebDriver theBrowser;
    Actor theUser = Actor.named("User");

    @Test
    public void shouldBeAbleToViewTheForSalePage(){

        theUser.can(BrowseTheWeb.with(theBrowser));

        when(theUser).attemptsTo(NavigateTo.theCategory(ForSale));

        then(theUser).should(seeThat(TheWebPage.title(),containsString("Search homes for sale in the Netherlands [funda]")));
    }

    @Test
    public void shouldBeAbleToViewTheRentPage(){

        theUser.can(BrowseTheWeb.with(theBrowser));

        when(theUser).attemptsTo(NavigateTo.theCategory(ForRent));

        then(theUser).should(seeThat(TheWebPage.title(),containsString("Search homes for rent in the Netherlands [funda]")));
    }

    @Test
    public void shouldBeAbleToViewTheNewlyBuiltPage(){

        theUser.can(BrowseTheWeb.with(theBrowser));

        when(theUser).attemptsTo(NavigateTo.theCategory(NewlyBuilt));

        then(theUser).should(seeThat(TheWebPage.title(),containsString("Search new development projects in the Netherlands [funda]")));
    }

    @Test
    public void shouldBeAbleToViewTheRecreationPage(){

        theUser.can(BrowseTheWeb.with(theBrowser));

        when(theUser).attemptsTo(NavigateTo.theCategory(Recreation));

        then(theUser).should(seeThat(TheWebPage.title(),containsString("Search holiday homes in the Netherlands [funda]")));
    }

    @Test
    public void shouldBeAbleToViewTheEuropePage(){

        theUser.can(BrowseTheWeb.with(theBrowser));

        when(theUser).attemptsTo(NavigateTo.theCategory(Europe));

        then(theUser).should(seeThat(TheWebPage.title(),containsString("Search homes in Europe [funda]")));
    }
}
