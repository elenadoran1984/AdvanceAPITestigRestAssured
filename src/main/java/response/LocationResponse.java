package response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LocationResponse {

    private String country;
    private String countryAbbreviation;
    private String postCode;
    private List<Place> places;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("country abbreviation")
    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    @JsonProperty("country abbreviation")
    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    @JsonProperty("post code")
    public String getPostCode() {
        return postCode;
    }

    @JsonProperty("post code")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public static class Place {

        private String placeName;
        private String longitude;
        private String latitude;
        private String state;
        private String stateAbbreviation;

        @JsonProperty("place name")
        public String getPlaceName() {
            return placeName;
        }

        @JsonProperty("place name")
        public void setPlaceName(String placeName) {
            this.placeName = placeName;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        @JsonProperty("state abbreviation")
        public String getStateAbbreviation() {
            return stateAbbreviation;
        }

        @JsonProperty("state abbreviation")
        public void setStateAbbreviation(String stateAbbreviation) {
            this.stateAbbreviation = stateAbbreviation;
        }
    }
}
