package rpis81.dudka.oop.model.source;

import rpis81.dudka.oop.model.*;

import java.time.LocalDate;

public class DataSource {

    public String fServiceName = "интернет 150мб\\сек";
    public double fServiceCost = 450;
    public String sServiceName = "интернет 90мб\\сек";
    public double sServiceCost = 250;

    public final int testSizeAccounts = 10;
    public final int testSizeIndividualAccount = 13;
    public final int testSizeEntityAccount = 13;
    public final int testSizePeople = testSizeIndividualAccount * 2;
    public final String[] names = new String[]{
            "name_1", "name_2", "name_3", "name_4", "name_5",
            "name_6", "name_7", "name_8", "name_9", "name_10",
            "name_11", "name_12", "name_13", "name_14", "name_15",
            "name_16", "name_17", "name_18", "name_19", "name_20",
    };
    public final int testSizeIndividualsTariffs = testSizeIndividualAccount * 2;
    public final int testSizeEntityTariffs = testSizeEntityAccount * 2;
    public final int testSizeServices = (testSizeIndividualsTariffs + testSizeEntityTariffs) * 5;

    public final Service[] testServices = getTestServices();
    public final IndividualsTariff[] testTariffs = getTestIndividualsTariffs();
    public final EntityTariff[] entityTariffs = getTestEntityTariffs();

    public final Person[] people = getTestPeople();
    public final IndividualAccount[] testIndividualAccounts = getTestIndividualAccounts();
    public final EntityAccount[] testEntityAccounts = getTestEntityAccounts();

    public final Account[] testAccounts = getTestAccounts();

    private Service[] getTestServices() {
        Service[] testServices = new Service[testSizeServices];
        for (int i = 0, k = 0; i < testSizeServices; i++) {
            switch (k) {
                case 0:
                    testServices[i] = new Service(
                            "интернет " + (i + 1) + "мб\\сек",
                            (i * 10), ServiceTypes.INTERNET, Service.ACTIVATION_DATE_DEFAULT);
                    k += 1;
                    break;
                case 1:
                    testServices[i] = new Service(
                            "интернет " + (i + 1) + "мб\\сек",
                            (i * 10), ServiceTypes.PHONE, Service.ACTIVATION_DATE_DEFAULT);
                    k += 1;
                    break;
                case 2:
                    testServices[i] = new Service(
                            "интернет " + (i + 1) + "мб\\сек",
                            (i * 10), ServiceTypes.STORAGE, Service.ACTIVATION_DATE_DEFAULT);
                    k += 1;
                    break;
                case 3:
                    testServices[i] = new Service(
                            "интернет " + (i + 1) + "мб\\сек",
                            (i * 10), ServiceTypes.MAIL, Service.ACTIVATION_DATE_DEFAULT);
                    k += 1;
                    break;
                case 4:
                    testServices[i] = new Service(
                            "интернет " + (i + 1) + "мб\\сек",
                            (i * 10), ServiceTypes.ADDITIONAL_SERVICE, Service.ACTIVATION_DATE_DEFAULT);
                    k = 0;
                    break;
            }
        }
        return testServices;
    }

    private IndividualsTariff[] getTestIndividualsTariffs() {
        Service[] services = getTestServices();
        IndividualsTariff[] testTariffs = new IndividualsTariff[testSizeIndividualsTariffs];
        Service[] tmpIndividualAccounts = new Service[5];
        for (int i = 0; i < testSizeIndividualsTariffs; i++) {
            System.arraycopy(services, i * 5, tmpIndividualAccounts, 0, 5);
            testTariffs[i] = new IndividualsTariff(tmpIndividualAccounts);
        }
        return testTariffs;
    }

    private EntityTariff[] getTestEntityTariffs() {
        Service[] services = getTestServices();
        EntityTariff[] testTariffs = new EntityTariff[testSizeEntityTariffs];
        Service[] tmpIndividualAccounts = new Service[5];
        for (int i = 0; i < testSizeEntityTariffs; i++) {
            System.arraycopy(services, (i * 5) + (testSizeIndividualsTariffs * 5),
                    tmpIndividualAccounts, 0, 5);
            testTariffs[i] = new EntityTariff(tmpIndividualAccounts);
        }
        return testTariffs;
    }

    private Person[] getTestPeople() {
        Person[] people = new Person[testSizePeople];
        for (int i = 0; i < testSizePeople; i++) {
            people[i] = new Person("fNamePerson_" + (i + 1), "sNamePerson_" + (i + 1));
        }
        return people;
    }

    private IndividualAccount[] getTestIndividualAccounts() {
        IndividualAccount[] accounts = new IndividualAccount[testSizeIndividualAccount];
        long[] numbers = new long[]{
                1000000100001L, 93999163599999L, 999909988899999L, 999999999094449L, 999120999999999L,
                999919999996999L, 919992199999999L, 999999099999999L, 999999054549999L, 99909999459999L,
                999900100996999L, 231234123634212L, 234321034532413L, 323412302365423L, 43653241242302L,
                234323121125432L, 999313439996999L, 235436568605633L, 325412680654634L, 932404214612479L};
        for (int i = 0; i < testSizeIndividualAccount; i++) {
            accounts[i] = new IndividualAccount(numbers[i], people[i], testTariffs[i], LocalDate.now());
        }
        return accounts;
    }

    private EntityAccount[] getTestEntityAccounts() {

        EntityAccount[] accounts = new EntityAccount[testSizeEntityAccount];
        long[] numbers = new long[]{
                1000000000001L, 93999963599999L, 999999988899999L, 999999999994449L, 999123999999999L,
                999999999996999L, 999992199999999L, 999999999999999L, 999999954549999L, 99999999459999L,
                999900000996999L, 234234123634212L, 234321234532413L, 323412312365423L, 43653241242332L,
                234323122125432L, 999323439996999L, 235436568675633L, 325412685654634L, 932474214612479L};
        for (int i = 0; i < testSizeIndividualAccount; i++) {
            accounts[i] = new EntityAccount(numbers[i], names[i], testTariffs[i], LocalDate.now());
        }
        return accounts;
    }

    private Account[] getTestAccounts() {
        Account[] accounts = new Account[testSizeAccounts];
        boolean isAc = true;
        for (int i = 0, k = 0, j = 0; i < testSizeAccounts; i++) {
            if (isAc) {
                accounts[i] = testIndividualAccounts[k++];
                isAc = false;
            } else {
                accounts[i] = testEntityAccounts[j++];
                isAc = true;
            }
        }
        return accounts;
    }
}
