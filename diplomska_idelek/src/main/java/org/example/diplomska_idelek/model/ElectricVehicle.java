package org.example.diplomska_idelek.model;

import java.util.Objects;

public class ElectricVehicle {

    private String vin;
    private String county;
    private String city;
    private String state;
    private String postalCode;
    private String modelYear;
    private String make;
    private String model;
    private String electricVehicleType;
    private String cafvEligibility;
    private String electricRange;
    private String baseMsrp;
    private String legislativeDistrict;
    private String dolVehicleId;
    private String vehicleLocation;
    private String electricUtility;
    private String censusTract2020;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getElectricVehicleType() {
        return electricVehicleType;
    }

    public void setElectricVehicleType(String electricVehicleType) {
        this.electricVehicleType = electricVehicleType;
    }

    public String getCafvEligibility() {
        return cafvEligibility;
    }

    public void setCafvEligibility(String cafvEligibility) {
        this.cafvEligibility = cafvEligibility;
    }

    public String getElectricRange() {
        return electricRange;
    }

    public void setElectricRange(String electricRange) {
        this.electricRange = electricRange;
    }

    public String getBaseMsrp() {
        return baseMsrp;
    }

    public void setBaseMsrp(String baseMsrp) {
        this.baseMsrp = baseMsrp;
    }

    public String getLegislativeDistrict() {
        return legislativeDistrict;
    }

    public void setLegislativeDistrict(String legislativeDistrict) {
        this.legislativeDistrict = legislativeDistrict;
    }

    public String getDolVehicleId() {
        return dolVehicleId;
    }

    public void setDolVehicleId(String dolVehicleId) {
        this.dolVehicleId = dolVehicleId;
    }

    public String getVehicleLocation() {
        return vehicleLocation;
    }

    public void setVehicleLocation(String vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
    }

    public String getElectricUtility() {
        return electricUtility;
    }

    public void setElectricUtility(String electricUtility) {
        this.electricUtility = electricUtility;
    }

    public String getCensusTract2020() {
        return censusTract2020;
    }

    public void setCensusTract2020(String censusTract2020) {
        this.censusTract2020 = censusTract2020;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectricVehicle that = (ElectricVehicle) o;
        return Objects.equals(vin, that.vin) && Objects.equals(county, that.county) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(postalCode, that.postalCode) && Objects.equals(modelYear, that.modelYear) && Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(electricVehicleType, that.electricVehicleType) && Objects.equals(cafvEligibility, that.cafvEligibility) && Objects.equals(electricRange, that.electricRange) && Objects.equals(baseMsrp, that.baseMsrp) && Objects.equals(legislativeDistrict, that.legislativeDistrict) && Objects.equals(dolVehicleId, that.dolVehicleId) && Objects.equals(vehicleLocation, that.vehicleLocation) && Objects.equals(electricUtility, that.electricUtility) && Objects.equals(censusTract2020, that.censusTract2020);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin, county, city, state, postalCode, modelYear, make, model, electricVehicleType, cafvEligibility, electricRange, baseMsrp, legislativeDistrict, dolVehicleId, vehicleLocation, electricUtility, censusTract2020);
    }
}
