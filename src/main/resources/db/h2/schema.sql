DROP TABLE IF EXISTS request_info_mapping;
DROP TABLE IF EXISTS request_time_stamp;
DROP TABLE IF EXISTS incident_item;
DROP TABLE IF EXISTS location;

CREATE TABLE incident_item (
    id            INTEGER IDENTITY PRIMARY KEY,
    description   VARCHAR(30)
);

CREATE TABLE location(
    id INTEGER IDENTITY PRIMARY KEY,
    latitude VARCHAR(30),
    longitude VARCHAR(30),
    incident_item_id INTEGER references incident_item(id)
);

CREATE TABLE request_time_stamp (
    id                  INTEGER IDENTITY PRIMARY KEY,
    request_time_stamp    INTEGER
);

CREATE TABLE request_info_mapping (
    id                  INTEGER IDENTITY PRIMARY KEY,
    incident_item_id      INTEGER references incident_item(id),
    request_time_stamp_id  INTEGER references request_time_stamp(id)
);