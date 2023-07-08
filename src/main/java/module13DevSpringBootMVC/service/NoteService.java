package module13DevSpringBootMVC.service;

import module13DevSpringBootMVC.entity.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private static final Map<Long, Note> notes = new HashMap<>();

    static {
        notes.put(1L,Note.builder().id(1).title("Title1").content("Client1").build());
        notes.put(2L,Note.builder().id(2).title("Title2").content("Client2").build());
        notes.put(3L,Note.builder().id(3).title("Title3").content("Client3").build());
        notes.put(4L,Note.builder().id(4).title("Title4").content("Client4").build());
        notes.put(5L,Note.builder().id(5).title("Title5").content("Client5").build());
    }

    //метод повертає список всіх нотаток
    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    //метод додає нову нотатку
    public Note add(Note note) {
        if (notes.isEmpty()) {
            note.setId(1L);
        } else {
            note.setId(notes.keySet().stream().max(Long::compareTo).orElse(0L) + 1);
        }
        return notes.put(note.getId(), note);
    }
    //видаляє нотатку з вказаним ідентифікатором.
    public void deleteById(long id) {
        if (notes.containsKey(id)) {
            notes.remove(id);
        } else {
            throw new NoSuchElementException();
        }
    }
    //шукає нотатку по note.id
    public void update(Note note) {
        if (notes.containsKey(note.getId())) {
            notes.put(note.getId(), note);
        } else {
            throw new NoSuchElementException();
        }
    }

    public Note getById(long id) {
        return Optional.ofNullable(notes.get(id))
                .orElseThrow(NoSuchElementException::new);
    }
}
