package org.test.suiblogexample.sui.contract.repository;

import org.test.suiblogexample.sui.contract.SuiPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuiPackageRepository extends JpaRepository<SuiPackage, String> {
    
}
