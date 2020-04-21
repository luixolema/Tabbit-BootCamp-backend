package com.tabit.dcm2;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.*;
import com.tabit.dcm2.repository.IBoxManagementRepo;
import com.tabit.dcm2.repository.IGuestRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestdataImporter {

    @Autowired
    private IGuestRepo guestRepo;

    @Autowired
    private IBoxManagementRepo boxManagementRepo;

    /**
     * to import some testdata to the application just set ActiveProfile to mysql.
     * but dont commit it!
     * <p>
     * start the application first, the import the testdata
     */
    @Test
    public void import_testdata_for_application() {
        saveGuests();
        saveBoxNumbers();
    }

    private void saveGuests() {
        Guest guest = createGuestAntonioBanderas();
        Guest guest2 = createGuestAntonyHopkins();
        Guest guest3 = createGuestMonicaBellucci();
        Guest guest4 = createGuestKeanuReeves();
        Guest guest5 = createGuestAgentSmith();
        Guest guest6 = createGuestHarrisonFord();
        Guest guest7 = createGuestSeanConnery();
        Guest guest8 = createGuestClintEastwood();
        Guest guest9 = createGuestMelGibson();

        guestRepo.saveAll(ImmutableList.of(guest, guest2, guest3, guest4, guest5, guest6, guest7, guest8, guest9));
    }


    private void saveBoxNumbers() {
        BoxManagement boxManagement1 = new BoxManagement();
        boxManagement1.setBoxNumber("123AB");

        BoxManagement boxManagement2 = new BoxManagement();
        boxManagement2.setBoxNumber("666");

        BoxManagement boxManagement3 = new BoxManagement();
        boxManagement3.setBoxNumber("98765");

        BoxManagement boxManagement4 = new BoxManagement();
        boxManagement4.setBoxNumber("4567");

        boxManagementRepo.saveAll(ImmutableList.of(boxManagement1,boxManagement2,boxManagement3,boxManagement4));
    }

    private Guest createGuestAntonioBanderas() {
        LocalDate guestBirthDate = LocalDate.now().minusYears(59);
        String firstName = "Antonio";

        Stay stayVeryOld = RandomStay.createRandomStayWithoutId();
        stayVeryOld.setArriveDate(LocalDate.now().minusYears(15));
        stayVeryOld.setLeaveDate(LocalDate.now().minusYears(14));
        stayVeryOld.setCheckInDate(stayVeryOld.getArriveDate().plusDays(1));
        stayVeryOld.setCheckOutDate(stayVeryOld.getCheckInDate().plusDays(10));
        stayVeryOld.setBirthDate(guestBirthDate);
        stayVeryOld.setFirstName(firstName);
        stayVeryOld.setLastName("Montana");
        stayVeryOld.setBoxNumber("123456");
        stayVeryOld.setActive(false);

        Stay stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(7));
        stayOld.setLeaveDate(LocalDate.now().minusYears(6));
        stayOld.setCheckInDate(stayOld.getArriveDate().plusDays(1));
        stayOld.setCheckOutDate(stayOld.getCheckInDate().plusDays(10));
        stayOld.setBirthDate(guestBirthDate);
        stayOld.setFirstName(firstName);
        stayOld.setLastName("Montana");
        stayOld.setBoxNumber("12");
        stayOld.setActive(false);

        Stay stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(3));
        stayActual.setLeaveDate(LocalDate.now().plusDays(3));
        stayActual.setCheckInDate(stayActual.getArriveDate().plusDays(1));
        stayActual.setCheckOutDate(stayActual.getLeaveDate());
        stayActual.setBirthDate(guestBirthDate);
        stayActual.setFirstName(firstName);
        stayActual.setLastName("Banderas With Many Stays And Different Names");
        stayActual.setBoxNumber("123AB");
        stayActual.setPreBooking("Antonio Banderas made a pre-booking and save 19 Euro. Smart man.");
        stayActual.setActive(true);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName(firstName);
        guest.setLastName("Banderas With Many Stays And Different Names");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayVeryOld, stayOld, stayActual));

        return guest;
    }

    private Guest createGuestAntonyHopkins() {
        String firstName = "Anthony";
        LocalDate guestBirthDate = LocalDate.now().minusYears(45);

        Stay stayVeryOld = RandomStay.createRandomStayWithoutId();
        stayVeryOld.setArriveDate(LocalDate.now().minusYears(10));
        stayVeryOld.setLeaveDate(LocalDate.now().minusYears(9));
        stayVeryOld.setCheckInDate(stayVeryOld.getArriveDate().plusDays(1));
        stayVeryOld.setCheckOutDate(stayVeryOld.getCheckInDate().plusDays(10));
        stayVeryOld.setBirthDate(guestBirthDate);
        stayVeryOld.setFirstName(firstName);
        stayVeryOld.setLastName("Hopkins");
        stayVeryOld.setBoxNumber("6789");
        stayVeryOld.setActive(false);

        Stay stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        stayOld.setLeaveDate(LocalDate.now().minusYears(4));
        stayOld.setCheckInDate(stayOld.getArriveDate().plusDays(1));
        stayOld.setCheckOutDate(stayOld.getCheckInDate().plusDays(10));
        stayOld.setBirthDate(guestBirthDate);
        stayOld.setFirstName(firstName);
        stayOld.setLastName("Montana");
        stayOld.setBoxNumber("ABCD");
        stayOld.setPreBooking("Anthony Hopkins made a pre-booking and save 28 Euro. Smart man.");
        stayOld.setActive(false);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName(firstName);
        guest.setLastName("Hopkins With Stays And Different Names");
        guest.setCheckedin(false);
        guest.setStays(ImmutableList.of(stayVeryOld, stayOld));

        return guest;
    }

    private Guest createGuestMonicaBellucci() {
        LocalDate guestBirthDate = LocalDate.now().minusYears(54);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Monica");
        guest.setLastName("Bellucci No Stays");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestKeanuReeves() {
        LocalDate guestBirthDate = LocalDate.now().minusYears(55);

        Stay stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(8));
        stayActual.setLeaveDate(LocalDate.now().plusDays(8));
        stayActual.setCheckInDate(stayActual.getArriveDate().plusDays(1));
        stayActual.setCheckOutDate(stayActual.getLeaveDate());
        stayActual.setBirthDate(guestBirthDate);
        stayActual.setFirstName("Keanu");
        stayActual.setLastName("Reeves");
        stayActual.setBoxNumber("666");
        stayActual.setActive(true);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Keanu");
        guest.setLastName("Reeves");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayActual));
        return guest;
    }

    private Guest createGuestAgentSmith() {
        LocalDate guestBirthDate = LocalDate.now().minusYears(42);

        Stay stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(10));
        stayActual.setLeaveDate(LocalDate.now().plusDays(10));
        stayActual.setCheckInDate(stayActual.getArriveDate().plusDays(1));
        stayActual.setCheckOutDate(stayActual.getLeaveDate());
        stayActual.setBirthDate(guestBirthDate);
        stayActual.setFirstName("Agent");
        stayActual.setLastName("Smith");
        stayActual.setBoxNumber("98765");
        stayActual.setActive(true);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Agent");
        guest.setLastName("Smith Shows Name Always From Stay");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayActual));

        return guest;
    }

    private Guest createGuestHarrisonFord() {
        LocalDate guestBirthDate = LocalDate.now().minusYears(77);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Harrison");
        guest.setLastName("Ford No Stay");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestSeanConnery() {
        LocalDate guestBirthDate = LocalDate.now().minusYears(51);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Sean");
        guest.setLastName("Connery No Stay");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestClintEastwood() {
        LocalDate guestBirthDate = LocalDate.now().minusYears(89);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Clint");
        guest.setLastName("Eastwood No Stay");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestMelGibson() {
        LocalDate guestBirthDate = LocalDate.now().minusYears(52);

        Stay stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(6));
        stayOld.setLeaveDate(LocalDate.now().minusYears(4));
        stayOld.setCheckInDate(stayOld.getArriveDate().plusDays(1));
        stayOld.setCheckOutDate(stayOld.getCheckInDate().plusDays(20));
        stayOld.setBirthDate(guestBirthDate);
        stayOld.setFirstName("Mel");
        stayOld.setLastName("Gibson Same Names");
        stayOld.setBoxNumber("ZXY");
        stayOld.setActive(false);

        Stay stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(15));
        stayActual.setLeaveDate(LocalDate.now().plusDays(15));
        stayActual.setCheckInDate(stayActual.getArriveDate().plusDays(1));
        stayActual.setCheckOutDate(stayActual.getLeaveDate());
        stayActual.setBirthDate(guestBirthDate);
        stayActual.setFirstName("Mel");
        stayActual.setLastName("Gibson Same Names");
        stayActual.setBoxNumber("4567");
        stayActual.setActive(true);
        stayActual.setPreBooking("Mel Gibson made a pre-booking and save 20 Euro. Smart man.");

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Mel");
        guest.setLastName("Gibson Same Names");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayOld, stayActual));
        return guest;
    }


}
