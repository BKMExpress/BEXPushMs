package com.bkm.bkmpushnotification.core.dao;

import com.bkm.bkmpushnotification.core.domain.PushServiceLog;
import com.bkm.bkmpushnotification.utility.BkmConstants;
import com.bkm.bkmpushnotification.utility.BkmQueries;
import com.bkm.bkmpushnotification.utility.BkmUtil;
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
public class PushServiceLogDao implements BkmGenericDao<PushServiceLog> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(PushServiceLog.class);

    @Override
    public Optional<PushServiceLog> findById(long id) {
        return null;
    }

    @Override
    public Optional<List<PushServiceLog>> findAll() {
        return null;
    }

    @Override
    public void insert(PushServiceLog entity) {
        if(!BkmUtil.isNullOrEmptyString(entity.getRequest()) && entity.getRequest().length()>= BkmConstants.BKM_PUSH_SERVICE_LOG_MAX_SIZE)
            entity.setRequest(entity.getRequest().substring(0,BkmConstants.BKM_PUSH_SERVICE_LOG_MAX_SIZE));

        if(!BkmUtil.isNullOrEmptyString(entity.getResponse()) && entity.getResponse().length()>= BkmConstants.BKM_PUSH_SERVICE_LOG_MAX_SIZE)
            entity.setResponse(entity.getResponse().substring(0,BkmConstants.BKM_PUSH_SERVICE_LOG_MAX_SIZE));

        jdbcTemplate.update(BkmQueries.INSERT_PUSH_SERVICE_LOG, entity.getServiceName(), entity.getRequest(),
                entity.getResponse(), entity.getServer(), entity.getCreatedDate(), entity.getCreatedBy(), entity.getUpdatedDate(), entity.getUpdatedBy());
    }

    @Override
    public int update(PushServiceLog entity) {
        return 0;
    }

    @Override
    public int delete(PushServiceLog entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }
}
