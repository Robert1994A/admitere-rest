package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.inf.ucv.admitere.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	Page<User> findByUsernameOrEmailAllIgnoreCaseContaining(@Param("username") String username,
			@Param("email") String email, Pageable pageable);

	User findByUsername(@Param("name") String name);

	User findByEmail(@Param("email") String email);

	User findByUsernameOrEmail(String username, String email);

	User findByRecoverPaswordToken(String recoverToken);

	User findByRegisterTokenAndEmail(String registerToken, String email);

	List<User> findAllById(List<String> list);
}