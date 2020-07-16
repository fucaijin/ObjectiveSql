package com.github.braisdom.funcsql;

import com.github.braisdom.funcsql.util.StringUtil;

import java.util.Objects;

public class GeneralSQLGenerator implements SQLGenerator {

    private static final String SELECT_STATEMENT = "SELECT %s FROM %s";

    @Override
    public String createQuerySQL(String tableName, String projections, String filter, String groupBy,
                                 String having, String orderBy, int offset, int limit) {
        Objects.requireNonNull(tableName, "The tableName cannot be null");

        StringBuilder sql = new StringBuilder();

        projections = (projections == null || projections.length() < 0) ? "*" : projections;
        String standardSql = String.format(SELECT_STATEMENT, projections, tableName);

        sql.append(standardSql);

        if(!StringUtil.isBlank(filter))
            sql.append(" WHERE ").append(filter);

        if(!StringUtil.isBlank(groupBy))
            sql.append(" GROUP BY ").append(groupBy);

        if(!StringUtil.isBlank(having))
            sql.append(" HAVING ").append(groupBy);

        if(!StringUtil.isBlank(orderBy))
            sql.append(" ORDER BY ").append(orderBy);

        if(offset > 0)
            sql.append(" OFFSET ").append(offset);

        if(limit > 0)
            sql.append(" LIMIT ").append(limit);

        return sql.toString();
    }

    @Override
    public String createUpdateSQL(String tableName, String update, String filter) {
        return null;
    }

    @Override
    public String createDeleteSQL(String tableName, String filter) {
        return null;
    }
}