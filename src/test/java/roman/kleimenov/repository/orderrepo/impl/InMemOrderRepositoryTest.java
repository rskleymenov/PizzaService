package roman.kleimenov.repository.orderrepo.impl;

import org.junit.Test;

public class InMemOrderRepositoryTest {

	@Test(expected = NullPointerException.class)
	public void saveOrderShoudRiseNullPointerException() {
		new InMemOrderRepository().saveOrder(null);
	}

}
