package rpis81.dudka.oop.model;


import java.util.*;

public class AccountManager implements Iterable<Account> {

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

    private boolean isValidIndex(int index) {
        return index > -1 && index < size();
    }

    public boolean add(Account account) {
        if (account == null) throw new NullPointerException();
        try {
            getAccount(account.getNumber());
        } catch (NoSuchElementException exception) {
            checkQuantity();
            this.accounts[size++] = account;
            return true;
        }
        return false;
    }

    public boolean add(int index, Account account) {
        if (account == null) throw new NullPointerException();
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        try {
            getAccount(account.getNumber());
        } catch (NoSuchElementException exception) {
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
        }
        return false;
    }

    public Account get(int index) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        return this.accounts[index];
    }

    public Account set(int index, Account account) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        if (account == null) throw new NullPointerException();
        try {
            getAccount(account.getNumber());
        } catch (NoSuchElementException exception) {
            Account oldAccount = this.accounts[index];
            this.accounts[index] = account;
            return oldAccount;
        }
        throw new NoSuchElementException();
    }

    public Account remove(int index) {
        if (!isValidIndex(index)) throw new IndexOutOfBoundsException();
        Account oldAccount = this.accounts[index];
        this.accounts[index] = null;
        size--;
        shiftValues(index);
        return oldAccount;
    }

    public int size() {
        return size;
    }

    public Account getAccount(long accountNumber) {
        if (!AbstractAccount.isValidNumber(accountNumber)) throw new IllegalAccountNumber();
        for (Account it : this) {
            if (it.getNumber() == accountNumber) {
                return it;
            }
        }
        throw new NoSuchElementException();
    }

    public Set<Account> getAccounts() {
        return new HashSet<>(Arrays.asList(this.accounts).subList(0, size));
    }

    public Set<Account> getAccounts(ServiceTypes type) {
        if (type == null) throw new NullPointerException();
        Set<Account> accounts = new HashSet<>();
        for (Account it : this) {
            if (it.getTariff().toArray(type).size() > 0) {
                accounts.add(it);
            }
        }
        return accounts;
    }

    public List<Account> getIndividualAccounts() {
        List<Account> accounts = new ArrayList<>();
        for (Account it : this) {
            if (it instanceof IndividualAccount) {
                accounts.add(it);
            }
        }
        return accounts;
    }

    public List<Account> getEntityAccounts() {
        List<Account> accounts = new ArrayList<>();
        for (Account it : this) {
            if (it instanceof EntityAccount) {
                accounts.add(it);
            }
        }
        return accounts;
    }

    public Tariff getTariff(long accountNumber) {
        if (!AbstractAccount.isValidNumber(accountNumber)) throw new IllegalAccountNumber();
        for (int i = 0; i < size; i++) {

            if (this.accounts[i].getNumber() == accountNumber) {
                return this.accounts[i].getTariff();
            }
        }
        return null;
    }

    public Tariff setTariff(long accountNumber, Tariff tariff) {
        if (!AbstractAccount.isValidNumber(accountNumber)) throw new IllegalAccountNumber();
        if (tariff   == null) throw new NullPointerException();
        for (int i = 0; i < size; i++) {
            if (this.accounts[i].getNumber() == accountNumber) {
                Tariff oldTariff = this.accounts[i].getTariff();
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
        final StringBuilder sb = new StringBuilder();
        for (Account it : this) {
            sb.append(it.toString()).append('\n');
        }
        return sb.toString();
    }

    @Override
    public Iterator<Account> iterator() {
        return new AccountIterator();
    }

    private class AccountIterator implements Iterator<Account> {

        private int count = 0;

        @Override
        public boolean hasNext() {
            return count != size;
        }

        @Override
        public Account next() {
            if (!hasNext()) throw new NoSuchElementException();
            return get(count++);
        }
    }
}
