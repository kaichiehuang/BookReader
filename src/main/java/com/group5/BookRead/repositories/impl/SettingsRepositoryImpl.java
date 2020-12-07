package com.group5.BookRead.repositories.impl;

import com.group5.BookRead.models.ExcludedBook;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.models.Settings;
import com.group5.BookRead.repositories.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class SettingsRepositoryImpl implements SettingsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class SettingsRowMapper implements RowMapper<Settings> {

        @Override
        public Settings mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            Settings settings = new Settings();
            settings.setId(rs.getInt("id"));
            settings.setDefaultBookShelfId(rs.getInt("defaultBookshelf_id"));
            settings.setUserId(rs.getInt("user_id"));
            return settings;
        }

    }

    /**  insert settings
     * @param  settings
     * @return status code
     */

    @Override
    public int insert(final Settings settings) throws SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into Settings (user_id, "
                        + "defaultBookshelf_id) " + "values(?, ?)",
                new Object[] {settings.getUserId(),
                        settings.getDefaultBookShelfId()});
    }

    /**  update settings
     * @param settings
     * @return status code
     */
    @Override
    public int update(final Settings settings) {
        try {
            return jdbcTemplate.update("update Settings "
                            + "set defaultBookshelf_id = ?,"
                            + "where user_id = ?",
                    new Object[] {
                            settings.getDefaultBookShelfId(),
                            settings.getUserId(),
                    });
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
            return 0;
        }
    }

    /**  find settings by userId
     * @param userId
     * @return Settings
     */
    @Override
    public Settings findByUserId(final int userId) {
        try {
            Settings updatedSettings = jdbcTemplate.queryForObject(
                    "select * from Settings "
                            + "where user_id = ?",
                    new Object[] {userId},
                    new SettingsRepositoryImpl.SettingsRowMapper());
            return updatedSettings;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
