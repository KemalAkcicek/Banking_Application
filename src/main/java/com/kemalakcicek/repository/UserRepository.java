package com.kemalakcicek.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kemalakcicek.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM user WHERE first_name =:name", nativeQuery = true)
	List<User> getUserByName(@Param("name") String name);

	@Query(value = "SELECT * FROM user WHERE role_id =:id", nativeQuery = true)
	List<User> getUserByRole(@Param("id") Long roleId);

	Optional<User> findByeMail(String eMail);

}
