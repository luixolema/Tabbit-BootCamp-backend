package com.tabit.dcm2.service.impl;

import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.exception.ResourceNotFoundException;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.IStayRepo;
import com.tabit.dcm2.service.dto.RandomStayDto;
import com.tabit.dcm2.service.dto.StayDto;
import com.tabit.dcm2.testutils.ValueProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StayServiceTest {
    @Mock
    private IStayRepo stayRepo;

    @Mock
    private IGuestRepo guestRepo;

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
    public void updateStay_shall_call_only_save_stay() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();
        Stay randomStay = RandomStay.createRandomStay();
        Guest randomGuest = getGuestFromStayDto(randomStayDto);

        // when
        when(stayRepo.findById(randomStayDto.getStayDetails().getId())).thenReturn(Optional.of(randomStay));
        when(guestRepo.findById(randomStayDto.getGuestPersonalDetails().getId())).thenReturn(Optional.of(randomGuest));
        stayService.updateStay(randomStayDto);

        // then
        verify(stayRepo).save(randomStay);
        verify(guestRepo, times(0)).save(randomGuest);
    }

    @Test
    public void updateStay_shall_call_save_stay_and_guest() {
        // given
        StayDto randomStayDto = RandomStayDto.createRandomStayDto();
        Stay randomStay = RandomStay.createRandomStay();
        Guest randomGuest = RandomGuest.createRandomGuest();

        // when
        when(stayRepo.findById(randomStayDto.getStayDetails().getId())).thenReturn(Optional.of(randomStay));
        when(guestRepo.findById(randomStayDto.getGuestPersonalDetails().getId())).thenReturn(Optional.of(randomGuest));
        stayService.updateStay(randomStayDto);

        // then
        verify(stayRepo).save(randomStay);
        verify(guestRepo).save(randomGuest);
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
