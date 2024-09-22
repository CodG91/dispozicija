package org.example.diplomska_idelek;

import jakarta.annotation.PostConstruct;
import org.example.diplomska_idelek.common.Queries;
import org.example.diplomska_idelek.mapper.*;
import org.example.diplomska_idelek.model.ElectricVehicle;
import org.example.diplomska_idelek.model.ElectricVehicleGeneric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class CsvLoader {

    private final Logger log =  LoggerFactory.getLogger(CsvLoader.class);

    private final JdbcTemplate postgresJdbcTemplate;
    private final JdbcTemplate oracleJdbcTemplate;

    private static final String POSTGRES = "POSTGRES";
    private static final String ORACLE = "ORACLE";

    public CsvLoader(@Qualifier("postgresJdbcTemplate") JdbcTemplate jdbcTemplate, @Qualifier("oralceJdbcTemplate") JdbcTemplate oracleJdbcTemplate) {

        this.postgresJdbcTemplate = jdbcTemplate;
        this.oracleJdbcTemplate = oracleJdbcTemplate;
    }

    @PostConstruct
    public void loadCsv() throws IOException, InterruptedException {

        loadOracleData();
        loadPostgresData();
        measureSelectAllStatement();
        measureGroupBy();
        numElectricVehiclesByModel();
        averageElectricRangeVehiclesByState();
        electricUtilityProvider();
        numberVehiclesMakeModelYear();
        teslaModelCityNum();
        stateCountyMakeModelNum();

    }


    private void loadOracleData() throws IOException, InterruptedException {

        if (!isElectricCarDataAvailable()) {

            StopWatch loadStopWatch = new StopWatch();
            loadStopWatch.start();
            log.info("Load to Oracle started");
            Resource resource = new ClassPathResource("csv/Electric_Vehicle_Population_Data.csv");
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < 149000) {
                i++;
                String[] values = line.split(",");
                String vin = values[0];
                String county = values[1];
                String city = values[2];
                String state = values[3];
                String postalCode = values[4];
                String modelYear = values[5];
                String make = values[6];
                String model = values[7];
                String electricVehicleType = values[8];
                String cafvEligibility = values[9];
                String electricRange = values[10];
                String baseMsrp = values[11];
                String legislativeDistrict = values[12];
                String dolVehicleId = values[13];
                String vehicleLocation = values[14];
                String electricUtility = values[15];
                String censusTract2020 = values[16];

                oracleJdbcTemplate.update(Queries.INSERT_STATEMENT,
                        vin, county, city, state, postalCode, modelYear, make, model, electricVehicleType,
                        cafvEligibility, electricRange, baseMsrp, legislativeDistrict, dolVehicleId,
                        vehicleLocation, electricUtility, censusTract2020);
                Thread.sleep(20);
            }
            loadStopWatch.stop();
            log.info("Load to oracle is finished in {} seconds", loadStopWatch.getTotalTimeSeconds());
            reader.close();
            inputStream.close();
        } else {
            log.info("Data already exists in table. I will not load!");
        }

    }

    private void loadPostgresData() throws IOException, InterruptedException {

        if (!isElectricCarDataAvailable()) {

            StopWatch loadStopWatch = new StopWatch();
            loadStopWatch.start();
            log.info("Load to Postgres started");
            Resource resource = new ClassPathResource("csv/Electric_Vehicle_Population_Data.csv");
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < 149000) {
                i++;
                String[] values = line.split(",");
                String vin = values[0];
                String county = values[1];
                String city = values[2];
                String state = values[3];
                String postalCode = values[4];
                String modelYear = values[5];
                String make = values[6];
                String model = values[7];
                String electricVehicleType = values[8];
                String cafvEligibility = values[9];
                String electricRange = values[10];
                String baseMsrp = values[11];
                String legislativeDistrict = values[12];
                String dolVehicleId = values[13];
                String vehicleLocation = values[14];
                String electricUtility = values[15];
                String censusTract2020 = values[16];

                postgresJdbcTemplate.update(Queries.INSERT_STATEMENT,
                        vin, county, city, state, postalCode, modelYear, make, model, electricVehicleType,
                        cafvEligibility, electricRange, baseMsrp, legislativeDistrict, dolVehicleId,
                        vehicleLocation, electricUtility, censusTract2020);
                Thread.sleep(20);
            }
            loadStopWatch.stop();
            log.info("Load to postgres is finished in {} seconds", loadStopWatch.getTotalTimeSeconds());
            reader.close();
            inputStream.close();
        } else {
            log.info("Data already exists in table. I will not load!");
        }

    }

    private void measureSelectAllStatement() {

        StopWatch postgresStopWatch = initStopWatch();
        postgresStopWatch.start();
        log.info("Select all statement started on {} database!", POSTGRES);
        List<ElectricVehicle> postgreData = postgresJdbcTemplate.query(Queries.SELECT_ALL, new ElectricVehicleMapper());

        postgresStopWatch.stop();
        log.info("Select all statement is finished for {} database with row size of {} and it took {} miliseconds!", POSTGRES, postgreData.size(), postgresStopWatch.getTotalTimeMillis());

        StopWatch oracleStopWatch = initStopWatch();
        oracleStopWatch.start();
        log.info("Select all statement started on {} database!", ORACLE);
        List<ElectricVehicle> oracleData = oracleJdbcTemplate.query(Queries.SELECT_ALL, new ElectricVehicleMapper());
        oracleStopWatch.stop();
        log.info("Select all statement is finished for {} database with row size of {} and it took {} miliseconds!", ORACLE, oracleData.size(), oracleStopWatch.getTotalTimeMillis());
    }

    private void measureGroupBy() {

        StopWatch postgresStopWatch = initStopWatch();
        postgresStopWatch.start();
        log.info("Group by statement started on {} database!", POSTGRES);
        List<ElectricVehicleGeneric> postgreData = postgresJdbcTemplate.query(Queries.GROUP_BY, new ElectricVehicleGroupByMapper());

        postgresStopWatch.stop();
        log.info("Group by statement is finished for {} database with row size of {} and it took {} miliseconds!", POSTGRES, postgreData.get(0).getField1(), postgresStopWatch.getTotalTimeMillis());

        StopWatch oracleStopWatch = initStopWatch();
        oracleStopWatch.start();
        log.info("Group by statement started on {} database!", ORACLE);
        List<ElectricVehicleGeneric> oracleData = oracleJdbcTemplate.query(Queries.GROUP_BY, new ElectricVehicleGroupByMapper());
        oracleStopWatch.stop();
        log.info("Group by statement is finished for {} database with row size of {} and it took {} miliseconds!", ORACLE, oracleData.get(0).getField1(), oracleStopWatch.getTotalTimeMillis());
    }

    private void numElectricVehiclesByModel() {

        StopWatch postgresStopWatch = initStopWatch();
        postgresStopWatch.start();
        log.info("Number of Electric vehicles by model statement started on {} database!", POSTGRES);
        List<ElectricVehicleGeneric> postgreData = postgresJdbcTemplate.query(Queries.GROUP_BY_MODEL, new ElectricVehicleGroupByModelMapper());

        postgresStopWatch.stop();
        log.info("Number of Electric vehicles by model statement is finished for {} database with row size of {} and it took {} miliseconds!", POSTGRES, postgreData.size(), postgresStopWatch.getTotalTimeMillis());

        StopWatch oracleStopWatch = initStopWatch();
        oracleStopWatch.start();
        log.info("Number of Electric vehicles by model statement started on {} database!", ORACLE);
        List<ElectricVehicleGeneric> oracleData = oracleJdbcTemplate.query(Queries.GROUP_BY_MODEL, new ElectricVehicleGroupByModelMapper());
        oracleStopWatch.stop();
        log.info("Number of Electric vehicles by model statement is finished for {} database with row size of {} and it took {} miliseconds!", ORACLE, oracleData.size(), oracleStopWatch.getTotalTimeMillis());
    }

    private void averageElectricRangeVehiclesByState() {

        StopWatch postgresStopWatch = initStopWatch();
        postgresStopWatch.start();
        log.info("Average electric range of vehicles by state statement started on {} database!", POSTGRES);
        List<ElectricVehicleGeneric> postgreData = postgresJdbcTemplate.query(Queries.MODEL_PER_STATE, new AvgElectricRangeVehiclesByStateMapper());

        postgresStopWatch.stop();
        log.info("Average electric range of vehicles by state statement is finished for {} database with row size of {} and it took {} miliseconds!", POSTGRES, postgreData.size(), postgresStopWatch.getTotalTimeMillis());

        StopWatch oracleStopWatch = initStopWatch();
        oracleStopWatch.start();
        log.info("Average electric range of vehicles by state statement started on {} database!", ORACLE);
        List<ElectricVehicleGeneric> oracleData = oracleJdbcTemplate.query(Queries.MODEL_PER_STATE, new AvgElectricRangeVehiclesByStateMapper());
        oracleStopWatch.stop();
        log.info("Average electric range of vehicles by state statement is finished for {} database with row size of {} and it took {} miliseconds!", ORACLE, oracleData.size(), oracleStopWatch.getTotalTimeMillis());
    }

    private void electricUtilityProvider() {

        StopWatch postgresStopWatch = initStopWatch();
        postgresStopWatch.start();
        log.info("Electric Utility Provider statement started on {} database!", POSTGRES);
        List<ElectricVehicleGeneric> postgreData = postgresJdbcTemplate.query(Queries.ELECTRIC_UTILITY_PROVIDER,
                new ElectricUtilityProviderMapper());

        postgresStopWatch.stop();
        log.info("Electric Utility Provider statement is finished for {} database with row size of {} and it took {} miliseconds!",
                POSTGRES, postgreData.size(), postgresStopWatch.getTotalTimeMillis());

        StopWatch oracleStopWatch = initStopWatch();
        oracleStopWatch.start();
        log.info("Electric Utility Provider statement started on {} database!", ORACLE);
        List<ElectricVehicleGeneric> oracleData = oracleJdbcTemplate.query(Queries.ELECTRIC_UTILITY_PROVIDER,
                new ElectricUtilityProviderMapper());
        oracleStopWatch.stop();
        log.info("Electric Utility Provider vehicles by state statement is finished for {} database with row size of {} and it took {} miliseconds!",
                ORACLE, oracleData.size(), oracleStopWatch.getTotalTimeMillis());
    }


    private void numberVehiclesMakeModelYear() {

        StopWatch postgresStopWatch = initStopWatch();
        postgresStopWatch.start();
        log.info("Number of vehicles per company, per model, per model year statement started on {} database!", POSTGRES);
        List<ElectricVehicleGeneric> postgreData = postgresJdbcTemplate.query(Queries.YEAR_MODEL_MAKE, new NumberVehiclesMakeModelYear());

        postgresStopWatch.stop();
        log.info("Number of vehicles per company, per model, per model year statement is finished for {} database with row size of {} and it took " +
                "{} miliseconds!", POSTGRES, postgreData.size(), postgresStopWatch.getTotalTimeMillis());

        StopWatch oracleStopWatch = initStopWatch();
        oracleStopWatch.start();
        log.info("RNumber of vehicles per company, per model, per model year statement started on {} database!", ORACLE);
        List<ElectricVehicleGeneric> oracleData = oracleJdbcTemplate.query(Queries.YEAR_MODEL_MAKE, new NumberVehiclesMakeModelYear());
        oracleStopWatch.stop();
        log.info("Number of vehicles per company, per model, per model year statement is finished for {} database with row size of {} and it took" +
                " {} miliseconds!", ORACLE, oracleData.size(), oracleStopWatch.getTotalTimeMillis());
    }

    private void teslaModelCityNum() {

        StopWatch postgresStopWatch = initStopWatch();
        postgresStopWatch.start();
        log.info("Number of Tesla vehicles per model per city statement started on {} database!", POSTGRES);
        List<ElectricVehicleGeneric> postgreData = postgresJdbcTemplate.query(Queries.TESLA_MODEL_CITY_NUM, new TeslaModelCityNumMapper());

        postgresStopWatch.stop();
        log.info("Number of Tesla vehicles per model per city statement is finished for {} database with row size of {} and it took {} miliseconds!", POSTGRES, postgreData.size(), postgresStopWatch.getTotalTimeMillis());

        StopWatch oracleStopWatch = initStopWatch();
        oracleStopWatch.start();
        log.info("Number of Tesla vehicles per model per city statement started on {} database!", ORACLE);
        List<ElectricVehicleGeneric> oracleData = oracleJdbcTemplate.query(Queries.TESLA_MODEL_CITY_NUM, new TeslaModelCityNumMapper());
        oracleStopWatch.stop();
        log.info("Number of Tesla vehicles per model per city statement is finished for {} database with row size of {} and it took {} miliseconds!", ORACLE, oracleData.size(), oracleStopWatch.getTotalTimeMillis());
    }

    private void stateCountyMakeModelNum() {

        StopWatch postgresStopWatch = initStopWatch();
        postgresStopWatch.start();
        log.info("Number of vehicles by State, by county, by company and its models statement started on {} database!", POSTGRES);
        List<ElectricVehicleGeneric> postgreData = postgresJdbcTemplate.query(Queries.STATE_COUNTY_MAKE_MODEL_NUM, new StateCountyMakeModelNumMapper());

        postgresStopWatch.stop();
        log.info("Number of vehicles by State, by county, by company and its models statement is finished for {} database with row size of {} and it took {} miliseconds!", POSTGRES, postgreData.size(), postgresStopWatch.getTotalTimeMillis());

        StopWatch oracleStopWatch = initStopWatch();
        oracleStopWatch.start();
        log.info("Number of vehicles by State, by county, by company and its models statement started on {} database!", ORACLE);
        List<ElectricVehicleGeneric> oracleData = oracleJdbcTemplate.query(Queries.STATE_COUNTY_MAKE_MODEL_NUM, new StateCountyMakeModelNumMapper());
        oracleStopWatch.stop();
        log.info("Number of vehicles by State, by county, by company and its models statement is finished for {} database with row size of {} and it took {} miliseconds!", ORACLE, oracleData.size(), oracleStopWatch.getTotalTimeMillis());
    }

    private boolean isElectricCarDataAvailable() {
        Integer rowCount = postgresJdbcTemplate.queryForObject(Queries.CONTROL_QUERY, Integer.class);
        return rowCount > 0;
    }

    private StopWatch initStopWatch(){
        return new StopWatch();
}
}
