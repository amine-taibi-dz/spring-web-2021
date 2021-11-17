package dz.acs.webflow.activation;

import java.util.List;

/**
 * A service interface for retrieving hotels and bookings from a backing repository. Also supports the ability to cancel
 * a booking.
 */
public interface ActivationService {

    boolean activateUser(UserMe userMe);

}
