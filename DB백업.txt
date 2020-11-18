CREATE TABLE MEMBER (
	m_seq NUMBER,
	m_id varchar2(10 char) NOT NULL PRIMARY KEY,
	m_password varchar2(16 char) NOT NULL,
	m_name varchar2(10 char) NOT NULL,
	m_location varchar2(60 char) NOT NULL,
	m_mail varchar2(40 char) NOT NULL,
	m_gender varchar2(2 char) NOT NULL,
	m_position varchar2(5 char) NOT NULL,
	m_introduce varchar2(1000 char) NOT NULL,
	m_image varchar2(200 char) DEFAULT 'admin',
	m_interests varchar2(15 char) NOT NULL,
	m_status varchar2(5 char) DEFAULT 'true',
	m_matching_count NUMBER DEFAULT 0,
	m_deadline DATE DEFAULT SYSDATE-1
);

CREATE SEQUENCE seq_member
INCREMENT BY 1
START WITH 1;


CREATE TABLE top_interests (
	top_id NUMBER PRIMARY KEY,
	top_name varchar2(15 char) NOT NULL 
);

CREATE SEQUENCE seq_top_interests
INCREMENT BY 1
START WITH 1;

CREATE TABLE sub_interests (
	sub_id NUMBER PRIMARY KEY,
	top_id NUMBER,
	sub_name varchar2(15 char) NOT NULL,
	CONSTRAINT fk_top_interests FOREIGN KEY (top_id) 
	REFERENCES top_interests(top_id)
);

CREATE SEQUENCE seq_sub_interests
INCREMENT BY 1
START WITH 1;

CREATE TABLE chat (
	m_id varchar2(10 char),
	chat_id NUMBER PRIMARY KEY,
	content varchar2(500 char),
	CONSTRAINT fk_m_id FOREIGN KEY (m_id) 
	REFERENCES member(m_id)
); 

CREATE SEQUENCE seq_chat
INCREMENT BY 1
START WITH 1;

INSERT INTO top_interests(top_id, top_name) VALUES(seq_top_interests.nextval, '프로그래밍');
INSERT INTO top_interests(top_id, top_name) VALUES(seq_top_interests.nextval, '운동');
INSERT INTO top_interests(top_id, top_name) VALUES(seq_top_interests.nextval, '레슨');
INSERT INTO top_interests(top_id, top_name) VALUES(seq_top_interests.nextval, '인테리어');
INSERT INTO top_interests(top_id, top_name) VALUES(seq_top_interests.nextval, '요리');
INSERT INTO top_interests(top_id, top_name) VALUES(seq_top_interests.nextval, '외국어');
INSERT INTO top_interests(top_id, top_name) VALUES(seq_top_interests.nextval, '교육');
INSERT INTO top_interests(top_id, top_name) VALUES(seq_top_interests.nextval, '뷰티');

INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,1,'C++');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,1,'C');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,1,'Java');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,1,'Python');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,1,'HTML');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,1,'JSP');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,1,'Django');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,1,'Node.JS');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,1,'Android');

INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,2,'요가');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,2,'필라테스');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,2,'헬스');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,2,'수영');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,2,'골프');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,2,'스키');

INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,3,'보컬');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,3,'드럼');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,3,'기타');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,3,'연기');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,3,'댄스');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,3,'미술');

INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,4,'실내');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,4,'페인트');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,4,'욕실');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,4,'타일');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,4,'도배');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,4,'조명');

INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,5,'한식');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,5,'중식');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,5,'일식');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,5,'양식');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,5,'퓨전');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,5,'제과제빵');

INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,6,'일본어');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,6,'중국어');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,6,'태국어');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,6,'러시아어');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,6,'이탈리아어');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,6,'독일어');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,6,'스페인어');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,6,'불어');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,6,'영어');

INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,7,'수학');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,7,'국어');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,7,'과학');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,7,'한국사');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,7,'한문');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,7,'토익');

INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,8,'메이크업');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,8,'미용');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,8,'네일아트');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,8,'특수분장');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,8,'웨딩');
INSERT INTO sub_interests(sub_id, top_id, sub_name) VALUES(seq_sub_interests.nextval,8,'문신');

