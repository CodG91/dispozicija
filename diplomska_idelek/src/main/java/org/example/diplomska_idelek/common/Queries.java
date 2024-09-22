package org.example.diplomska_idelek.common;

public class Queries {

    private static final String ALL_FIELDS = "vin, county, city, state, postal_code, model_year, make, model, electric_vehicle_type, " +
            "cafv_eligibility, electric_range, base_msrp, legislative_district, dol_vehicle_id, vehicle_location, electric_utility, " +
            "census_tract_2020 ";
    private static final String FROM_TABLE_NAME = "from electric_vehicle";
    public static final String CONTROL_QUERY = "SELECT COUNT(*) FROM electric_vehicle";
    public static final String INSERT_STATEMENT = "INSERT INTO electric_vehicle (vin, county, city, state, postal_code, model_year, " +
            "make, model, electric_vehicle_type, cafv_eligibility, electric_range, base_msrp, " +
            "legislative_district, dol_vehicle_id, vehicle_location, electric_utility, census_tract_2020) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SELECT_ALL = "SELECT " +
            ALL_FIELDS +
            FROM_TABLE_NAME;

    public static final String GROUP_BY = "SELECT SUM(VEHICLE_COUNT) AS VEHICLE_SUM FROM ( SELECT STATE, COUNT(1) " +
            "AS VEHICLE_COUNT FROM ELECTRIC_VEHICLE GROUP BY STATE) TAB1";

    public static final String GROUP_BY_MODEL = "SELECT model_year, COUNT(*) AS num_vehicles\n" +
            "FROM electric_vehicle\n" +
            "GROUP BY model_year";

    public static final String MODEL_PER_STATE = "SELECT state, make, count(MODEL) AS model\n" +
            "FROM electric_vehicle\n" +
            "WHERE 1 = 1\n" +
            "GROUP BY state, make, model\n" +
            "HAVING count(MODEL) > 5\n" +
            "order by state DESC, MAKE DESC, MODEL DESC";

    public static final String ELECTRIC_UTILITY_PROVIDER = "SELECT make, model, electric_utility, COUNT(*) AS num_cars\n" +
            "            FROM electric_vehicle\n" +
            "            GROUP BY make, model, electric_utility order by make, num_cars DESC";


    public static final String YEAR_MODEL_MAKE = "SELECT make, model, model_year, COUNT(*) AS num_vehicles FROM electric_vehicle " +
            "GROUP BY make, model, model_year order by make, model, num_vehicles DESC";

    public static final String TESLA_MODEL_CITY_NUM = "SELECT make, model, city, COUNT(*) AS num_cars FROM electric_vehicle " +
            "where make ='TESLA' GROUP BY make, model, city order by num_cars DESC , city DESC";

    public static final String STATE_COUNTY_MAKE_MODEL_NUM = "SELECT state, COUNTY, make, model, COUNT(*) " +
            "AS num_cars FROM electric_vehicle GROUP BY state, county, make, model order by state DESC, COUNTY DESC , " +
            "make DESC, model DESC, num_cars DESC";

}
