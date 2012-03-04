--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.5
-- Dumped by pg_dump version 9.0.5
-- Started on 2011-10-24 17:14:07 MSK

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 542 (class 2612 OID 11574)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--


-- CREATE SCHEMA main;
-- ALTER SCHEMA main OWNER TO postgres;
-- SET search_path = main, pg_catalog;

DROP SCHEMA IF EXISTS public CASCADE;

CREATE SCHEMA public
  AUTHORIZATION postgres;

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO br_user;
-- COMMENT ON SCHEMA public IS 'standard public schema';



--CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;
--ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;


SET search_path = public, pg_catalog;

--
-- TOC entry 545 (class 1255 OID 45980)
-- Dependencies: 6
-- Name: concat_id_doc(text); Type: AGGREGATE; Schema: public; Owner: postgres
--

CREATE AGGREGATE concat_id_doc(text) (
    SFUNC = textcat,
    STYPE = text,
    INITCOND = ''
);


ALTER AGGREGATE public.concat_id_doc(text) OWNER TO postgres;

--
-- TOC entry 142 (class 1259 OID 45981)
-- Dependencies: 6
-- Name: author_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--




CREATE SEQUENCE authority_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.authority_seq OWNER TO br_user;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 144 (class 1259 OID 45985)
-- Dependencies: 1879 6
-- Name: authority; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE authority (
    id_authority integer DEFAULT nextval('authority_seq'::regclass) NOT NULL,
    role_name character varying(250) NOT NULL
);


ALTER TABLE public.authority OWNER TO postgres;

--
-- TOC entry 145 (class 1259 OID 45989)
-- Dependencies: 1880 1881 6
-- Name: authors; Type: TABLE; Schema: public; Owner: br_user; Tablespace: 
--

--
-- TOC entry 171 (class 1259 OID 46106)
-- Dependencies: 6
-- Name: user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_seq OWNER TO postgres;



CREATE TABLE users (
    id_user integer DEFAULT nextval('user_seq'::regclass) NOT NULL,
    id_authority integer NOT NULL,
    last_name character varying(1024) NOT NULL,
    first_name character varying(1024) NOT NULL,
    username character varying(512) NOT NULL,
    password character varying(512) NOT NULL,
    city character varying(1024),
    state character varying(1024),
    street character varying(1024),
    postal character varying(1024),
    is_receive boolean DEFAULT false NOT NULL,
    enabled boolean DEFAULT true NOT NULL
);


ALTER TABLE public.users OWNER TO br_user;

ALTER TABLE users
  ADD CONSTRAINT users_username_key UNIQUE(username);

--
-- TOC entry 1997 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN users.username; Type: COMMENT; Schema: public; Owner: br_user
--

COMMENT ON COLUMN users.username IS 'email';



ALTER TABLE ONLY authority
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (id_authority);



ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);

    

ALTER TABLE ONLY users
    ADD CONSTRAINT users_id_authority_fkey FOREIGN KEY (id_authority) REFERENCES authority(id_authority);



REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO br_user;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 1983 (class 0 OID 0)
-- Dependencies: 143
-- Name: authority_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE authority_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE authority_seq FROM postgres;
GRANT ALL ON SEQUENCE authority_seq TO postgres;
GRANT ALL ON SEQUENCE authority_seq TO br_user;
GRANT ALL ON SEQUENCE authority_seq TO PUBLIC;


--
-- TOC entry 1984 (class 0 OID 0)
-- Dependencies: 144
-- Name: authority; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE authority FROM PUBLIC;
REVOKE ALL ON TABLE authority FROM postgres;
GRANT ALL ON TABLE authority TO postgres;
GRANT ALL ON TABLE authority TO br_user;


--
-- TOC entry 1996 (class 0 OID 0)
-- Dependencies: 171
-- Name: user_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE user_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE user_seq FROM postgres;
GRANT ALL ON SEQUENCE user_seq TO postgres;
GRANT ALL ON SEQUENCE user_seq TO br_user;
GRANT ALL ON SEQUENCE user_seq TO PUBLIC;

