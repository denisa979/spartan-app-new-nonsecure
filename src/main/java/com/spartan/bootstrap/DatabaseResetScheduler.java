package com.spartan.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class DatabaseResetScheduler {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseResetScheduler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(cron = "59 59 23 * * ?", zone = "America/New_York")
    public void resetDatabase() {
        try {
            log.info("STARTING DATABASE RESET");
            deleteAllData();
            log.info("COPYING DATA FROM default_spartans TO spartans");
            copyDefaultData();
            log.info("DATABASE RESET IS COMPLETED");
        } catch (Exception e) {
            log.error("Error during database reset: ", e);
        }
    }

    private void deleteAllData() {
        jdbcTemplate.execute("SET session_replication_role = 'replica';");
        jdbcTemplate.execute("DELETE FROM spartans;");
        jdbcTemplate.execute("SET session_replication_role = 'origin';");
    }

    private void copyDefaultData() {
        jdbcTemplate.execute("INSERT INTO spartans SELECT * FROM default_spartans;");
        resetSequences();
    }

    private void resetSequences() {
        jdbcTemplate.execute("SELECT setval(pg_get_serial_sequence('spartans', 'id'), COALESCE((SELECT MAX(id) FROM spartans), 1), false);");
    }
}
