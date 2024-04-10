package hotel.housekeeping;

import hotel.reservation.Reservation;
import org.springframework.modulith.ApplicationModuleListener;

public class HouseKeeping {

    /**
     * Process reservation completed event, to ensure housekeeping staff are prepared for incoming guests
     */
    @ApplicationModuleListener
    void processReservation(Reservation.ReservationCompleted reservation) {
        // pre-plan schedule, etc
    }
}
