package es.upm.miw.controllers;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import es.upm.miw.documents.core.Ticket;
import es.upm.miw.dtos.ShoppingDto;
import es.upm.miw.dtos.TicketCreationInputDto;
import es.upm.miw.repositories.core.TicketRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class TicketControllerIT {

    @Autowired
    private TicketController ticketController;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testCreateTicket() {
        TicketCreationInputDto ticketCreationInputDto = new TicketCreationInputDto(new BigDecimal("1150"), new BigDecimal("0"),
                new BigDecimal("0"), new ArrayList<ShoppingDto>());
        ticketCreationInputDto.getShoppingCart()
                .add(new ShoppingDto("1", "various", new BigDecimal("100"), 1, new BigDecimal("50.00"), new BigDecimal("50"), false));
        ticketCreationInputDto.getShoppingCart()
                .add(new ShoppingDto("1", "various", new BigDecimal("100"), 2, new BigDecimal("50.00"), new BigDecimal("100"), true));
        ticketCreationInputDto.getShoppingCart().add(
                new ShoppingDto("article2", "descrip-a2", new BigDecimal("10"), 100, new BigDecimal("0"), new BigDecimal("1000"), true));
        this.ticketController.createTicket(ticketCreationInputDto);
        Ticket ticket1 = this.ticketRepository.findFirstByOrderByCreationDateDescIdDesc();
        this.ticketController.createTicket(ticketCreationInputDto);
        Ticket ticket2 = this.ticketRepository.findFirstByOrderByCreationDateDescIdDesc();

        assertEquals(ticket1.simpleId() + 1, ticket2.simpleId());
        
        this.ticketRepository.delete(ticket1);
        this.ticketRepository.delete(ticket2);
    }
}