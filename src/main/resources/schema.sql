CREATE TABLE IF NOT EXISTS member (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS company (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  country VARCHAR(100),
  region VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS job_posting (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  company_id BIGINT NOT NULL,
  job_position VARCHAR(255),
  compensation INT,
  tech_stack VARCHAR(255),
  job_description VARCHAR(255),
  FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS job_application (
  id VARCHAR(255) PRIMARY KEY,
  posting_id BIGINT NOT NULL,
  member_id BIGINT NOT NULL,
  FOREIGN KEY (posting_id) REFERENCES job_posting(id) ON DELETE CASCADE,
  FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE
);


