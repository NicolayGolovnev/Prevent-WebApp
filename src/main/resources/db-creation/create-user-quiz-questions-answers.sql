INSERT INTO public.quiz (id, title, access, gender, min_age, max_age, weight_arg)
VALUES (1, 'Большой функциональный опросник PLS', true, 'all', 1, 100, 1),
       (2, 'Функция желудка', true, 'all', 1, 100, 1),
       (3, 'Воспаление желудка', true, 'all', 1, 100, 1);

INSERT INTO public.usr (id, first_name, last_name, third_name, sex, birthday, telephone)
VALUES (1, 'Иванов', 'Иван', 'Иваныч', 'м', '1960-04-28 22:17:51.000000', '88005553535');


INSERT INTO public.question (id, content, num_question, weight_arg, id_quiz)
VALUES (1, 'Несварение желудка после еды', 1, 1, 2),
       (2, 'Чрезмерная отрыжка, вздутие живота после еды', 2, 1, 2),
       (3, 'Спазмы в желудке во время или после еды', 3, 1, 2),
       (4, 'Ощущение, что еда просто осела в желудке, создавая ощущение
дискомфорта, наполненности, распирания и вздутия вовремя или после еды', 4, 1, 2),
       (5, 'Плохой привкус во рту', 5, 1, 2),
       (6, 'Быстро насыщаетесь небольшим количеством еды', 6, 1, 2),
       (7, 'Пропускаете еду или питаетесь с перебоями из-за отсутствия аппетита', 7, 1, 2),
       (8, 'Сильные эмоции или мысли о еде', 1, 1, 3),
       (9, 'Чувствуете голод через 1-2 часа после принятия большого количества пищи', 2, 1, 3),
       (10, 'Боль в желудке, жжение и/или ноющая боль, через 1-4 часа после еды', 3, 1, 3),
       (11, 'Боль в желудке, жжение и/или ноющая боль, которая облегчается приемом
пищи, питьем газированной воды, сливок или молока или приемом
антацидных препаратов', 4, 1, 3),
       (12, 'Ощущение жжения в нижней части грудной клетки, особенно, если ложитесь
или наклоняетесь вперед', 5, 1, 3),
       (13, 'Проблемы с пищеварением устраняются отдыхом и расслаблением', 6, 1, 3),
       (14, 'Принятие острой или жирной (жареной) пищи, шоколада, кофе, алкоголя
вызывают жжение или ноющую боль в желудке', 7, 1, 3),
       (15, 'Ощущение тошноты во время еды', 8, 1, 3),
       (16, 'Трудность или боль во время проглатывания еды или жидкости', 9, 1, 3);

INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (1, 'Нет или редко', 0, 1);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (2, 'Иногда', 1, 1);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (3, 'Часто', 4, 1);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (5, 'Нет или редко', 0, 2);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (6, 'Иногда', 1, 2);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (7, 'Часто', 4, 2);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (9, 'Нет или редко', 0, 3);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (10, 'Иногда', 1, 3);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (11, 'Часто', 4, 3);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (13, 'Нет или редко', 0, 4);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (14, 'Иногда', 1, 4);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (15, 'Часто', 4, 4);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (17, 'Нет или редко', 0, 5);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (18, 'Иногда', 1, 5);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (19, 'Часто', 4, 5);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (21, 'Нет или редко', 0, 6);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (22, 'Иногда', 1, 6);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (23, 'Часто', 4, 6);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (25, 'Нет или редко', 0, 7);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (26, 'Иногда', 1, 7);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (27, 'Часто', 4, 7);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (29, 'Нет или редко', 0, 8);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (30, 'Иногда', 1, 8);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (31, 'Часто', 4, 8);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (32, 'Очень часто', 8, 8);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (33, 'Нет или редко', 0, 9);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (34, 'Иногда', 1, 9);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (35, 'Часто', 4, 9);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (36, 'Очень часто', 8, 9);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (12, 'Очень часто', 8, 3);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (28, 'Очень часто', 8, 7);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (8, 'Очень часто', 8, 2);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (24, 'Очень часто', 8, 6);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (4, 'Очень часто', 8, 1);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (20, 'Очень часто', 8, 5);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (16, 'Очень часто', 8, 4);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (37, 'Нет или редко', 0, 10);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (38, 'Иногда', 1, 10);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (39, 'Часто', 4, 10);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (40, 'Очень часто', 8, 10);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (41, 'Нет или редко', 0, 11);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (42, 'Иногда', 1, 11);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (43, 'Часто', 4, 11);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (44, 'Очень часто', 8, 11);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (45, 'Нет или редко', 0, 12);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (46, 'Иногда', 1, 12);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (47, 'Часто', 4, 12);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (48, 'Очень часто', 8, 12);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (49, 'Нет или редко или иногда', 0, 13);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (50, 'Часто или очень часто', 8, 13);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (51, 'Нет или редко', 0, 14);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (52, 'Иногда', 1, 14);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (53, 'Часто', 4, 14);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (54, 'Очень часто', 8, 14);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (55, 'Нет или редко', 0, 15);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (56, 'Иногда', 1, 15);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (57, 'Часто', 4, 15);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (58, 'Очень часто', 8, 15);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (59, 'Нет или редко', 0, 16);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (60, 'Иногда', 1, 16);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (61, 'Часто', 4, 16);
INSERT INTO public.answer (id, content, weight_arg, id_question)
VALUES (62, 'Очень часто', 8, 16);

