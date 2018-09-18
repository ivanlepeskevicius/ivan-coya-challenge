package funda.test.model;

import com.google.common.base.MoreObjects;

public class SearchResults {

    private String SearchField;
    private String DistanceField;
    private String FromField;
    private String ToField;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("SearchField", SearchField)
                .add("DistanceField", DistanceField)
                .add("FromField", FromField)
                .add("ToField", ToField)
                .toString();
    }

    public SearchResults(String SearchField, String DistanceField, String FromField, String ToField) {
        this.SearchField = SearchField;
        this.DistanceField = DistanceField;
        this.FromField = FromField;
        this.ToField = ToField;

    }

    public String getSearchField() {
        return SearchField;
    }

    public String getDistanceField() {
        return DistanceField;
    }

    public String getFromField() { return FromField; }

    public String getToField() {
        return ToField;
    }

}
