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
    "id" bigserial UNIQUE NOT NULL,
    "first_name" Text NOT NULL,
    "last_name" Text NOT NULL,
    "third_name" Text,
    "sex" Text NOT NULL,
    "birthday" timestamp without time zone NOT NULL,
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
    "id" bigserial UNIQUE NOT NULL,
    "title" Text,
    "access" Boolean,
    "gender" Text,
    "min_age" Bigint,
    "max_age" Bigint,
    "weight_arg" Bigint,
    "id_quiz" Bigint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship3" ON "quiz" ("id_quiz")
;

ALTER TABLE "quiz" ADD CONSTRAINT "PK_quiz" PRIMARY KEY ("id")
;

-- Table question

CREATE TABLE "question"
(
    "id" bigserial UNIQUE NOT NULL,
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
    "id" bigserial NOT NULL,
    "content" Text,
    "weight_arg" Bigint
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "answer" ADD CONSTRAINT "PK_answer" PRIMARY KEY ("id")
;

-- Table key_quiz

CREATE TABLE "key_quiz"
(
    "id" bigserial UNIQUE NOT NULL,
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
    "id" bigserial UNIQUE NOT NULL,
    "content_answer" Text,
    "id_question" Bigint,
    "id_user_n_quiz" Bigint NOT NULL,
    "id_answer_n_question" Bigint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship5" ON "user_answer" ("id_question","id_answer_n_question")
;

ALTER TABLE "user_answer" ADD CONSTRAINT "PK_user_answer" PRIMARY KEY ("id_user_n_quiz","id")
;

-- Table answer_n_question

CREATE TABLE "answer_n_question"
(
    "id" bigserial UNIQUE NOT NULL,
    "id_question" Bigint NOT NULL,
    "id_answer" Bigint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship9" ON "answer_n_question" ("id_answer")
;

ALTER TABLE "answer_n_question" ADD CONSTRAINT "PK_answer_n_question" PRIMARY KEY ("id_question","id")
;

-- Table user_n_quiz

CREATE TABLE "user_n_quiz"
(
    "id" bigserial UNIQUE NOT NULL,
    "status" Text NOT NULL,
    "complete_date" timestamp without time zone NOT NULL,
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

ALTER TABLE "quiz"
    ADD CONSTRAINT "Relationship3"
        FOREIGN KEY ("id_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
;

ALTER TABLE "key_quiz"
    ADD CONSTRAINT "Relationship4"
        FOREIGN KEY ("id_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "user_answer"
    ADD CONSTRAINT "Relationship5"
        FOREIGN KEY ("id_question", "id_answer_n_question")
            REFERENCES "answer_n_question" ("id_question", "id")
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
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

ALTER TABLE "answer_n_question"
    ADD CONSTRAINT "Relationship8"
        FOREIGN KEY ("id_question")
            REFERENCES "question" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "answer_n_question"
    ADD CONSTRAINT "Relationship9"
        FOREIGN KEY ("id_answer")
            REFERENCES "answer" ("id")
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
;
