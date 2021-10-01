CREATE TABLE rhythm (
    id uuid PRIMARY KEY,
    name varchar(50) not null,
    meter varchar(50)
);

CREATE TABLE song (
    id uuid PRIMARY KEY,
    name varchar(50) not null,
    comments varchar(150),
    rhythm_id uuid REFERENCES rhythm (id)
);