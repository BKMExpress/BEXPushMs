package com.bkm.bkmpushnotification.core.dao;

import com.bkm.bkmpushnotification.core.domain.ListPushLog;
import com.bkm.bkmpushnotification.core.domain.PushLog;
import com.bkm.bkmpushnotification.core.domain.TokenInfo;
import com.bkm.bkmpushnotification.utility.BkmConstants;
import com.bkm.bkmpushnotification.utility.BkmQueries;
import com.bkm.bkmpushnotification.utility.BkmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 20.02.2018.
 */
@Repository
public class PushLogDao implements BkmGenericDao<PushLog> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(PushLog.class);

    @Override
    public Optional<PushLog> findById(long id) {
        return null;
    }

    @Override
    public Optional<List<PushLog>> findAll() {
        return null;
    }

    public Optional<List<PushLog>> findByDynamicParameters(ListPushLog item) {

        List<PushLog> pushLogList = new ArrayList<>();
        if(!BkmUtil.isNullOrEmptyList(item.getTokenList())){
            for (TokenInfo tokenInfo : item.getTokenList()){
                StringBuilder query = new StringBuilder();
                query.append(BkmQueries.SELECT_PUSH_LOG_FOR_DYNAMIC_QUERY).append(" ");
                List<Object> parameters = new ArrayList<>();

                if(!BkmUtil.isNullOrEmptyString(tokenInfo.getPushToken())){
                    query.append(BkmConstants.PUSH_TOKEN);
                    query.append(BkmConstants.SQL_QUERY_FOR_EQUAL_MARK);
                    parameters.add(tokenInfo.getPushToken());
                }

                if(tokenInfo.getStartDate()!=null && tokenInfo.getEndDate()!=null){
                    query.append(BkmConstants.SQL_QUERY_FOR_AND + "(");
                    query.append(BkmConstants.CREATED_DATE);
                    query.append(BkmConstants.SQL_QUERY_FOR_BETWEEN);
                    query.append(" ? AND ? )");
                    parameters.add(tokenInfo.getStartDate());
                    parameters.add(tokenInfo.getEndDate());
                }

                if (item.getPushType() > 0) {
                    query.append(BkmConstants.SQL_QUERY_FOR_AND);
                    query.append(BkmConstants.PUSH_TYPE_COLUMN_NAME);
                    query.append(BkmConstants.SQL_QUERY_FOR_EQUAL_MARK);
                    parameters.add(item.getPushType());
                }
                if (item.getPushSubType() > 0) {
                    query.append(BkmConstants.SQL_QUERY_FOR_AND);
                    query.append(BkmConstants.PUSHSUB_TYPE_COLUMN_NAME);
                    query.append(BkmConstants.SQL_QUERY_FOR_EQUAL_MARK);
                    parameters.add(item.getPushSubType());
                }
                if (item.getDeviceType() > 0) {
                    query.append(BkmConstants.SQL_QUERY_FOR_AND);
                    query.append(BkmConstants.DEVICE_TYPE_COLUMN_NAME);
                    query.append(BkmConstants.SQL_QUERY_FOR_EQUAL_MARK);
                    parameters.add(item.getDeviceType());
                }

                query.append(BkmConstants.SQL_QUERY_FOR_ORDER);
                Optional<List<PushLog>> listOptional = Optional.ofNullable(jdbcTemplate.query(query.toString(), parameters.toArray(), rowMapper));
                if(listOptional.isPresent() && !BkmUtil.isNullOrEmptyList(listOptional.get())){
                    pushLogList.addAll(listOptional.get());
                }
            };
        }
        return Optional.ofNullable(pushLogList);
    }

    @Override
    public void insert(PushLog entity) {
    }

    public long insert(int appId, String token, String message, int pushType, int pushSubType, int deviceType, String pushRecordId, int isSend) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    final PreparedStatement ps = connection.prepareStatement(BkmQueries.INSERT_PUSH_LOG,
                        Statement.RETURN_GENERATED_KEYS);

                    ps.setInt(1, appId);
                    ps.setString(2, token);
                    ps.setString(3, message);
                    ps.setInt(4, pushType);
                    ps.setInt(5, pushSubType);
                    ps.setInt(6, deviceType);
                    ps.setInt(7, isSend);
                    ps.setString(8, pushRecordId);
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int update(PushLog entity) {
        return 0;
    }

    public int updateSendInfoById(Long id) {
        return jdbcTemplate.update(BkmQueries.UPDATE_PUSH_LOG_SEND_INFO, new Object[]{id});
    }

    public int updateSendInfoForBulkById(Long id) {
        return jdbcTemplate.update(BkmQueries.UPDATE_PUSH_LOG_SEND_FOR_BULK_INFO, new Object[]{id});
    }

    public int updateShowInfoById(Long id) {
        return jdbcTemplate.update(BkmQueries.UPDATE_PUSH_LOG_SHOW_INFO, new Object[]{id});
    }
    

    @Override
    public int delete(PushLog entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }
}
