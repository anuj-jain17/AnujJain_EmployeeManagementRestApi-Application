package com.gl.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.bean.Ticket;
import com.gl.dao.TicketDAO;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	TicketDAO ticketDao;

	@Override
	public List<Ticket> getAllTickets() {
		
		return ticketDao.findAll();
	}

	@Override
	public Optional<Ticket> getTicketById(int id) {
		
		return ticketDao.findById(id);
	}

	@Override
	public Ticket createTicket(Ticket ticket) {
		
		return ticketDao.save(ticket);
	}

	@Override
	public Ticket updateTicket(int id, Ticket ticket) {
		ticket.setId(id);
		return ticketDao.save(ticket);
	}

	@Override
	public void deleteTicket(int id) {
		ticketDao.deleteById(id);

	}

	@Override
	public List<Ticket> searchTickets(String keyword) {
		
		return ticketDao.findAll().stream()
				.filter(ticket->ticket.getTitle().toLowerCase().contains(keyword.toLowerCase())||
					ticket.getDescription().toLowerCase().contains(keyword.toLowerCase()))
				.collect(Collectors.toList());
	}

}
