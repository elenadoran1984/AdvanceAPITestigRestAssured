package request;

public class LocationRequest {
    private String countryAbbreviation;
    private String postCode;

    public LocationRequest(
            String countryAbbreviation, String postCode) {
        this.countryAbbreviation = countryAbbreviation;
        this.postCode = postCode;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getEndpointPath() {
        return "/" + countryAbbreviation + "/" + postCode;
    }
}

