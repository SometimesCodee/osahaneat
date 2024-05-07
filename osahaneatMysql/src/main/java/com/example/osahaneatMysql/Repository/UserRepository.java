package com.example.osahaneatMysql.Repository;

import com.example.osahaneatMysql.Entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    List<Users> findByUserNameAndPassword(String username, String password);
    List<Users> findByRolesRoleName(String roleName);
    Users findByUserName(String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM users u WHERE u.userName = ?1")
    void deleteByUserName(String username);

}
