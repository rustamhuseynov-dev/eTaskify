package az.atl.project.eTaskify.service;

import az.atl.project.eTaskify.dto.TaskDto;
import org.springframework.validation.BindingResult;

public interface TaskService {
    TaskDto taskTheUser(TaskDto taskDto, BindingResult br);
}
