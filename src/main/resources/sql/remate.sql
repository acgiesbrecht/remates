--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.3
-- Dumped by pg_dump version 9.2.3
-- Started on 2015-05-16 08:08:09

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 193 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2040 (class 0 OID 0)
-- Dependencies: 193
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 168 (class 1259 OID 16394)
-- Name: tbl_miembros; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_miembros (
    id integer NOT NULL,
    nombre text NOT NULL,
    ctacte integer NOT NULL,
    domicilio text,
    box integer
);


ALTER TABLE public.tbl_miembros OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16539)
-- Name: tbl_pagos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_pagos (
    id integer NOT NULL,
    fechahora timestamp without time zone,
    id_miembro integer,
    id_remate integer,
    concepto text,
    monto integer,
    tipo integer
);


ALTER TABLE public.tbl_pagos OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16443)
-- Name: tbl_remates_detalle; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates_detalle (
    fechahora timestamp without time zone,
    id integer NOT NULL,
    id_miembro integer NOT NULL,
    id_categoria integer NOT NULL,
    monto integer NOT NULL,
    observacion text DEFAULT ''::text,
    id_remate integer NOT NULL
);


ALTER TABLE public.tbl_remates_detalle OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16593)
-- Name: pendientes_total; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW pendientes_total AS
    WITH remates AS (SELECT m.nombre, m.ctacte, sum(rd.monto) AS monto FROM (tbl_miembros m LEFT JOIN tbl_remates_detalle rd ON ((m.id = rd.id_miembro))) GROUP BY m.nombre, m.ctacte), pagos AS (SELECT m.nombre, m.ctacte, COALESCE(sum(p.monto), (0)::bigint) AS monto FROM (tbl_miembros m LEFT JOIN tbl_pagos p ON ((m.id = p.id_miembro))) GROUP BY m.nombre, m.ctacte) SELECT remates.nombre, remates.ctacte, remates.monto AS compras_total, pagos.monto AS pagos_total, (remates.monto - pagos.monto) AS saldo FROM remates, pagos WHERE ((remates.nombre = pagos.nombre) AND ((remates.monto - pagos.monto) > 0)) ORDER BY remates.nombre;


ALTER TABLE public.pendientes_total OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16558)
-- Name: recibo; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW recibo AS
    SELECT p.id, p.fechahora, d.nombre, p.concepto, p.monto FROM tbl_pagos p, tbl_miembros d WHERE ((p.id_miembro = d.id) AND (p.tipo = 1));


ALTER TABLE public.recibo OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16410)
-- Name: tbl_remates_categorias; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates_categorias (
    id integer NOT NULL,
    descripcion text
);


ALTER TABLE public.tbl_remates_categorias OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16416)
-- Name: tbl_categorias_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_categorias_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_categorias_id_seq OWNER TO postgres;

--
-- TOC entry 2041 (class 0 OID 0)
-- Dependencies: 171
-- Name: tbl_categorias_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_categorias_id_seq OWNED BY tbl_remates_categorias.id;


--
-- TOC entry 172 (class 1259 OID 16418)
-- Name: tbl_iglesia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_iglesia (
    id integer NOT NULL,
    nombre text,
    ruc integer,
    ctacte integer,
    domicilio text,
    box integer
);


ALTER TABLE public.tbl_iglesia OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16424)
-- Name: tbl_iglesia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_iglesia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_iglesia_id_seq OWNER TO postgres;

--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 173
-- Name: tbl_iglesia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_iglesia_id_seq OWNED BY tbl_iglesia.id;


--
-- TOC entry 174 (class 1259 OID 16426)
-- Name: tbl_miembros_box_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_miembros_box_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_miembros_box_seq OWNER TO postgres;

--
-- TOC entry 2043 (class 0 OID 0)
-- Dependencies: 174
-- Name: tbl_miembros_box_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_miembros_box_seq OWNED BY tbl_miembros.box;


--
-- TOC entry 175 (class 1259 OID 16428)
-- Name: tbl_miembros_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_miembros_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_miembros_id_seq OWNER TO postgres;

--
-- TOC entry 2044 (class 0 OID 0)
-- Dependencies: 175
-- Name: tbl_miembros_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_miembros_id_seq OWNED BY tbl_miembros.id;


--
-- TOC entry 188 (class 1259 OID 16537)
-- Name: tbl_pagos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_pagos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_pagos_id_seq OWNER TO postgres;

--
-- TOC entry 2045 (class 0 OID 0)
-- Dependencies: 188
-- Name: tbl_pagos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_pagos_id_seq OWNED BY tbl_pagos.id;


--
-- TOC entry 169 (class 1259 OID 16400)
-- Name: tbl_recibos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_recibos (
    id integer NOT NULL,
    fechahora time without time zone,
    id_miembro integer NOT NULL,
    monto integer,
    concepto text,
    id_remate integer
);


ALTER TABLE public.tbl_recibos OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16430)
-- Name: tbl_recibos_id_miembro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_recibos_id_miembro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_recibos_id_miembro_seq OWNER TO postgres;

--
-- TOC entry 2046 (class 0 OID 0)
-- Dependencies: 176
-- Name: tbl_recibos_id_miembro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_recibos_id_miembro_seq OWNED BY tbl_recibos.id_miembro;


--
-- TOC entry 177 (class 1259 OID 16432)
-- Name: tbl_recibos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_recibos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_recibos_id_seq OWNER TO postgres;

--
-- TOC entry 2047 (class 0 OID 0)
-- Dependencies: 177
-- Name: tbl_recibos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_recibos_id_seq OWNED BY tbl_recibos.id;


--
-- TOC entry 178 (class 1259 OID 16434)
-- Name: tbl_remates; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates (
    id integer NOT NULL,
    fecha timestamp without time zone,
    descripcion text
);


ALTER TABLE public.tbl_remates OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16440)
-- Name: tbl_remates_cuotas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates_cuotas (
    id_remate integer NOT NULL,
    fecha_1 timestamp without time zone,
    fecha_2 timestamp without time zone,
    fecha_3 timestamp without time zone,
    fecha_4 timestamp without time zone
);


ALTER TABLE public.tbl_remates_cuotas OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16450)
-- Name: tbl_remates_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_remates_id_categoria_seq OWNER TO postgres;

--
-- TOC entry 2048 (class 0 OID 0)
-- Dependencies: 181
-- Name: tbl_remates_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_categoria_seq OWNED BY tbl_remates_detalle.id_categoria;


--
-- TOC entry 182 (class 1259 OID 16452)
-- Name: tbl_remates_id_miembro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_miembro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_remates_id_miembro_seq OWNER TO postgres;

--
-- TOC entry 2049 (class 0 OID 0)
-- Dependencies: 182
-- Name: tbl_remates_id_miembro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_miembro_seq OWNED BY tbl_remates_detalle.id_miembro;


--
-- TOC entry 183 (class 1259 OID 16454)
-- Name: tbl_remates_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_remates_id_seq OWNER TO postgres;

--
-- TOC entry 2050 (class 0 OID 0)
-- Dependencies: 183
-- Name: tbl_remates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_seq OWNED BY tbl_remates_detalle.id;


--
-- TOC entry 184 (class 1259 OID 16456)
-- Name: tbl_remates_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_remates_id_seq1 OWNER TO postgres;

--
-- TOC entry 2051 (class 0 OID 0)
-- Dependencies: 184
-- Name: tbl_remates_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_seq1 OWNED BY tbl_remates.id;


--
-- TOC entry 185 (class 1259 OID 16458)
-- Name: tbl_transferencias; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_transferencias (
    fechahora timestamp without time zone NOT NULL,
    concepto text,
    monto integer NOT NULL,
    id integer NOT NULL,
    id_miembro integer NOT NULL,
    id_remate integer NOT NULL
);


ALTER TABLE public.tbl_transferencias OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16464)
-- Name: tbl_transferencias_id_miembro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_transferencias_id_miembro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_transferencias_id_miembro_seq OWNER TO postgres;

--
-- TOC entry 2052 (class 0 OID 0)
-- Dependencies: 186
-- Name: tbl_transferencias_id_miembro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_transferencias_id_miembro_seq OWNED BY tbl_transferencias.id_miembro;


--
-- TOC entry 187 (class 1259 OID 16466)
-- Name: tbl_transferencias_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_transferencias_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tbl_transferencias_id_seq OWNER TO postgres;

--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 187
-- Name: tbl_transferencias_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_transferencias_id_seq OWNED BY tbl_transferencias.id;


--
-- TOC entry 191 (class 1259 OID 16562)
-- Name: transferencia; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW transferencia AS
    SELECT t.id, t.fechahora, d.ctacte AS cta_debito, i.ctacte AS cta_credito, d.nombre AS d_nombre, i.nombre AS c_nombre, d.domicilio AS d_domicilio, i.domicilio AS c_domicilio, d.box AS d_box, i.box AS c_box, t.monto, t.concepto FROM tbl_pagos t, tbl_miembros d, tbl_iglesia i WHERE ((t.id_miembro = d.id) AND (t.tipo = 0));


ALTER TABLE public.transferencia OWNER TO postgres;

--
-- TOC entry 1995 (class 2604 OID 16472)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_miembros ALTER COLUMN id SET DEFAULT nextval('tbl_miembros_id_seq'::regclass);


--
-- TOC entry 2002 (class 2604 OID 16542)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_pagos ALTER COLUMN id SET DEFAULT nextval('tbl_pagos_id_seq'::regclass);


--
-- TOC entry 1996 (class 2604 OID 16473)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_recibos ALTER COLUMN id SET DEFAULT nextval('tbl_recibos_id_seq'::regclass);


--
-- TOC entry 1998 (class 2604 OID 16474)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates ALTER COLUMN id SET DEFAULT nextval('tbl_remates_id_seq1'::regclass);


--
-- TOC entry 1997 (class 2604 OID 16475)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_categorias ALTER COLUMN id SET DEFAULT nextval('tbl_categorias_id_seq'::regclass);


--
-- TOC entry 2000 (class 2604 OID 16476)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle ALTER COLUMN id SET DEFAULT nextval('tbl_remates_id_seq'::regclass);


--
-- TOC entry 2001 (class 2604 OID 16477)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_transferencias ALTER COLUMN id SET DEFAULT nextval('tbl_transferencias_id_seq'::regclass);


--
-- TOC entry 2010 (class 2606 OID 16479)
-- Name: pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates_categorias
    ADD CONSTRAINT pk PRIMARY KEY (id);


--
-- TOC entry 2012 (class 2606 OID 16481)
-- Name: tbl_iglesia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_iglesia
    ADD CONSTRAINT tbl_iglesia_pkey PRIMARY KEY (id);


--
-- TOC entry 2004 (class 2606 OID 16483)
-- Name: tbl_miembros_ctacte_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_miembros
    ADD CONSTRAINT tbl_miembros_ctacte_nombre_key UNIQUE (ctacte, nombre);


--
-- TOC entry 2006 (class 2606 OID 16485)
-- Name: tbl_miembros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_miembros
    ADD CONSTRAINT tbl_miembros_pkey PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 16547)
-- Name: tbl_pagos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_pagos
    ADD CONSTRAINT tbl_pagos_pkey PRIMARY KEY (id);


--
-- TOC entry 2008 (class 2606 OID 16487)
-- Name: tbl_recibos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_recibos
    ADD CONSTRAINT tbl_recibos_pkey PRIMARY KEY (id);


--
-- TOC entry 2016 (class 2606 OID 16489)
-- Name: tbl_remates_cuotas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates_cuotas
    ADD CONSTRAINT tbl_remates_cuotas_pkey PRIMARY KEY (id_remate);


--
-- TOC entry 2018 (class 2606 OID 16491)
-- Name: tbl_remates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_pkey PRIMARY KEY (id);


--
-- TOC entry 2014 (class 2606 OID 16493)
-- Name: tbl_remates_pkey1; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates
    ADD CONSTRAINT tbl_remates_pkey1 PRIMARY KEY (id);


--
-- TOC entry 2020 (class 2606 OID 16495)
-- Name: tbl_transferencias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_transferencias
    ADD CONSTRAINT tbl_transferencias_pkey PRIMARY KEY (id);


--
-- TOC entry 2031 (class 2606 OID 16548)
-- Name: tbl_pagos_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_pagos
    ADD CONSTRAINT tbl_pagos_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2032 (class 2606 OID 16553)
-- Name: tbl_pagos_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_pagos
    ADD CONSTRAINT tbl_pagos_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2023 (class 2606 OID 16496)
-- Name: tbl_recibos_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_recibos
    ADD CONSTRAINT tbl_recibos_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2024 (class 2606 OID 16501)
-- Name: tbl_recibos_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_recibos
    ADD CONSTRAINT tbl_recibos_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2025 (class 2606 OID 16506)
-- Name: tbl_remates_cuotas_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_cuotas
    ADD CONSTRAINT tbl_remates_cuotas_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2026 (class 2606 OID 16578)
-- Name: tbl_remates_detalle_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_detalle_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2027 (class 2606 OID 16583)
-- Name: tbl_remates_id_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_id_categoria_fkey FOREIGN KEY (id_categoria) REFERENCES tbl_remates_categorias(id);


--
-- TOC entry 2028 (class 2606 OID 16588)
-- Name: tbl_remates_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id);


--
-- TOC entry 2029 (class 2606 OID 16526)
-- Name: tbl_transferencias_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_transferencias
    ADD CONSTRAINT tbl_transferencias_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2030 (class 2606 OID 16531)
-- Name: tbl_transferencias_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_transferencias
    ADD CONSTRAINT tbl_transferencias_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id);


--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-05-16 08:08:09

--
-- PostgreSQL database dump complete
--

