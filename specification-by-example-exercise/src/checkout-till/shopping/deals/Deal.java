package shopping.deals;

import shopping.checkout.Product;
import shopping.deals.Discount;

import java.util.HashMap;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: chuckie
 * Date: 06/11/12
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class Deal {

    protected final Integer serialNo;

    public Deal(Integer serialNo){
        this.serialNo = serialNo;
    }

    public Integer getSerialNo(){
        return serialNo;
    }

    public abstract HashMap<Product,Discount> enforce (HashMap<Product,Integer> scannedProducts);

}
