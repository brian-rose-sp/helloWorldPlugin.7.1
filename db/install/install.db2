
--
-- DB2 DDL plugin script
--

CREATE  TABLE helloworld_table (
  user_id bigint not null,
  message varchar(1024),
  primary key (user_id)
) IN identityiq_pl_ts;


