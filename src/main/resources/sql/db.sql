
create database DbCompare if not if not exist;
CREATE TABLE `db_config` (
                             `id` int(11) NOT NULL AUTO_INCREMENT comment 'primary key',
                             `connect_name` varchar(255) NOT NULL comment 'db connect_name',
                             `type` varchar(255) NOT NULL comment 'db config type',
                             `url` varchar(255) NOT NULL comment 'db config url',
                             `user` varchar(255) DEFAULT NULL comment 'db config user',
                             `pwd` varchar(255) DEFAULT NULL comment 'db config pwd',
                             `create_time` datetime DEFAULT NULL comment 'create_time',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 comment='db config';;

CREATE TABLE `job_config` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `origin_table_name` varchar(255) DEFAULT NULL,
                             `origin_table_primary` varchar(255) DEFAULT NULL,
                             `origin_table_fields` varchar(255) DEFAULT NULL,
                             `to_table_name` varchar(255) DEFAULT NULL,
                             `to_table_primary` varchar(255) DEFAULT NULL,
                             `to_table_fields` varchar(255) DEFAULT NULL,
                             `db_config_id` int(11) DEFAULT NULL,
                             `create_time` datetime DEFAULT NULL,
                             `schdule_time` varchar(255) DEFAULT NULL,
                             `schdule_status` tinyint(1) DEFAULT 1 comment '0:true,1:false',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 comment='job_config';

CREATE TABLE `job_instance` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `job_config_id` int(11) NOT NULL,
                              `origin_total_count` varchar(255) NOT NULL comment 'origin_total_count',
                              `origin_total_sql` varchar(255) NOT NULL comment 'origin_total_sql',
                              `to_total_count` varchar(255) DEFAULT NULL comment 'to_total_count',
                              `to_total_sql` varchar(255) DEFAULT NULL comment 'to_total_sql',
                              `inner_join_count` varchar(255) DEFAULT NULL comment 'inner_join_count',
                              `inner_join_sql` varchar(255) DEFAULT NULL comment 'inner_join_sql',
                              `left_join_count` varchar(255) DEFAULT NULL comment 'left_join_count',
                              `left_join_sql` varchar(255) DEFAULT NULL comment 'left_join_sql',
                              `right_join_count` varchar(255) DEFAULT NULL comment 'right_join_count',
                              `right_join_sql` varchar(255) DEFAULT NULL comment 'right_join_sql',
                              `create_time` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 comment='job_instance';

