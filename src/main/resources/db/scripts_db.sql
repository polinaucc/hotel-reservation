INSERT INTO users (email, username, password )
VALUES ('test1@gmail.com', 'test1', '$2a$10$wZXSm13GbIn9TrWDRSXhZ.QKE/HVAceeJvFMFENzLZHcBp9C2AURC');
INSERT INTO users (email, username, password )
VALUES ('admin@admin', 'admin', '$2a$10$Ku9Sf4YzNXyWykVrh8IYyuQo8XJTOZV48AtIZ0yBF53kMUjBHlJXS');

INSERT INTO user_role (user_id, authorities)
VALUES (1, 'CLIENT');
INSERT INTO user_role (user_id, authorities)
VALUES (2, 'ADMIN');

INSERT INTO client (birthday, first_name, middle_name, last_name, passport, user_id, first_name_uk, middle_name_uk, last_name_uk)
VALUES ('1984-12-10', 'Artem', 'Olehovych', 'Kravchenko', 'BT111111', 1, 'Артем', 'Олегович', 'Кравченко' );

INSERT INTO description (cost, count_beds, count_persons, room_type)
VALUES (1520.00, 2, 3, 'BUSINESS');
INSERT INTO description (cost, count_beds, count_persons, room_type)
VALUES (965.00, 1, 2, 'BALCONY');
INSERT INTO description (cost, count_beds, count_persons, room_type)
VALUES (450.00, 1, 2, 'ECONOMY');

INSERT INTO room (room_number, description_id )
VALUES ('1-01', 2);
INSERT INTO room (room_number, description_id )
VALUES ('1-02', 3);

INSERT INTO request (in_date, out_date, status, client_id, description_id)
VALUES ('2020-05-07', '2020-05-12', 'Accepted', 1,  2);
INSERT INTO request (in_date, out_date, status, client_id, description_id)
VALUES ('2020-05-31', '2020-08-15', 'New_request', 1, 1);
INSERT INTO request (in_date, out_date, status, client_id, description_id)
VALUES ('2020-05-24', '2020-05-25', 'Rejected', 1, 3);

INSERT INTO reservation (sum, request_id, room_id)
VALUES (4825.00, 1, 1);