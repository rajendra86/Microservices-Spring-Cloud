/* Below table not created*/
CREATE TABLE oauth_client_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    client_id VARCHAR(255)
);

/*Below tables are created in MySQL */
CREATE TABLE oauth_access_token (
    token_id VARCHAR(256) DEFAULT NULL,
    token BLOB,
    authentication_id VARCHAR(256) DEFAULT NULL,
    user_name VARCHAR(256) DEFAULT NULL,
    client_id VARCHAR(256) DEFAULT NULL,
    authentication BLOB,
    refresh_token VARCHAR(256) DEFAULT NULL
);

CREATE TABLE oauth_refresh_token (
    token_id VARCHAR(256) DEFAULT NULL,
    token BLOB,
    authentication BLOB
);

CREATE TABLE oauth_client_details (
  client_id varchar(256) DEFAULT NULL,
  resource_ids varchar(256) DEFAULT NULL,
  client_secret varchar(256) DEFAULT NULL,
  scope varchar(256) DEFAULT NULL,
  authorized_grant_types varchar(256) DEFAULT NULL,
  web_server_redirect_uri varchar(256) DEFAULT NULL,
  authorities varchar(256) DEFAULT NULL,
  access_token_validity decimal(38,0) DEFAULT NULL,
  refresh_token_validity decimal(38,0) DEFAULT NULL,
  additional_information varchar(256) DEFAULT NULL,
  autoapprove varchar(256) DEFAULT NULL
);

CREATE TABLE oauth_user_details (
  service_id decimal(9,0) NOT NULL,
  service_name varchar(20) DEFAULT NULL,
  service_description varchar(100) DEFAULT NULL,
  username varchar(45) DEFAULT NULL,
  password varchar(15) DEFAULT NULL,
  service_token varchar(45) DEFAULT NULL,
  token_expiration date DEFAULT NULL,
  active_ind char(1) DEFAULT NULL
);
