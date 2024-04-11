package hotel.reservation.internal;

import hotel.reservation.Reservation;
import hotel.reservation.Reservation.ReservationIdentifier;

import org.springframework.data.repository.CrudRepository;

public interface Reservations extends CrudRepository<Reservation, ReservationIdentifier> {

}
