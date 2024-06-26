package az.atl.project.eTaskify.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "signup_request")
@Getter
@Setter
@ToString
public class SignupRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String messageToAdmin;

}
