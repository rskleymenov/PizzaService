package roman.kleimenov.repository.pizzarepo;

import roman.kleimenov.domain.impl.Pizza;

public interface PizzaRepository {
	Pizza getPizzaByID(int id);
}
