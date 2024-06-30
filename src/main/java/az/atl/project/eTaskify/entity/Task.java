package az.atl.project.eTaskify.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskTitle;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime deadline;

    private String whoWasGiven;

    private String status;

}
