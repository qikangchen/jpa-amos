SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS incident_item;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS request_time_stamp;
DROP TABLE IF EXISTS request_local_info;
DROP TABLE IF EXISTS request_info_mapping;
DROP TABLE IF EXISTS request_local_mapping;
DROP TABLE IF EXISTS matched_item_mapping;

CREATE TABLE IF NOT EXISTS incident_item (
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS location(
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    latitude VARCHAR(30),
    longitude VARCHAR(30),
    incident_item_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (incident_item_id) REFERENCES incident_item(id)
);

CREATE TABLE IF NOT EXISTS matched_item_mapping(
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tomtom_db_item_id INT(4) UNSIGNED NOT NULL,
    here_db_item_id INT(4) UNSIGNED NOT NULL,
    confidence_level INT(4) UNSIGNED NOT NULL,
    request_time_stamp_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (request_time_stamp_id) REFERENCES request_time_stamp(id),
    FOREIGN KEY (tomtom_db_item_id) REFERENCES incident_item(id),
    FOREIGN KEY (here_db_item_id) REFERENCES incident_item(id)
);

CREATE TABLE IF NOT EXISTS request_time_stamp (
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    request_time_stamp INT(10)
);

CREATE TABLE IF NOT EXISTS request_local_info (
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    centre_latitude DOUBLE,
    centre_longitude DOUBLE,
    search_radius_in_km DOUBLE,
    city_name VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS request_info_mapping (
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    incident_item_id INT(4) UNSIGNED NOT NULL,
    request_time_stamp_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (incident_item_id) REFERENCES incident_item(id),
    FOREIGN KEY (request_time_stamp_id) REFERENCES request_time_stamp(id)
);

CREATE TABLE IF NOT EXISTS request_local_mapping (
    id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    request_time_stamp_id INT(4) UNSIGNED NOT NULL,
    request_local_info_id INT(4) UNSIGNED NOT NULL,
    FOREIGN KEY (request_time_stamp_id) REFERENCES request_time_stamp(id),
    FOREIGN KEY (request_local_info_id) REFERENCES request_local_info(id)
);


