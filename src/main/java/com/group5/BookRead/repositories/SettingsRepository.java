package com.group5.BookRead.repositories;

import com.group5.BookRead.models.Settings;

import java.sql.SQLIntegrityConstraintViolationException;

public interface SettingsRepository {
    int insert(Settings settings) throws SQLIntegrityConstraintViolationException;
    int update(Settings settings);
    Settings findByUserId(int id);
}
