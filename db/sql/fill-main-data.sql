SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;


INSERT INTO authority VALUES (1, 'ROLE_ADMINISTRATOR');
INSERT INTO authority VALUES (2, 'ROLE_USER');

SELECT pg_catalog.setval('authority_seq', 5, false);
