package org.kkaddus.gallary.backend.repository;

import org.kkaddus.gallary.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    Member findByEmailAndPassword(String email, String password);
}
