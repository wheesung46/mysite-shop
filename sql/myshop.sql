--myshop
CREATE DATABASE myshop;

--User
CREATE TABLE users (
	 no		  INT AUTO_INCREMENT,
	 name	  VARCHAR(40) NOT NULL,
	 email	  VARCHAR(80) NOT NULL,
	 password VARCHAR(20) NOT NULL,
	 gender	  VARCHAR(10),
	 type	  VARCHAR(10) DEFAULT 'U',
	 PRIMARY KEY (no)
);

--Create Admin
insert into users 
 values(0, '관리자'
 	 , 'admin', '0000'
	 ,'male', 'A');
	 
--Product
CREATE TABLE products (
	 no		  INT AUTO_INCREMENT,
	 pname	  VARCHAR(200),
	 pcomment VARCHAR(4000),
	 price	  INT,
	 org_file_name VARCHAR(200),
	 save_file_name VARCHAR(200),
	 file_ext_name VARCHAR(10),
	 file_size INT,
	 user_no  INT,
	 PRIMARY KEY (no)
);

--Order
CREATE TABLE orders (
	 no		 INT AUTO_INCREMENT,
	 pname	 VARCHAR(200),
	 price	 INT,
	 reg_date TIMESTAMP,
	 user_no INT,
	 user_name VARCHAR(40),
	 PRIMARY KEY (no)
);

--QnA
CREATE TABLE qna (
	 no		  INT AUTO_INCREMENT,
	 title	  VARCHAR(200),
	 content  VARCHAR(4000),
	 reg_date TIMESTAMP,
	 user_no  INT,
	 chk	  INT DEFAULT 0,
	 PRIMARY KEY (no)
);