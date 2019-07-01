package com.media.restaurant.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.media.restaurant.domain.ItemsType;

@Repository
public interface ItemsTypeRepository extends JpaRepository<ItemsType, Long>{

  List<ItemsType> findAllByActiveFlag(String activeFlag);

}
