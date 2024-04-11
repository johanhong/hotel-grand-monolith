package hotel.reservation.internal;

import hotel.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final Reservations reservations;

    @GetMapping("/reservations")
    public Iterable<Reservation> getAll() {
        return reservations.findAll();
    }
}
