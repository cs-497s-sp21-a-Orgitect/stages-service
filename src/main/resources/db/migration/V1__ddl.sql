CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence;


-- Creating process table -------------------------------------------------
CREATE TABLE IF NOT EXISTS process
(
    id              BIGSERIAL PRIMARY KEY,
    name            CHARACTER VARYING(400),
    description     CHARACTER VARYING(400)
);

CREATE UNIQUE INDEX IF NOT EXISTS process_pkey ON process (id INT8_OPS);


-- Creating stage table -------------------------------------------------

CREATE TABLE IF NOT EXISTS stage
(
    id              BIGSERIAL PRIMARY KEY,
    action          CHARACTER varying(400),
    duration        BIGINT,
    ordering        INT,
    process_id      BIGINT NOT NULL REFERENCES process(id)
);

CREATE UNIQUE INDEX IF NOT EXISTS stage_pkey ON stage (id INT8_OPS);

-- Creating request table ----------------------------------------------------
CREATE TABLE IF NOT EXISTS request
(
    id                      BIGSERIAL PRIMARY KEY,
    process_id              BIGINT REFERENCES process(id),
    customer_id             BIGINT,
    actor_id                BIGINT,
    current_stage_id        BIGINT REFERENCES stage(id)
);

CREATE UNIQUE INDEX IF NOT EXISTS request_pkey ON request (id INT8_OPS);
