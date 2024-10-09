package org.rabbitx.rabbitbetest.repository.position;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository extends CrudRepository<MarketEntity, Long> {
    Optional<MarketEntity> findByMarketName(String name);
}
