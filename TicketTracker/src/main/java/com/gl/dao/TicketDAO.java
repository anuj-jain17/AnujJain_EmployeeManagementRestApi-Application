package com.gl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.bean.Ticket;

public interface TicketDAO extends JpaRepository<Ticket, Integer> {

}
