package org.recap.model.accession;

import java.util.Date;

/**
 * Created by saravanakumarp on 27/9/16.
 */
public class ILSResponse {

    private String itemStatus;
    private String feeType;
    private String itemID;
    private String institution;
    private String title;
    private String owningInstitutionBibId;
    private String owningInstitutionHoldingId;
    private String createdBy;
    private String lastUpdatedBY;
    private Date lastUpdatedDate;
    private int owningInstitutionID;
    private byte[] content;
    private int owningInstitutionHoldingsID;
    private String callNumberType;
    private String callNumber;
    private String barCode;
    private String owningInstitutionItemID;
    private int collectionGroupID;
    private String customerCode;
    private int itemAvailabilityStatusID;

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }


    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwningInstitutionBibId() {
        return owningInstitutionBibId;
    }

    public void setOwningInstitutionBibId(String owningInstitutionBibId) {
        this.owningInstitutionBibId = owningInstitutionBibId;
    }

    public String getOwningInstitutionHoldingId() {
        return owningInstitutionHoldingId;
    }

    public void setOwningInstitutionHoldingId(String owningInstitutionHoldingId) {
        this.owningInstitutionHoldingId = owningInstitutionHoldingId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBY() {
        return lastUpdatedBY;
    }

    public void setLastUpdatedBY(String lastUpdatedBY) {
        this.lastUpdatedBY = lastUpdatedBY;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getOwningInstitutionID() {
        return owningInstitutionID;
    }

    public void setOwningInstitutionID(int OwningInstitutionID) {
        this.owningInstitutionID = OwningInstitutionID;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getOwningInstitutionHoldingsID() {
        return owningInstitutionHoldingsID;
    }

    public void setOwningInstitutionHoldingsID(int owningInstitutionHoldingsID) {
        this.owningInstitutionHoldingsID = owningInstitutionHoldingsID;
    }

    public String getCallNumberType() {
        return callNumberType;
    }

    public void setCallNumberType(String callNumberType) {
        this.callNumberType = callNumberType;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }



    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }


    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }


    public String getOwningInstitutionItemID() {
        return owningInstitutionItemID;
    }

    public void setOwningInstitutionItemID(String owningInstitutionItemID) {
        this.owningInstitutionItemID = owningInstitutionItemID;
    }

    public int getCollectionGroupID() {
        return collectionGroupID;
    }

    public void setCollectionGroupID(int collectionGroupID) {
        this.collectionGroupID = collectionGroupID;
    }
    public int getItemAvailabilityStatusID() {
        return itemAvailabilityStatusID;
    }

    public void setItemAvailabilityStatusID(int itemAvailabilityStatusID) {
        this.itemAvailabilityStatusID = itemAvailabilityStatusID;
    }

}
