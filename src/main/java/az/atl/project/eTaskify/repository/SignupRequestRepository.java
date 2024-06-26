package az.atl.project.eTaskify.repository;

import az.atl.project.eTaskify.entity.SignupRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignupRequestRepository extends JpaRepository<SignupRequest, Long> {
}
