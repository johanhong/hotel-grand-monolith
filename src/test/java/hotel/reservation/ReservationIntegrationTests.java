package hotel.reservation;

import static org.assertj.core.api.Assertions.*;

import hotel.guest.Guest.GuestIdentifier;
import hotel.reservation.Reservation.ReservationCompleted;
import hotel.reservation.internal.Reservations;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.AssertablePublishedEvents;
import org.springframework.modulith.test.Scenario;

@ApplicationModuleTest
@RequiredArgsConstructor
class ReservationIntegrationTests {

	private final ReservationManagement orders;
	private final Reservations repository;

	@Test
	void bootstrapsOrderModule() {

	}

	@Test
	void persistsAndLoadsOrder() {

		var reference = repository.save(new Reservation(new GuestIdentifier(UUID.randomUUID())));
		var result = repository.findById(reference.getId());

		// Equal but not the same
		assertThat(result).hasValue(reference);
		assertThat(result).hasValueSatisfying(it -> assertThat(it).isNotSameAs(reference));
	}

	@Test
	void completionCausesEventPublished(AssertablePublishedEvents events) {

		var order = new Reservation(new GuestIdentifier(UUID.randomUUID()));

		orders.complete(order);

		assertThat(events).contains(ReservationCompleted.class)
				.matching(ReservationCompleted::id, order.getId());
	}

	@Test
	void completionCausesEventPublished(Scenario scenario) {

		var order = new Reservation(new GuestIdentifier(UUID.randomUUID()));

		scenario.stimulate(() -> orders.complete(order))
				.andWaitForEventOfType(ReservationCompleted.class)
				.matchingMappedValue(ReservationCompleted::id, order.getId())
				.toArrive();
	}
}
