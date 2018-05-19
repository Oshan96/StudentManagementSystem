CREATE DATABASE Sem1Pro;

USE Sem1Pro;

CREATE TABLE student(sid VARCHAR(4) PRIMARY KEY,
					name VARCHAR(25)NOT NULL,
					dob DATE NOT NULL,
					age INT(3) NOT NULL,
					nic VARCHAR (12) NOT NULL,
					tel1 VARCHAR(15)NOT NULL,
					tel2 VARCHAR(15),
					address VARCHAR(30) NOT NULL);
					
CREATE TABLE teacher(tid VARCHAR(4) PRIMARY KEY,
					name VARCHAR(25) NOT NULL,
					tel1 VARCHAR(15)NOT NULL,
					tel2 VARCHAR(15),
					address VARCHAR(30) NOT NULL);

CREATE TABLE classes(cid VARCHAR(4) PRIMARY KEY,
					name VARCHAR(20) NOT NULL,
					medium VARCHAR(10) NOT NULL,
					stream VARCHAR(8) NOT NULL,
					tid VARCHAR(4),
					FOREIGN KEY (tid) REFERENCES teacher(tid) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE batch(bid VARCHAR(4) PRIMARY KEY,
					hall VARCHAR(5) NOT NULL,desc 
					admission DECIMAL(6,2) NOT NULL,
					byear int(4) NOT NULL,
					cid VARCHAR(4),
					cday VARCHAR(10),
					FOREIGN KEY (cid) REFERENCES classes(cid) ON DELETE CASCADE ON UPDATE CASCADE);
					
CREATE TABLE payment(pid VARCHAR(4) PRIMARY KEY,
					pdate DATE NOT NULL,
					rid VARCHAR(4),
					FOREIGN KEY (rid) REFERENCES reg_detail(rid) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE reg_detail(rid VARCHAR(4) PRIMARY KEY,
						bid VARCHAR(4),
						sid VARCHAR(4),
						reg_date DATE NOT NULL,
						FOREIGN KEY (bid) REFERENCES batch(bid) ON DELETE CASCADE ON UPDATE CASCADE,
						FOREIGN KEY (sid) REFERENCES student(sid) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE exam_detail(eid VARCHAR(4) PRIMARY KEY,
						edate DATE NOT NULL,
						marks INT(3) NOT NULL,
						rid VARCHAR(4),
						bid VARCHAR(4),
						FOREIGN KEY (rid) REFERENCES reg_detail(rid) ON DELETE CASCADE ON UPDATE CASCADE,
						FOREIGN KEY (bid) REFERENCES batch(bid) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE attendance(aid VARCHAR(4) PRIMARY KEY,
						rid VARCHAR(4),
						bid VARCHAR(4),
						FOREIGN KEY (rid) REFERENCES reg_detail(rid) ON DELETE CASCADE ON UPDATE CASCADE,
						FOREIGN KEY (bid) REFERENCES batch(bid) ON DELETE CASCADE ON UPDATE CASCADE);
						
CREATE TABLE users(username VARCHAR(16) PRIMARY KEY,
					password VARCHAR(12) NOT NULL,
					privilege VARCHAR(13) NOT NULL);
					
INSERT INTO users VALUES("Admin","admin","Administrator"),
						("User","user","User");
						

INSERT INTO teacher VALUES("T001","W.D.Perera","0712345678","0382245654","No. 40, Panadura");
						
INSERT INTO classes VALUES("C001","Mathematics","Sinhala","O/L","T001");

INSERT INTO batch VALUES("B001","H001",1000.00,2017,"C001");

INSERT INTO student VALUES("S001","Nimal","1996-04-03",year(curDate())-year(dob),"961670567V","0764356745","0783542223","No.70, Wadduwa");

INSERT INTO payment VALUES("P001",curDate());

INSERT INTO reg_detail VALUES("R001","B001","P001","S001",curDate());

INSERT INTO teacher VALUES("T002","S.K.Peiris","0711235672","0751236787","No.80,Panadura"),
						("T003","A.D.M.Perera","0766781666","0771112766","No.60,Nallura"),
						("T004","K.M.Silva","0772233678","0382267129","No.30,Wadduwa");

INSERT INTO classes VALUES("C002","Com.Maths(Theory)","Sinhala","A/L","T002"),
						("C003","Com.Maths(Paper)","Sinhala","A/L","T002"),
						("C004","Physics(Theory)","Sinhala","A/L","T003"),
						("C005","Physics(Paper)","Sinhala","A/L","T003"),
						("C006","ICT","English","A/L","T004");

INSERT INTO batch VALUES("B002","H001",1000.00,2017,"C002","Wednesday"),
						("B003","H001",1000.00,2017,"C002","Monday"),
						("B004","H002",1200.00,2016,"C003","Tuesday"),
						("B005","H002",800.00,2017,"C006","Monday");