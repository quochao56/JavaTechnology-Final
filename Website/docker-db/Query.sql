DROP DATABASE if EXISTS MusicShop;
CREATE DATABASE MusicShop;
USE MusicShop;

CREATE TABLE users (
	userID VARCHAR(255) PRIMARY KEY,
	username NVARCHAR(100),
	pwd VARCHAR(255),
	ROLE VARCHAR(100)
);

CREATE TABLE singer (
	singerID VARCHAR(10) PRIMARY KEY,
	singerName NVARCHAR(255),
	singerDesciption LONGTEXT
);

CREATE TABLE genre (
	genreID VARCHAR(10) PRIMARY KEY,
	genreName NVARCHAR(100)
);

CREATE TABLE album (
	albumID VARCHAR(10) PRIMARY KEY,
	genreID VARCHAR(10),
	singerID VARCHAR(10),
	thumnails BLOB,
	numberOfSongs INT,
	albumDescription LONGTEXT,
	price FLOAT,
	
	FOREIGN KEY album(genreID) REFERENCES genre(genreID),
	FOREIGN KEY singer(singerID) REFERENCES singer(singerID)
);

CREATE TABLE song (
	songID VARCHAR(10) PRIMARY KEY,
	albumID VARCHAR(10),
	songName TEXT,
	duration INT,
	thumnail BLOB,
	songDescription LONGTEXT,
	
	FOREIGN KEY song(albumID) REFERENCES album(albumID)
);

CREATE TABLE receipt (
	receiptID VARCHAR(10) PRIMARY KEY,
	userID VARCHAR(255),
	total FLOAT,
	dateEstablised DATETIME DEFAULT NOW(),
	state VARCHAR(100),
	
	FOREIGN KEY receipt(userID) REFERENCES users(userID)
);

CREATE TABLE receiptLine(
	receiptID VARCHAR(10),
	albumID VARCHAR(10),
	quantity INT,
	price FLOAT,
	PRIMARY KEY (receiptID, albumID),
	FOREIGN KEY receiptLine(albumID) REFERENCES album(albumID),
	FOREIGN KEY receiptLine(receiptID) REFERENCES receipt(receiptID)
);