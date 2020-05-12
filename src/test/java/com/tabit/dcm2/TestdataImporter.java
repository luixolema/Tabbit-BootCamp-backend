package com.tabit.dcm2;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.*;
import com.tabit.dcm2.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestdataImporter {

    @Autowired
    private IGuestRepo guestRepo;

    @Autowired
    private IBoxManagementRepo boxManagementRepo;

    @Autowired
    private IEquipmentRepo equipmentRepo;

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private IDiveCenterRepo diveCenterRepo;

    // equipment for Acceptance criteria
    private Equipment mask_XD_M_16;
    private Equipment mask_XD_M_17;
    private Equipment mask_XD_M_15;
    private Equipment fins_XD_F_12;
    private Equipment suite_XS_G_13;
    private DiveCenter MakadiBayDiveCenter;
    private DiveCenter AdiveCenter;
    private DiveCenter OmegaDiveCenter;


    /**
     * to import some testdata to the application just set ActiveProfile to mysql.
     * but dont commit it!
     * <p>
     * start the application first, then import the testdata
     */
    @Test
    public void import_testdata_for_application() {
        createDiveCenters();
    }

    private void createDiveCenters() {
        createMakadiBayDiveCenterWithUsers();
        makadiBaySaveGuests();

        createDiveCenterAWithUsers();
        ADiveCenterSaveGuests();

        createDiveCenterOmegaWithUsers();
        OmegaDiveCenterSaveGuests();
    }

    private void createMakadiBayDiveCenterWithUsers() {
        MakadiBayDiveCenter = new DiveCenter();
        MakadiBayDiveCenter.setName("Makadi Bay");
        MakadiBayDiveCenter.setLocation("26.9869265, 33.9068201");
        diveCenterRepo.saveAll(ImmutableList.of(MakadiBayDiveCenter));

        User user = RandomUser.createRandomUserWithPasswordWithoutId("password");
        user.setDiveCenter(MakadiBayDiveCenter);
        user.setLogin("user@gmail.com");

        User user2 = RandomUser.createRandomUserWithPasswordWithoutId("password");
        user2.setDiveCenter(MakadiBayDiveCenter);
        user2.setLogin("user2@gmail.com");

        userRepo.saveAll(ImmutableList.of(user, user2));
    }

    private void makadiBaySaveGuests() {

        setupEquipment(MakadiBayDiveCenter);

        Guest guestWithGoodLoans1 = createGuestAlexander1(MakadiBayDiveCenter);
        Guest guestWithGoodLoans2 = createGuestAlexander2(MakadiBayDiveCenter);

        guestRepo.saveAll(ImmutableList.of(guestWithGoodLoans1, guestWithGoodLoans2));
    }

    private void ADiveCenterSaveGuests() {

        setupEquipment(AdiveCenter);

        Guest guest5 = createGuestAgentSmith(AdiveCenter);
        Guest guest6 = createGuestHarrisonFord(AdiveCenter);
        Guest guest7 = createGuestSeanConnery(AdiveCenter);
        Guest guest8 = createGuestClintEastwood(AdiveCenter);
        Guest guest9 = createGuestMelGibson(AdiveCenter);

        guestRepo.saveAll(ImmutableList.of(guest5, guest6, guest7, guest8, guest9));
    }

    private void OmegaDiveCenterSaveGuests() {

        setupEquipment(OmegaDiveCenter);

        Guest guest = createGuestAntonioBanderas(OmegaDiveCenter);
        Guest guest2 = createGuestAntonyHopkins(OmegaDiveCenter);
        Guest guest3 = createGuestMonicaBellucci(OmegaDiveCenter);
        Guest guest4 = createGuestKeanuReeves(OmegaDiveCenter);

        guestRepo.saveAll(ImmutableList.of(guest, guest2, guest3, guest4));
    }

    private void createDiveCenterAWithUsers() {
        AdiveCenter = new DiveCenter();
        AdiveCenter.setName("A");
        AdiveCenter.setLocation("27.7777777, 38.8888888");
        diveCenterRepo.save(AdiveCenter);

        User userX = RandomUser.createRandomUserWithPassword("password");
        userX.setName("X");
        userX.setDiveCenter(AdiveCenter);
        userX.setLogin("emailX@gmail.com");

        User userY = RandomUser.createRandomUserWithPassword("password");
        userY.setName("Y");
        userY.setDiveCenter(AdiveCenter);
        userY.setLogin("emailY@gmail.com");
        userRepo.saveAll(ImmutableList.of(userX, userY));
    }

    private void createDiveCenterOmegaWithUsers() {
        OmegaDiveCenter = new DiveCenter();
        OmegaDiveCenter.setName("Omega");
        OmegaDiveCenter.setLocation("21.1111111, 33.3333333");
        diveCenterRepo.save(OmegaDiveCenter);

        User userAlpha = RandomUser.createRandomUserWithPassword("password");
        userAlpha.setName("Alpha");
        userAlpha.setDiveCenter(OmegaDiveCenter);
        userAlpha.setLogin("emailAlpha@gmail.com");

        User userBeta = RandomUser.createRandomUserWithPassword("password");
        userBeta.setName("Beta");
        userBeta.setDiveCenter(OmegaDiveCenter);
        userBeta.setLogin("emailBeta@gmail.com");
        userRepo.saveAll(ImmutableList.of(userAlpha, userBeta));
    }

    private Guest createGuestAlexander1(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(59);
        String firstName = "Alexander";

        Stay stayOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayOld.setArriveDate(LocalDate.now().minusYears(7));
        stayOld.setLeaveDate(LocalDate.now().minusYears(6));
        stayOld.setCheckInDate(stayOld.getArriveDate().plusDays(1));
        stayOld.setCheckOutDate(stayOld.getCheckInDate().plusDays(10));
        stayOld.setBirthDate(guestBirthDate);
        stayOld.setFirstName(firstName);
        stayOld.setLastName("Criteria 1");
        stayOld.setBoxNumber("12");
        stayOld.setActive(false);

        Loan loanOld1 = new Loan();
        loanOld1.setDiveCenter(diveCenter);
        loanOld1.setEquipment(suite_XS_G_13);
        loanOld1.setDateOut(stayOld.getCheckInDate().plusDays(1));
        loanOld1.setDateReturn(stayOld.getCheckInDate().plusDays(4));
        loanOld1.setPrice(5.25d);

        Loan loanOld2 = new Loan();
        loanOld2.setDiveCenter(diveCenter);
        loanOld2.setEquipment(mask_XD_M_15);
        loanOld2.setDateOut(stayOld.getCheckInDate().plusDays(3));
        loanOld2.setDateReturn(stayOld.getCheckInDate().plusDays(6));
        loanOld2.setPrice(3.55d);

        Loan loanOld3 = new Loan();
        loanOld3.setDiveCenter(diveCenter);
        loanOld3.setEquipment(fins_XD_F_12);
        loanOld3.setDateOut(stayOld.getCheckInDate().plusDays(2));
        loanOld3.setDateReturn(stayOld.getCheckInDate().plusDays(7));
        loanOld3.setPrice(7.50d);

        stayOld.setLoans(ImmutableList.of(loanOld1, loanOld2, loanOld3));

        Stay stayActual = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayActual.setArriveDate(LocalDate.now().minusDays(3));
        stayActual.setLeaveDate(LocalDate.now().plusDays(3));
        stayActual.setCheckInDate(stayActual.getArriveDate().plusDays(1));
        stayActual.setCheckOutDate(stayActual.getLeaveDate());
        stayActual.setBirthDate(guestBirthDate);
        stayActual.setFirstName(firstName);
        stayActual.setLastName("Velonias Criteria 1");
        stayActual.setBoxNumber("888");
        stayActual.setPreBooking("Smart man.");
        stayActual.setActive(true);

        Loan loan1 = new Loan();
        loan1.setDiveCenter(diveCenter);
        loan1.setEquipment(mask_XD_M_16);
        loan1.setDateOut(stayActual.getCheckInDate());
        loan1.setDateReturn(stayActual.getCheckInDate().plusDays(1));
        loan1.setPrice(5.25d);

        Loan loan2 = new Loan();
        loan2.setDiveCenter(diveCenter);
        loan2.setEquipment(mask_XD_M_17);
        loan2.setDateOut(stayActual.getCheckInDate().plusDays(1));
        loan2.setPrice(3.55d);

        Loan loan3 = new Loan();
        loan3.setDiveCenter(diveCenter);
        loan3.setEquipment(fins_XD_F_12);
        loan3.setDateOut(stayActual.getCheckInDate());
        loan3.setPrice(7.50d);

        stayActual.setLoans(ImmutableList.of(loan1, loan2, loan3));

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName(firstName);
        guest.setLastName("Velonias Criteria 1");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayOld, stayActual));

        saveBoxNumbers(diveCenter, Arrays.asList("888", "12"));

        return guest;
    }

    private Guest createGuestAlexander2(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(59);
        String firstName = "Alexander";

        Stay stayOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayOld.setArriveDate(LocalDate.now().minusYears(7));
        stayOld.setLeaveDate(LocalDate.now().minusYears(6));
        stayOld.setCheckInDate(stayOld.getArriveDate().plusDays(1));
        stayOld.setCheckOutDate(stayOld.getCheckInDate().plusDays(10));
        stayOld.setBirthDate(guestBirthDate);
        stayOld.setFirstName(firstName);
        stayOld.setLastName("Criteria 2");
        stayOld.setBoxNumber("dsfsdfds");
        stayOld.setActive(false);

        Loan loanOld1 = new Loan();
        loanOld1.setDiveCenter(diveCenter);
        loanOld1.setEquipment(suite_XS_G_13);
        loanOld1.setDateOut(stayOld.getCheckInDate());
        loanOld1.setDateReturn(stayOld.getCheckOutDate());
        loanOld1.setPrice(5.25d);

        Loan loanOld2 = new Loan();
        loanOld2.setDiveCenter(diveCenter);
        loanOld2.setEquipment(mask_XD_M_15);
        loanOld2.setDateOut(stayOld.getCheckInDate());
        loanOld2.setDateReturn(stayOld.getCheckOutDate());
        loanOld2.setPrice(3.55d);

        Loan loanOld3 = new Loan();
        loanOld3.setDiveCenter(diveCenter);
        loanOld3.setEquipment(fins_XD_F_12);
        loanOld3.setDateOut(stayOld.getCheckInDate());
        loanOld3.setDateReturn(stayOld.getCheckOutDate());
        loanOld3.setPrice(7.50d);

        stayOld.setLoans(ImmutableList.of(loanOld1, loanOld2, loanOld3));

        Stay stayActual = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayActual.setArriveDate(LocalDate.now().minusDays(3));
        stayActual.setLeaveDate(LocalDate.now().plusDays(3));
        stayActual.setCheckInDate(stayActual.getArriveDate().plusDays(1));
        stayActual.setCheckOutDate(stayActual.getLeaveDate());
        stayActual.setBirthDate(guestBirthDate);
        stayActual.setFirstName(firstName);
        stayActual.setLastName("Velonias Criteria 2");
        stayActual.setBoxNumber("999");
        stayActual.setPreBooking("Smart man.");
        stayActual.setActive(true);

        Loan loan1 = new Loan();
        loan1.setDiveCenter(diveCenter);
        loan1.setEquipment(mask_XD_M_16);
        loan1.setDateOut(stayActual.getCheckInDate());
        loan1.setPrice(5.25d);

        Loan loan2 = new Loan();
        loan2.setDiveCenter(diveCenter);
        loan2.setEquipment(fins_XD_F_12);
        loan2.setDateOut(stayActual.getCheckInDate());
        loan2.setPrice(3.55d);

        stayActual.setLoans(ImmutableList.of(loan1, loan2));

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName(firstName);
        guest.setLastName("Velonias Criteria 2");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayOld, stayActual));

        saveBoxNumbers(diveCenter, Arrays.asList("dsfsdfds", "999"));

        return guest;
    }

    private Guest createGuestAntonioBanderas(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(59);
        String firstName = "Antonio";

        Stay stayVeryOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayVeryOld.setArriveDate(LocalDate.now().minusYears(15));
        stayVeryOld.setLeaveDate(LocalDate.now().minusYears(14));
        stayVeryOld.setCheckInDate(stayVeryOld.getArriveDate().plusDays(1));
        stayVeryOld.setCheckOutDate(stayVeryOld.getCheckInDate().plusDays(10));
        stayVeryOld.setBirthDate(guestBirthDate);
        stayVeryOld.setFirstName(firstName);
        stayVeryOld.setLastName("Montana");
        stayVeryOld.setBoxNumber("123456");
        stayVeryOld.setActive(false);

        Stay stayOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayOld.setArriveDate(LocalDate.now().minusYears(7));
        stayOld.setLeaveDate(LocalDate.now().minusYears(6));
        stayOld.setCheckInDate(stayOld.getArriveDate().plusDays(1));
        stayOld.setCheckOutDate(stayOld.getCheckInDate().plusDays(10));
        stayOld.setBirthDate(guestBirthDate);
        stayOld.setFirstName(firstName);
        stayOld.setLastName("Montana");
        stayOld.setBoxNumber("12");
        stayOld.setActive(false);

        Stay stayActual = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
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

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName(firstName);
        guest.setLastName("Banderas With Many Stays And Different Names");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayVeryOld, stayOld, stayActual));

        saveBoxNumbers(diveCenter, Arrays.asList("123456", "12", "123AB"));

        return guest;
    }

    private Guest createGuestAntonyHopkins(DiveCenter diveCenter) {
        String firstName = "Anthony";
        LocalDate guestBirthDate = LocalDate.now().minusYears(45);

        Stay stayVeryOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayVeryOld.setArriveDate(LocalDate.now().minusYears(10));
        stayVeryOld.setLeaveDate(LocalDate.now().minusYears(9));
        stayVeryOld.setCheckInDate(stayVeryOld.getArriveDate().plusDays(1));
        stayVeryOld.setCheckOutDate(stayVeryOld.getCheckInDate().plusDays(10));
        stayVeryOld.setBirthDate(guestBirthDate);
        stayVeryOld.setFirstName(firstName);
        stayVeryOld.setLastName("Hopkins");
        stayVeryOld.setBoxNumber("6789");
        stayVeryOld.setActive(false);

        Stay stayOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
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

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName(firstName);
        guest.setLastName("Hopkins With Stays And Different Names");
        guest.setCheckedin(false);
        guest.setStays(ImmutableList.of(stayVeryOld, stayOld));

        saveBoxNumbers(diveCenter, Arrays.asList("6789", "ABCD"));

        return guest;
    }

    private Guest createGuestMonicaBellucci(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(54);

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Monica");
        guest.setLastName("Bellucci No Stays");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestKeanuReeves(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(55);

        Stay stayActual = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayActual.setArriveDate(LocalDate.now().minusDays(8));
        stayActual.setLeaveDate(LocalDate.now().plusDays(8));
        stayActual.setCheckInDate(stayActual.getArriveDate().plusDays(1));
        stayActual.setCheckOutDate(stayActual.getLeaveDate());
        stayActual.setBirthDate(guestBirthDate);
        stayActual.setFirstName("Keanu");
        stayActual.setLastName("Reeves");
        stayActual.setBoxNumber("666");
        stayActual.setActive(true);

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Keanu");
        guest.setLastName("Reeves");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayActual));

        saveBoxNumbers(diveCenter, Collections.singletonList("666"));

        return guest;
    }

    private Guest createGuestAgentSmith(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(42);

        Stay stayActual = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayActual.setArriveDate(LocalDate.now().minusDays(10));
        stayActual.setLeaveDate(LocalDate.now().plusDays(10));
        stayActual.setCheckInDate(stayActual.getArriveDate().plusDays(1));
        stayActual.setCheckOutDate(stayActual.getLeaveDate());
        stayActual.setBirthDate(guestBirthDate);
        stayActual.setFirstName("Agent");
        stayActual.setLastName("Smith");
        stayActual.setBoxNumber("98765");
        stayActual.setActive(true);

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Agent");
        guest.setLastName("Smith Shows Name Always From Stay");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayActual));

        saveBoxNumbers(diveCenter, Collections.singletonList("98765"));

        return guest;
    }

    private Guest createGuestHarrisonFord(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(77);

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Harrison");
        guest.setLastName("Ford No Stay");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestSeanConnery(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(51);

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Sean");
        guest.setLastName("Connery No Stay");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestClintEastwood(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(89);

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Clint");
        guest.setLastName("Eastwood No Stay");
        guest.setCheckedin(false);
        guest.setStays(new ArrayList<>());
        return guest;
    }

    private Guest createGuestMelGibson(DiveCenter diveCenter) {
        LocalDate guestBirthDate = LocalDate.now().minusYears(52);

        Stay stayOld = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
        stayOld.setArriveDate(LocalDate.now().minusYears(6));
        stayOld.setLeaveDate(LocalDate.now().minusYears(4));
        stayOld.setCheckInDate(stayOld.getArriveDate().plusDays(1));
        stayOld.setCheckOutDate(stayOld.getCheckInDate().plusDays(20));
        stayOld.setBirthDate(guestBirthDate);
        stayOld.setFirstName("Mel");
        stayOld.setLastName("Gibson Same Names");
        stayOld.setBoxNumber("ZXY");
        stayOld.setActive(false);

        Stay stayActual = RandomStay.createRandomStayWithoutIdGivenDiveCenter(diveCenter);
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

        Guest guest = RandomGuest.createRandomGuestWithoutIdGivenDiveCenter(diveCenter);
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Mel");
        guest.setLastName("Gibson Same Names");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayOld, stayActual));


        saveBoxNumbers(diveCenter, Arrays.asList("ZXY", "4567"));

        return guest;
    }

    private void setupEquipment(DiveCenter diveCenter) {
        EquipmentType mask = RandomEquipmentType.createRandomEquipmentTypeWithoutIdGivenDiveCenter(diveCenter);
        mask.setType("Mask");
        EquipmentType fins = RandomEquipmentType.createRandomEquipmentTypeWithoutIdGivenDiveCenter(diveCenter);
        fins.setType("Fins");
        EquipmentType suite = RandomEquipmentType.createRandomEquipmentTypeWithoutIdGivenDiveCenter(diveCenter);
        suite.setType("Suite");

        mask_XD_M_16 = RandomEquipment.createRandomEquipmentWithoutIdGivenDiveCenter(diveCenter);
        mask_XD_M_16.setEquipmentType(mask);
        mask_XD_M_16.setSerialNumber("XD_M_16");

        mask_XD_M_17 = RandomEquipment.createRandomEquipmentWithoutIdGivenDiveCenter(diveCenter);
        mask_XD_M_17.setEquipmentType(mask);
        mask_XD_M_17.setSerialNumber("XD_M_17");

        mask_XD_M_15 = RandomEquipment.createRandomEquipmentWithoutIdGivenDiveCenter(diveCenter);
        mask_XD_M_15.setEquipmentType(mask);
        mask_XD_M_15.setSerialNumber("XD_M_15");

        fins_XD_F_12 = RandomEquipment.createRandomEquipmentWithoutIdGivenDiveCenter(diveCenter);
        fins_XD_F_12.setEquipmentType(fins);
        fins_XD_F_12.setSerialNumber("XD_F_12");

        suite_XS_G_13 = RandomEquipment.createRandomEquipmentWithoutIdGivenDiveCenter(diveCenter);
        suite_XS_G_13.setEquipmentType(suite);
        suite_XS_G_13.setSerialNumber("XS_G_13");
    }

    private void saveBoxNumbers(DiveCenter diveCenter, List<String> boxesNumbers) {

        boxesNumbers.forEach(boxNumber -> {
            BoxManagement boxManagement = new BoxManagement();
            boxManagement.setDiveCenter(diveCenter);
            boxManagement.setBoxNumber(boxNumber);

            boxManagementRepo.save(boxManagement);
        });
    }
}



