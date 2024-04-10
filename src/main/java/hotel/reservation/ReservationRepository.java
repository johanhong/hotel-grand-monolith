package hotel.reservation;

import hotel.reservation.Reservation.ReservationIdentifier;

import org.springframework.data.repository.CrudRepository;

interface ReservationRepository extends CrudRepository<Reservation, ReservationIdentifier> {

}
