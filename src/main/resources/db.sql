/*
Created: 19.01.2022
Modified: 26.01.2022
Model: PostgreSQL 12
Database: PostgreSQL 12
*/

-- Create tables section -------------------------------------------------

-- Table usr

CREATE TABLE "usr"
(
    "id" BigSerial NOT NULL,
    "first_name" Text NOT NULL,
    "last_name" Text NOT NULL,
    "third_name" Text,
    "sex" Text NOT NULL,
    "birthday" Timestamp,
    "telephone" Text NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "usr" ADD CONSTRAINT "PK_usr" PRIMARY KEY ("id")
;

-- Table quiz

CREATE TABLE "quiz"
(
    "id" BigSerial NOT NULL,
    "title" Text,
    "access" Boolean,
    "gender" Text,
    "min_age" Bigint,
    "max_age" Bigint,
    "weight_arg" Bigint
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "quiz" ADD CONSTRAINT "PK_quiz" PRIMARY KEY ("id")
;

-- Table question

CREATE TABLE "question"
(
    "id" BigSerial NOT NULL,
    "content" Text,
    "num_question" Bigint,
    "weight_arg" Bigint,
    "id_quiz" Bigint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship7" ON "question" ("id_quiz")
;

ALTER TABLE "question" ADD CONSTRAINT "PK_question" PRIMARY KEY ("id")
;

-- Table answer

CREATE TABLE "answer"
(
    "id" BigSerial NOT NULL,
    "content" Text,
    "weight_arg" Bigint,
    "id_question" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship3" ON "answer" ("id_question")
;

ALTER TABLE "answer" ADD CONSTRAINT "PK_answer" PRIMARY KEY ("id")
;

-- Table key_quiz

CREATE TABLE "key_quiz"
(
    "id" BigSerial NOT NULL,
    "min_arg" Bigint,
    "max_arg" Bigint,
    "result_arg" Text,
    "id_quiz" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "key_quiz" ADD CONSTRAINT "PK_key_quiz" PRIMARY KEY ("id_quiz","id")
;

-- Table user_answer

CREATE TABLE "user_answer"
(
    "id" BigSerial NOT NULL,
    "content_answer" Text,
    "id_user_n_quiz" Bigint NOT NULL,
    "id_answer" Bigint NOT NULL,
    "id_question" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship1" ON "user_answer" ("id_answer")
;

CREATE INDEX "IX_Relationship4" ON "user_answer" ("id_question")
;

ALTER TABLE "user_answer" ADD CONSTRAINT "PK_user_answer" PRIMARY KEY ("id_user_n_quiz","id")
;

-- Table user_n_quiz

CREATE TABLE "user_n_quiz"
(
    "id" BigSerial NOT NULL,
    "status" Text NOT NULL,
    "complete_date" Timestamp,
    "result" Text,
    "id_usr" Bigint NOT NULL,
    "id_quiz" Bigint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship2" ON "user_n_quiz" ("id_quiz")
;

ALTER TABLE "user_n_quiz" ADD CONSTRAINT "PK_user_n_quiz" PRIMARY KEY ("id_usr","id")
;

-- Table quiz_n_quiz

CREATE TABLE "quiz_n_quiz"
(
    "id" BigSerial NOT NULL,
    "id_parent_quiz" Bigint NOT NULL,
    "id_child_quiz" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship6" ON "quiz_n_quiz" ("id_child_quiz")
;

ALTER TABLE "quiz_n_quiz" ADD CONSTRAINT "PK_quiz_n_quiz" PRIMARY KEY ("id_parent_quiz","id")
;

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE "user_n_quiz"
    ADD CONSTRAINT "Relationship1"
        FOREIGN KEY ("id_usr")
            REFERENCES "usr" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "user_n_quiz"
    ADD CONSTRAINT "Relationship2"
        FOREIGN KEY ("id_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
;

ALTER TABLE "key_quiz"
    ADD CONSTRAINT "Relationship14"
        FOREIGN KEY ("id_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "user_answer"
    ADD CONSTRAINT "Relationship6"
        FOREIGN KEY ("id_user_n_quiz", "id_user_n_quiz")
            REFERENCES "user_n_quiz" ("id_usr", "id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "question"
    ADD CONSTRAINT "Relationship7"
        FOREIGN KEY ("id_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
;

ALTER TABLE "user_answer"
    ADD CONSTRAINT "Relationship13"
        FOREIGN KEY ("id_answer")
            REFERENCES "answer" ("id")
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
;

ALTER TABLE "answer"
    ADD CONSTRAINT "Relationship3"
        FOREIGN KEY ("id_question")
            REFERENCES "question" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "user_answer"
    ADD CONSTRAINT "Relationship4"
        FOREIGN KEY ("id_question")
            REFERENCES "question" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "quiz_n_quiz"
    ADD CONSTRAINT "Relationship15"
        FOREIGN KEY ("id_parent_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "quiz_n_quiz"
    ADD CONSTRAINT "Relationship16"
        FOREIGN KEY ("id_child_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

