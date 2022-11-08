package rikkei.academy.model.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rikkei.academy.model.User;

import javax.persistence.*;

@Entity
@Table(name="categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCategory;

    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + nameCategory + '\'' +
                ", user=" + user +
                '}';
    }
}
