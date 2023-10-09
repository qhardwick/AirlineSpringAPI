package com.revature;

import com.revature.utils.CassandraUtil;

public class SetupDbTables {
    public static void main(String[] args) {

        dropTables();
    }

    private static void dropTables() {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS Passenger;");
    }
}
