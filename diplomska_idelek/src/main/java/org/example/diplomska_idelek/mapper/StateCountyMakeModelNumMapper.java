package org.example.diplomska_idelek.mapper;

import org.example.diplomska_idelek.model.ElectricVehicleGeneric;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StateCountyMakeModelNumMapper implements RowMapper<ElectricVehicleGeneric> {
    @Override
    public ElectricVehicleGeneric mapRow(ResultSet rs, int rowNum) throws SQLException {
        ElectricVehicleGeneric ev = new ElectricVehicleGeneric();
        ev.setField1(rs.getString("state"));
        ev.setField2(rs.getString("county"));
        ev.setField3(rs.getString("make"));
        ev.setField4(rs.getString("model"));
        ev.setField5(rs.getString("num_cars"));
        return ev;
    }
}
