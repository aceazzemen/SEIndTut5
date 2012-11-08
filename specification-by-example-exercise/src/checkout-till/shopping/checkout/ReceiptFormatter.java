package shopping.checkout;

import shopping.deals.Discount;

import java.math.BigDecimal;

public class ReceiptFormatter {
	private final Printer printer;

	public ReceiptFormatter(Printer printer) {
		this.printer = printer;
	}
	
	public void printReceiptLine(Product product, int count, BigDecimal lineTotal) {
		printer.print(count + " " + product.name() + " @ "
				+ product.unitPrice() + " each = " + lineTotal + "\n");
	}

    public void printDiscountLine(Discount discount) {
        printer.print(discount.getCounts() + " " + discount.getName() +
                " = -" + discount.getAmount() + "\n");
    }
	
	public void printTotalLine(BigDecimal total) {
		printer.print("Total = " + total + "\n");
	}
	
	public void endOfReceipt() {
		printer.feed();
	}
}