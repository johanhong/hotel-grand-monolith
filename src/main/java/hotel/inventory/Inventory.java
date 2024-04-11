package hotel.inventory;

import hotel.reservation.Reservation.ReservationCompleted;
import lombok.RequiredArgsConstructor;
import org.jmolecules.ddd.annotation.Service;
import org.springframework.modulith.ApplicationModuleListener;

@Service
@RequiredArgsConstructor
public class Inventory {

	private final Inventories repository;

	/**
	 * Process reservation completed event.
	 */
	@ApplicationModuleListener
	void processReservationCompleted(ReservationCompleted order) {

	}
}
