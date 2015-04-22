# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table estadistica (
  usuario                   varchar(255) not null,
  question                  varchar(255) not null,
  category                  varchar(255),
  aciertos                  integer,
  fallos                    integer)
;

create table player (
  id                        varchar(255) not null,
  pass                      varchar(255),
  constraint pk_player primary key (id))
;

create sequence estadistica_seq;

create sequence player_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists estadistica;

drop table if exists player;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists estadistica_seq;

drop sequence if exists player_seq;

