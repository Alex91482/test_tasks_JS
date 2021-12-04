CREATE TABLE event
(
    Id BIGINT PRIMARY KEY,
    occurred_event VARCHAR(64) NOT NULL,
    event_date  TIMESTAMP NOT NULL,
    water_fill_level INTEGER NOT NULL,
    coffee_fill_level INTEGER NOT NULL
);

INSERT INTO event (Id, occurred_event, event_date, water_fill_level, coffee_fill_level) values (1234, 'Power on','2021-12-01 9:00', 1000, 1000);
INSERT INTO event (Id, occurred_event, event_date, water_fill_level, coffee_fill_level) values (1235, 'Prepare Americano','2021-12-01 9:20', 900, 900);
INSERT INTO event (Id, occurred_event, event_date, water_fill_level, coffee_fill_level) values (1236, 'Prepare Americano','2021-12-01 10:00', 800, 800);
INSERT INTO event (Id, occurred_event, event_date, water_fill_level, coffee_fill_level) values (1237, 'Prepare Americano','2021-12-01 13:00', 700, 700);
INSERT INTO event (Id, occurred_event, event_date, water_fill_level, coffee_fill_level) values (1238, 'Prepare Americano','2021-12-01 17:00', 600, 600);
