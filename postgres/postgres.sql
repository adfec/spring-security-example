-- Adminer 4.8.0 PostgreSQL 13.2 (Debian 13.2-1.pgdg100+1) dump

<br />
<b>Warning</b>:  PDO::query(): SQLSTATE[42601]: Syntax error: 7 ERROR:  syntax error at or near &quot;STATUS&quot;
LINE 1: SHOW FUNCTION STATUS WHERE Db = 'postgres'
                      ^ in <b>/var/www/html/adminer.php</b> on line <b>185</b><br />
<br />
<b>Warning</b>:  PDO::query(): SQLSTATE[42601]: Syntax error: 7 ERROR:  syntax error at or near &quot;STATUS&quot;
LINE 1: SHOW PROCEDURE STATUS WHERE Db = 'postgres'
                       ^ in <b>/var/www/html/adminer.php</b> on line <b>185</b><br />
<br />
<b>Warning</b>:  PDO::query(): SQLSTATE[42703]: Undefined column: 7 ERROR:  column &quot;consrc&quot; does not exist
LINE 1: SELECT conname, consrc
                        ^
HINT:  Perhaps you meant to reference the column &quot;pg_constraint.conkey&quot; or the column &quot;pg_constraint.conbin&quot;. in <b>/var/www/html/adminer.php</b> on line <b>185</b><br />
DROP TABLE IF EXISTS "permisos";
DROP SEQUENCE IF EXISTS permisos_id_seq;
CREATE SEQUENCE permisos_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."permisos" (
    "id" integer DEFAULT nextval('permisos_id_seq') NOT NULL,
    "nombre" character varying(20) NOT NULL,
    "descripcion" character varying(50) NOT NULL,
    CONSTRAINT "permisos_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "permisos" ("id", "nombre", "descripcion") VALUES
(1,	'Leer',	'Puede leer registros'),
(2,	'Crear',	'Puede crear registros'),
(3,	'Borrar',	'Puede borrar registros');

<br />
<b>Warning</b>:  PDO::query(): SQLSTATE[42703]: Undefined column: 7 ERROR:  column &quot;consrc&quot; does not exist
LINE 1: SELECT conname, consrc
                        ^
HINT:  Perhaps you meant to reference the column &quot;pg_constraint.conkey&quot; or the column &quot;pg_constraint.conbin&quot;. in <b>/var/www/html/adminer.php</b> on line <b>185</b><br />
DROP TABLE IF EXISTS "rol_permiso";
CREATE TABLE "public"."rol_permiso" (
    "id_rol" integer NOT NULL,
    "id_permiso" integer NOT NULL
) WITH (oids = false);

INSERT INTO "rol_permiso" ("id_rol", "id_permiso") VALUES
(1,	1),
(1,	2),
(1,	3),
(2,	1);

<br />
<b>Warning</b>:  PDO::query(): SQLSTATE[42703]: Undefined column: 7 ERROR:  column &quot;consrc&quot; does not exist
LINE 1: SELECT conname, consrc
                        ^
HINT:  Perhaps you meant to reference the column &quot;pg_constraint.conkey&quot; or the column &quot;pg_constraint.conbin&quot;. in <b>/var/www/html/adminer.php</b> on line <b>185</b><br />
DROP TABLE IF EXISTS "roles";
DROP SEQUENCE IF EXISTS roles_id_seq;
CREATE SEQUENCE roles_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."roles" (
    "id" integer DEFAULT nextval('roles_id_seq') NOT NULL,
    "nombre" character varying(20) NOT NULL,
    CONSTRAINT "roles_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "roles" ("id", "nombre") VALUES
(1,	'Administrador'),
(2,	'Invitado');

<br />
<b>Warning</b>:  PDO::query(): SQLSTATE[42703]: Undefined column: 7 ERROR:  column &quot;consrc&quot; does not exist
LINE 1: SELECT conname, consrc
                        ^
HINT:  Perhaps you meant to reference the column &quot;pg_constraint.conkey&quot; or the column &quot;pg_constraint.conbin&quot;. in <b>/var/www/html/adminer.php</b> on line <b>185</b><br />
DROP TABLE IF EXISTS "usuario_rol";
CREATE TABLE "public"."usuario_rol" (
    "id_usuario" integer NOT NULL,
    "id_rol" integer NOT NULL
) WITH (oids = false);

INSERT INTO "usuario_rol" ("id_usuario", "id_rol") VALUES
(1,	1),
(2,	2);

<br />
<b>Warning</b>:  PDO::query(): SQLSTATE[42703]: Undefined column: 7 ERROR:  column &quot;consrc&quot; does not exist
LINE 1: SELECT conname, consrc
                        ^
HINT:  Perhaps you meant to reference the column &quot;pg_constraint.conkey&quot; or the column &quot;pg_constraint.conbin&quot;. in <b>/var/www/html/adminer.php</b> on line <b>185</b><br />
DROP TABLE IF EXISTS "usuarios";
DROP SEQUENCE IF EXISTS usuarios_id_seq;
CREATE SEQUENCE usuarios_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."usuarios" (
    "id" integer DEFAULT nextval('usuarios_id_seq') NOT NULL,
    "nombre" character varying(20) NOT NULL,
    "contrasenia" character varying(20) NOT NULL,
    CONSTRAINT "usuarios_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "usuarios" ("id", "nombre", "contrasenia") VALUES
(1,	'admin',	'admin'),
(2,	'guest',	'guest');

ALTER TABLE ONLY "public"."rol_permiso" ADD CONSTRAINT "fk_permiso" FOREIGN KEY (id_permiso) REFERENCES permisos(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."rol_permiso" ADD CONSTRAINT "fk_rol" FOREIGN KEY (id_rol) REFERENCES roles(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."usuario_rol" ADD CONSTRAINT "fk_rol" FOREIGN KEY (id_rol) REFERENCES roles(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."usuario_rol" ADD CONSTRAINT "fk_usuario" FOREIGN KEY (id_usuario) REFERENCES usuarios(id) NOT DEFERRABLE;

-- 2021-04-24 04:20:37.885948+00
