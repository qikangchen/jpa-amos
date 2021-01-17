INSERT IGNORE INTO incident_item VALUES (1, 'Baustelle', '{"latitude":"11","longitude":"12"}');
INSERT IGNORE INTO incident_item VALUES (2, 'Umleitung', '{"latitude":"13","longitude":"14"}');


INSERT IGNORE INTO request_time_stamp VALUES (1, 1000);

INSERT IGNORE INTO request_local_info VALUES (1, 11.11, 22.22, 10, "Berlin");

INSERT IGNORE INTO matched_item_mapping VALUES (1, 1, 2, 5, 1);

INSERT IGNORE INTO request_info_mapping VALUES (1, 1, 1);
INSERT IGNORE INTO request_info_mapping VALUES (2, 2, 1);

INSERT IGNORE INTO request_local_mapping VALUES (1, 1, 1);