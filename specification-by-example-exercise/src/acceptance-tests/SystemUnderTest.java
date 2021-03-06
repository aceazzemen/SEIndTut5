
import shopping.checkout.Checkout;
import shopping.checkout.Product;
import shopping.checkout.fakes.*;
import shopping.deals.Deal;

import java.util.ArrayList;


/*
 * Unfortunately, FIT forces you to use static (e.g. global) variables to
 * share information between fixtures.  This class holds the objects
 * that we are testing and those that we are using to support the tests
 * in static variables and defines some useful methods.
 */
public class SystemUnderTest {
	public static final FakeProductRange productRange = new FakeProductRange();
	public static final FakeLEDDisplay display = new FakeLEDDisplay();
	public static final FakePrinter printer = new FakePrinter();
    public static final FakeBeeper beeper = new FakeBeeper();
    public static final FakeTPOTDeal deal = new FakeTPOTDeal();

	public static final Checkout till = new Checkout(productRange, display, beeper, printer);
	
	public static void addTPOTProduct(Product p){
        deal.addProducts(p);
    }

    public static void finalisedDeal(){
        till.addDeal(deal);
    }

	public static void resetTill() {
		till.reset();
		display.clearDisplay();
		printer.clearOutput();
	}
}
