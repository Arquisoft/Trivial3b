# --- !Ups

create table estadistica (
  usuario                   varchar(255),
  question                  varchar(255),
  category                  varchar(255),
  aciertos                  integer,
  fallos                    integer,
  constraint pk_estadistica primary key (usuario, question))
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

