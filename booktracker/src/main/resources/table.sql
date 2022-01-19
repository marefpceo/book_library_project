create table user_login (
	--id integer identity primary key,
	id integer primary key,
	firstName varchar(100) not null,
	lastName varchar(100),
	username varchar(100) not null, --primary key,
	password varchar(200) not null,
	enabled integer not null,
	expired integer not null,
	rec_lgcl_del_ind varchar(1) not null
);

create table user_auth(
	id integer,
	FOREIGN KEY (id) REFERENCES user_login(id),
	authority varchar(100) not null
);

create table user_info(
	id integer,
	FOREIGN KEY (id) REFERENCES user_login(id),
	fav_genres varchar(200),
	avg_reading_hrs_daily integer
);