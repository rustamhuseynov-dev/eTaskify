insert into users
(username,password,enabled)
values('rustamh','$2a$12$.Znqv/6mcAQC1nXIq795EeJ/f8f.V81xXJ5w4UESs5er.izXbUkDu',1);

insert into users
(username,password,enabled)
values('kamilm','$2a$12$Q1pJztD039WOSYTqN23Dk.VenW8xg8GTf6tyx.S1SuF34olqXjeme',1);

insert into authorities
(username,authority)
values
    ('kamilm','ROLE_USER');

insert into authorities
(username,authority)
values
    ('rustamh','ROLE_ADMIN');