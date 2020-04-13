package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.dto.GuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.RandomGuestPersonalDetailsDto;
import com.tabit.dcm2.service.dto.RandomStayDto;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.service.util.GuestMapper;
import com.tabit.dcm2.testutils.ValueProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
        when(stayRepo.findById(randomId)).thenThrow(ResourceNotFoundException.class);

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

    @Test
    public void updatePersonalDetails_shall_call_save_on_stay_repo() {
        //given
        Stay randomStay = RandomStay.createRandomStay();
        GuestPersonalDetailsDto randomGuestPersonalDetailsDto = RandomGuestPersonalDetailsDto.createRandomGuestPersonalDetailsDto();

        //when
        stayService.updatePersonalDetails(randomStay, randomGuestPersonalDetailsDto);

        //then
        verify(stayRepo).save(randomStay);
    }
}
