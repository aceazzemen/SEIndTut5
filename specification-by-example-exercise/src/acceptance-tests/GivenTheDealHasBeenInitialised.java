import fit.Fixture;
import fit.Parse;


public class GivenTheDealHasBeenInitialised extends Fixture {
	@Override
	public void doTable(Parse p) {
        SystemUnderTest.finalisedDeal();
	}
}
