package org.recap.model.export;

import java.util.List;

/**
 * Created by saravanakumarp on 23/9/16.
 */
public class AccessionRequest {



    private String itemBarCode;
    private String customerCode;



    public void setItemBarCode(String item) {
        this.itemBarCode = item;
    }



    public void setCustomerCode(String customer) {
        this.customerCode = customer;
    }


    public String getItemBarCode() {
        return itemBarCode;
    }


    public String getCustomerCode() {
        return customerCode;
    }

}
