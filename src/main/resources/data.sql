INSERT INTO roles (id,name,can_publish) VALUES (1,'ROLE_CUSTOMER',false);
INSERT INTO roles (id,name,can_publish) VALUES (2,'ROLE_SUPPLIER',true);

INSERT INTO categories (name) VALUES ('Limpieza');
INSERT INTO categories (name) VALUES ('Mantenimiento');
INSERT INTO categories (name) VALUES ('Decoracion');

INSERT INTO attentions (name,category_id)
            VALUES ('Reparacion de luces',2),
                   ('Reparacion de tuberias',2);

INSERT INTO locations (country,state,city)
    VALUES ('Peru','Lima','Ate Vitarte'),
           ('Peru','Lima','Barranco'),
           ('Peru','Lima','Breña'),
           ('Peru','Lima','Carabayllo'),
           ('Peru','Lima','Chaclacayo'),
           ('Peru','Lima','Chorrillos'),
           ('Peru','Lima','Cieneguilla'),
           ('Peru','Lima','Comas'),
           ('Peru','Lima','Independencia'),
           ('Peru','Lima','Jesus Maria'),
           ('Peru','Lima','La Molina'),
           ('Peru','Lima','La Victoria'),
           ('Peru','Lima','Lima'),
           ('Peru','Lima','Lince'),
           ('Peru','Lima','Los Olivos'),
           ('Peru','Lima','Magdalena'),
           ('Peru','Lima','Miraflores'),
           ('Peru','Lima','Pueblo Libre'),
           ('Peru','Lima','San Borja'),
           ('Peru','Lima','San Isidro'),
           ('Peru','Lima','San Juan de Lurigancho'),
           ('Peru','Lima','San Juan de Miraflores'),
           ('Peru','Lima','San Miguel'),
           ('Peru','Lima','Surco'),
           ('Peru','Lima','Surquillo'),
           ('Peru','Lima','Villa el Salvador'),
           ('Peru','Lima','Villa Maria del Triunfo');

INSERT INTO users (firstname,lastname,email,password,phone,birthdate,role_id)
    VALUES ('Usuario1','unlastnammed','usuario1@gmail.com','12345','960522544','1998-03-02',1),
           ('Usuario2','unlastnammed','usuario2@gmail.com','12345','990565333','1999-05-09',1),
           ('Usuario3','unlastnammed','usuario3@gmail.com','12345','970667555','1995-07-03',1),
           ('Usuario4','unlastnammed','usuario4@gmail.com','12345','920555333','1999-08-05',2),
           ('Usuario5','unlastnammed','usuario5@gmail.com','12345','940202744','1999-10-24',2);