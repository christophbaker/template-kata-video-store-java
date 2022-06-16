import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Video Store")
public class VideoStoreTests {
    private Customer customer;

    @BeforeEach
    protected void setUp ()  {
        customer = new Customer ("Fred");
    }

    @Test
    @DisplayName("correctly generates a single new release statement")
    public void singleNewReleaseStatement () {
        customer.addRental (new Rental (new Movie ("The Cell", Movie.NEW_RELEASE), 3));

        assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n", customer.statement ());
    }
    @Test
    @DisplayName("correctly generates a dual new release statement")
    public void dualNewReleaseStatement () {
        customer.addRental (new Rental (new Movie ("The Cell", Movie.NEW_RELEASE), 3));
        customer.addRental (new Rental (new Movie ("The Tigger Movie", Movie.NEW_RELEASE), 3));

        assertEquals ("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n", customer.statement ());
    }

    @Test
    @DisplayName("correctly generates a single children's statement")
    public void singleChildrensStatement () {
        customer.addRental (new Rental (new Movie ("The Tigger Movie", Movie.CHILDRENS), 3));

        assertEquals ("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n", customer.statement ());
    }

    @Test
    @DisplayName("correctly generates a dual children's statement")
    public void dualChildrensStatement () {
        customer.addRental (new Rental (new Movie ("The Tigger Movie", Movie.CHILDRENS), 3));
        customer.addRental (new Rental (new Movie ("Zootopia", Movie.CHILDRENS), 4));

        assertEquals ("Rental Record for Fred\n\tThe Tigger Movie\t1.5\n\tZootopia\t3.0\nYou owed 4.5\nYou earned 2 frequent renter points\n", customer.statement ());
    }

    @Test
    @DisplayName("correctly generates a multiple regular statement")
    public void multipleRegularStatement () {
        customer.addRental (new Rental (new Movie ("Plan 9 from Outer Space", Movie.REGULAR), 1));
        customer.addRental (new Rental (new Movie ("8 1/2", Movie.REGULAR), 2));
        customer.addRental (new Rental (new Movie ("Eraserhead", Movie.REGULAR), 3));

        assertEquals ("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n", customer.statement ());
    }
}
