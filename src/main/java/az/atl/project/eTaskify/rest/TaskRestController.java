package az.atl.project.eTaskify.rest;

import az.atl.project.eTaskify.dto.TaskDto;
import az.atl.project.eTaskify.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/task")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    @PostMapping(path = "/give")
    public ResponseEntity<TaskDto> taskTheUser(@Valid @RequestBody TaskDto taskDto, BindingResult br){
        return new ResponseEntity<>(taskService.taskTheUser(taskDto,br), HttpStatus.OK);
    }
}
