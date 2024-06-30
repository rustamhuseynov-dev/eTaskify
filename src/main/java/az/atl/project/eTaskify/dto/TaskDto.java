package az.atl.project.eTaskify.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TaskDto {

    private String username;

    private String taskTitle;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime deadline;

    private String status;
}
