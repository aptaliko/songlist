CREATE TABLE rhythm (
    id uuid PRIMARY KEY,
    name varchar(50) not null,
    meter varchar(50)
);

CREATE TABLE dance (
    id uuid PRIMARY KEY,
    name varchar(50) not null
);

CREATE TABLE mode (
    id uuid PRIMARY KEY,
    name varchar(50) not null
);

CREATE TABLE song (
    id uuid PRIMARY KEY,
    name varchar(50) not null,
    comments varchar(150),
    rhythm_id uuid REFERENCES rhythm (id),
    dance_id uuid REFERENCES dance (id),
    mode_id uuid REFERENCES mode (id)
);