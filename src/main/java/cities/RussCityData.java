package cities;

public enum RussCityData implements ICityData {
    TOMSK("Томск",CountriesData.RUSSIA);

    private String nameCity;
    private CountriesData countriesData;

    RussCityData(String nameCity, CountriesData countriesData) {
        this.nameCity = nameCity;
        this.countriesData = countriesData;
    }
    public String getName()
    {
        return nameCity;
    }
    public CountriesData getCountriesData()
    {
        return countriesData;
    }
}
