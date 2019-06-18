package com.bkm.bkmpushnotification.core.dao;

import com.bkm.bkmpushnotification.core.domain.PushAppParam;
import com.bkm.bkmpushnotification.core.domain.PushAppUser;
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
public class PushAppUserDao implements BkmGenericDao<PushAppUser>{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(PushAppUser.class);

    @Override
    public Optional<PushAppUser> findById(long id) {
        return null;
    }

    @Override
    public Optional<List<PushAppUser>> findAll() {
        return Optional.ofNullable(jdbcTemplate.query(BkmQueries.GET_ALL_PUSH_APP_USER, rowMapper));
    }

    @Override
    public void insert(PushAppUser entity) {

    }

    @Override
    public int update(PushAppUser entity) {
        return 0;
    }

    @Override
    public int delete(PushAppUser entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }
}
