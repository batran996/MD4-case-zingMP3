package rikkei.academy.service.sing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rikkei.academy.model.sing.Singer;
import rikkei.academy.repository.ISingRepository;

import java.util.Optional;

@Service
@Transactional
public class SingServiceIMPL implements ISingService{
    @Autowired
    private ISingRepository singRepository;

    @Override
    public Page<Singer> findAll(Pageable pageable) {
        return singRepository.findAll(pageable);
    }

    @Override
    public Optional<Singer> findById(Long id) {
        return singRepository.findById(id);
    }

    @Override
    public Singer save(Singer singer) {
        return singRepository.save(singer);
    }

    @Override
    public void deleteById(Long id) {
        singRepository.deleteById(id);

    }
}
