CREATE TABLE robot3 (
	id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	userID VARCHAR(20),
	packageID VARCHAR(20),
	robotID VARCHAR(20),
	dataaccess CHAR(1),
	

	filepath VARCHAR(100),
	CreatedDate VARCHAR(50),
	UpdatedDate VARCHAR(50),
		RobotCode TEXT,
	file BLOB,
	PRIMARY KEY (id)
            ) ENGINE=InnoDB;
