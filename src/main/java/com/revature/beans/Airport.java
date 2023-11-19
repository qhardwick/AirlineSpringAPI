package com.revature.beans;

import com.revature.constants.ConnectionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("Airport")
public class Airport {

    @PrimaryKeyColumn(
            name = "code",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED)
    private String code;
    private String name;
    private String city;
    private String state;
    private String country;
    private Map<ConnectionType,Integer> minimumConnectionTimes;
}
