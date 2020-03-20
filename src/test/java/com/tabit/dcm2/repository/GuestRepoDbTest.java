package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Box;
import com.tabit.dcm2.entity.Guest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("mysql")
@DataJpaTest
public class GuestRepoDbTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IGuestRepo guestRepo;

    @Test
    public void whenFindAll_thenReturnAll() {
        Box box1 = new Box();
        Box box2 = new Box();
        Guest guest1 = new Guest("Jack", "Black", box1, true);
        Guest guest2 = new Guest("Walter", "White", box2, true);
        entityManager.persist(guest1);
        entityManager.persist(guest2);
        entityManager.flush();
        List<Guest> guests = guestRepo.findAll();

        Assertions.assertThat(guests).hasSize(2);
    }

    @Test
    public void whenFindByCheckedIn_thenReturnListOfGuests() {
        Box box1 = new Box();
        Box box2 = new Box();
        Guest guest1 = new Guest("Jack", "Black", box1, true);
        Guest guest2 = new Guest("Walter", "White", box2, false);
        entityManager.persist(guest1);
        entityManager.persist(guest2);
        entityManager.flush();
        List<Guest> guests = guestRepo.findByCheckedInTrue();

        Assertions.assertThat(guests.get(0)).isEqualTo(guest1);
    }

}
