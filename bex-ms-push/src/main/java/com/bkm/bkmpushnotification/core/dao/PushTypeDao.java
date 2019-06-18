package com.bkm.bkmpushnotification.core.dao;

import com.bkm.bkmpushnotification.core.domain.PushType;
import com.bkm.bkmpushnotification.utility.BkmDaoUtil;
import com.bkm.bkmpushnotification.utility.BkmQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 06.02.2018.
 */
@Repository
public class PushTypeDao implements BkmGenericDao<PushType> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(PushType.class);

    @Override
    public Optional<PushType> findById(long id) {
        return BkmDaoUtil.findFirst(jdbcTemplate.query(BkmQueries.GET_PUSH_TYPE_BY_ID, new Object[]{id},rowMapper));
    }

    @Override
    public Optional<List<PushType>> findAll() {
        return Optional.ofNullable(jdbcTemplate.query(BkmQueries.GET_ALL_PUSH_TYPE, rowMapper));
    }

    @Override
    public void insert(PushType entity) {

    }

    @Override
    public int update(PushType entity) {
        return 0;
    }

    @Override
    public int delete(PushType entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }
}
