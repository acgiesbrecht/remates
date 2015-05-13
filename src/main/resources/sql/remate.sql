--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.4rc1
-- Started on 2015-05-12 17:28:50

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 192 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 192
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 24853)
-- Name: tbl_miembros; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_miembros (
    id integer NOT NULL,
    nombre text NOT NULL,
    ctacte integer NOT NULL,
    domicilio text,
    box integer
);


ALTER TABLE tbl_miembros OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 24859)
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


ALTER TABLE tbl_recibos OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 24865)
-- Name: recibo; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW recibo AS
 SELECT r.id,
    r.fechahora,
    d.nombre,
    r.concepto,
    r.monto
   FROM tbl_recibos r,
    tbl_miembros d
  WHERE (r.id_miembro = d.id);


ALTER TABLE recibo OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 24869)
-- Name: tbl_remates_categorias; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates_categorias (
    id integer NOT NULL,
    descripcion text
);


ALTER TABLE tbl_remates_categorias OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 24875)
-- Name: tbl_categorias_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_categorias_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_categorias_id_seq OWNER TO postgres;

--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 174
-- Name: tbl_categorias_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_categorias_id_seq OWNED BY tbl_remates_categorias.id;


--
-- TOC entry 175 (class 1259 OID 24877)
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


ALTER TABLE tbl_iglesia OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 24883)
-- Name: tbl_iglesia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_iglesia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_iglesia_id_seq OWNER TO postgres;

--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 176
-- Name: tbl_iglesia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_iglesia_id_seq OWNED BY tbl_iglesia.id;


--
-- TOC entry 177 (class 1259 OID 24885)
-- Name: tbl_miembros_box_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_miembros_box_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_miembros_box_seq OWNER TO postgres;

--
-- TOC entry 2040 (class 0 OID 0)
-- Dependencies: 177
-- Name: tbl_miembros_box_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_miembros_box_seq OWNED BY tbl_miembros.box;


--
-- TOC entry 178 (class 1259 OID 24887)
-- Name: tbl_miembros_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_miembros_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_miembros_id_seq OWNER TO postgres;

--
-- TOC entry 2041 (class 0 OID 0)
-- Dependencies: 178
-- Name: tbl_miembros_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_miembros_id_seq OWNED BY tbl_miembros.id;


--
-- TOC entry 179 (class 1259 OID 24889)
-- Name: tbl_recibos_id_miembro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_recibos_id_miembro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_recibos_id_miembro_seq OWNER TO postgres;

--
-- TOC entry 2042 (class 0 OID 0)
-- Dependencies: 179
-- Name: tbl_recibos_id_miembro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_recibos_id_miembro_seq OWNED BY tbl_recibos.id_miembro;


--
-- TOC entry 180 (class 1259 OID 24891)
-- Name: tbl_recibos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_recibos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_recibos_id_seq OWNER TO postgres;

--
-- TOC entry 2043 (class 0 OID 0)
-- Dependencies: 180
-- Name: tbl_recibos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_recibos_id_seq OWNED BY tbl_recibos.id;


--
-- TOC entry 181 (class 1259 OID 24893)
-- Name: tbl_remates; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates (
    id integer NOT NULL,
    fecha timestamp without time zone,
    descripcion text
);


ALTER TABLE tbl_remates OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 24899)
-- Name: tbl_remates_cuotas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates_cuotas (
    id_remate integer NOT NULL,
    fecha_1 timestamp without time zone,
    fecha_2 timestamp without time zone,
    fecha_3 timestamp without time zone,
    fecha_4 timestamp without time zone
);


ALTER TABLE tbl_remates_cuotas OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 24902)
-- Name: tbl_remates_detalle; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tbl_remates_detalle (
    fechahora timestamp without time zone,
    id integer NOT NULL,
    id_miembro integer NOT NULL,
    id_categoria integer NOT NULL,
    monto integer,
    observacion text DEFAULT ''::text,
    id_remate integer
);


ALTER TABLE tbl_remates_detalle OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 24909)
-- Name: tbl_remates_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_remates_id_categoria_seq OWNER TO postgres;

--
-- TOC entry 2044 (class 0 OID 0)
-- Dependencies: 184
-- Name: tbl_remates_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_categoria_seq OWNED BY tbl_remates_detalle.id_categoria;


--
-- TOC entry 185 (class 1259 OID 24911)
-- Name: tbl_remates_id_miembro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_miembro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_remates_id_miembro_seq OWNER TO postgres;

--
-- TOC entry 2045 (class 0 OID 0)
-- Dependencies: 185
-- Name: tbl_remates_id_miembro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_miembro_seq OWNED BY tbl_remates_detalle.id_miembro;


--
-- TOC entry 186 (class 1259 OID 24913)
-- Name: tbl_remates_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_remates_id_seq OWNER TO postgres;

--
-- TOC entry 2046 (class 0 OID 0)
-- Dependencies: 186
-- Name: tbl_remates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_seq OWNED BY tbl_remates_detalle.id;


--
-- TOC entry 187 (class 1259 OID 24915)
-- Name: tbl_remates_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_remates_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_remates_id_seq1 OWNER TO postgres;

--
-- TOC entry 2047 (class 0 OID 0)
-- Dependencies: 187
-- Name: tbl_remates_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_remates_id_seq1 OWNED BY tbl_remates.id;


--
-- TOC entry 188 (class 1259 OID 24917)
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


ALTER TABLE tbl_transferencias OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 24923)
-- Name: tbl_transferencias_id_miembro_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_transferencias_id_miembro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_transferencias_id_miembro_seq OWNER TO postgres;

--
-- TOC entry 2048 (class 0 OID 0)
-- Dependencies: 189
-- Name: tbl_transferencias_id_miembro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_transferencias_id_miembro_seq OWNED BY tbl_transferencias.id_miembro;


--
-- TOC entry 190 (class 1259 OID 24925)
-- Name: tbl_transferencias_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_transferencias_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_transferencias_id_seq OWNER TO postgres;

--
-- TOC entry 2049 (class 0 OID 0)
-- Dependencies: 190
-- Name: tbl_transferencias_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tbl_transferencias_id_seq OWNED BY tbl_transferencias.id;


--
-- TOC entry 191 (class 1259 OID 24927)
-- Name: transferencia; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW transferencia AS
 SELECT t.id,
    t.fechahora,
    d.ctacte AS cta_debito,
    i.ctacte AS cta_credito,
    d.nombre AS d_nombre,
    i.nombre AS c_nombre,
    d.domicilio AS d_domicilio,
    i.domicilio AS c_domicilio,
    t.monto
   FROM tbl_transferencias t,
    tbl_miembros d,
    tbl_iglesia i
  WHERE ((t.id_miembro = d.id) AND (i.id = 1));


ALTER TABLE transferencia OWNER TO postgres;

--
-- TOC entry 1888 (class 2604 OID 24931)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_miembros ALTER COLUMN id SET DEFAULT nextval('tbl_miembros_id_seq'::regclass);


--
-- TOC entry 1889 (class 2604 OID 24932)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_recibos ALTER COLUMN id SET DEFAULT nextval('tbl_recibos_id_seq'::regclass);


--
-- TOC entry 1891 (class 2604 OID 24933)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates ALTER COLUMN id SET DEFAULT nextval('tbl_remates_id_seq1'::regclass);


--
-- TOC entry 1890 (class 2604 OID 24934)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_categorias ALTER COLUMN id SET DEFAULT nextval('tbl_categorias_id_seq'::regclass);


--
-- TOC entry 1893 (class 2604 OID 24935)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle ALTER COLUMN id SET DEFAULT nextval('tbl_remates_id_seq'::regclass);


--
-- TOC entry 1894 (class 2604 OID 24936)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_transferencias ALTER COLUMN id SET DEFAULT nextval('tbl_transferencias_id_seq'::regclass);


--
-- TOC entry 1902 (class 2606 OID 24938)
-- Name: pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates_categorias
    ADD CONSTRAINT pk PRIMARY KEY (id);


--
-- TOC entry 1904 (class 2606 OID 24940)
-- Name: tbl_iglesia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_iglesia
    ADD CONSTRAINT tbl_iglesia_pkey PRIMARY KEY (id);


--
-- TOC entry 1896 (class 2606 OID 25011)
-- Name: tbl_miembros_ctacte_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_miembros
    ADD CONSTRAINT tbl_miembros_ctacte_nombre_key UNIQUE (ctacte, nombre);


--
-- TOC entry 1898 (class 2606 OID 24942)
-- Name: tbl_miembros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_miembros
    ADD CONSTRAINT tbl_miembros_pkey PRIMARY KEY (id);


--
-- TOC entry 1900 (class 2606 OID 24944)
-- Name: tbl_recibos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_recibos
    ADD CONSTRAINT tbl_recibos_pkey PRIMARY KEY (id);


--
-- TOC entry 1908 (class 2606 OID 24946)
-- Name: tbl_remates_cuotas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates_cuotas
    ADD CONSTRAINT tbl_remates_cuotas_pkey PRIMARY KEY (id_remate);


--
-- TOC entry 1910 (class 2606 OID 24948)
-- Name: tbl_remates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_pkey PRIMARY KEY (id);


--
-- TOC entry 1906 (class 2606 OID 24950)
-- Name: tbl_remates_pkey1; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_remates
    ADD CONSTRAINT tbl_remates_pkey1 PRIMARY KEY (id);


--
-- TOC entry 1912 (class 2606 OID 24952)
-- Name: tbl_transferencias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tbl_transferencias
    ADD CONSTRAINT tbl_transferencias_pkey PRIMARY KEY (id);


--
-- TOC entry 1913 (class 2606 OID 24953)
-- Name: tbl_recibos_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_recibos
    ADD CONSTRAINT tbl_recibos_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1914 (class 2606 OID 24958)
-- Name: tbl_recibos_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_recibos
    ADD CONSTRAINT tbl_recibos_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1915 (class 2606 OID 24963)
-- Name: tbl_remates_cuotas_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_cuotas
    ADD CONSTRAINT tbl_remates_cuotas_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1916 (class 2606 OID 24968)
-- Name: tbl_remates_detalle_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_detalle_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1917 (class 2606 OID 24973)
-- Name: tbl_remates_id_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_id_categoria_fkey FOREIGN KEY (id_categoria) REFERENCES tbl_remates_categorias(id);


--
-- TOC entry 1918 (class 2606 OID 24978)
-- Name: tbl_remates_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_remates_detalle
    ADD CONSTRAINT tbl_remates_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id);


--
-- TOC entry 1919 (class 2606 OID 24983)
-- Name: tbl_transferencias_id_miembro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_transferencias
    ADD CONSTRAINT tbl_transferencias_id_miembro_fkey FOREIGN KEY (id_miembro) REFERENCES tbl_miembros(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 1920 (class 2606 OID 24988)
-- Name: tbl_transferencias_id_remate_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_transferencias
    ADD CONSTRAINT tbl_transferencias_id_remate_fkey FOREIGN KEY (id_remate) REFERENCES tbl_remates(id);


--
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-05-12 17:28:51

--
-- PostgreSQL database dump complete
--

