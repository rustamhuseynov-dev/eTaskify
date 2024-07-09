package az.atl.project.eTaskify.repository;

import az.atl.project.eTaskify.entity.Authority;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Transactional
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Optional<Authority> findByUsername(String username);

    @Query(value = "delete from authorities where username=?1", nativeQuery = true)
    @Modifying
    void deleteByUsername(String username);
}
