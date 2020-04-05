package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.GuestRule;
import com.tabit.dcm2.entity.StayRule;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * for developing and local testing set ActiveProfile to mysql.
 * but dont commit it!
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AbstractDbTest {
    private static EntityManager entityManager;

    protected GuestRule guestRule = new GuestRule();
    protected StayRule stayRule = new StayRule();
    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(stayRule).around(guestRule);

    @Autowired
    public final void setEntityManager(EntityManagerFactory factory) {
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        guestRule.setEntityManager(entityManager);
        stayRule.setEntityManager(entityManager);
    }
}
