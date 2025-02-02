package com.spartan.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            System.out.println("STARTING DATABASE RESET");
            deleteAllData();
            System.out.println("LOADING THE DEFAULT DATA");
            loadDataSql();
            System.out.println("DATABASE RESET IS COMPLETED");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void deleteAllData() {

        jdbcTemplate.execute("SET session_replication_role = 'replica';");
        jdbcTemplate.execute("DELETE FROM spartans;");
        jdbcTemplate.execute("SET session_replication_role = 'origin';");

        resetSequences();

    }

    private void resetSequences() {
        jdbcTemplate.execute("SELECT setval(pg_get_serial_sequence('spartans', 'id'), coalesce(max(id), 1), false) FROM spartans;");
    }

    private void loadDataSql() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/data.sql"))) {
            String sql = lines.collect(Collectors.joining("\n"));
            jdbcTemplate.execute(sql);
        } catch (IOException e) {
            throw new IOException("Error reading SQL file", e);
        }
    }

}
