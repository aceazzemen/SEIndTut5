package shopping.checkout.fakes;

import shopping.checkout.Product;
import shopping.deals.Deal;
import shopping.deals.Discount;
import shopping.deals.ProductCounter;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chuckie
 * Date: 06/11/12
 * Time: 18:29
 * To change this template use File | Settings | File Templates.
 */
public class FakeTPOTDeal extends Deal {

    private final ArrayList<Product> products;

    public FakeTPOTDeal(Integer serialNo, ArrayList<Product> products){
        super(serialNo);
        this.products = products;
        Collections.sort(products,new PriceSort());
    }

    @Override
    public HashMap<Product, Discount> enforce(HashMap<Product, Integer> scannedProducts) {
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
            double amount = 0;
            while(count>=3){
                count -= 3;
                //not elegant but whatever
                while(current.decreaseStep()){
                    current = prod.next();
                }
                times++;
                amount+= current.unitPrice();
            }

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
