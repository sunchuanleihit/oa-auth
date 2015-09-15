
# 用户表
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `phone` varchar(64) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱',
  `flag` varchar(64) NOT NULL DEFAULT '0000000000' COMMENT '标识，绑定手机，绑定邮箱，绑定微信，绑定QQ，绑定微博，绑定支付宝，是否VIP等',
  `real_name` varchar(64) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `gender` tinyint(3) NOT NULL DEFAULT '0' COMMENT '性别，0=保密，1=男，2=女',
  `birthday` varchar(64) NOT NULL DEFAULT '' COMMENT '生日，如1990-12-12',
  `source` tinyint(3) NOT NULL DEFAULT '0' COMMENT '注册来源，10=pcweb，20=m站，30=iOS，40=Android',
  `created_time` timestamp NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_name` (`name`),
  KEY `IX_phone` (`phone`),
  KEY `IX_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

# 账号表
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账号id',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `type` tinyint(3) NOT NULL DEFAULT '10' COMMENT '账号类型，10=用户名，20=微信，30=QQ，40=微博，50=支付宝',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '账号名，如用户名，微信openid，QQ openid，微博openid，支付宝openid 等',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `created_time` timestamp NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '创建时间',
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_name_type` (`name`,`type`),
  KEY `IX_user_id_type` (`user_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号表';

