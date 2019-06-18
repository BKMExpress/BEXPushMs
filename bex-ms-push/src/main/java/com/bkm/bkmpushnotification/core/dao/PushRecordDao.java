package com.bkm.bkmpushnotification.core.dao;

import com.bkm.bkmpushnotification.core.domain.PushRecord;
import com.bkm.bkmpushnotification.utility.BkmQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 20.02.2018.
 */
@Repository
public class PushRecordDao implements BkmGenericDao<PushRecord> {

    private static final BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(PushRecord.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<PushRecord> findById(long id) {
        return null;
    }

    @Override
    public Optional<List<PushRecord>> findAll() {
        return null;
    }

    @Override
    public void insert(PushRecord entity) {

    }

    public Long getCountByProcessed(int processed, String pushMasterId) {
        return jdbcTemplate.queryForObject(BkmQueries.GET_PUSH_RECORD_COUNT_BY_PROCESSED, new Object[]{processed, pushMasterId}, Long.class);
    }

    @Override
    public int update(PushRecord entity) {
        return 0;
    }

    @Override
    public int delete(PushRecord entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }

    public Optional<List<PushRecord>> getLineListByProcessed(String pushMasterId) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(pushMasterId);
        return Optional.ofNullable(jdbcTemplate.query(BkmQueries.GET_PUSH_RECORDS_BY_PROCESSED, parameters.toArray(), rowMapper));
    }
}
