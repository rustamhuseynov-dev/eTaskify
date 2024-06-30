package az.atl.project.eTaskify.repository;

import az.atl.project.eTaskify.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
