package shopping.checkout.fakes;

import shopping.checkout.Product;
import shopping.deals.Deal;
import shopping.deals.Discount;
import shopping.deals.ProductCounter;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chuckie
 * Date: 06/11/12
 * Time: 18:29
 * To change this template use File | Settings | File Templates.
 */
public class FakeTPOTDeal extends Deal {

    private static Integer sn = 1231243;
    private static final String name = "TOPT Deal";
    private final ArrayList<Product> products;

    public FakeTPOTDeal(){
        super(sn);
        this.products = new ArrayList<Product>();
        Collections.sort(products,new PriceSort());
    }

    public void addProducts(Product p){
        products.add(p);
    }

    @Override
    public Discount enforce(HashMap<Product, Integer> scannedProducts) {
        //get all related products
        ArrayList<ProductCounter> prodlist = new ArrayList<ProductCounter>();
        int count = 0;
        for ( Product prod : products){
            Integer amount = scannedProducts.get(prod);
            count += amount == null? 0 : amount;
            if(amount!=null||amount!=0){
                prodlist.add(new ProductCounter(prod, amount));
            }
        }

        Iterator<ProductCounter> prod = prodlist.listIterator();
        ProductCounter current;
        if(count>0){
            //should check if prod is empty but its tutorial
//            Discount discount = new Discount();
            current = prod.next();
            int times = 0;
            BigDecimal amount = new BigDecimal((int)0);
            while(count>=3){
                count -= 3;
                //not elegant but whatever
                while(current.decreaseStep()){
                    current = prod.next();
                }
                times++;
                BigDecimal something = current.unitPrice();
                amount = amount.add(current.unitPrice());

            }
            return new Discount(name ,times,amount);

        }
        return null;
    }

    private class PriceSort implements Comparator<Product>{

        @Override
        public int compare(Product p1, Product p2) {
            return p1.unitPrice().compareTo(p2.unitPrice());
        }
    }

}
