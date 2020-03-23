package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.BoxRule;
import com.tabit.dcm2.entity.GuestRule;
import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractRepoDbTest {
    private static EntityManager entityManager;

    protected BoxRule boxRule = new BoxRule();
    protected GuestRule guestRule = new GuestRule();
    @Rule
    public RuleChain ruleChain = RuleChain.outerRule(guestRule).around(boxRule);

    @Autowired
    public final void setEntityManager(EntityManagerFactory factory) {
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        boxRule.setEntityManager(entityManager);
        guestRule.setEntityManager(entityManager);
    }
}
