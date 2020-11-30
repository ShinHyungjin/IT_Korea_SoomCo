package com.soomco.member;

import java.util.Date;

public class SoomcoMember {
	private int seq;
	private String id;
	private String password;
	private String name;
	private String location;
	private String mail;
	private String gender;
	private String position;
	private String introduce;
	private String image;
	private String top_interests;
	private String sub_interests;
	private String status;
	private int matching_count;
	private String deadline;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getTop_interests() {
		return top_interests;
	}
	public void setTop_interests(String top_interests) {
		this.top_interests = top_interests;
	}
	public String getSub_interests() {
		return sub_interests;
	}
	public void setSub_interests(String sub_interests) {
		this.sub_interests = sub_interests;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMatching_count() {
		return matching_count;
	}
	public void setMatching_count(int matching_count) {
		this.matching_count = matching_count;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
}
