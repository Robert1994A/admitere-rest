package ro.inf.ucv.admitere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.admitere.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	public int countByName(String name);

	public Role findByName(String name);

}
