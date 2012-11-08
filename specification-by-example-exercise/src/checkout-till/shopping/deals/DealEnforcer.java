package shopping.deals;

import shopping.checkout.Product;

import java.util.ArrayList;
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

    public DealEnforcer(){
        activeDeals = new HashMap<Integer, Deal>();
    }

    public void addDeal(Deal deal){
        this.activeDeals.put(deal.getSerialNo(),deal);
    }

    public void removeDeal(Deal deal){
        this.activeDeals.remove(deal.getSerialNo());
    }

    public void clearAll(){
        this.activeDeals.clear();
    }

    public ArrayList<Discount> retrieveDiscounts(HashMap<Product, Integer> scannedProducts){
        ArrayList<Discount> discounts = new ArrayList<Discount>();
        Iterator<Deal> deals = activeDeals.values().iterator();
        while(deals.hasNext()){
            Discount dis = deals.next().enforce(scannedProducts);
            if (dis !=null){
                discounts.add(dis);
            }
        }
        return discounts;
    }


}
