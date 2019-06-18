package com.bkm.bkmpushnotification.core.dao;

import com.bkm.bkmpushnotification.core.domain.AllowedToken;
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
public class AllowedTokenDao implements BkmGenericDao<AllowedToken> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(AllowedToken.class);

    public Optional<AllowedToken> findByToken(String token) {
        return BkmDaoUtil.findFirst(jdbcTemplate.query(BkmQueries.GET_ALLOWED_TOKENS_BY_TOKEN, new Object[]{token}, rowMapper));
    }

    @Override
    public Optional<AllowedToken> findById(long id) {
        return null;
    }

    @Override
    public Optional<List<AllowedToken>> findAll() {
        return null;
    }

    @Override
    public void insert(AllowedToken entity) {

    }

    @Override
    public int update(AllowedToken entity) {
        return 0;
    }

    @Override
    public int delete(AllowedToken entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }
}
