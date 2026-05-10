package com.vince.xq.common.utils.security;

/**
 * JDBC 连接安全校验工具类
 *
 * 防止通过恶意 JDBC URL 参数触发:
 *   - MySQL: allowLoadLocalInfile 文件读取, autoDeserialize 反序列化 RCE
 *   - PostgreSQL: socketFactory 任意类加载 RCE
 *   - H2: INIT 参数代码执行
 *
 * 校验策略: 对 URL 全文 toLowerCase() + contains() 匹配,
 * 覆盖所有参数分隔符格式 (?, ;, (), address=)
 */
public class JdbcSecurityUtil {

    /**
     * 全驱动危险参数黑名单 (全小写)
     */
    private static final String[] UNSAFE_PARAMS = {
            // MySQL / MariaDB
            "allowloadlocalinfile",
            "allowurlinlocalinfile",
            "allowloadlocalinfileinpath",
            "autodeserialize",
            "queryinterceptors",
            "statementinterceptors",
            "detectcustomcollations",
            "maxallowedpacket",
            // PostgreSQL
            "socketfactory",
            "socketfactoryarg",
            "sslfactory",
            "sslhostnameverifier",
            "sslpasswordcallback",
            "authenticationpluginclassname",
            "jaasapplicationname",
            // H2
            "init=",
            "runscript",
            "trace_level_system_out",
    };

    /**
     * 校验 JDBC URL 是否包含危险参数
     *
     * @param jdbcUrl JDBC 连接地址
     * @throws IllegalArgumentException 包含危险参数时抛出
     */
    public static void validate(String jdbcUrl) {
        if (jdbcUrl == null || jdbcUrl.trim().isEmpty()) {
            return;
        }

        String lowerUrl = jdbcUrl.toLowerCase();

        for (String unsafeParam : UNSAFE_PARAMS) {
            if (lowerUrl.contains(unsafeParam)) {
                throw new IllegalArgumentException("JDBC URL 包含不安全参数: " + unsafeParam);
            }
        }
    }
}
