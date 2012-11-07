package shopping.deals;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: chuckie
 * Date: 06/11/12
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class Discount {
    private final Integer counts;
    private final BigDecimal amount;

    public Discount(Integer counts, BigDecimal amount){
        this.amount = amount;
        this.counts = counts;
    }

    public Integer getCounts(){
        return counts;
    }

    public BigDecimal getAmount(){
        return amount;
    }

}
