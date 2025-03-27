package in.hefshine.agromart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.hefshine.agromart.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
