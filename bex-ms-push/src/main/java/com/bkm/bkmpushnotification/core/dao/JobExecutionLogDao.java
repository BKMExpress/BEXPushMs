package com.bkm.bkmpushnotification.core.dao;

import com.bkm.bkmpushnotification.core.domain.JobExecutionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 20.02.2018.
 */
@Repository
public class JobExecutionLogDao implements BkmGenericDao<JobExecutionLog> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<JobExecutionLog> findById(long id) {
        return null;
    }

    @Override
    public Optional<List<JobExecutionLog>> findAll() {
        return null;
    }

    @Override
    public void insert(JobExecutionLog entity) {

    }

    @Override
    public int update(JobExecutionLog entity) {
        return 0;
    }

    @Override
    public int delete(JobExecutionLog entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }
}
