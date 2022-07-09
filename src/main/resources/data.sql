DROP DATABASE IF EXISTS poseidendb;

CREATE DATABASE poseidendb;

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

CREATE TABLE IF NOT EXISTS CurvePoint (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  curve_id tinyint,
  as_of_date TIMESTAMP,
  term DOUBLE ,
  value DOUBLE ,
  creation_date TIMESTAMP ,

  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Rating (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  moodys_rating VARCHAR(125),
  sandprating VARCHAR(125),
  fitch_rating VARCHAR(125),
  order_number tinyint,

  PRIMARY KEY (id)
);

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

CREATE TABLE IF NOT EXISTS Users (
  id tinyint(4) NOT NULL AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),

  PRIMARY KEY (id)
);

insert into Users(fullname, username, password, role) values('Administrator', 'admin', '$2a$10$BKCfT2p8s.Mzv2zS03KOW.OwCPrwpsSyej8p1hGIES5dzSMbcKp8y', 'ROLE_ADMIN');
insert into Users(fullname, username, password, role) values('User', 'user', '$2a$10$BKCfT2p8s.Mzv2zS03KOW.OwCPrwpsSyej8p1hGIES5dzSMbcKp8y', 'ROLE_USER')