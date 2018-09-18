package funda.test.ui;

import funda.test.model.Category;
import net.serenitybdd.screenplay.targets.Target;

public class NavigationBar {
    public static Target category(Category category){
        return Target.the(category.name() + " category")
                .locatedBy("//a[contains(text(),'{0}')]")
                .of(category.label());
    }
}
