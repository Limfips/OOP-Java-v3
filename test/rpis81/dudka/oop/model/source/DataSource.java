package rpis81.dudka.oop.model.source;

import rpis81.dudka.oop.model.Account;
import rpis81.dudka.oop.model.IndividualsTariff;
import rpis81.dudka.oop.model.Person;
import rpis81.dudka.oop.model.Service;

public class DataSource {

    public String fServiceName = "интернет 150мб\\сек";
    public double fServiceCost = 450;
    public String sServiceName = "интернет 90мб\\сек";
    public double sServiceCost = 250;


    public final int testSizeAccount = 13;
    public final int testSizePeople = testSizeAccount * 2;
    public final int testSizeIndividualsTariffs = testSizeAccount * 2;
    public final int testSizeServices = testSizeIndividualsTariffs * 5;

    public final Service[] testServices = getTestServices();
    public final IndividualsTariff[] testIndividualsTariffs = getTestIndividualsTariffs();
    public final Person[] people = getTestPeople();
    public final Account[] accounts = getTestAccounts();

    private Service[] getTestServices() {
        Service[] testServices = new Service[testSizeServices];
        for (int i = 0; i < testSizeServices; i++) {
            testServices[i] = new Service(
                    "интернет " + (i + 1) + "мб\\сек",
                    i * 3 / 10.0 - 5 * 100);
        }
        return testServices;
    }

    private IndividualsTariff[] getTestIndividualsTariffs() {
        Service[] services = getTestServices();
        IndividualsTariff[] testIndividualsTariffs = new IndividualsTariff[testSizeIndividualsTariffs];
        Service[] tmpAccounts = new Service[5];
        for (int i = 0; i < testSizeIndividualsTariffs; i++) {
            System.arraycopy(services, i * 5, tmpAccounts, 0, 5);
            testIndividualsTariffs[i] = new IndividualsTariff(tmpAccounts);
        }
        return testIndividualsTariffs;
    }

    private Person[] getTestPeople() {
        Person[] people = new Person[testSizePeople];
        for (int i = 0; i < testSizePeople; i++) {
            people[i] = new Person("fNamePerson_" + (i + 1), "sNamePerson_" + (i + 1));
        }
        return people;
    }

    private Account[] getTestAccounts() {
        Account[] accounts = new Account[testSizeAccount];
        long[] numbers = new long[]{
                1, 2, 3, 4, 5,
                6, 7, 8, 9, 10,
                11, 12, 13, 14, 15,
                16, 17, 18, 19, 20};
        for (int i = 0; i < testSizeAccount; i++) {
            accounts[i] = new Account(numbers[i], people[i], testIndividualsTariffs[i]);
        }
        return accounts;
    }
}
