INSERT into oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types,
authorities, access_token_validity, refresh_token_validity) values('DEV', 'rest_api', 'DEV',
'trust,read,write', 'client_credentials, authorization_code, implicit, password, refresh_token',
'ROLE_CLIENT, ROLE_TRUSTED_CLIENT', '600','3600');

INSERT INTO oauth_user_details(service_id, service_name, service_description, username, password, service_token, 
token_expiration, active_ind) VALUES ('1', 'SOA', 'SOA Service', 'ADMIN', 'ADMIN_PWD', null, null, 'Y');

INSERT INTO oauth_user_details(service_id, service_name, service_description, username, password, service_token, 
token_expiration, active_ind) VALUES ('2', 'SOA', 'SOA Service', 'USER', 'USER_PWD', null, null, 'Y');

