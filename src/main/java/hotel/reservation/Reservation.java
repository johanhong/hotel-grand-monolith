package hotel.reservation;

import hotel.guest.Guest;
import hotel.guest.Guest.GuestIdentifier;
import hotel.reservation.Reservation.LineItem.LineItemId;
import hotel.reservation.Reservation.ReservationIdentifier;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Association;
import org.jmolecules.ddd.types.Entity;
import org.jmolecules.ddd.types.Identifier;
import org.jmolecules.event.types.DomainEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Table(name = "reservation")
public class Reservation extends AbstractAggregateRoot<Reservation> implements AggregateRoot<Reservation, ReservationIdentifier> {

	private final ReservationIdentifier id;
	private final Association<Guest, GuestIdentifier> customer;
	private Status status;

	private final List<LineItem> lineItems = new ArrayList<>();

	public Reservation(GuestIdentifier customerId) {

		this.id = new ReservationIdentifier(UUID.randomUUID());
		this.status = Status.OPEN;
		this.customer = Association.forId(customerId);
	}

	Reservation complete() {

		this.status = Status.COMPLETED;

		registerEvent(new ReservationCompleted(id));

		return this;
	}

	Reservation add(LineItem item) {

		this.lineItems.add(item);

		return this;
	}

	public record ReservationIdentifier(UUID id) implements Identifier {}

	public record ReservationCompleted(ReservationIdentifier id) implements DomainEvent {}

	enum Status {
		OPEN, COMPLETED, CANCELLED;
	}

	@Getter
	static class LineItem implements Entity<Reservation, LineItemId> {

		private LineItemId id;
		private String description;
		private long amount;

		LineItem(String description, long amount) {

			this.id = new LineItemId(UUID.randomUUID());
			this.description = description;
			this.amount = amount;
		}

		record LineItemId(UUID id) implements Identifier {}
	}
}
