CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence;

CREATE TABLE IF NOT EXISTS stage
(
    id              BIGSERIAL PRIMARY KEY,
    action          CHARACTER varying(400),
    duration        BIGINT
);

CREATE UNIQUE INDEX IF NOT EXISTS stage_pkey ON stage (id INT8_OPS);