package az.atl.project.eTaskify.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TaskDto {

    @NotEmpty(message = "Boş qoymaq olmaz")
    private String username;

    @Size(min = 10,message = "Minimum 10 karakter olmalıdı")
    @Size(max = 100,message = "Maximum 100 karakter olmalıdı")
    private String taskTitle;

    @Size(min = 5,message = "Minimum 5 karakter olmalıdı")
    @Size(max = 100,message = "Maximum 100 karakter olmalıdı")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime deadline;

    @Size(max = 25,min = 1,message = "max 25 min 1 karakter olmalidi")
    private String status;
}
