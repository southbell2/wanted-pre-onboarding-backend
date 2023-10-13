wanted-pre-onboarding-backend
=====================

## 지원자 정보
- 이름 : 이종남
- 이메일 : southbell09@daum.net

## 기술 스택
- Java 17
- Spring Boot 3.1.4
- Spring Data JPA
- MySQL 8.1.0

## ERD
![ERD](/images/erd.png)
- 필요한 모델이 회사, 사용자, 채용공고, 지원내역이므로 각각을 테이블로 만들어줍니다.
- 필요한 연관관계를 외래 키로 설정해 줍니다. 이후에 조인으로 필요 데이터를 가지고 오게 됩니다.

## API
[POSTMAN을 이용한 API 문서](https://documenter.getpostman.com/view/17053301/2s9YR55E4L)

## 구현 과정
### 1. 채용공고 등록
#### 요구사항
![요구사항1](/images/req1.jpg)
#### 구현
- json 형식으로 채용공고 등록에 필요한 데이터를 받습니다.
- JPA를 사용하여 데이터를 데이터베이스에 저장합니다.

### 2. 채용공고 수정
#### 요구사항
![요구사항2](/images/req2.jpg)
#### 구현
- json 형식으로 채용공고 수정에 필요한 데이터를 받습니다.
- 회사 id를 제외한 모든 필드들이 대상이 됩니다.
- JPA를 사용하여 데이터를 데이터베이스에 업데이트합니다.

### 3. 채용공고 삭제
#### 요구사항
![요구사항3](/images/req3.jpg)
#### 구현
- /job-posting/{jobPostingId} 와 같이 Path Variable로 채용공고 id를 받습니다.
- JPA를 사용하여 해당 id의 데이터를 데이터베이스에서 삭제합니다.

### 4-1. 채용공고 목록 가져오기
#### 요구사항
![요구사항4-1](/images/req4-1.jpg)
#### 구현
- /job-posting?page=1 와 같이 쿼리 파라미터로 page에 관한 데이터를 받습니다.
- 가장 최신의 순서대로 채용공고를 5개씩 페이징해서 json 형식으로 반환합니다.
- job_posting 테이블의 id는 auto-increment를 사용해서 만들기 때문에 id의 내림차순으로 정렬하면 최신 데이터부터 정렬할 수 있습니다.
- 이렇게 정렬된 데이터를 MySQL의 offset, limit을 이용하여 페이징합니다. (JPA의 EntityManager를 사용)

### 4-2. 채용공고 검색
#### 요구사항
![요구사항4-2](/images/req4-2.jpg)
#### 구현
- /job-posting/search?page=1&keyword=java 와 같이 쿼리 파라미터로 page와 검색에 사용될 keyword 데이터를 받습니다.
- keyword로 검색 시 채용공고 상세 보기의 필드 중 한 곳이라도 keyword를 포함시 검색이 가능하게 합니다.
- job_posting 테이블과 company 테이블을 join해서 job_posting 컬럼과 company 컬럼 중 하나라도 keyword가 포함 될 시 데이터를 읽어옵니다.
- 검색어가 포함된 데이터를 최신 순서대로 5개씩 페이징해서 json 형식으로 반환합니다.
- 최신 순서대로 정렬하기 위해서 job_posting의 id로 내림차순 정렬합니다.
- JPQL의 FETCH JOIN과 LIKE로 데이터를 조인해서 해당 검색어에 맞는 데이터를 읽어옵니다.

### 5. 채용 상세 페이지 가져오기
#### 요구사항
![요구사항5](/images/req5.jpg)
#### 구현
- /job-posting/{jobPostingId} 와 같이 Path Variable로 채용공고 id를 받습니다.
- JPA로 채용공고 id의 데이터를 읽어서 json 형식으로 반환합니다.
- job_posting 테이블과 company 테이블을 조인해서 채용공고 id에 해당하는 데이터를 읽어옵니다.
- 읽어온 데이터에서 company_id로 해당 채용공고를 올린 회사의 id를 얻게됩니다.
- job_posting 테이블과 company 테이블을 조인해서 해당 회사 id에서 올린 다른 채용공고 id를 읽어옵니다.
- 이때 처음 Path Variable로 받은 채용공고 id는 제외합니다.
- 다른 채용공고 id를 리스트에 담아서 함께 반환합니다.

### 6. 채용공고 지원
#### 요구사항
![요구사항6](/images/req6.jpg)
#### 구현
- json 형식으로 채용공고 지원에 대한 데이터를 받습니다. 이때 memberId 와 jobPostingId를 받습니다.
- job_application 테이블은 id를 VARCHAR 형식으로 문자열 형식으로 저장합니다.
- id는 'memberId'x'jobPostingId' 와 같은 형식으로 만듭니다. ex) memberId=2, jobPostingId=3 => id='2x3'
- 위와 같이 id를 만들면 A라는 회원이 B라는 채용공고에 지원한 id는 유니크합니다. ex) '2x3'은 회원 id 2가 채용공고 id 3에 지원한 경우만 존재
- 데이터베이스의 primary key는 중복이 불가능하기 때문에 데이터베이스 레벨에서 사용자는 특정 채용공고에 한 번 밖에 지원이 불가능합니다.
- 객체에 id를 담아서 Spring Data JPA의 기본 save() 메서드를 사용할 시 문제가 생길 수 있기 때문에 따로 커스텀 메서드를 만들어 저장합니다.

## 테스트
- 서비스 레이어의 단위 테스트만 만들었습니다.
- Mockito를 사용해서 가짜 객체를 주입해 단위 테스트를 진행했습니다.
