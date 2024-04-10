package hotel.reservation;

import static org.assertj.core.api.Assertions.*;

import hotel.guest.Guest.GuestIdentifier;
import hotel.reservation.EventPublicationRegistryTests.FailingAsyncTransactionalEventListener;
import hotel.reservation.Reservation.ReservationCompleted;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.modulith.events.core.EventPublicationRegistry;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.annotation.DirtiesContext;

@ApplicationModuleTest
@RequiredArgsConstructor
@Import(FailingAsyncTransactionalEventListener.class)
@DirtiesContext
class EventPublicationRegistryTests {

	private final ReservationManagement orders;
	private final EventPublicationRegistry registry;
	private final FailingAsyncTransactionalEventListener listener;

	@Test
	void leavesPublicationIncompleteForFailingListener(Scenario scenario) throws Exception {

		var order = new Reservation(new GuestIdentifier(UUID.randomUUID()));

		scenario.stimulate(() -> orders.complete(order))
				.andWaitForStateChange(() -> listener.getEx())
				.andVerify(__ -> {
					assertThat(registry.findIncompletePublications()).hasSize(1);
				});
	}

	static class FailingAsyncTransactionalEventListener {

		@Getter Exception ex;

		@ApplicationModuleListener
		void on(ReservationCompleted event) throws Exception {

			var exception = new IllegalStateException();

			try {

				throw exception;

			} finally {
				this.ex = exception;
			}
		}
	}
}
