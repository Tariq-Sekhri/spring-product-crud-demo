


CREATE TABLE sec_user (
  userId            BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  email             VARCHAR(75) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  enabled           BIT NOT NULL 
);

CREATE TABLE sec_role(
  roleId   BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
);
CREATE TABLE user_role
(
  id     BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);
ALTER TABLE user_role ADD CONSTRAINT user_role_fk1 UNIQUE (userId, roleId);
ALTER TABLE user_role   ADD CONSTRAINT user_role_fk2 FOREIGN KEY (userId)   REFERENCES sec_user (userId);
 
ALTER TABLE user_role   ADD CONSTRAINT user_role_fk3 FOREIGN KEY (roleId)   REFERENCES sec_role (roleId);
  
  
INSERT INTO sec_user (email, encryptedPassword, enabled)

VALUES ('user@user', '$2a$10$H7IXD5AU.AlH7WvWkUrUeeU9YhpWKEtBcXa33iuoOWYrUE/oTklxG', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('admin@admin', '$2a$10$RnRIbwQhsIuh/qdrN3IwO.JkJ0xXwXdUp76qDruHawNAJ9XR0rF3q', 1);

INSERT INTO sec_role (roleName) VALUES ('ROLE_USER');

INSERT INTO sec_role (roleName) VALUES ('ROLE_ADMIN');

INSERT INTO user_role (userId, roleId) VALUES (1, 1);

INSERT INTO user_role (userId, roleId) VALUES (2, 2);
