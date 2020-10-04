package com.mamalimomen.controllers.utilities;

import com.mamalimomen.base.controllers.utilities.PersistenceUnit;
import com.mamalimomen.base.controllers.utilities.PersistenceUnitManager;
import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.services.impl.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AppManager {
    private static final List<EntityManager> emList = new ArrayList<>();
    private static final Map<Services, BaseService> serviceMapper = new HashMap<>();

    public static synchronized void startApp() {
        EntityManager em = PersistenceUnitManager.getEntityManager(PersistenceUnit.UNIT_ONE);
        serviceMapper.put(Services.ACCOUNT_SERVICE, new AccountServiceImpl(em));
        serviceMapper.put(Services.BANK_BRANCH_SERVICE, new BankBranchServiceImpl(em));
        serviceMapper.put(Services.CREDIT_CARD_SERVICE, new CreditCardServiceImpl(em));
        serviceMapper.put(Services.CUSTOMER_SERVICE, new CustomerServiceImpl(em));
        serviceMapper.put(Services.EMPLOYEE_SERVICE, new EmployeeServiceImpl(em));
        emList.add(em);
        MenuFactory.getMenu(null).routerMenu();
    }

    public static synchronized BaseService getService(Services service) {
        return serviceMapper.get(service);
    }

    public static synchronized void endApp() {
        for (EntityManager em : emList) {
            em.close();
        }
        PersistenceUnitManager.closeAllPersistenceProviders();
    }
}
