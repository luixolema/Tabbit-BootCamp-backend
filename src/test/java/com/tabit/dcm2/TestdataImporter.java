package com.tabit.dcm2;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.Guest;
import com.tabit.dcm2.entity.RandomGuest;
import com.tabit.dcm2.entity.RandomStay;
import com.tabit.dcm2.entity.Stay;
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

    /**
     * to import some testdata to the application just set ActiveProfile to mysql.
     * but dont commit it!
     * <p>
     * start the application first, the import the testdata
     */
    @Test
    public void import_testdata_for_application() {
        saveGuests();
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

    private Guest createGuestAntonioBanderas() {
        String firstName = "Antonio";

        Stay stayVeryOld = RandomStay.createRandomStayWithoutId();
        stayVeryOld.setArriveDate(LocalDate.now().minusYears(15));
        stayVeryOld.setLeaveDate(LocalDate.now().minusYears(14));
        stayVeryOld.setFirstName(firstName);
        stayVeryOld.setLastName("Montana");
        stayVeryOld.setBoxNumber("123456");

        Stay stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(7));
        stayOld.setLeaveDate(LocalDate.now().minusYears(6));
        stayOld.setFirstName(firstName);
        stayOld.setLastName("Montana");
        stayOld.setBoxNumber("12");

        Stay stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(3));
        stayActual.setLeaveDate(LocalDate.now().plusDays(3));
        stayActual.setFirstName(firstName);
        stayActual.setLastName("Banderas With Many Stays And Different Names");
        stayActual.setBoxNumber("123AB");
        stayActual.setPreBooking("Antonio Banderas made a pre-booking and save 19 Euro. Smart man.");
        stayActual.setActive(true);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setFirstName(firstName);
        guest.setLastName("Banderas With Many Stays And Different Names");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayVeryOld, stayOld, stayActual));

        return guest;
    }

    private Guest createGuestAntonyHopkins() {
        String firstName = "Anthony";

        Stay stayVeryOld = RandomStay.createRandomStayWithoutId();
        stayVeryOld.setArriveDate(LocalDate.now().minusYears(10));
        stayVeryOld.setLeaveDate(LocalDate.now().minusYears(9));
        stayVeryOld.setFirstName(firstName);
        stayVeryOld.setLastName("Hopkins");
        stayVeryOld.setBoxNumber("6789");

        Stay stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(5));
        stayOld.setLeaveDate(LocalDate.now().minusYears(4));
        stayOld.setFirstName(firstName);
        stayOld.setLastName("Montana");
        stayOld.setBoxNumber("ABCD");
        stayOld.setPreBooking("Anthony Hopkins made a pre-booking and save 28 Euro. Smart man.");

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setFirstName(firstName);
        guest.setLastName("Hopkins With Stays And Different Names");
        guest.setCheckedin(false);
        guest.setStays(ImmutableList.of(stayVeryOld, stayOld));

        return guest;
    }

    private Guest createGuestMonicaBellucci() {
        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setFirstName("Monica");
        guest.setLastName("Bellucci No Stays");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestKeanuReeves() {
        Stay stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(8));
        stayActual.setLeaveDate(LocalDate.now().plusDays(8));
        stayActual.setFirstName("Keanu");
        stayActual.setLastName("Reeves");
        stayActual.setBoxNumber("666");
        stayActual.setActive(true);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setFirstName("Keanu");
        guest.setLastName("Reeves");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayActual));
        return guest;
    }

    private Guest createGuestAgentSmith() {
        Stay stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(10));
        stayActual.setLeaveDate(LocalDate.now().plusDays(10));
        stayActual.setFirstName("Agent");
        stayActual.setLastName("Smith");
        stayActual.setBoxNumber("98765");
        stayActual.setActive(true);

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setFirstName("Agent");
        guest.setLastName("Smith Shows Name Always From Stay");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayActual));

        return guest;
    }

    private Guest createGuestHarrisonFord() {
        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setFirstName("Harrison");
        guest.setLastName("Ford No Stay");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestSeanConnery() {
        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setFirstName("Sean");
        guest.setLastName("Connery No Stay");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestClintEastwood() {
        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setFirstName("Clint");
        guest.setLastName("Eastwood No Stay");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestMelGibson() {
        Stay stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(6));
        stayOld.setLeaveDate(LocalDate.now().minusYears(4));
        stayOld.setFirstName("Mel");
        stayOld.setLastName("Gibson Same Names");
        stayOld.setBoxNumber("ZXY");

        Stay stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(15));
        stayActual.setLeaveDate(LocalDate.now().plusDays(15));
        stayActual.setFirstName("Mel");
        stayActual.setLastName("Gibson Same Names");
        stayActual.setBoxNumber("4567");
        stayActual.setActive(true);
        stayActual.setPreBooking("Mel Gibson made a pre-booking and save 20 Euro. Smart man.");

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setFirstName("Mel");
        guest.setLastName("Gibson Same Names");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayOld, stayActual));
        return guest;
    }


}
