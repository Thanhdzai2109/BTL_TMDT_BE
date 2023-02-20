package org.sid.backendleDeal.dao;

import org.sid.backendleDeal.entities.Offres;
import org.sid.backendleDeal.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository  extends JpaRepository<Shop, Long> {
}
