package org.example.diplomska_idelek.mapper;

import org.example.diplomska_idelek.model.ElectricVehicleGeneric;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AvgElectricRangeVehiclesByStateMapper implements RowMapper<ElectricVehicleGeneric> {
    @Override
    public ElectricVehicleGeneric mapRow(ResultSet rs, int rowNum) throws SQLException {
        ElectricVehicleGeneric ev = new ElectricVehicleGeneric();
        ev.setField1(rs.getString("state"));
        ev.setField2(rs.getString("make"));
        ev.setField3(rs.getString("model"));
        return ev;
    }
}
