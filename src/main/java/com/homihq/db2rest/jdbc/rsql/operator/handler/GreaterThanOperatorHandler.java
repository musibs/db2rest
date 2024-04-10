package com.homihq.db2rest.jdbc.rsql.operator.handler;

import com.homihq.db2rest.core.Dialect;
import com.homihq.db2rest.core.model.DbColumn;

import java.util.Map;
import java.util.Random;

public class GreaterThanOperatorHandler implements OperatorHandler {

   private static final String OPERATOR = " > ";

    @Override
    public String handle(Dialect dialect, DbColumn column, String value, Class type, Map<String, Object> paramMap) {
        Object vo = parseValue(value, type);

        if(dialect.supportAlias()) {
            String key = reviewAndSetParam(column.getAliasedNameParam(), vo, paramMap);
            return column.getAliasedName() + OPERATOR + PREFIX + key;
        }
        else{

            String key = reviewAndSetParam(column.name(), vo, paramMap);
            return column.name() + OPERATOR + PREFIX + key;
        }
    }



}
