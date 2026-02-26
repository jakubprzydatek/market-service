package pl.tradingplatfrom.marketservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tradingplatfrom.marketservice.model.StockPrice;

import java.util.List;

public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

    List<StockPrice> findByTicker(String ticker);

}
