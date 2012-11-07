package shopping.deals;

import shopping.checkout.Product;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Chuckie
 * Date: 06/11/12
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class ProductCounter extends Product{

    private int number;

    public ProductCounter(String name, String barcode, BigDecimal unitPrice) {
        super(name, barcode, unitPrice);
    }

    public ProductCounter(Product prod, int number){
        super(prod.name(),prod.barcode(),prod.unitPrice());
        this.number = number;
    }

    public void changeAmount(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    public boolean decreaseStep(){
        number--;
        return number<0;
    }

}
