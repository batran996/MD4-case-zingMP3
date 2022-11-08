package rikkei.academy.controller.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.dto.request.SongDTO;
import rikkei.academy.dto.response.ResponseMessage;
import rikkei.academy.model.song.Category;
import rikkei.academy.model.song.Song;
import rikkei.academy.service.category.ICategoryService;
import rikkei.academy.service.song.ISongService;

import java.util.Optional;

@RestController
@RequestMapping("/api/song")
@CrossOrigin
public class SongController {
    @Autowired
    private ISongService songService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> findAllSong(@PageableDefault(size = (9))Pageable pageable){
        Page<Song> songs = songService.findAll(pageable);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createSong(
            @RequestBody
            SongDTO songDTO
    ){
        Song song = new Song();
        song.setName(songDTO.getName());
        song.setSong(songDTO.getSong());
        Long idCategory  = songDTO.getIdCategory();
        Optional<Category> category = categoryService.findById(idCategory);
        if (!category.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Category not found!"),HttpStatus.OK);
        }else {
            song.setCategory(category.get());
        }
        songService.save(song);
        return new ResponseEntity<>(new ResponseMessage("Create song success!"),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editSong(@PathVariable Long id, @RequestBody SongDTO songDTO){
        Optional<Song> songOptional = songService.findById(id);
        if (!songOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Song not found!"),HttpStatus.NOT_FOUND);
        }
        Song song = new Song();
        song.setId(id);
        song.setName(songDTO.getName());
        song.setSong(songDTO.getSong());
        Long idCategory = songDTO.getIdCategory();
        Optional<Category> category = categoryService.findById(idCategory);
        if (!category.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Category not found!"),HttpStatus.NOT_FOUND);
        }else {
            song.setCategory(category.get());
        }
        songService.save(song);
        return new ResponseEntity<>(new ResponseMessage("Edit song success!"),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> details(@PathVariable Long id){
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Song not found"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song.get(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
public ResponseEntity<?> deleteById(@PathVariable("id") Optional<Song> song){
        if (!song.isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Song not found"), HttpStatus.NOT_FOUND);
        }
        songService.deleteById(song.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete success!!"),HttpStatus.OK);
    }
    @GetMapping("search/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name){
        return new ResponseEntity<>(songService.findByNameContaining(name),HttpStatus.OK);
    }
    @GetMapping("search/page")
    public ResponseEntity<?> searchPageSong(@RequestParam String name, Pageable pageable){
        return new ResponseEntity<>(songService.findByNameContaining(name,pageable),HttpStatus.OK);
    }
}
