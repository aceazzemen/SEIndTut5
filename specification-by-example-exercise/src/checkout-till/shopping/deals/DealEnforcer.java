package shopping.deals;

import shopping.checkout.Product;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: chuckie
 * Date: 06/11/12
 * Time: 17:54
 * To change this template use File | Settings | File Templates.
 */
public class DealEnforcer {

    private HashMap<Integer, Deal> activeDeals;

    public void addDeal(Deal deal){
        this.activeDeals.put(deal.getSerialNo(),deal);
    }

    public void removeDeal(Deal deal){
        this.activeDeals.remove(deal.getSerialNo());
    }

    public HashMap<Product, Discount> retrieveDiscounts(HashMap<Product, Integer> scannedProducts){
        HashMap<Product,Discount> discounts = new HashMap<Product,Discount>();
        Iterator<Deal> deals = activeDeals.values().iterator();
        while(deals.hasNext()){
            discounts.putAll(deals.next().enforce(scannedProducts));
        }
        return discounts;
    }


}
