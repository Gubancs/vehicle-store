-- Sequences -----------------------------------------------------
DROP SEQUENCE IF EXISTS entity_seq;
CREATE SEQUENCE entity_seq START 1 INCREMENT 50;

-- Tables --------------------------------------------------------

DROP TABLE IF EXISTS vehicles;

CREATE TABLE vehicles (
    validity date,
    id bigint PRIMARY KEY,
    registration character varying(20) UNIQUE,
    uuid character varying(36) UNIQUE,
    owner character varying(200),
    data character varying(255)
);


-- Indices -------------------------------------------------------

CREATE UNIQUE INDEX IF NOT EXISTS vehicles_pkey ON vehicles(id int8_ops);
CREATE UNIQUE INDEX IF NOT EXISTS idx_vehicle_uuid ON vehicles(uuid text_ops);
CREATE INDEX IF NOT EXISTS idx_data_gist ON vehicles USING GIST (data gist_trgm_ops);
CREATE INDEX IF NOT EXISTS idx_owner_gist ON vehicles USING GIST (owner gist_trgm_ops);
CREATE INDEX IF NOT EXISTS idx_registration_gist ON vehicles USING GIST (registration gist_trgm_ops);
