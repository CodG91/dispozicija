package org.example.diplomska_idelek.mapper;

import org.example.diplomska_idelek.model.ElectricVehicleGeneric;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeslaModelCityNumMapper implements RowMapper<ElectricVehicleGeneric> {
    @Override
    public ElectricVehicleGeneric mapRow(ResultSet rs, int rowNum) throws SQLException {
        ElectricVehicleGeneric ev = new ElectricVehicleGeneric();
        ev.setField1(rs.getString("make"));
        ev.setField2(rs.getString("model"));
        ev.setField3(rs.getString("city"));
        ev.setField4(rs.getString("num_cars"));
        return ev;
    }
}
