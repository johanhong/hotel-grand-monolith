package hotel.guest;

import hotel.guest.Guest.GuestIdentifier;
import lombok.Getter;
import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

/**
 * Guest Aggregate Root.
 */
@Getter
public class Guest implements AggregateRoot<Guest, GuestIdentifier> {

	private final GuestIdentifier id;
	private final String name;
	private final String address;

	public Guest(String name, String address) {

		this.id = new GuestIdentifier(UUID.randomUUID());
		this.name = name;
		this.address = address;
	}

	public record GuestIdentifier(UUID id) implements Identifier {}

}
