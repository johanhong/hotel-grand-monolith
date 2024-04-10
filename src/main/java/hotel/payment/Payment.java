package hotel.payment;

import org.jmolecules.ddd.types.Identifier;
import java.util.UUID;

public class Payment {

    public record PaymentIdentifier(UUID id) implements Identifier {}
}
