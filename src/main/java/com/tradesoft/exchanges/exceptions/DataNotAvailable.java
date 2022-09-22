package com.tradesoft.exchanges.exceptions;

import java.util.function.Supplier;

public class DataNotAvailable extends RuntimeException {

    public DataNotAvailable(){
        super("Data not Found");
    }
}
