package com.arsh.covidrf;

public class CountryStatus {
    private String country;
    private String last_update;
    private int cases;
    private int deaths;
    private int recovered;

public CountryStatus(String country, String last_update, int cases, int deaths, int recovered){
    this.cases = cases;
    this.country = country;
    this.deaths = deaths;
    this.last_update = last_update;
    this.recovered =recovered;
}

    @Override
    public String toString() {
        return "CountryStatus{" +
                "country='" + country + '\'' +
                ", last_update='" + last_update + '\'' +
                ", cases=" + cases +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}
