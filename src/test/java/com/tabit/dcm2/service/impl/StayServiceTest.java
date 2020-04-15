package com.tabit.dcm2.service.impl;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.BoxReservationException;
import com.tabit.dcm2.exception.GuestIllegalStateException;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.dto.RandomStayDto;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.service.util.GuestMapper;
import com.tabit.dcm2.testutils.ValueProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StayServiceTest {
    @Mock
    private IStayRepo stayRepo;
    @Mock
    private IGuestRepo guestRepo;
    @Mock
    private GuestMapper guestMapper;

    @InjectMocks
    private StayService stayService;

    private ValueProvider valueProvider = new ValueProvider();

    @Test
    public void findById_shall_return_stay_by_id() {
        // given
        Stay randomStay = RandomStay.createRandomStay();
        when(stayRepo.findById(randomStay.getId())).thenReturn(Optional.of(randomStay));

        // when
        Stay actualStay = stayService.findById(randomStay.getId());

        // then
        assertThat(actualStay).isEqualTo(randomStay);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findById_shall_throw_exception() {
        // given
        Long randomId = valueProvider.randomId();
        when(stayRepo.findById(randomId)).thenReturn(Optional.empty());

        // when
        stayService.findById(randomId);
    }

    @Test
    public void updateStay_shall_only_update_stay_if_personeldetails_dont_changed() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();
        Stay randomStay = RandomStay.createRandomStay();
        Guest guestWithNoChanges = getGuestFromStayDto(randomStayDto);
        randomStay.setGuest(guestWithNoChanges);

        when(stayRepo.findById(randomStayDto.getStayDetails().getId())).thenReturn(Optional.of(randomStay));

        // when
        stayService.updateStay(randomStayDto);

        // then
        verify(stayRepo).save(randomStay);
        verifyZeroInteractions(guestRepo, guestMapper);
    }

    @Test
    public void updateStay_shall_update_stay_and_guest_if_personeldetails_changed() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();
        Stay randomStay = RandomStay.createRandomStay();
        Guest guestWithChanges = RandomGuest.createRandomGuest();
        randomStay.setGuest(guestWithChanges);

        when(stayRepo.findById(randomStayDto.getStayDetails().getId())).thenReturn(Optional.of(randomStay));

        // when
        stayService.updateStay(randomStayDto);

        // then
        verify(stayRepo).save(randomStay);
        verify(guestMapper).mapPersonalDetailsFromDto(guestWithChanges, randomStayDto.getGuestPersonalDetails());
        verify(guestRepo).save(guestWithChanges);
    }

    @Test
    public void isBoxEmpty_shall_return_the_right_value() {
        // given
        Stay stay = RandomStay.createRandomStayWithoutIdGivenActiveState(true);
        when(stayRepo.getBoxNumbers()).thenReturn(ImmutableList.of(stay.getBoxNumber()));

        // when
        boolean resultTrue = stayService.isBoxFree(stay.getBoxNumber() + "Update");
        boolean resultFalse = stayService.isBoxFree(stay.getBoxNumber());

        // then
        assertThat(resultTrue).isTrue();
        assertThat(resultFalse).isFalse();
    }

    @Test
    public void addActiveStay_shall_create_new_active_stay_and_update_guest_personeldetails_and_set_checked_in_flag_true() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();
        Guest notCheckedInGuest = RandomGuest.createRandomGuest();
        notCheckedInGuest.setStays(new ArrayList<>());
        notCheckedInGuest.setCheckedin(false);

        when(guestRepo.findById(randomStayDto.getGuestPersonalDetails().getId())).thenReturn(Optional.of(notCheckedInGuest));

        // when
        stayService.addActiveStay(randomStayDto);

        // then
        verify(guestMapper).mapPersonalDetailsFromDto(notCheckedInGuest, randomStayDto.getGuestPersonalDetails());
        verify(guestRepo).save(notCheckedInGuest);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void addActiveStay_shall_throw_exception_for_not_existing_guest() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();

        when(guestRepo.findById(randomStayDto.getGuestPersonalDetails().getId())).thenReturn(Optional.empty());

        // when
        stayService.addActiveStay(randomStayDto);
    }

    @Test(expected = GuestIllegalStateException.class)
    public void addActiveStay_shall_throw_exception_for_adding_active_stay_for_checked_in_guest() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();
        Guest checkedInGuest = RandomGuest.createRandomGuest();
        checkedInGuest.setCheckedin(true);

        when(guestRepo.findById(randomStayDto.getGuestPersonalDetails().getId())).thenReturn(Optional.of(checkedInGuest));

        // when
        stayService.addActiveStay(randomStayDto);
    }

    @Test(expected = BoxReservationException.class)
    public void addActiveStay_shall_throw_exception_for_adding_active_stay_for_already_reserved_box() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();
        Guest guest = RandomGuest.createRandomGuest();
        guest.setCheckedin(false);

        when(guestRepo.findById(randomStayDto.getGuestPersonalDetails().getId())).thenReturn(Optional.of(guest));
        when(stayRepo.getBoxNumbers()).thenReturn(ImmutableList.of(randomStayDto.getStayDetails().getBoxNumber()));

        // when
        stayService.addActiveStay(randomStayDto);
    }

    private Guest getGuestFromStayDto(StayDto stayDto) {
        Guest guest = new Guest();
        guest.setId(stayDto.getGuestPersonalDetails().getId());
        guest.setFirstName(stayDto.getGuestPersonalDetails().getFirstName());
        guest.setLastName(stayDto.getGuestPersonalDetails().getLastName());
        guest.setBirthDate(stayDto.getGuestPersonalDetails().getBirthDate());
        guest.setNationality(stayDto.getGuestPersonalDetails().getNationality());
        guest.setCountry(stayDto.getGuestPersonalDetails().getCountry());
        guest.setCity(stayDto.getGuestPersonalDetails().getCity());
        guest.setPostcode(stayDto.getGuestPersonalDetails().getPostcode());
        guest.setStreet(stayDto.getGuestPersonalDetails().getStreet());
        guest.setEmail(stayDto.getGuestPersonalDetails().getEmail());
        guest.setPhone(stayDto.getGuestPersonalDetails().getPhone());
        guest.setPassportId(stayDto.getGuestPersonalDetails().getPassportId());

        return guest;
    }
}
