-- ----------------------------
-- exam_type
-- ----------------------------
DROP TABLE IF EXISTS `exam_type`;
CREATE TABLE `exam_type` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `type` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`)
);

-- ----------------------------
-- mark
-- ----------------------------
DROP TABLE IF EXISTS `mark`;
CREATE TABLE `mark` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        `value` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
);

-- ----------------------------
-- study_group
-- ----------------------------
DROP TABLE IF EXISTS `study_group`;
CREATE TABLE `study_group` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`id`)
);

-- ----------------------------
-- subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) DEFAULT NULL,
                           `shortname` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
);

-- ----------------------------
-- study_plan
-- ----------------------------
DROP TABLE IF EXISTS `study_plan`;
CREATE TABLE `study_plan` (
                              `id` int NOT NULL,
                              `subject_id` int DEFAULT NULL,
                              `exam_type_id` int DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              CONSTRAINT `study_plan_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES subject (id),
                              CONSTRAINT `study_plan_ibfk_2` FOREIGN KEY (`exam_type_id`) REFERENCES exam_type (id)
);

-- ----------------------------
-- student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `surname` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `second_name` varchar(255) DEFAULT NULL,
                           `study_group_id` int DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           CONSTRAINT `student_ibfk_1` FOREIGN KEY (`study_group_id`) REFERENCES study_group (id)
);

-- ----------------------------
-- journal
-- ----------------------------
DROP TABLE IF EXISTS `journal`;
CREATE TABLE `journal` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `student_id` int DEFAULT NULL,
                           `study_plan_id` int DEFAULT NULL,
                           `in_time` bit(1) DEFAULT NULL,
                           `count` int DEFAULT NULL,
                           `mark_id` int DEFAULT NULL,
                           CONSTRAINT `journal_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES student (id),
                           CONSTRAINT `journal_ibfk_2` FOREIGN KEY (`study_plan_id`) REFERENCES study_plan (id),
                           CONSTRAINT `journal_ibfk_3` FOREIGN KEY (`mark_id`) REFERENCES mark (id)
);

-- ----------------------------
-- INSERT`s
-- ----------------------------

INSERT INTO exam_type VALUES (1, 'Экзамен');
INSERT INTO exam_type VALUES (2, 'Зачет');
INSERT INTO exam_type VALUES (3, 'Зачет с оценкой');
INSERT INTO exam_type VALUES (4, 'Курсовая');

INSERT INTO mark VALUES (1, 'Отлично', '5');
INSERT INTO mark VALUES (2, 'Хорошо', '4');
INSERT INTO mark VALUES (3, 'Удовлетворительно', '3');
INSERT INTO mark VALUES (4, 'Неудволетворительно', '2');
INSERT INTO mark VALUES (5, 'Зачет', 'з');
INSERT INTO mark VALUES (6, 'Незачет', 'н');
INSERT INTO mark VALUES (7, 'Неявка', NULL);

INSERT INTO study_group VALUES (1, 'ИКБО-11-17');
INSERT INTO study_group VALUES (2, 'ИКБО-12-17');
INSERT INTO study_group VALUES (3, 'ИКБО-13-17');

INSERT INTO subject VALUES (1, 'Проектирование информационных систем', 'ПрИС');
INSERT INTO subject VALUES (2, 'Системы искусственного интеллекта', 'СИИ');
INSERT INTO subject VALUES (3, 'Программная инженерия', 'ПИ');
INSERT INTO subject VALUES (4, 'Национальная система информационной безопасности', 'НСИБ');
INSERT INTO subject VALUES (5, 'Системный анализ', 'СисАнал');
INSERT INTO subject VALUES (6, 'Распределенные базы данных', 'РБД');
INSERT INTO subject VALUES (7, 'Системное программное обеспечение', 'СПО');

INSERT INTO study_plan VALUES (1, 1, 1);
INSERT INTO study_plan VALUES (2, 1, 4);
INSERT INTO study_plan VALUES (3, 2, 1);
INSERT INTO study_plan VALUES (4, 3, 1);
INSERT INTO study_plan VALUES (5, 4, 2);
INSERT INTO study_plan VALUES (6, 5, 1);
INSERT INTO study_plan VALUES (7, 6, 2);
INSERT INTO study_plan VALUES (8, 7, 1);

INSERT INTO student VALUES (1, 'Пьяных', 'Георгий', 'Сигизмундович', 1);
INSERT INTO student VALUES (2, 'Курбанов', 'Самуил', 'Самсонович', 1);
INSERT INTO student VALUES (3, 'Шаталов', 'Геннадий', 'Дмитриевич', 1);
INSERT INTO student VALUES (4, 'Капп', 'Влада', 'Авдеевна', 1);
INSERT INTO student VALUES (5, 'Ростовцева', 'Любава', 'Анатолиевна', 1);
INSERT INTO student VALUES (6, 'Терехова', 'Роза', 'Станиславовна', 1);
INSERT INTO student VALUES (7, 'Грызлов', 'Евгений', 'Фомевич', 2);
INSERT INTO student VALUES (8, 'Яйцев', 'Венедикт', 'Валерьянович', 2);
INSERT INTO student VALUES (9, 'Кузинов', 'Андрей', 'Ростиславич', 2);
INSERT INTO student VALUES (10, 'Аввакумова', 'Алла', 'Тарасовна', 2);
INSERT INTO student VALUES (11, 'Эссена', 'Аза', 'Сидорова', 2);
INSERT INTO student VALUES (12, 'Ткач', 'Виктория', 'Кондратьевна', 2);
INSERT INTO student VALUES (13, 'Каверин', 'Ян', 'Аполлинариев', 3);
INSERT INTO student VALUES (14, 'Ратников', 'Ульян', 'Ермолаевич', 3);
INSERT INTO student VALUES (15, 'Веселков', 'Олег', 'Вадимович', 3);
INSERT INTO student VALUES (16, 'Водопьянова', 'Евгения', 'Платоновна', 3);
INSERT INTO student VALUES (17, 'Люба', 'Елизавета', 'Тимофеевна', 3);
INSERT INTO student VALUES (18, 'Квакина', 'Дарья', 'Петровна', 3);
