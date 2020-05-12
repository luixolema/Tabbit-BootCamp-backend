package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.DiveCenter;
import com.tabit.dcm2.entity.RandomDiveCenter;
import com.tabit.dcm2.entity.RandomUser;
import com.tabit.dcm2.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepoDbTest extends AbstractDbTest {
    @Autowired
    private IUserRepo userRepo;

    @Test
    public void findByLogin_shall_return_user_by_login() {
        DiveCenter diveCenter = RandomDiveCenter.createRandomDiveCenterWithoutId();
        diveCenterRule.persist(ImmutableList.of(diveCenter));

        User user = RandomUser.createRandomUserWithoutIdGivenDiveCenter(diveCenter);
        userRule.persist(ImmutableList.of(user));

        // when
        Optional<User> actualUser = userRepo.findByLogin(user.getLogin());

        // then
        assertThat(actualUser).isPresent();
        assertUser(actualUser.get(), user);
    }

    private void assertUser(User actualUser, User expectedUser) {
        assertThat(actualUser.getId()).isEqualTo(expectedUser.getId());
        assertThat(actualUser.getName()).isEqualTo(expectedUser.getName());
        assertThat(actualUser.getLogin()).isEqualTo(expectedUser.getLogin());
        assertThat(actualUser.getPassword()).isEqualTo(expectedUser.getPassword());

        assertThat(actualUser.getDiveCenter().getId()).isEqualTo(expectedUser.getDiveCenter().getId());
    }
}
