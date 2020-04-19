package com.tabit.dcm2;

import com.google.common.collect.ImmutableList;
import com.tabit.dcm2.entity.*;
import com.tabit.dcm2.repository.IEquipmentRepo;
import com.tabit.dcm2.repository.IEquipmentTypeRepo;
import com.tabit.dcm2.repository.IGuestRepo;
import com.tabit.dcm2.repository.ILoanRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestdataImporter {

    @Autowired
    private IGuestRepo guestRepo;

    @Autowired
    private IEquipmentRepo equipmentRepo;

    @Autowired
    private ILoanRepo loanRepo;

    @Autowired
    private IEquipmentTypeRepo equipmentTypeRepo;

    private List<Guest> guestsInDb = new ArrayList<>();

    private EquipmentType equipmentTypeMask;
    private EquipmentType equipmentTypeSuit;
    private EquipmentType equipmentTypeFins;

    private Equipment equipmentMask_XD_M_15;
    private Equipment equipmentMask_XD_M_16;
    private Equipment equipmentMask_XD_M_17;
    private Equipment equipmentFins_XD_F_12;
    private Equipment equipmentSuit_XS_G_13;

    private Guest guest0;
    private Guest guest1;
    private Guest guest2;
    private Guest guest3;
    private Guest guest4;
    private Guest guest5;
    private Guest guest6;
    private Guest guest7;
    private Guest guest8;
    private Guest guest9;


    @Autowired
    /**
     * to import some testdata to the application just set ActiveProfile to mysql.
     * but dont commit it!
     * <p>
     * start the application first, the import the testdata
     */
    @Test
    public void import_testdata_for_application() {
        saveGuests();
        saveEquipments();
        saveLoads();
    }

    private void saveGuests() {
        guest0 = createGuestAlexander();
        guest1 = createGuestAntonioBanderas();
        guest2 = createGuestAntonyHopkins();
        guest3 = createGuestMonicaBellucci();
        guest4 = createGuestKeanuReeves();
        guest5 = createGuestAgentSmith();
        guest6 = createGuestHarrisonFord();
        guest7 = createGuestSeanConnery();
        guest8 = createGuestClintEastwood();
        guest9 = createGuestMelGibson();

        guestRepo.saveAll(ImmutableList.of(
                guest0, guest1, guest2, guest3, guest4, guest5, guest6, guest7, guest8, guest9
        ));
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

    private Guest createGuestAlexander() {
        LocalDate guestBirthDate = LocalDate.now().minusYears(52);

        Stay stayOld = RandomStay.createRandomStayWithoutId();
        stayOld.setArriveDate(LocalDate.now().minusYears(6));
        stayOld.setLeaveDate(LocalDate.now().minusYears(4));
        stayOld.setCheckInDate(stayOld.getArriveDate().plusDays(1));
        stayOld.setCheckOutDate(stayOld.getCheckInDate().plusDays(20));
        stayOld.setBirthDate(guestBirthDate);
        stayOld.setFirstName("Alexander");
        stayOld.setLastName("Velonias");
        stayOld.setBoxNumber(" ");
        stayOld.setActive(false);

        Stay stayActual = RandomStay.createRandomStayWithoutId();
        stayActual.setArriveDate(LocalDate.now().minusDays(15));
        stayActual.setLeaveDate(LocalDate.now().plusDays(15));
        stayActual.setCheckInDate(stayActual.getArriveDate().plusDays(1));
        stayActual.setCheckOutDate(stayActual.getLeaveDate());
        stayActual.setBirthDate(guestBirthDate);
        stayActual.setFirstName("Alexander");
        stayActual.setLastName("Velonias");
        stayActual.setBoxNumber("4567");
        stayActual.setActive(true);
        stayActual.setPreBooking("none");

        Guest guest = RandomGuest.createRandomGuestWitoutId();
        guest.setBirthDate(guestBirthDate);
        guest.setFirstName("Alexander");
        guest.setLastName("Velonias");
        guest.setCheckedin(true);
        guest.setStays(ImmutableList.of(stayOld, stayActual));
        return guest;
    }

    private void saveEquipments() {
        //equipment types
        equipmentTypeMask = new EquipmentType();
        equipmentTypeMask.setType("Mask");
        equipmentTypeMask.setPrice(12.20);
        equipmentTypeMask.setDescription("Big mask");
        equipmentTypeMask.setActive(true);

        equipmentTypeFins = new EquipmentType();
        equipmentTypeFins.setType("Fins");
        equipmentTypeFins.setPrice(4.50);
        equipmentTypeFins.setDescription("Small fins");
        equipmentTypeFins.setActive(true);

        equipmentTypeSuit = new EquipmentType();
        equipmentTypeSuit.setType("Suite");
        equipmentTypeSuit.setPrice(4.50);
        equipmentTypeSuit.setDescription("Small fins");
        equipmentTypeSuit.setActive(true);

        equipmentTypeRepo.saveAll(ImmutableList.of(equipmentTypeFins, equipmentTypeMask, equipmentTypeSuit));

        //equipments
        equipmentMask_XD_M_15 = new Equipment();
        equipmentMask_XD_M_15.setStatus(Equipment.EquipmentStatus.AVAILABLE);
        equipmentMask_XD_M_15.setSerialNumber("XD_M_15");
        equipmentMask_XD_M_15.setEquipmentType(equipmentTypeMask);

        equipmentMask_XD_M_16 = new Equipment();
        equipmentMask_XD_M_16.setStatus(Equipment.EquipmentStatus.BROKEN);
        equipmentMask_XD_M_16.setSerialNumber("XD_M_16");
        equipmentMask_XD_M_16.setEquipmentType(equipmentTypeMask);


        equipmentMask_XD_M_17 = new Equipment();
        equipmentMask_XD_M_17.setStatus(Equipment.EquipmentStatus.STOLEN);
        equipmentMask_XD_M_17.setSerialNumber("XD_M_17");
        equipmentMask_XD_M_17.setEquipmentType(equipmentTypeMask);


        equipmentFins_XD_F_12 = new Equipment();
        equipmentFins_XD_F_12.setStatus(Equipment.EquipmentStatus.IN_REPAIR);
        equipmentFins_XD_F_12.setSerialNumber("XD_F_12");
        equipmentFins_XD_F_12.setEquipmentType(equipmentTypeFins);

        equipmentSuit_XS_G_13 = new Equipment();
        equipmentSuit_XS_G_13.setStatus(Equipment.EquipmentStatus.IN_REPAIR);
        equipmentSuit_XS_G_13.setSerialNumber("XS_G_13");
        equipmentSuit_XS_G_13.setEquipmentType(equipmentTypeSuit);

        equipmentRepo.saveAll(ImmutableList.of(
                equipmentMask_XD_M_15,
                equipmentMask_XD_M_16,
                equipmentMask_XD_M_17,
                equipmentFins_XD_F_12,
                equipmentSuit_XS_G_13
        ));
    }

    private void saveLoads() {
        Stay stay1 = guest0.getStays().get(1);
        Stay stay2 = guest0.getStays().get(0);

        // Loans stay 1
        Loan loan1 = new Loan();
        loan1.setStay(stay1);
        loan1.setDateOut(stay1.getCheckInDate());
        loan1.setDateReturn(stay1.getCheckInDate().plusDays(1));
        loan1.setEquipment(equipmentMask_XD_M_16);

        Loan loan2 = new Loan();
        loan2.setStay(stay1);
        loan2.setDateOut(stay1.getCheckInDate().plusDays(1));
        loan2.setEquipment(equipmentMask_XD_M_17);

        Loan loan3 = new Loan();
        loan3.setStay(stay1);
        loan3.setDateOut(stay1.getCheckInDate());
        loan3.setEquipment(equipmentFins_XD_F_12);

        // Loans stay 2
        Loan loan4 = new Loan();
        loan4.setStay(stay2);
        loan4.setDateOut(stay2.getCheckInDate().plusDays(1));
        loan4.setDateReturn(stay2.getCheckOutDate().minusDays(1));
        loan4.setEquipment(equipmentSuit_XS_G_13);

        Loan loan5 = new Loan();
        loan5.setStay(stay2);
        loan5.setDateOut(stay2.getCheckInDate().plusDays(1));
        loan5.setDateReturn(stay2.getCheckOutDate().minusDays(1));
        loan5.setEquipment(equipmentMask_XD_M_17);

        Loan loan6 = new Loan();
        loan6.setStay(stay2);
        loan6.setDateOut(stay2.getCheckInDate().plusDays(1));
        loan6.setDateReturn(stay2.getCheckOutDate().minusDays(1));
        loan6.setEquipment(equipmentFins_XD_F_12);


        loanRepo.saveAll(ImmutableList.of(loan1, loan2, loan3, loan4, loan5, loan6));
    }

}
