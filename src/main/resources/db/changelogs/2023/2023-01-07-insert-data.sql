--liquibase formatted sql
--changeset {test@test.com}:{1}
insert into tour (creation_date) values (now()), (now());
insert into
    story (creation_date, lang, title, published, story_order, tour_id)
values (now(), 'RU', 'Тестовый заголовок!', true, 1, 1),
       (now(), 'RU', 'Тестовый заголовок неопубликован!', false, 2, 1),
       (now(), 'EN', 'test title', false, 2, 1),
       (now(), 'EN', 'test title', true, 3, 1);
insert into
    story (creation_date, lang, title, published, story_order, tour_id)
values (now(), 'RU', 'Тестовый заголовок!', true, 1, 2),
       (now(), 'RU', 'Тестовый заголовок неопубликован!', false, 2, 2),
       (now(), 'EN', 'test title', false, 2, 2),
       (now(), 'EN', 'test title', true, 3, 2),
       (now(), 'FR', 'Le parle vue France', true, 4, 2);