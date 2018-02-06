package ro.inf.ucv.admitere.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.User;

@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, String> {

	@RestResource
	List<User> findAll();

	@RestResource(path = "byUsernameOrEmail")
	Page<User> findByUsernameOrEmailAllIgnoreCaseContaining(@Param("username") String username,
			@Param("email") String email, Pageable pageable);

	@RestResource(path = "byUsername")
	User findByUsername(@Param("name") String name);

	@RestResource(path = "byEmail")
	User findByEmail(@Param("email") String email);

	@RestResource(exported = false)
	User findByRegisterToken(String registerToken);

	@RestResource(exported = false)
	User findByUsernameOrEmail(String username, String email);

	@RestResource(exported = false)
	User findByRecoverPaswordToken(String recoverToken);
}