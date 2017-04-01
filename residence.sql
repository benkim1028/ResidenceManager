drop table contract;
drop table resident;
drop table room;
drop table roomtype;
drop table residence;
drop table school;

create table school
(
	sname char(40) not null,
	city char(20) not null,
	address char(40) not null,
	primary key (sname)
);
 
grant select on school to public;
 
create table residence
(
	rname char(40) not null,
	address char(40) not null,
	sname char(40) not null,
	primary key (rname, address),
	foreign key (sname) references school(sname) ON DELETE CASCADE
);
 
grant select on residence to public;
 
create table roomtype
(
	type char(20) not null,
	accommodation integer not null,
	rsize integer not null,
	rate float not null,
	features char(40) null,
	primary key (type)
);

grant select on roomtype to public;

create table room
(
	roomid char(5) not null,
	roomnumber char(4) not null,
	occupancy int not null,
	rname char(40) not null,
	address char(40) not null,
	type char(20) not null,
	primary key (roomid),
	foreign key (rname, address) references residence(rname, address) ON DELETE CASCADE,
	foreign key (type) references roomtype(type) ON DELETE CASCADE,
	check (occupancy >= 0)
);
 
grant select on room to public;

create table resident
(
	studentid char(10) not null,
	name char(20) not null,
	age int not null,
	phone char(12) null,
	email char(40) null,
	roomid char(5) not null,
	primary key (studentid),
	foreign key (roomid) references room(roomid) ON DELETE CASCADE,
	check (age >= 18)
);

grant select on resident to public;

create table contract
(
	contractid char(10) not null,
	fromdate date not null,
	todate date not null,
	studentid char(10) not null,
	rname char(40) not null,
	address char(40) not null,
	primary key (contractid),
	foreign key (studentid) references resident(studentid) ON DELETE CASCADE,
	foreign key (rname, address) references residence(rname, address) ON DELETE CASCADE
);
 
grant select on contract to public;



insert into school
values('University Of British Columbia', 'Vancouver', '2329 West Mall');

insert into school
values('Simon Fraser University', 'Burnaby', '8888 University Dr');

insert into school
values('McGill University', 'Montreal', ' 845 Rue Sherbrooke O');

insert into school
values('Concordia University', 'Montreal', '7141 Rue Sherbrooke O');

insert into school
values('University Of Waterloo', 'Waterloo', '200 University Ave W');



insert into residence
values('Totem Park', '2525 West Mall', 'University Of British Columbia');

insert into residence
values('Walter Gage ', '5959 Student Union Boulevard', 'University Of British Columbia');

insert into residence
values('Place Vanier', '1935 Lower Mall', 'University Of British Columbia');

insert into residence
values('Marine Drive', '2205 Lower Mall', 'University Of British Columbia');

insert into residence
values('Thunderbird', '6335 Thunderbird Cres.', 'University Of British Columbia');



insert into roomtype
values('Studio', '1', '600', '1000', 'kitchen, bathroom');

insert into roomtype
values('One Bedroom', '1', '1000', '1200', 'kitchen, bathroom, lounge');

insert into roomtype
values('Two Bedrooms Suite', '2', '500', '950', 'kitchen, bathroom');

insert into roomtype
values('Four Bedrooms Suite', '4', '500', '900', 'kitchen, bathroom');

insert into roomtype
values('Six Bedrooms Suite', '6', '500', '850', 'kitchen, bathroom, lounge');



insert into room
values('TP101', '101', '2', 'Totem Park', '2525 West Mall', 'Two Bedrooms Suite');

insert into room
values('TP102', '102', '2', 'Totem Park', '2525 West Mall', 'Two Bedrooms Suite');

insert into room
values('TP201', '201', '4', 'Totem Park', '2525 West Mall', 'Four Bedrooms Suite');

insert into room
values('TP202', '202', '2', 'Totem Park', '2525 West Mall', 'Four Bedrooms Suite');

insert into room
values ('WG101', '101', '1', 'Walter Gage', '5959 Student Union Boulevard', 'One Bedroom');

insert into room
values ('WG404', '404', '3', 'Walter Gage', '5959 Student Union Boulevard', 'Four Bedrooms Suite');

insert into room
values ('WG606', '606', '6', 'Walter Gage', '5959 Student Union Boulevard', 'Six Bedrooms Suite');

insert into room
values('WG500', '500', '1', 'Walter Gage', '5959 Student Union Boulevard', 'Studio');

insert into room
values ('PV111', '111', '2', 'Place Vanier', '1935 Lower Mall', 'Two Bedrooms Suite');

insert into room
values ('PV222', '222', '2', 'Place Vanier', '1935 Lower Mall', 'Two Bedrooms Suite');

insert into room
values ('PV333', '333', '2', 'Place Vanier', '1935 Lower Mall', 'Four Bedrooms Suite');

insert into room
values('PV444', '444', '1', 'Place Vanier', '1935 Lower Mall', 'Six Bedrooms Suite');

insert into room
values('MD211', '211', '2', 'Marine Drive', '2205 Lower Mall', 'Two Bedrooms Suite');

insert into room
values('MD313', '313', '2', 'Marine Drive', '2205 Lower Mall', 'Four Bedrooms Suite');

insert into room
values('MD414', '414', '6', 'Marine Drive', '2205 Lower Mall', 'Six Bedrooms Suite');

insert into room
values('MD515', '515', '1', 'Marine Drive', '2205 Lower Mall', 'One Bedroom');

insert into room
values('MD601', '601', '1', 'Marine Drive', '2205 Lower Mall', 'Studio');

insert into room
values('TB110', '110', '1', 'Thunderbird', '6335 Thunderbird Cres.', 'One Bedroom');

insert into room
values('TB120', '120', '1', 'Thunderbird', '6335 Thunderbird Cres.', 'One Bedroom');

insert into room
values('TB130', '130', '1', 'Thunderbird', '6335 Thunderbird Cres.', 'One Bedroom');



insert into resident
values('10000000', 'Name 1', '21', '6041230001', 'aaa.aaa@gmail.com', 'TP101');

insert into resident
values('10000001', 'Name 2', '22', '6041230002', 'bbb.bbb@gmail.com', 'TP101');

insert into resident
values('10000002', 'Name 3', '21', '6041230003', 'ccc.ccc@gmail.com', 'TP102');

insert into resident
values('10000003', 'Name 4', '20', '6041230004', 'ddd.ddd@gmail.com', 'TP102');

insert into resident
values('10000004', 'Name 5', '18', '6041230005', 'eee.eee@gmail.com', 'TP201');

insert into resident
values('10000005', 'Name 6', '20', '6041230006', 'fff.fff@gmail.com', 'TP201');

insert into resident
values('10000006', 'Name 7', '21', '6041230007', 'ggg.ggg@gmail.com', 'TP201');

insert into resident
values('10000007', 'Name 8', '20', '6041230008', 'hhh.hhh@gmail.com', 'TP201');

insert into resident
values('10000008', 'Name 9', '19', '6041230009', 'iii.iii@gmail.com', 'TP202');

insert into resident
values('10000009', 'Name 10', '20', '6041230010', 'jjj.jjj@gmail.com', 'TP202');

insert into resident
values('20000000', 'Name 11', '24', '6041230011', 'kkk.kkk@gmail.com', 'WG101');

insert into resident
values('20000001', 'Name 12', '21', '6041230012', 'lll.lll@gmail.com', 'WG404');

insert into resident
values('20000002', 'Name 13', '20', '6041230013', 'mmm.mmm@gmail.com', 'WG404');

insert into resident
values('20000003', 'Name 14', '22', '6041230014', 'nnn.nnn@gmail.com', 'WG404');

insert into resident
values('20000004', 'Name 15', '18', '6041230015', 'ooo.ooo@gmail.com', 'WG606');

insert into resident
values('20000005', 'Name 16', '23', '6041230016', 'ppp.ppp@gmail.com', 'WG606');

insert into resident
values('20000006', 'Name 17', '24', '6041230017', 'qqq.qqq@gmail.com', 'WG606');

insert into resident
values('20000007', 'Name 18', '20', '6041230018', 'rrr.rrr@gmail.com', 'WG606');

insert into resident
values('20000008', 'Name 19', '19', '6041230019', 'sss.sss@gmail.com', 'WG606');

insert into resident
values('20000009', 'Name 20', '24', '6041230020', 'ttt.ttt@gmail.com', 'WG606');

insert into resident
values('30000000', 'Name 21', '21', '6041230021', 'uuu.uuu@gmail.com', 'WG500');

insert into resident
values('30000001', 'Name 22', '24', '6041230022', 'www.www@gmail.com', 'PV111');

insert into resident
values('30000002', 'Name 23', '22', '6041230023', 'xxx.xxx@gmail.com', 'PV111');

insert into resident
values('30000003', 'Name 24', '20', '6041230024', 'yyy.yyy@gmail.com', 'PV222');

insert into resident
values('30000004', 'Name 25', '19', '6041230025', 'zzz.zzz@gmail.com', 'PV222');

insert into resident
values('30000005', 'Name 26', '21', '6041230026', 'aaa.123@gmail.com', 'PV333');

insert into resident
values('30000006', 'Name 27', '23', '6041230027', 'aaa.321@gmail.com', 'PV333');

insert into resident
values('30000007', 'Name 28', '20', '6041230028', 'aaa.bbb@gmail.com', 'PV444');

insert into resident
values('30000008', 'Name 29', '24', '6041230029', 'aaa.111@gmail.com', 'MD211');

insert into resident
values('30000009', 'Name 30', '21', '6041230030', 'aaa.333@gmail.com', 'MD211');

insert into resident
values('40000000', 'Name 31', '19', '6041230031', 'aaa.444@gmail.com', 'MD313');

insert into resident
values('40000001', 'Name 32', '21', '6041230032', 'aaa.555@gmail.com', 'MD313');

insert into resident
values('40000002', 'Name 33', '23', '6041230033', 'aaa.666@gmail.com', 'MD414');

insert into resident
values('40000003', 'Name 34', '19', '6041230034', 'aaa.777@gmail.com', 'MD414');

insert into resident
values('40000004', 'Name 35', '22', '6041230035', 'aaa.888@gmail.com', 'MD414');

insert into resident
values('40000005', 'Name 36', '21', '6041230036', 'aaa.999@gmail.com', 'MD414');

insert into resident
values('40000006', 'Name 37', '22', '6041230037', 'aaa.ccc@gmail.com', 'MD414');

insert into resident
values('40000007', 'Name 38', '20', '6041230038', 'aaa.ddd@gmail.com', 'MD414');

insert into resident
values('40000008', 'Name 39', '21', '6041230039', 'aaa.eee@gmail.com', 'MD515');

insert into resident
values('40000009', 'Name 40', '19', '6041230040', 'aaa.fff@gmail.com', 'MD601');

insert into resident
values('50000000', 'Name 41', '24', '6041230041', 'aaa.ggg@gmail.com', 'TB110');

insert into resident
values('50000001', 'Name 42', '23', '6041230042', 'aaa.hhh@gmail.com', 'TB120');

insert into resident
values('50000002', 'Name 43', '21', '6041230043', 'aaa.iii@gmail.com', 'TB130');




insert into contract
values('10000000', '2016-09-01', '2017-04-25', '10000000', 'Totem Park', '2525 West Mall');

insert into contract
values('10000001', '2016-09-01', '2017-04-25', '10000001', 'Totem Park', '2525 West Mall');

insert into contract
values('10000002', '2016-09-01', '2017-04-25', '10000002', 'Totem Park', '2525 West Mall');

insert into contract
values('10000003', '2016-09-01', '2017-04-25', '10000003', 'Totem Park', '2525 West Mall');

insert into contract
values('10000004', '2016-09-01', '2017-04-25', '10000004', 'Totem Park', '2525 West Mall');

insert into contract
values('10000005', '2016-04-25', '2017-04-25', '10000005', 'Totem Park', '2525 West Mall');

insert into contract
values('10000006', '2016-04-25', '2017-04-25', '10000006', 'Totem Park', '2525 West Mall');

insert into contract
values('10000007', '2016-04-25', '2017-04-25', '10000007', 'Totem Park', '2525 West Mall');

insert into contract
values('10000008', '2016-04-25', '2017-04-25', '10000008', 'Totem Park', '2525 West Mall');

insert into contract
values('10000009', '2016-04-25', '2017-04-25', '10000009', 'Totem Park', '2525 West Mall');

insert into contract
values('10000010', '2016-09-01', '2017-04-25', '20000000', 'Walter Gage', '5959 Student Union Boulevard');

insert into contract
values('10000011', '2016-09-01', '2017-04-25', '20000001', 'Walter Gage', '5959 Student Union Boulevard');

insert into contract
values('10000012', '2016-09-01', '2017-04-25', '20000002', 'Walter Gage ', '5959 Student Union Boulevard');

insert into contract
values('10000013', '2016-09-01', '2017-04-25', '20000003', 'Walter Gage ', '5959 Student Union Boulevard');

insert into contract
values('10000014', '2016-09-01', '2017-04-25', '20000004', 'Walter Gage ', '5959 Student Union Boulevard');

insert into contract
values('10000015', '2016-04-25', '2017-04-25', '20000005', 'Walter Gage', '5959 Student Union Boulevard');

insert into contract
values('10000016', '2016-04-25', '2017-04-25', '20000006', 'Walter Gage', '5959 Student Union Boulevard');

insert into contract
values('10000017', '2016-04-25', '2017-04-25', '20000007', 'Walter Gage ', '5959 Student Union Boulevard');

insert into contract
values('10000018', '2016-04-25', '2017-04-25', '20000008', 'Walter Gage ', '5959 Student Union Boulevard');

insert into contract
values('10000019', '2016-04-25', '2017-04-25', '20000009', 'Walter Gage ', '5959 Student Union Boulevard');

insert into contract
values('10000020', '2016-09-01', '2017-04-25', '30000000', 'Walter Gage', '5959 Student Union Boulevard');

insert into contract
values('10000021', '2016-09-01', '2017-04-25', '30000001', 'Place Vanier', '1935 Lower Mall');

insert into contract
values('10000022', '2016-09-01', '2017-04-25', '30000002', 'Place Vanier', '1935 Lower Mall');

insert into contract
values('10000023', '2016-09-01', '2017-04-25', '30000003', 'Place Vanier', '1935 Lower Mall');

insert into contract
values('10000024', '2016-09-01', '2017-04-25', '30000004', 'Place Vanier', '1935 Lower Mall');

insert into contract
values('10000025', '2016-04-25', '2017-04-25', '30000005', 'Place Vanier', '1935 Lower Mall');

insert into contract
values('10000026', '2016-04-25', '2017-04-25', '30000006', 'Place Vanier', '1935 Lower Mall');

insert into contract
values('10000027', '2016-04-25', '2017-04-25', '30000007', 'Place Vanier', '1935 Lower Mall');

insert into contract
values('10000028', '2016-04-25', '2017-04-25', '30000008', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000029', '2016-04-25', '2017-04-25', '30000009', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000030', '2016-09-01', '2017-04-25', '40000000', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000031', '2016-09-01', '2017-04-25', '40000001', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000032', '2016-09-01', '2017-04-25', '40000002', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000033', '2016-09-01', '2017-04-25', '40000003', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000034', '2016-09-01', '2017-04-25', '40000004', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000035', '2016-04-25', '2017-04-25', '40000005', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000036', '2016-04-25', '2017-04-25', '40000006', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000037', '2016-04-25', '2017-04-25', '40000007', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000038', '2016-04-25', '2017-04-25', '40000008', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000039', '2016-04-25', '2017-04-25', '40000009', 'Marine Drive', '2205 Lower Mall');

insert into contract
values('10000040', '2016-09-01', '2017-04-25', '50000000', 'Thunderbird', '6335 Thunderbird Cres.');

insert into contract
values('10000041', '2016-09-01', '2017-04-25', '50000001', 'Thunderbird', '6335 Thunderbird Cres.');

insert into contract
values('10000042', '2016-09-01', '2017-04-25', '50000002', 'Thunderbird', '6335 Thunderbird Cres.');


