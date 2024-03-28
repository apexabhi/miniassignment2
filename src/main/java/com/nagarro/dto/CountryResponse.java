//Country DTO to handle JSON response from API2
package com.nagarro.dto;

import java.util.List;

public class CountryResponse {
    private int count;
    private String name;
    private List<CountryInfo> country;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CountryInfo> getCountry() {
        return country;
    }

    public void setCountry(List<CountryInfo> country) {
        this.country = country;
    }

    public static class CountryInfo {
        private String country_id;
        private double probability;

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }
    }
}
