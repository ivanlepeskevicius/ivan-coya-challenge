package funda.test.model;


public enum Category {
    ForSale("For sale"),
    ForRent("For rent"),
    NewlyBuilt("Newly built"),
    Recreation("Recreation"),
    Europe("Europe");

    Category(String label) {
        this.label = label;
    }

    private final String label;

    public String label() {
        return label;
    }
}
