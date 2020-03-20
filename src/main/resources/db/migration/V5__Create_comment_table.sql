create table comment
(
	id bigint auto_increment primary key,
	content varchar(1000),
	parent_id bigint not null,
	type int,
	commentator int not null,
	gmt_create bigint,
	gmt_modified bigint,
	like_count bigint default 0
);