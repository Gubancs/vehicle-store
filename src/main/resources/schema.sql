-- Tables --------------------------------------------------------

DROP TABLE IF EXISTS vehicles;

CREATE TABLE vehicles (
    validity date,
    uuid character varying(36) PRIMARY KEY,
    registration character varying(20) UNIQUE,
    owner character varying(200),
    data character varying(200)
);


-- Indices -------------------------------------------------------

CREATE INDEX IF NOT EXISTS idx_data_gist ON vehicles USING GIST (data gist_trgm_ops);
CREATE INDEX IF NOT EXISTS idx_owner_gist ON vehicles USING GIST (owner gist_trgm_ops);
CREATE INDEX IF NOT EXISTS idx_registration_gist ON vehicles USING GIST (registration gist_trgm_ops);
