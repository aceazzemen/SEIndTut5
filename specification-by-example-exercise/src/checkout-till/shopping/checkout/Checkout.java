package shopping.checkout;

import shopping.deals.Deal;
import shopping.deals.DealEnforcer;
import shopping.deals.Discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * The central logic of the checkout till.
 */
public class Checkout implements BarcodeScanListener {
	private final ProductRange productRange;
	private final ReceiptFormatter printer;
	private final CustomerInformationDisplay display;
    private final DealEnforcer dealEnforcer;
	
	private final LinkedHashMap<Product, Integer> scannedProducts = new LinkedHashMap<Product, Integer>();

	public Checkout(ProductRange productRange, LEDDisplay display, Beeper beeper, Printer printer) {
		this.productRange = productRange;
		this.printer = new ReceiptFormatter(printer);
		this.display = new CustomerInformationDisplay(display, beeper);
        this.dealEnforcer = new DealEnforcer();
	}
	
	public void reset() {
		scannedProducts.clear();
        dealEnforcer.clearAll();
	}

    public void addDeal(Deal deal){
       dealEnforcer.addDeal(deal);
    }
	
	public void barcodeScanned(String barcode) {
		Product product;
		try {
			product = productRange.productWithBarcode(barcode);
			scannedProducts.put( product, unitsScanned(product) + 1);
			display.displayRunningTotal(runningTotal());
		} catch (ProductNotFoundException e) {
			display.reportError("unknown product");
		}
	}
	
	private BigDecimal runningTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (Product product : scannedProducts.keySet()) {
			int count = unitsScanned(product);
			BigDecimal lineTotal = product.priceOf(count);
			
			total = total.add(lineTotal);
		}
		
		return total;
	}
	
	private int unitsScanned(Product product) {
		if (scannedProducts.containsKey(product)) {
			return scannedProducts.get(product);
		} else {
			return 0;
		}
	}

	public void paymentAccepted() {
		BigDecimal total = BigDecimal.ZERO;

		for (Product product : scannedProducts.keySet()) {
			int count = unitsScanned(product);
			BigDecimal lineTotal = product.priceOf(count);

			total = total.add(lineTotal);
			
			printer.printReceiptLine(product, count, lineTotal);
		}
        ArrayList<Discount> discounts = dealEnforcer.retrieveDiscounts(scannedProducts);
        for (Discount discount : discounts){
            total = total.subtract(discount.getAmount());
            printer.printDiscountLine(discount);
        }
		printer.printTotalLine(total);
		printer.endOfReceipt();
	}
}
