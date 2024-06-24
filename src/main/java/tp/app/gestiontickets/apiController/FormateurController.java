package tp.app.gestiontickets.apiController;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import tp.app.gestiontickets.model.*;
import tp.app.gestiontickets.repositories.NotificationRepository;
import tp.app.gestiontickets.repositories.TicketReponseRepository;
import tp.app.gestiontickets.services.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/formateur")
public class FormateurController {
    private TicketService ticketService;
    private FormateurService formateurService;
    private NotificationRepository notificationRepository;
    private EtatService etatService;
    private BaseConnaissanceService baseConnaissanceService;
    private TicketReponseRepository ticketReponseRepository;
    private JavaMailSender mailSender;
    private TicketReponseService tickitReponseService;

    public FormateurController(TicketService ticketService, TicketReponseService tickitReponseService, JavaMailSender mailSender, FormateurService formateurService, TicketReponseRepository ticketReponseRepository, NotificationRepository notificationRepository, EtatService etatService, BaseConnaissanceService baseConnaissanceService) {
        this.ticketService = ticketService;
        this.formateurService = formateurService;
        this.notificationRepository = notificationRepository;
        this.etatService = etatService;
        this.baseConnaissanceService = baseConnaissanceService;
        this.ticketReponseRepository = ticketReponseRepository;
        this.mailSender = mailSender;
        this.tickitReponseService =tickitReponseService;
    }

    @GetMapping("/voirTickets")
    public List<Ticket> voirTickets() {
        return this.ticketService.voirTickets();
    }

    @GetMapping("/voirSesTickets")
    public List<Ticket> voirSesTickets(@RequestBody Formateur formateur) {
        Formateur formateur1 = this.formateurService.recupererFormateurByEmail(formateur.getEmail());
        System.out.println(formateur1.getEmail());
        return ticketService.voirTicketReponduPar(formateur1);
    }

    @GetMapping("/voirSesNotifications")
    public List<Notification> voirSesNotifications(@RequestBody Formateur formateur){
        return this.notificationRepository.findAllByFormateur(formateur);
    }

    @GetMapping("/voirTicketReponse/{id}")
    public List<TicketReponse> voirTicketReponse(@PathVariable long id) {
        Ticket ticket = this.ticketService.getTicket(id).orElseThrow(() -> new RuntimeException("Aucun ticket n'existe avec cet id !"));
        return this.tickitReponseService.voirTicketReponse(ticket);
    }

    @GetMapping("/voirBaseConnaissance")
    public List<BaseConnaissance> voirBaseConnaissance(){
        return this.baseConnaissanceService.voirBaseConnaissance();
    }

    @PostMapping("/creerBaseConnaissance")
    public BaseConnaissance creerBaseConnaissance(@RequestBody BaseConnaissance baseConnaissance) {
        return this.baseConnaissanceService.creerBaseConnai(baseConnaissance);
    }

    @PutMapping("/modifierBaseConnaissance/{id}")
    public BaseConnaissance modifierBaseConnaissance(@PathVariable long id, @RequestBody BaseConnaissance baseConnaissance) {
        return this.baseConnaissanceService.modifierBaseConnai(id, baseConnaissance);
    }

    @PutMapping("/traiterTicket")
    public Ticket traiterTicket(@RequestParam long id, @RequestBody TicketReponse ticketReponse) {
        Ticket ticket = this.ticketService.getTicket(id).orElseThrow(() -> new RuntimeException("Aucun ticket n'existe avec cet id !"));
        System.out.println(ticket.toString());
        System.out.println(ticketReponse.toString());
        ticketReponse.setTicket(ticket);
        TicketReponse reponse = this.ticketReponseRepository.save(ticketReponse);

        System.out.println(ticket.toString());
        ticket.setEtat(this.etatService.getEtatparLibelle("terminé"));
        Ticket tIcket = this.ticketService.modifierTicket(ticket.getId(), ticket);
        if (tIcket != null) {
            Notification notification = new Notification();
            notification.setDate(new Date());
            notification.setTicket(tIcket);
            notification.setApprenant(tIcket.getApprenant());
            notification.setMessage("Votre ticket à été répondu.");
            this.notificationRepository.save(notification);
            this.sendEmail(ticket.getApprenant().getEmail(), "Ticket", notification.getMessage());
        }
        return tIcket;
    }

    @DeleteMapping("/supprimerBaseConnaissance/{id}")
    public void supprimerBaseConnaissance(@PathVariable long id) {
        this.baseConnaissanceService.supprimerBaseConnai(id);
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
