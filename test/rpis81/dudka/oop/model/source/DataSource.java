package rpis81.dudka.oop.model.source;

import rpis81.dudka.oop.model.*;

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
        for (int i = 0; i < testSizeServices; i++) {
            testServices[i] = new Service(
                    "интернет " + (i + 1) + "мб\\сек",
                    (i * 10));
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
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15,
                16, 17, 18, 19, 20};
        for (int i = 0; i < testSizeIndividualAccount; i++) {
            accounts[i] = new IndividualAccount(numbers[i], people[i], testTariffs[i]);
        }
        return accounts;
    }

    private EntityAccount[] getTestEntityAccounts() {

        EntityAccount[] accounts = new EntityAccount[testSizeEntityAccount];
        long[] numbers = new long[]{
                101, 102, 103, 104, 105,
                106, 107, 108, 109, 1010,
                1011, 1012, 1013, 1014, 1015,
                1016, 1017, 1018, 1019, 1020};
        for (int i = 0; i < testSizeIndividualAccount; i++) {
            accounts[i] = new EntityAccount(numbers[i], names[i], testTariffs[i]);
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
