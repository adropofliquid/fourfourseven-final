package com.adropofliquid.fourfoursevenfinal;

import org.springframework.data.repository.CrudRepository;

public interface PersonelRepo extends CrudRepository<Personel, Integer> {

    boolean existsPersonelByPersonel(String personel);
}
