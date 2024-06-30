package az.atl.project.eTaskify.service.serviceImpl;

import az.atl.project.eTaskify.dto.TaskDto;
import az.atl.project.eTaskify.entity.Task;
import az.atl.project.eTaskify.entity.User;
import az.atl.project.eTaskify.exception.OurException;
import az.atl.project.eTaskify.repository.TaskRepository;
import az.atl.project.eTaskify.service.TaskService;
import az.atl.project.eTaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Override
    public TaskDto taskTheUser(TaskDto taskDto, BindingResult br) {
        if (br.hasErrors()){
            throw new OurException(br,null);
        }
        User user = userService.findByUsername(taskDto.getUsername())
                .orElseThrow(()-> new OurException(null,"bele bir username tapilmadi"));

        Task task = new Task();
        task.setWhoWasGiven(taskDto.getUsername());
        System.out.println(taskDto.getDeadline());
        modelMapper.map(taskDto,task);
        taskRepository.save(task);
        //response
        System.out.println(task.getDeadline());

        return TaskDto.builder()
                .taskTitle(task.getTaskTitle())
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .username(task.getWhoWasGiven())
                .build();
    }
}
