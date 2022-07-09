
CREATE SCHEMA IF NOT EXISTS poseidendb;

use poseidendb;

CREATE TABLE IF NOT EXISTS BidList (
  bid_list_id tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bid_quantity DOUBLE,
  ask_quantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bid_list_date TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (bid_list_id)
);

insert into bidlist (account, ask, ask_quantity, benchmark, bid, bid_list_date, bid_quantity, book, commentary, creation_date, creation_name, deal_name, deal_type, revision_date, revision_name, security, side, source_list_id, status, trader, type) values ('account 1', null, null, null, null, null, 1.0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'type 1');
insert into bidlist (account, ask, ask_quantity, benchmark, bid, bid_list_date, bid_quantity, book, commentary, creation_date, creation_name, deal_name, deal_type, revision_date, revision_name, security, side, source_list_id, status, trader, type) values ('account 2', null, null, null, null, null, 2.0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'type 2');
insert into bidlist (account, ask, ask_quantity, benchmark, bid, bid_list_date, bid_quantity, book, commentary, creation_date, creation_name, deal_name, deal_type, revision_date, revision_name, security, side, source_list_id, status, trader, type) values ('account 3', null, null, null, null, null, 3.0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'type 3');
insert into bidlist (account, ask, ask_quantity, benchmark, bid, bid_list_date, bid_quantity, book, commentary, creation_date, creation_name, deal_name, deal_type, revision_date, revision_name, security, side, source_list_id, status, trader, type) values ('account 4', null, null, null, null, null, 4.0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'type 4');
insert into bidlist (account, ask, ask_quantity, benchmark, bid, bid_list_date, bid_quantity, book, commentary, creation_date, creation_name, deal_name, deal_type, revision_date, revision_name, security, side, source_list_id, status, trader, type) values ('account 5', null, null, null, null, null, 5.0, null, null, null, null, null, null, null, null, null, null, null, null, null, 'type 5');

CREATE TABLE IF NOT EXISTS Trade (
  trade_id tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buy_quantity DOUBLE,
  sell_quantity DOUBLE,
  buy_price DOUBLE ,
  sell_price DOUBLE,
  trade_date TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date TIMESTAMP ,
  revision_name VARCHAR(125),
  revision_date TIMESTAMP ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (trade_id)
);
insert into trade (account, type, buy_quantity, sell_quantity, buy_price, sell_price, trade_date, security, status, trader, benchmark, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side) values ('trade account 1', 'type 1', 12, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
insert into trade (account, type, buy_quantity, sell_quantity, buy_price, sell_price, trade_date, security, status, trader, benchmark, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side) values ('trade account 2', 'type 2', 3, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
insert into trade (account, type, buy_quantity, sell_quantity, buy_price, sell_price, trade_date, security, status, trader, benchmark, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side) values ('trade account 3', 'type 3', 18, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
insert into trade (account, type, buy_quantity, sell_quantity, buy_price, sell_price, trade_date, security, status, trader, benchmark, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side) values ('trade account 4', 'type 4', 9, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
insert into trade (account, type, buy_quantity, sell_quantity, buy_price, sell_price, trade_date, security, status, trader, benchmark, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side) values ('trade account 5', 'type 5', 2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);


CREATE TABLE IF NOT EXISTS CurvePoint (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  curve_id tinyint,
  as_of_date TIMESTAMP,
  term DOUBLE not null ,
  value DOUBLE not null ,
  creation_date TIMESTAMP ,

  PRIMARY KEY (id)
);
insert into CurvePoint (curve_id, as_of_date, term, value, creation_date) values (5, null, 10.0, 20.0, null);
insert into CurvePoint (curve_id, as_of_date, term, value, creation_date) values (7, null, 17.0, 15.0, null);
insert into CurvePoint (curve_id, as_of_date, term, value, creation_date) values (10, null, 20.0, 30.0, null);
insert into CurvePoint (curve_id, as_of_date, term, value, creation_date) values (13, null, 40.0, 33.0, null);
insert into CurvePoint (curve_id, as_of_date, term, value, creation_date) values (15, null, 22.0, 60.0, null);


CREATE TABLE IF NOT EXISTS Rating (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  moodys_rating VARCHAR(125),
  sandprating VARCHAR(125),
  fitch_rating VARCHAR(125),
  order_number tinyint,

  PRIMARY KEY (id)
);
insert into Rating (moodys_rating, sandprating, fitch_rating, order_number)values ('Moodys Rating 1', 'Sand PRating 1', 'Fitch Rating 1', 10);
insert into Rating (moodys_rating, sandprating, fitch_rating, order_number)values ('Moodys Rating 2', 'Sand PRating 2', 'Fitch Rating 2', 20);
insert into Rating (moodys_rating, sandprating, fitch_rating, order_number)values ('Moodys Rating 3', 'Sand PRating 3', 'Fitch Rating 3', 30);
insert into Rating (moodys_rating, sandprating, fitch_rating, order_number)values ('Moodys Rating 4', 'Sand PRating 4', 'Fitch Rating 4', 40);
insert into Rating (moodys_rating, sandprating, fitch_rating, order_number)values ('Moodys Rating 4', 'Sand PRating 5', 'Fitch Rating 5', 50);

CREATE TABLE IF NOT EXISTS RuleName (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sql_str VARCHAR(125),
  sql_part VARCHAR(125),

  PRIMARY KEY (id)
);
insert into RuleName (name, description, json, template, sql_str, sql_part) values ('rule name 1', 'description 1', 'json 1', 'template 1', 'sqlStr 1', 'sqlPart 1');
insert into RuleName (name, description, json, template, sql_str, sql_part) values ('rule name 2', 'description 2', 'json 2', 'template 2', 'sqlStr 2', 'sqlPart 2');
insert into RuleName (name, description, json, template, sql_str, sql_part) values ('rule name 3', 'description 3', 'json 3', 'template 3', 'sqlStr 3', 'sqlPart 3');
insert into RuleName (name, description, json, template, sql_str, sql_part) values ('rule name 4', 'description 4', 'json 4', 'template 4', 'sqlStr 4', 'sqlPart 4');
insert into RuleName (name, description, json, template, sql_str, sql_part) values ('rule name 5', 'description 5', 'json 5', 'template 5', 'sqlStr 5', 'sqlPart 5');

CREATE TABLE IF NOT EXISTS Users (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),

  PRIMARY KEY (id)
);

insert into Users(fullname, username, password, role) values('Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ROLE_ADMIN');
insert into Users(fullname, username, password, role) values('User 1', 'user 1', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ROLE_USER');
insert into Users(fullname, username, password, role) values('User 2', 'user 2', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ROLE_USER');
insert into Users(fullname, username, password, role) values('User 3', 'user 3', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ROLE_USER');