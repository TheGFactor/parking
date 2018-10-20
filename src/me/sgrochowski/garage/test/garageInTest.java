package me.sgrochowski.garage.test;

import me.sgrochowski.garage.Tickets;
import org.junit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class garageInTest {
    @Test
    public void testNoTicketFound(){
        Tickets ticket = new Tickets();
        ticket.newTicket(6);
        Integer[] test = new Integer[]{-1,0,0,0};
        assertEquals(test, ticket.checkOut("ABC", 8), "This should return -1, as ABC is not a valid ticket number");

    }
}
