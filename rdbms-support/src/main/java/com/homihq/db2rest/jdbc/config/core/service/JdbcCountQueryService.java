package com.homihq.db2rest.jdbc.config.core.service;

import com.homihq.db2rest.core.exception.GenericDataAccessException;
import com.homihq.db2rest.jdbc.config.core.DbOperationService;
import com.homihq.db2rest.core.dto.CountResponse;
import com.homihq.db2rest.jdbc.config.dto.ReadContext;
import com.homihq.db2rest.jdbc.config.processor.ReadProcessor;
import com.homihq.db2rest.jdbc.config.sql.SqlCreatorTemplate;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JdbcCountQueryService implements CountQueryService {

    private final DbOperationService dbOperationService;
    private final List<ReadProcessor> processorList;
    private final SqlCreatorTemplate sqlCreatorTemplate;

    @Override
    public CountResponse count(ReadContext readContext) {
        for (ReadProcessor processor : processorList) {
            processor.process(readContext);
        }

        String sql = sqlCreatorTemplate.count(readContext);
        log.debug("{}", sql);
        log.debug("{}", readContext.getParamMap());

        try {
            return dbOperationService.count(readContext.getParamMap(), sql);
        } catch (DataAccessException e) {
            log.error("Error in read op : " , e);
            throw new GenericDataAccessException(e.getMostSpecificCause().getMessage());
        }

    }




}
