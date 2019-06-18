package com.bkm.bkmpushnotification.core.dao;

import com.bkm.bkmpushnotification.aspect.BkmRestAspect;
import com.bkm.bkmpushnotification.core.domain.PushMaster;
import com.bkm.bkmpushnotification.utility.BkmQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 20.02.2018.
 */
@Repository
public class PushMasterDao implements BkmGenericDao<PushMaster> {

    private static final BeanPropertyRowMapper rowMapper = BeanPropertyRowMapper.newInstance(PushMaster.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<PushMaster> findById(long id) {
        return null;
    }

    @Override
    public Optional<List<PushMaster>> findAll() {
        return null;
    }

    public Optional<List<PushMaster>> findByCreatedDate(Date createdDate) {
        List<Object> parameters = new ArrayList<>();
        parameters.add(createdDate);
        return Optional.ofNullable(jdbcTemplate.query(BkmQueries.GET_PUSH_MASTER_BY_CREATE_DATE, parameters.toArray(), rowMapper));
    }

    @Override
    public void insert(PushMaster entity) {

    }

    @Override
    public int update(PushMaster entity) {
        return 0;
    }

    @Override
    public int delete(PushMaster entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }

    public void validationQuery() {
        jdbcTemplate.execute(BkmQueries.VALIDATION_QUERY);
    }
}
