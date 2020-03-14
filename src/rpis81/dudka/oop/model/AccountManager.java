package rpis81.dudka.oop.model;

import java.util.Arrays;

public class AccountManager {

    private Account[] accounts;
    private int size;

    public AccountManager(int size) {
        this(new Account[size]);
    }

    public AccountManager(Account[] clients) {
        this.accounts = new Account[clients.length];
        toFill(clients);
    }

    private void toFill(Account[] sourceArray) {
        int i = 0;
        for (Account it : sourceArray) {
            if (it != null) {
                this.accounts[i++] = it;
            }
        }
        size = i;
    }

    public boolean add(Account account) {
        checkQuantity();
        this.accounts[size++] = account;
        return true;
    }

    public boolean add(int index, Account account) {
        if (index > -1 && index < this.accounts.length){
            if (size + 1 > this.accounts.length) {
                increaseArray();
            }
            if (index < size) {
                int length = (size - index);
                for (int i = 0, j = size; i < length; i++) {
                    Account tmp = this.accounts[j];
                    this.accounts[j] = this.accounts[j-1];
                    this.accounts[j-1] = tmp;
                    j--;
                }
                this.accounts[index] = account;
                size += 1;
            } else {
                add(account);
            }
            return true;
        }
        return false;
    }

    public Account get(int index) {
        if (index > -1 && index < size){
            return this.accounts[index];
        }
        return null;
    }

    public Account set(int index, Account account) {
        if (index > -1 && index < size){
            Account oldAccount = this.accounts[index];
            this.accounts[index] = account;
            return oldAccount;
        }
        return null;
    }

    public Account remove(int index) {
        if (index > -1 && index < size){
            Account oldAccount = this.accounts[index];
            this.accounts[index] = null;
            size--;
            shiftValues(index);
            return oldAccount;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public Account[] getAccounts() {
        Account[] newAccount = new Account[size];
        System.arraycopy(this.accounts, 0, newAccount, 0, size);
        return newAccount;
    }

    public IndividualsTariff getTariff(long accountNumber) {
        for (int i = 0; i < size; i++) {

            if (this.accounts[i].getNumber() == accountNumber) {
                return this.accounts[i].getTariff();
            }
        }
        return null;
    }

    public IndividualsTariff setTariff(long accountNumber, IndividualsTariff tariff) {
        for (int i = 0; i < size; i++) {
            if (this.accounts[i].getNumber() == accountNumber) {
                IndividualsTariff oldTariff = this.accounts[i].getTariff();
                this.accounts[i].setTariff(tariff);
                return oldTariff;
            }
        }
        return null;
    }

    private void checkQuantity(){
        if (size == this.accounts.length) {
            increaseArray();
        }
    }

    private void increaseArray() {
        Account[] tmp = this.accounts;
        this.accounts = new Account[size * 2];
        toFill(tmp);
    }

    private void shiftValues(int index){
        int length = this.accounts.length - 1;
        if (length - index >= 0) System.arraycopy(this.accounts, index + 1, this.accounts, index, length - index);
        this.accounts[length] = null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountManager{");
        sb.append("accounts=").append(Arrays.toString(accounts));
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
