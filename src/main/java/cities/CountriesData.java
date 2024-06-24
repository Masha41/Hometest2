package cities;

public enum CountriesData {

    RUSSIA("Россия");
    private String namecountry;

    CountriesData(String namecountry) {
        this.namecountry = namecountry;
    }
    public String getNamecountry() {
        return this.namecountry;
    }
}
