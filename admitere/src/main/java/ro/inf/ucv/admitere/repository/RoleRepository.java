package ro.inf.ucv.admitere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Role;

@Repository
@RepositoryRestResource(path = "roles")
public interface RoleRepository extends JpaRepository<Role, Long> {

	public int countByName(String name);

	@RestResource(path = "name")
	public Role findByName(String name);

}
