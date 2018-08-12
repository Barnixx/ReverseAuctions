package pl.reverseAuctions.model.address;

import pl.reverseAuctions.model.user.User;


public class Address {

    private Long id;
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private User user;

    public Address() {
    }

    public Address(Long id, String country, String city, String postalCode, String street, User user) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
