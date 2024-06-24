package data.gender;

public enum GenderData {
    FEMALE("w");
    private String name;
    GenderData(String  name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
