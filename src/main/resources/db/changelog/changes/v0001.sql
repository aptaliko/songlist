CREATE TABLE rhythm (
    id uuid PRIMARY KEY,
    name varchar(50) not null UNIQUE,
    meter varchar(50)
);

CREATE TABLE dance (
    id uuid PRIMARY KEY,
    name varchar(50) not null UNIQUE
);

CREATE TABLE mode (
    id uuid PRIMARY KEY,
    name varchar(50) not null UNIQUE
);

CREATE TABLE region (
    id uuid PRIMARY KEY,
    name varchar(50) not null UNIQUE,
    parent_id uuid REFERENCES region (id)
);

CREATE TABLE song (
    id uuid PRIMARY KEY,
    name varchar(50) not null UNIQUE,
    comments varchar(150),
    rhythm_id uuid REFERENCES rhythm (id),
    dance_id uuid REFERENCES dance (id),
    mode_id uuid REFERENCES mode (id),
    region_id uuid REFERENCES region (id)
);