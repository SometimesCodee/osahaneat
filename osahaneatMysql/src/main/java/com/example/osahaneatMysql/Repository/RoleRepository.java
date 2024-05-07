package com.example.osahaneatMysql.Repository;

import com.example.osahaneatMysql.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

}
