package com.tabit.dcm2.repository;

import com.google.common.collect.ImmutableList;
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
        User user = RandomUser.createRandomUserWithoutId();
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
    }
}
