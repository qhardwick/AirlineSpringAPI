package com.revature.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Airline {

    @PrimaryKeyColumn(
            name = "code",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED)
    private String code;
    private String name;

}
