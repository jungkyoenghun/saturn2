package com.saturn.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//DAO 기능 디비에 접근하려면 인터페이스가 필요하다
//어떤 클레스에 대한 리파지토리냐? 프라이머리 키가 뭐냐 롱
//데이터를 추가하고 꺼내오는것이 가능해 진다.
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

}
