DROP TABLE IF EXISTS meite_user_token;
CREATE TABLE meite_user_token (
  id int(11) NOT NULL AUTO_INCREMENT,
  token varchar(255) DEFAULT NULL,
  login_type varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  device_infor varchar(255) DEFAULT NULL,
  is_availability int(2) DEFAULT NULL,
  user_id int(11) DEFAULT NULL,
  create_time timestamp NULL DEFAULT NULL,
  update_time timestamp NULL DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=2