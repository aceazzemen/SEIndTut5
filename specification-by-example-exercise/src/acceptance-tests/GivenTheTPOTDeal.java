import fit.ColumnFixture;
import fit.Parse;
import shopping.checkout.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: chuckie
 * Date: 06/11/12
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
public class GivenTheTPOTDeal extends ColumnFixture {
    private ArrayList<Product> products = new ArrayList<Product>();
    public String Product;

    @Override
    public void doRows(Parse rows) {
        super.doRows(rows);
    }

    @Override
    public void reset() throws Exception {
        products=null;
    }

    @Override
    public void execute() throws Exception {
        if (Product != null) {
            Product product = SystemUnderTest.productRange.productNamed(Product);
            SystemUnderTest.addTPOTProduct(product);
        }
    }


    @Override
    @SuppressWarnings("rawtypes")
    public Object parse(String s, Class type) throws Exception {
        if (type == BigDecimal.class) {
            return new BigDecimal(s);
        }
        else {
            return super.parse(s, type);
        }
    }
}
