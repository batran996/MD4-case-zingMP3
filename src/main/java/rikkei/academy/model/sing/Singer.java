package rikkei.academy.model.sing;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "singers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3,max = 60)

    private String name;
    @NotBlank
    @Size(min = 3,max = 60)

    private String address;
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date birthday;
    private Boolean gender;
}
