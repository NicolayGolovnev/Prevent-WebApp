/*
Created: 19.01.2022
Modified: 03.06.2022
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
    "telephone" Text NOT NULL,
    "email" Text,
    "password" Text NOT NULL
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
    "title" Text NOT NULL,
    "access" Boolean NOT NULL,
    "gender" Text,
    "min_age" Bigint,
    "max_age" Bigint,
    "weight_arg" Bigint NOT NULL
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
    "content" Text NOT NULL,
    "num_question" Bigint,
    "weight_arg" Bigint NOT NULL,
    "id_quiz" Bigint NOT NULL
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
    "weight_arg" Bigint NOT NULL,
    "id_question" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "answer" ADD CONSTRAINT "PK_answer" PRIMARY KEY ("id","id_question")
;

-- Table key_quiz

CREATE TABLE "key_quiz"
(
    "id" BigSerial NOT NULL,
    "gender" Text,
    "min_arg" Bigint NOT NULL,
    "max_arg" Bigint NOT NULL,
    "result_arg" Text NOT NULL,
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
    "id_usr" Bigint NOT NULL,
    "id_answer" Bigint,
    "id_question" Bigint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship11" ON "user_answer" ("id_answer","id_question")
;

ALTER TABLE "user_answer" ADD CONSTRAINT "PK_user_answer" PRIMARY KEY ("id","id_usr","id_user_n_quiz")
;

-- Table user_n_quiz

CREATE TABLE "user_n_quiz"
(
    "id" BigSerial NOT NULL,
    "status" Text NOT NULL,
    "complete_date" Timestamp,
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

-- Table history_results

CREATE TABLE "history_results"
(
    "id" BigSerial NOT NULL,
    "result" Text NOT NULL,
    "id_usr" Bigint NOT NULL,
    "id_user_n_quiz" Bigint NOT NULL,
    "id_children_quiz" Bigint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship9" ON "history_results" ("id_children_quiz")
;

ALTER TABLE "history_results" ADD CONSTRAINT "PK_history_results" PRIMARY KEY ("id_usr","id_user_n_quiz","id")
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
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "key_quiz"
    ADD CONSTRAINT "Relationship14"
        FOREIGN KEY ("id_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "question"
    ADD CONSTRAINT "Relationship7"
        FOREIGN KEY ("id_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "answer"
    ADD CONSTRAINT "Relationship3"
        FOREIGN KEY ("id_question")
            REFERENCES "question" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "quiz_n_quiz"
    ADD CONSTRAINT "Relationship15"
        FOREIGN KEY ("id_parent_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "quiz_n_quiz"
    ADD CONSTRAINT "Relationship16"
        FOREIGN KEY ("id_child_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "history_results"
    ADD CONSTRAINT "Relationship8"
        FOREIGN KEY ("id_usr", "id_user_n_quiz")
            REFERENCES "user_n_quiz" ("id_usr", "id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "history_results"
    ADD CONSTRAINT "Relationship9"
        FOREIGN KEY ("id_children_quiz")
            REFERENCES "quiz" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "user_answer"
    ADD CONSTRAINT "Relationship10"
        FOREIGN KEY ("id_usr", "id_user_n_quiz")
            REFERENCES "user_n_quiz" ("id_usr", "id")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

ALTER TABLE "user_answer"
    ADD CONSTRAINT "Relationship11"
        FOREIGN KEY ("id_answer", "id_question")
            REFERENCES "answer" ("id", "id_question")
            ON DELETE CASCADE
            ON UPDATE CASCADE
;

