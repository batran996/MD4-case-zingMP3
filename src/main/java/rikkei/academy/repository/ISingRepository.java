package rikkei.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rikkei.academy.model.sing.Singer;

public interface ISingRepository extends JpaRepository<Singer,Long> {
    
}
