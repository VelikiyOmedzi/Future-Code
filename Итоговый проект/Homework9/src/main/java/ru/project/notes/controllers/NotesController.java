package ru.project.notes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.project.notes.dao.WebNoteDAO;
import ru.project.notes.models.WebNote;

@Controller
@RequestMapping("/notes")
public class NotesController {//контролер наших заметок
	
	private final WebNoteDAO webNoteDAO;
	
	public NotesController(WebNoteDAO webNoteDAO) {//инициализация
		 this.webNoteDAO = webNoteDAO;
	}
	
	@GetMapping()
	public String index(Model model) {//возращает индекс
		model.addAttribute("notes", webNoteDAO.index());
		return "notes/index";
	}
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id,Model model) {//возвращает данные по id и model
		model.addAttribute("note", webNoteDAO.show(id));
		return "notes/show";
	}
	
	@GetMapping("/new")
	public String newNote(Model model) {//yjdfz pfvtnrf
		model.addAttribute("webNote",new WebNote());
		return "notes/new";
	}
	@PostMapping()
	public String create(@ModelAttribute("webNote") WebNote webNote) {//щздание новой заметки
		webNoteDAO.save(webNote);
		return "redirect:/notes";
	}

	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {//изменение заметки
		model.addAttribute("webNote",webNoteDAO.show(id));
		return "notes/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("webNote") WebNote webNote, @PathVariable("id") int id) {//обновление заметки
		webNoteDAO.update(id, webNote);
		return "redirect:/notes";
	}

	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {//удаление заметки
		webNoteDAO.delete(id);
		return "redirect:/notes";
	}

}
