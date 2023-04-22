INSERT INTO skills (id, name, skill_type, icon_url)
VALUES (1, 'React', 'frontend', '#'),
       (2, 'Spring Boot', 'backend', '#'),
       (3, 'C++', 'language', '#'),
       (4, 'Docker', 'other', '#'),
       (5, 'React Native', 'frontend', '#');

INSERT INTO projects(id, name, description, source, demo, img_url)
VALUES (1, 'AmiGO App', 'Collaborated in the development of the TomTom AmiGO GPS navigation app available on Android and iOS supporting Android Auto and Carplay.', 'https://github.com', 'https://www.tomtom.com/en_gb/sat-nav/amigo/', 'https://donado.io/project-imgs/amigo.jpg'),
       (2, 'Zocialize', 'Social media app with friend system, instant messaging, post text or photos, geotagging. Sort of a hybrid between instagram and twitter.', '#', '#', '#');

INSERT INTO projects(id, name, description, img_url)
VALUES (3, 'Third project', 'Collaborated in the development of the TomTom AmiGO GPS navigation app available on Android and iOS supporting Android Auto and Carplay.', 'https://donado.io/project-imgs/amigo.jpg'),
       (4, 'Fourth', 'Social media app with friend system, instant messaging, post text or photos, geotagging. Sort of a hybrid between instagram and twitter.', '#');

INSERT INTO skills_projects(skill_id, project_id)
VALUES (1, 2),
       (4, 2),
       (2, 2);