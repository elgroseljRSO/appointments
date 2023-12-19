INSERT INTO service_types (name, durationHours, cost) VALUES ('Masaža', 1, 100);
INSERT INTO service_types (name, durationHours, cost) VALUES ('Manikura', 1, 40);
INSERT INTO service_types (name, durationHours, cost) VALUES ('Ličenje', 1, 50);

INSERT INTO employees (name) VALUES ('Janez');
INSERT INTO employees (name) VALUES ('Micka');
INSERT INTO employees (name) VALUES ('Lucija');

INSERT INTO employees_service_types (service_type_id, employee_id) VALUES (1,1);
INSERT INTO employees_service_types (service_type_id, employee_id) VALUES (2,2);
INSERT INTO employees_service_types (service_type_id, employee_id) VALUES (1,3);
INSERT INTO employees_service_types (service_type_id, employee_id) VALUES (2,3);
INSERT INTO employees_service_types (service_type_id, employee_id) VALUES (3,3);

# INSERT INTO appointments (customer, start, employee_id) VALUES ("stranka@gmail.com", 8, 1);
INSERT INTO appointments (start, employee_id, service_type_id, customer) VALUES (8, 1, 1, 'stranka1@gmail.com');
INSERT INTO appointments (start, employee_id, service_type_id, customer) VALUES (8, 2, 2, 'stranka2@gmail.com');
INSERT INTO appointments (start, employee_id, service_type_id, customer) VALUES (10, 3, 1, 'stranka3@gmail.com');
INSERT INTO appointments (start, employee_id, service_type_id, customer) VALUES (11, 2, 2, 'stranka4@gmail.com');
INSERT INTO appointments (start, employee_id, service_type_id, customer) VALUES (12, 1, 1, 'stranka5@gmail.com');
INSERT INTO appointments (start, employee_id, service_type_id, customer) VALUES (15, 3, 3, 'stranka6@gmail.com');
# INSERT INTO appointments (start, employee_id, customer) VALUES (8, 1, "aba");



