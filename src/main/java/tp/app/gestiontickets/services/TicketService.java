package tp.app.gestiontickets.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Apprenant;
import tp.app.gestiontickets.model.Formateur;
import tp.app.gestiontickets.model.Notification;
import tp.app.gestiontickets.model.Ticket;
import tp.app.gestiontickets.repositories.NotificationRepository;
import tp.app.gestiontickets.repositories.TicketRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private NotificationRepository notificationRepository;
    private JavaMailSender mailSender;

    public TicketService(TicketRepository ticketRepository, NotificationRepository notificationRepository, JavaMailSender mailSender) {
        this.ticketRepository = ticketRepository;
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
    }

    public Optional<Ticket> getTicket(long id) {
        return this.ticketRepository.findById(id);
    }

    public Ticket creerTikect(Ticket ticket){
        Notification notification = new Notification();
        ticket.setDate(new Date());
        Ticket ticket1 = this.ticketRepository.save(ticket);
        notification.setDate(new Date());
        notification.setTicket(ticket1);
        notification.setFormateur(ticket1.getFormateur());
        notification.setMessage("Un ticket vient d'etre soumis.");
        this.notificationRepository.save(notification);
        this.sendEmail(ticket.getFormateur().getEmail(), "Ticket", notification.getMessage());
        return ticket1;
    }

    public Ticket modifierTicket(long id, Ticket ticket) {
        Ticket tIcket = this.getTicket(id).get();
        System.out.println(tIcket);

        if (tIcket != null) {
            ticket.setId(tIcket.getId());
            return this.ticketRepository.save(ticket);
        }
        return null;
    }

    public void supprimerTicket(long id) {
        this.ticketRepository.deleteById(id);
    }

    public List<Ticket> voirTicketCreerPar(Apprenant apprenant){
        return this.ticketRepository.findAllByApprenant(apprenant);
    }

    public List<Ticket> voirTicketReponduPar(Formateur formateur) {
        return this.ticketRepository.findAllByFormateur(formateur);
    }

    public List<Ticket> voirTickets() {
        return this.ticketRepository.findAll();
    }

    public List<Ticket> voirTicketsNotPrevate() {
        return this.ticketRepository.findAllByIsPrivate(false);
    }

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("gestion_tickes@gmail.com");
        this.mailSender.send(message);
    }
}
