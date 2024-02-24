package com.project.wiki.repository;

import com.project.wiki.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**   SiteUser의 PK는 Long타입이라서 저기가 Long인거고, Answer와 Question은 PK가 Integer임.   */
public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByusername(String username);
    Optional<SiteUser> findByNickname(String nickname);

}
