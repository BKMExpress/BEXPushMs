package com.bkm.bkmpushnotification.core.dao;

import com.bkm.bkmpushnotification.core.domain.PushSubType;
import com.bkm.bkmpushnotification.utility.BkmDaoUtil;
import com.bkm.bkmpushnotification.utility.BkmQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 20.02.2018.
 */
@Repository
public class PushSubTypeDao implements BkmGenericDao<PushSubType>{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(PushSubType.class);

    @Override
    public Optional<PushSubType> findById(long id) {
        return BkmDaoUtil.findFirst(jdbcTemplate.query(BkmQueries.GET_PUSH_SUB_TYPE_BY_ID, new Object[]{id}, rowMapper));
    }

    @Override
    public Optional<List<PushSubType>> findAll() {
        return Optional.ofNullable(jdbcTemplate.query(BkmQueries.GET_ALL_PUSH_SUB_TYPE, rowMapper));
    }

    @Override
    public void insert(PushSubType entity) {

    }

    @Override
    public int update(PushSubType entity) {
        return 0;
    }

    @Override
    public int delete(PushSubType entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }
}
