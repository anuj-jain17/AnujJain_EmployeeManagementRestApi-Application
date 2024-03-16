package com.gl.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.bean.Ticket;
import com.gl.service.TicketService;

@Controller
public class TicketController {

	@Autowired
	TicketService ticketService;

	@GetMapping("/")
	public String getAllTickets(Model model) {
		List<Ticket> tickets = ticketService.getAllTickets();
		model.addAttribute("tickets", tickets);
		return "ticket-list";
	}

	@GetMapping("/ticket/{id}")
	public String getTicketById(@PathVariable int id, Model model) {
		Optional<Ticket> ticket = ticketService.getTicketById(id);
		ticket.ifPresent(value -> model.addAttribute("ticket", value));
		return "ticket-view";
	}

	@GetMapping("/create")
	public String showCreateForm(Model model) {
		model.addAttribute("ticket", new Ticket());
		return "ticket-create";
	}

	@PostMapping("/create")
	public String createTicket(@ModelAttribute Ticket ticket) {
		ticket.setDate(new Date());
		ticketService.createTicket(ticket);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable int id, Model model) {
		Optional<Ticket> ticket = ticketService.getTicketById(id);
		ticket.ifPresent(value -> model.addAttribute("ticket", value));
		return "ticket-edit";
	}

	@PostMapping("/edit/{id}")
	public String editTicket(@PathVariable int id, @ModelAttribute Ticket ticket) {
		ticketService.updateTicket(id, ticket);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteTicket(@PathVariable int id) {
		ticketService.deleteTicket(id);
		return "redirect:/";
	}

	@GetMapping("/search")
	public String searchTickets(@RequestParam String keyword, Model model) {
		List<Ticket> tickets = ticketService.searchTickets(keyword);
		model.addAttribute("tickets", tickets);
		return "ticket-list";
	}

}
