package uk.co.richyhbm.coinbag.records;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import uk.co.richyhbm.coinbag.balances.Balance;
import uk.co.richyhbm.coinbag.enums.CryptoCurrencies;

public class Wallet extends SugarRecord {
    @Unique
    String address;
    int cryptoType;

    public Wallet() {

    }

    public Wallet(String address, CryptoCurrencies type){
        this.address = address;
        this.cryptoType = type.getValue();
    }

    public CryptoCurrencies getType() {
        return CryptoCurrencies.getFromValue(cryptoType);
    }

    public String getAddress() {
        return address;
    }

    public String getBalance() {
        if(Balance.balanceFetchers.containsKey(getType())){
            return Balance.balanceFetchers.get(getType()).getBalanceForAddress(address);
        }
        return "";
    }

    public String getValue() {
        return "";
    }
}
