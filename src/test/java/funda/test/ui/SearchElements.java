package funda.test.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SearchElements {

    public static Target SEARCH_FIELD = Target.the("search field").located(By.id("autocomplete-input"));
    public static Target LAST_SEARCH = Target.the("last search link").located(By.className("search-block__last-query"));
    public static Target DISTANCE_FIELD = Target.the("distance field").located(By.id("Straal"));
    public static Target FROM_FIELD = Target.the("from field").located(By.id("range-filter-selector-select-filter_koopprijsvan"));
    public static Target TO_FIELD = Target.the("to field").located(By.id("range-filter-selector-select-filter_koopprijstot"));
    public static Target INVALID_TO = Target.the("invalid value for the to field").located(By.xpath("//div[@class='range-filter-selector is-invalid']"));
    public static Target SEARCH_BTN = Target.the("search button").located(By.xpath("//button[contains(text(),'Search')]"));
    public static Target FIRST_SUGGESTION = Target.the("first suggestion").located(By.id("autocomplete-list-option0"));
    public static Target NO_SUGGESTION = Target.the("no suggestion").located(By.className("autocomplete-no-suggestion-message"));
    public static Target SEARCH_RESULTS = Target.the("results").located(By.className("search-output-result-count"));
    public static Target NO_RESULTS_WARNING = Target.the("no results").located(By.xpath("//div[@class='search-no-results search-no-results-warning']"));
}
