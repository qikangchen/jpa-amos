INSERT IGNORE INTO incident_item VALUES (1, 'Baustelle');
INSERT IGNORE INTO incident_item VALUES (2, 'Umleitung');

INSERT IGNORE INTO location VALUES (1, '11.11', '11.110', 1);
INSERT IGNORE INTO location VALUES (2, '22.22', '22.220', 1);
INSERT IGNORE INTO location VALUES (3, '33.33', '33.330', 2);
INSERT IGNORE INTO location VALUES (4, '44.44', '44.440', 2);
INSERT IGNORE INTO location VALUES (5, '55.55', '55.550', 2);


INSERT IGNORE INTO request_time_stamp VALUES (1, 1000);

INSERT IGNORE INTO request_local_info VALUES (1, 11.11, 22.22, 10);


INSERT IGNORE INTO request_info_mapping VALUES (1, 1, 1, 1);
INSERT IGNORE INTO request_info_mapping VALUES (2, 2, 1, 1);