package ru.project.notes.dao;

import java.util.ArrayList;
import java.util.List;

import ru.project.notes.models.WebNote;

public class WebNoteDAO {
	private static int NOTES_COUNT;
	private List<WebNote> notes;
	
	public WebNoteDAO() {
		notes = new ArrayList<WebNote>();
		
		notes.add(new WebNote(++NOTES_COUNT, "note-1"));//список заметок
		notes.add(new WebNote(++NOTES_COUNT, "note-2"));
		notes.add(new WebNote(++NOTES_COUNT, "note-3"));
		notes.add(new WebNote(++NOTES_COUNT, "note-4"));
		notes.add(new WebNote(++NOTES_COUNT, "note-5"));
	}
	
	public List<WebNote> index() {//индекс
		return notes;
	}
	
	public WebNote show(int id) {//поик
		return notes.stream().filter(n -> n.getId() == id).findAny().orElse(null);
	}
	
	public void save(WebNote webNote) {//сохранение
		webNote.setId(++NOTES_COUNT);
		notes.add(webNote);
	}
	
	public void update(int id, WebNote updatedNote) {//обновление
		WebNote toUpdateNote = show(id);
		toUpdateNote.setNote(updatedNote.getNote());
	}
	
	public void delete(int id) {//удаление
		notes.removeIf(n -> n.getId() == id);
	}

}

