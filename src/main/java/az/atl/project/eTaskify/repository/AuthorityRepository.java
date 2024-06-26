package az.atl.project.eTaskify.repository;

import az.atl.project.eTaskify.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
