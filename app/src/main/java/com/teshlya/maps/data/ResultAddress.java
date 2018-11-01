package com.teshlya.maps.data;

import com.google.gson.annotations.SerializedName;

public class ResultAddress {

    @SerializedName("address")
    Address address;

    public ResultAddress(String str) {
        address = new Address(str);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public class Address {
        @SerializedName("country")
        String country;

        @SerializedName("state")
        String state;

        @SerializedName("city")
        String city;

        public Address(String country) {
            this.country = country;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
