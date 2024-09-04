package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByIsDeleted(boolean isDeleted, Sort var1);

    Optional<User> findByUserNameAndIsDeleted(String username, boolean isDeleted);

    List<User> findAllByRoleDescriptionIgnoreCaseAndIsDeleted(String description, boolean isDeleted);

}
