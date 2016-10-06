package org.recap.service.accession;

import org.recap.camel.BibDataProcessor;
import org.recap.camel.ETLExchange;
import org.recap.model.accession.ILSResponse;
import org.recap.model.jaxb.Holding;
import org.recap.model.jaxb.JAXBHandler;
import org.recap.model.jpa.*;
import org.recap.repository.CollectionGroupDetailsRepository;
import org.recap.repository.InstitutionDetailsRepository;
import org.recap.repository.ItemStatusDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import org.recap.repository.*;
import org.springframework.stereotype.Service;

/**
 * Created by saravanakumarp on 28/9/16.
 */
@Service
public class AccessionService {


    private Map institutionEntityMap;
    private Map collectionGroupMap=null;

    @Autowired
    private InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    private CollectionGroupDetailsRepository collectionGroupDetailsRepository;

    @Autowired
    BibliographicDetailsRepository bibliographicDetailsRepository;



    public void saveBibliographic(BibliographicEntity bibliographicEntity){
        bibliographicDetailsRepository.save(bibliographicEntity);
    }

    public ILSResponse getILSResponseObject(){



        ILSResponse ils=new ILSResponse();
        ils.setCreatedBy("CreatedBy");
        ils.setLastUpdatedDate(new Date());
        ils.setLastUpdatedBY("tom");
        ils.setOwningInstitutionID(1);
        ils.setContent("mock Content".getBytes());
        ils.setCallNumberType("call number type");
        ils.setCallNumber("call number");
        ils.setBarCode("bar code");
        ils.setOwningInstitutionItemID("1");
        ils.setOwningInstitutionID(1);
        ils.setCustomerCode("customer code");
        ils.setItemAvailabilityStatusID(1);
        ils.setOwningInstitutionHoldingId("holdings");
        ils.setCollectionGroupID(1);


        return ils;
    }

    public BibliographicEntity getBibliographicEntity(ILSResponse ilsResponse){

        Random random = new Random();
        BibliographicEntity bibliographicEntity = new BibliographicEntity();
        bibliographicEntity.setContent(ilsResponse.getContent());
        bibliographicEntity.setCreatedDate(new Date());
        bibliographicEntity.setCreatedBy(ilsResponse.getCreatedBy());
        bibliographicEntity.setLastUpdatedBy(ilsResponse.getLastUpdatedBY());
        bibliographicEntity.setLastUpdatedDate(ilsResponse.getLastUpdatedDate());
        bibliographicEntity.setOwningInstitutionId(ilsResponse.getOwningInstitutionID());
        String owningInstitutionBibId = String.valueOf(random.nextInt());
        bibliographicEntity.setOwningInstitutionBibId(owningInstitutionBibId);

        bibliographicEntity.setHoldingsEntities(Arrays.asList(getHoldingsEntity(ilsResponse)));
        bibliographicEntity.setItemEntities(Arrays.asList(getItemEntity(ilsResponse)));

        return bibliographicEntity;

    }
    public HoldingsEntity getHoldingsEntity(ILSResponse ilsResponse) {
        //Random random = new Random();
        HoldingsEntity holdingsEntity = new HoldingsEntity();
        holdingsEntity.setContent(ilsResponse.getContent());
        holdingsEntity.setCreatedDate(new Date());
        holdingsEntity.setCreatedBy("test");
        holdingsEntity.setLastUpdatedDate(new Date());
        holdingsEntity.setLastUpdatedBy(ilsResponse.getLastUpdatedBY());
        holdingsEntity.setOwningInstitutionId(ilsResponse.getOwningInstitutionID());
        holdingsEntity.setOwningInstitutionHoldingsId(ilsResponse.getOwningInstitutionHoldingId());
        return holdingsEntity;
    }

    public ItemEntity getItemEntity(ILSResponse ilsResponse) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setCallNumberType(ilsResponse.getCallNumberType());
        itemEntity.setCallNumber(ilsResponse.getCallNumber());
        itemEntity.setCreatedDate(new Date());
        itemEntity.setCreatedBy("test");
        itemEntity.setLastUpdatedDate(ilsResponse.getLastUpdatedDate());
        itemEntity.setLastUpdatedBy(ilsResponse.getLastUpdatedBY());
        itemEntity.setBarcode(ilsResponse.getBarCode());
        itemEntity.setOwningInstitutionItemId(ilsResponse.getOwningInstitutionItemID());
        itemEntity.setOwningInstitutionId(ilsResponse.getOwningInstitutionID());
        itemEntity.setCollectionGroupId(ilsResponse.getCollectionGroupID());
        itemEntity.setCustomerCode(ilsResponse.getCustomerCode());
        itemEntity.setItemAvailabilityStatusId(ilsResponse.getItemAvailabilityStatusID());
        itemEntity.setHoldingsEntities(Arrays.asList(getHoldingsEntity(ilsResponse)));
        return itemEntity;
    }



}