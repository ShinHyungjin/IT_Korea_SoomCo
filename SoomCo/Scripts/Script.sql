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
	m_top_interests varchar2(15 char) NOT NULL,
	m_sub_interests varchar2(15 char) NOT NULL,
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
	sub_img varchar2(25 char),
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

UPDATE sub_interests SET sub_img = 'c_plus2.png' WHERE sub_id = 1;
UPDATE sub_interests SET sub_img = 'c.png' WHERE sub_id = 2;
UPDATE sub_interests SET sub_img = 'java.png' WHERE sub_id = 3;
UPDATE sub_interests SET sub_img = 'py.png' WHERE sub_id = 4;
UPDATE sub_interests SET sub_img = 'html.png' WHERE sub_id = 5;
UPDATE sub_interests SET sub_img = 'jsp.png' WHERE sub_id = 6;
UPDATE sub_interests SET sub_img = 'react.png' WHERE sub_id = 7;
UPDATE sub_interests SET sub_img = 'nodejs.png' WHERE sub_id = 8;
UPDATE sub_interests SET sub_img = 'android.png' WHERE sub_id = 9;
UPDATE sub_interests SET sub_img = 'yoga.png' WHERE sub_id = 10;
UPDATE sub_interests SET sub_img = 'pilates.png' WHERE sub_id = 11;
UPDATE sub_interests SET sub_img = 'health.png' WHERE sub_id = 12;
UPDATE sub_interests SET sub_img = 'swim.png' WHERE sub_id = 13;
UPDATE sub_interests SET sub_img = 'golf.png' WHERE sub_id = 14;
UPDATE sub_interests SET sub_img = 'ski.png' WHERE sub_id = 15;
UPDATE sub_interests SET sub_img = 'vocal.png' WHERE sub_id = 16;
UPDATE sub_interests SET sub_img = 'drum.png' WHERE sub_id = 17;
UPDATE sub_interests SET sub_img = 'guitar.png' WHERE sub_id = 18;
UPDATE sub_interests SET sub_img = 'action.png' WHERE sub_id = 19;
UPDATE sub_interests SET sub_img = 'dance.png' WHERE sub_id = 20;
UPDATE sub_interests SET sub_img = 'art.png' WHERE sub_id = 21;
UPDATE sub_interests SET sub_img = 'interior.png' WHERE sub_id = 22;
UPDATE sub_interests SET sub_img = 'paint.png' WHERE sub_id = 23;
UPDATE sub_interests SET sub_img = 'bathroom.png' WHERE sub_id = 24;
UPDATE sub_interests SET sub_img = 'tile.png' WHERE sub_id = 25;
UPDATE sub_interests SET sub_img = 'wallpaper.png' WHERE sub_id = 26;
UPDATE sub_interests SET sub_img = 'light.png' WHERE sub_id = 27;
UPDATE sub_interests SET sub_img = 'korea.png' WHERE sub_id = 28;
UPDATE sub_interests SET sub_img = 'china.png' WHERE sub_id = 29;
UPDATE sub_interests SET sub_img = 'japanese.png' WHERE sub_id = 30;
UPDATE sub_interests SET sub_img = 'western.png' WHERE sub_id = 31;
UPDATE sub_interests SET sub_img = 'fusion.png' WHERE sub_id = 32;
UPDATE sub_interests SET sub_img = 'bread.png' WHERE sub_id = 33;
UPDATE sub_interests SET sub_img = 'japanes.png' WHERE sub_id = 34;
UPDATE sub_interests SET sub_img = 'Chinese.png' WHERE sub_id = 35;
UPDATE sub_interests SET sub_img = 'Thai.png' WHERE sub_id = 36;
UPDATE sub_interests SET sub_img = 'Russian.png' WHERE sub_id = 37;
UPDATE sub_interests SET sub_img = 'Italian.png' WHERE sub_id = 38;
UPDATE sub_interests SET sub_img = 'German.png' WHERE sub_id = 39;
UPDATE sub_interests SET sub_img = 'Spanish.png' WHERE sub_id = 40;
UPDATE sub_interests SET sub_img = 'French.png' WHERE sub_id = 41;
UPDATE sub_interests SET sub_img = 'English.png' WHERE sub_id = 42;
UPDATE sub_interests SET sub_img = 'Math.png' WHERE sub_id = 43;
UPDATE sub_interests SET sub_img = 'korean.png' WHERE sub_id = 44;
UPDATE sub_interests SET sub_img = 'Sience.png' WHERE sub_id = 45;
UPDATE sub_interests SET sub_img = 'Korean_history.png' WHERE sub_id = 46;
UPDATE sub_interests SET sub_img = 'Chinese_characters.png' WHERE sub_id = 47;
UPDATE sub_interests SET sub_img = 'Toeic.png' WHERE sub_id = 48;
UPDATE sub_interests SET sub_img = 'makeup.png' WHERE sub_id = 49;
UPDATE sub_interests SET sub_img = 'hair.png' WHERE sub_id = 50;
UPDATE sub_interests SET sub_img = 'nailart.png' WHERE sub_id = 51;
UPDATE sub_interests SET sub_img = 'special_makeup.png' WHERE sub_id = 52;
UPDATE sub_interests SET sub_img = 'wedding.png' WHERE sub_id = 53;
UPDATE sub_interests SET sub_img = 'tatoo.png' WHERE sub_id = 54;


INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello1', 1234, '홍문제', '전라남도', 'hello1@naver.com', '여자', '고수', '개발자는 나에게!', 'https://pbs.twimg.com/profile_images/962934262369419266/nTpN_f_a.jpg', 'Programming', 'Java', 'true', 'false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello4', '1234', '강동원', '서울특별시', 'hello4@naver.com', '남자', '고수', '전문적인 레슨이 가능합니다 ^^', 'https://img.sbs.co.kr/newsnet/etv/upload/2020/07/10/30000650075_16v9.jpg', 'Programming', 'Java', 'true', 'false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello5', '1234', '신형진', '전라남도', 'hello5@hanmail.net', '남자', '고수', '경력많음', 'https://upload.wikimedia.org/wikipedia/commons/4/41/Gong_Yoo_%28Sep_2016%29.png', 'Interior', 'Paint', 'true', 'false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello6', 1234, '박수진', '충청북도','hello6@naver.com', '여자', '고수','시간이 많음','https://pds.joins.com/news/component/htmlphoto_mmdata/202010/27/29ca3536-30b0-453c-8b44-01b0db640637.jpg','Beauty','Hair','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello7', 1234, '김선호', '강원도','hello7@naver.com', '남자','고수','보다 나은^^','https://img.sbs.co.kr/newsnet/etv/upload/2019/11/05/30000637295_700.jpg','Cooking','Thai','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello8', 1234, '강재호', '제주도','hello8@gmail.com', '남자','고수','친절한 상담 가능','https://newsimg.sedaily.com/2018/07/26/1S28H6RYJE_1.jpg','Education','Pliates','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello9', 1234, '장춘식', '제주도','hello9@naver.com', '여자','고수','언제나 연락주세요','https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/5JCSH36YQECK3PTGPQFPHBZOFQ.jpg','Exercise','Yoga','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello10', 1234, '심민희', '제주도','hello10@hanmail.net', '남자','고수','클릭클릭','https://dszw1qtcnsa5e.cloudfront.net/community/20200626/d3599a6a-c63d-4086-820a-f12b5cf2a7ad/unnamed%201.jpg','Lesson','Dance','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello11', 1234, '김희경', '제주도','hello11@hanmail.net', '여자','고수','1:1채팅해주세요','https://img.insight.co.kr/static/2020/04/17/700/zsxxna900o6m87ji4361.jpg','Programming','React.js','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello12', 1234, '조세민', '제주도','hello12@naver.com', '남자','고수','쉽게 가르쳐드립니다.','https://topclass.chosun.com/news_img/1511/1511_008.jpg','Interior','Wallpaper','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'soomco1', 1234, '황호성', '제주도', 'soomcozzang@gmail.com', '남자','초보','잘부탁드립니다!','https://newsimg.sedaily.com/2020/06/03/1Z3VUVZPI4_1.jpg','Education','Math','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello13', 1234, '홍다영', '제주도','hello13@gmail.com', '여자','고수','전문적인 수업 가능 ','https://cdn.ppomppu.co.kr/zboard/data3/2018/0617/20180617103709_oifdtccv.jpg','Language','English','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello14', 1234, '최민진', '제주도','hello14@nate.com', '여자','고수','편하게 연락주세요','https://img.insight.co.kr/static/2018/01/26/700/779f9cylfw1j82j39iny.jpg','Beauty','Wedding','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello15', 1234, '봉준호', '제주도','hello15@naver.com', '남자','고수','시간,날짜협의가능','https://topclass.chosun.com/news_img/2007/2007_054.jpg','Lesson','Action','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello16', 1234, '김강민', '제주도','hello16@gmail.com', '남자','고수','믿고 맡겨주세요','https://newsimg.hankookilbo.com/cms/articlerelease/2019/11/04/201911042323363605_1.jpg', 'Cooking','Bread','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'hello17', 1234, '반희정', '경상북도','hello17@gmail.com', '여자','초보','안녕하세요','https://i.pinimg.com/736x/5a/d1/78/5ad17876e2a5c1ef01686c79322b014d.jpg', 'Cooking','Bread','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'soomco2', 1234, '마리아', '경기도', 'a1@naver.com', '남자', '고수', '픽미 ^^', 'https://www.ntoday.co.kr/news/photo/201912/69742_41738_119.jpg', 'Programming', 'Java', 'true','false');

INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a3', 1234, '루리', '경기도','a3@naver.com', '남자','고수','연락주세요','https://www.topstarnews.net/news/photo/201905/626756_319682_3714.jpg','Programming','Java','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a4', 1234, '다날', '제주도','a4@naver.com', '남자','초보','화이팅 ^^','https://file.mk.co.kr/meet/neds/2020/06/image_readtop_2020_568063_15911586564225789.jpg','Programming','JSP','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a5', 1234, '스미스', '충청북도','a5@naver.com', '남자','초보','저에게로^^','https://cdn.imweb.me/upload/S201807055b3d6a044a57a/ba5e18ddde654.jpg','Programming','Python','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a6', 1234, '마틸다', '충청남도','a6@naver.com', '남자','초보','드루와요 ^^','https://www.polinews.co.kr/data/photos/20191146/art_15739747475246_dd10e3.jpg','Programming','C++','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a7', 1234, '제이슨', '경기도','a7@naver.com', '여자','초보','저에게로','https://www.newsworks.co.kr/news/photo/201908/386124_282763_1025.jpg','Programming','C++','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a8', 1234, '줄리아', '충청남도','a8@naver.com', '남자','고수','초고수입니다 ^^','https://cphoto.asiae.co.kr/listimglink/6/2017091815255116269_1.jpg','Programming','C','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a9', 1234, '매튜', '강원도','a9@naver.com', '남자','고수','상담신청 ^^','https://pds.joins.com//news/component/htmlphoto_mmdata/201712/01/8e93c780-59e8-41ee-b52f-7953e8c1d660.jpg','Programming','Python','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a10', 1234, '윌리엄', '강원도','a10@naver.com', '남자','고수','나라고!  ^^','https://dispatch.cdnser.be/wp-content/uploads/2018/07/20180726235019_b7ab6961b91916ff012989f72d24a0f4.jpg','Programming','Django','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a11', 1234, '테이', '강원도','a11@naver.com', '여자','고수','고수입니다.','https://newsimg.sedaily.com/2020/07/01/1Z54TOQS1B_1.jpg','Programming','Node.js','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a12', 1234, '벤틀리', '경기도','a12@naver.com', '여자','고수','매칭!!!','https://www.breaknews.com/imgdata/breaknews_com/201711/2017110635339053.jpg','Programming','HTML','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a13', 1234, '샘', '인천광역시','a13@naver.com', '남자','고수','누구보다 열심히 가르쳐드립니다. ^^','https://file.mk.co.kr/meet/neds/2019/08/image_readtop_2019_620499_15655786583861132.jpg','Programming','Android','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a14', 1234, '스눕독', '대구광역시','a14@naver.com', '남자','고수','쉽게 가르쳐드립니다.','https://newsimg.sedaily.com/2017/07/20/1OIJSD6Q8H_1.jpg','Programming','Android','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a16', 1234, '강준', '전라남도','a16@naver.com', '남자','초보','드루와','https://img.sbs.co.kr/newsnet/etv/upload/2015/04/24/30000474840_500.jpg','Programming','Node.Js','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a17', 1234, '강가', '경상남도','a17@naver.com', '남자','초보','초보환영!!!','https://dimg.donga.com/wps/NEWS/IMAGE/2018/11/07/92771074.2.jpg','Programming','JSP','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a18', 1234, '강나', '서울특별시','a18@naver.com', '남자','초보','화이팅해봐요!','https://img5.yna.co.kr/photo/cms/2019/08/10/38/PCM20190810000138505_P4.jpg','Programming','C','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a19', 1234, '강다', '경상남도','a19@naver.com', '남자','초보','화이팅 ^^','https://upload.wikimedia.org/wikipedia/commons/b/bf/Jessica_on_the_CLEO_Thailand_magazine_%28cropped%29.png','Exercise','요가','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'soomco3', 1234, '박현', '경상북도','a20@naver.com', '남자','고수','우왕 ^^','https://img.sbs.co.kr/newsnet/etv/upload/2018/04/11/30000598878_700.jpg','Exercise','필라테스','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a21', 1234, '이준', '제주도','a21@naver.com', '남자','초보','최고예요 ^^','https://www.topstarnews.net/news/photo/201908/660013_363287_5133.jpg','Exercise','헬스','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a22', 1234, '홍탁', '서울특별시','a22@naver.com', '남자','초보','헬로 ^^','https://img.hankyung.com/photo/201903/BF.19226642.1.jpg','Exercise','수영','true','false');
INSERT INTO member(m_seq, m_id, m_password, m_name, m_location, m_mail, m_gender, m_position, m_introduce, m_image, m_top_interests, m_sub_interests, m_status, m_matching) VALUES(seq_member.nextval, 'a23', 1234, '김졍', '강원도','a23@naver.com', '여자','초보','화이팅 ^^','https://pds.joins.com/news/component/htmlphoto_mmdata/201812/21/bb3efb31-b03d-4a19-8003-7d798c120475.jpg','Exercise','스키','true','false');
