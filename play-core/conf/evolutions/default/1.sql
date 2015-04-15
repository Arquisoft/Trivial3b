# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table estadistica (
  id                        bigint not null,
  usuario                   varchar(255),
  question_id               integer,
  aciertos                  integer,
  fallos                    integer,
  constraint pk_estadistica primary key (id))
;

create table player (
  id                        varchar(255) not null,
  pass                      varchar(255),
  constraint pk_player primary key (id))
;

create table pregunta (
  id                        bigint not null,
  constraint pk_pregunta primary key (id))
;

create sequence estadistica_seq;

create sequence player_seq;

create sequence pregunta_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists estadistica;

drop table if exists player;

drop table if exists pregunta;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists estadistica_seq;

drop sequence if exists player_seq;

drop sequence if exists pregunta_seq;

