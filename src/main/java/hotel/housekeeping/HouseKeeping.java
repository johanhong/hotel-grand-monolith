package hotel.housekeeping;

import hotel.reservation.Reservation;
import org.springframework.modulith.ApplicationModuleListener;

public class HouseKeeping {

    /**
     * Process reservation completed event.
     */
    @ApplicationModuleListener
    void updateStock(Reservation.ReservationCompleted order) {

    }
}
