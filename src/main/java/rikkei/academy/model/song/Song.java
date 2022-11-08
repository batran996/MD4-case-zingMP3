package rikkei.academy.model.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rikkei.academy.model.User;
import rikkei.academy.model.song.Category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne
    private Category category;
    @Lob
    private String song;
    @ManyToOne
    private User user;

}
