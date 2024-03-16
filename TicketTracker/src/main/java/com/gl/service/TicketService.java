package com.gl.service;

import java.util.List;
import java.util.Optional;

import com.gl.bean.Ticket;

public interface TicketService {
	
	public List<Ticket> getAllTickets();
	public Optional<Ticket> getTicketById(int id);
	public Ticket createTicket(Ticket ticket);
	public Ticket updateTicket(int id, Ticket ticket);
	public void deleteTicket(int id);
	public List<Ticket> searchTickets(String keyword);

}
