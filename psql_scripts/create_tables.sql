
CREATE TABLE users
(
    user_id     serial PRIMARY KEY,
    email       VARCHAR ( 320 ) UNIQUE  NOT NULL,
    username    VARCHAR ( 50 )  UNIQUE  NOT NULL,
    password    TEXT                    NOT NULL,
    role        VARCHAR ( 50 )          NOT NULL
);

CREATE TABLE admins
(
    user_id serial PRIMARY KEY,
    username VARCHAR ( 50 ) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    token_hash TEXT,
    created_on TIMESTAMP NOT NULL,
    last_login TIMESTAMP
);

CREATE TABLE skills
(
    id serial PRIMARY KEY,
    name VARCHAR ( 100 ) NOT NULL,
    skill_type VARCHAR ( 100 ) NOT NULL,
    icon_url VARCHAR ( 200 ) NOT NULL
);

CREATE TABLE projects
(
    id serial PRIMARY KEY,
    name VARCHAR ( 50 ) UNIQUE NOT NULL,
    description TEXT NOT NULL,
    source VARCHAR ( 150 ),
    demo VARCHAR ( 150 ),
    img_url VARCHAR ( 200 )
);

CREATE TABLE skills_projects
(
    skill_id int NOT NULL,
    project_id int NOT NULL,
    PRIMARY KEY (skill_id, project_id),
    FOREIGN KEY (skill_id) REFERENCES skills(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);
