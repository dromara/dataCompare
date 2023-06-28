package com.vince.xq.project.system.dataAccess.domain;

import lombok.Data;

/**
 * ods表查询bean
 */
@Data
public class BigDataTableQuery {

    private String file;

    private String db;

    private String table;

    private String thread;

}
