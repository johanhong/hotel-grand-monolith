package hotel.reservation;

import hotel.guest.Guest.GuestIdentifier;
import hotel.reservation.internal.Reservations;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.jmolecules.ddd.annotation.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class ReservationManagement {

	private final Reservations reservations;

	public Reservation create(GuestIdentifier guestIdentifier) {
		return new Reservation(guestIdentifier);
	}

	public Reservation complete(Reservation reservation) {
		return reservations.save(reservation.complete());
	}
}
