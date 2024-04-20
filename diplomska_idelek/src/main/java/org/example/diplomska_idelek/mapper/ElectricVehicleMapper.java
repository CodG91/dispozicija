package org.example.diplomska_idelek.mapper;

import org.example.diplomska_idelek.model.ElectricVehicle;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElectricVehicleMapper implements RowMapper<ElectricVehicle> {

    @Override
    public ElectricVehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        ElectricVehicle ev = new ElectricVehicle();
        ev.setVin(rs.getString("vin"));
        ev.setCounty(rs.getString("county"));
        ev.setCity(rs.getString("city"));
        ev.setState(rs.getString("state"));
        ev.setPostalCode(rs.getString("postal_code"));
        ev.setModelYear(rs.getString("model_year"));
        ev.setMake(rs.getString("make"));
        ev.setModel(rs.getString("model"));
        ev.setElectricVehicleType(rs.getString("electric_vehicle_type"));
        ev.setCafvEligibility(rs.getString("cafv_eligibility"));
        ev.setElectricRange(rs.getString("electric_range"));
        ev.setBaseMsrp(rs.getString("base_msrp"));
        ev.setLegislativeDistrict(rs.getString("legislative_district"));
        ev.setDolVehicleId(rs.getString("dol_vehicle_id"));
        ev.setVehicleLocation(rs.getString("vehicle_location"));
        ev.setElectricUtility(rs.getString("electric_utility"));
        ev.setCensusTract2020(rs.getString("census_tract_2020"));
        return ev;
    }
}
