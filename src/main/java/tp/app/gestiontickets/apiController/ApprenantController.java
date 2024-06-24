package tp.app.gestiontickets.apiController;

import org.springframework.web.bind.annotation.*;
import tp.app.gestiontickets.model.*;
import tp.app.gestiontickets.repositories.NotificationRepository;
import tp.app.gestiontickets.services.ApprenantService;
import tp.app.gestiontickets.services.BaseConnaissanceService;
import tp.app.gestiontickets.services.TicketReponseService;
import tp.app.gestiontickets.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/apprenant")
public class ApprenantController {
    private ApprenantService apprenantService;
    private TicketService ticketService;
    private NotificationRepository notificationRepository;
    private BaseConnaissanceService baseConnaissanceService;
    private TicketReponseService ticketReponseService;

    public ApprenantController(ApprenantService apprenantService, TicketReponseService ticketReponseService, TicketService ticketService, NotificationRepository notificationRepository, BaseConnaissanceService baseConnaissanceService) {
        this.apprenantService = apprenantService;
        this.ticketService = ticketService;
        this.notificationRepository = notificationRepository;
        this.baseConnaissanceService = baseConnaissanceService;
        this.ticketReponseService = ticketReponseService;
    }

    @GetMapping("/voirTickets")
    public List<Ticket> voirTickets() {
        return this.ticketService.voirTicketsNotPrevate();
    }

    @GetMapping("/voirTicketReponse/{id}")
    public List<TicketReponse> voirTicketReponse(@PathVariable long id) {
        Ticket ticket = this.ticketService.getTicket(id).orElseThrow(() -> new RuntimeException("Aucun ticket n'existe avec cet id !"));
        return this.ticketReponseService.voirTicketReponse(ticket);
    }

    @GetMapping("/voirSesTickets")
    public List<Ticket> voirSesTickets(@RequestBody Apprenant apprenant) {
        Apprenant aPprenant = this.apprenantService.getApprenantByEmail(apprenant.getEmail());
        System.out.println(aPprenant.getEmail());
        return ticketService.voirTicketCreerPar(aPprenant);
    }

    @GetMapping("/voirSesNotifications")
    public List<Notification> voirSesNotifications(@RequestBody Apprenant apprenant){
        return this.notificationRepository.findAllByApprenant(apprenant);
    }

    @GetMapping("/voirBaseConnaissance")
    public List<BaseConnaissance> voirBaseConnaissance(){
        return this.baseConnaissanceService.voirBaseConnaissance();
    }

    @PostMapping("/creerTicket")
    public Ticket creerTicket(@RequestBody Ticket ticket) {
        return this.ticketService.creerTikect(ticket);
    }

    @PutMapping("/modifierTicket/{id}")
    public Ticket modifierTicket(@PathVariable long id, @RequestBody Ticket ticket) {
        return this.ticketService.modifierTicket(id,ticket);
    }

    @DeleteMapping("/supprimerTicket/{id}")
    public void supprimerTicket(@PathVariable long id) {
        this.ticketService.supprimerTicket(id);
    }
}
