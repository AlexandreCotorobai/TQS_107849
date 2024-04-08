package tqs.hw1.bustickets.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.bustickets.entities.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {
}
