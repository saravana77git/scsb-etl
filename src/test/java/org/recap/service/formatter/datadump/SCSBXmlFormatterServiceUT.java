package org.recap.service.formatter.datadump;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.recap.BaseTestCase;
import org.recap.ReCAPConstants;
import org.recap.model.export.DataDumpRequest;
import org.recap.model.jaxb.JAXBHandler;
import org.recap.model.jaxb.marc.BibRecords;
import org.recap.model.jpa.BibliographicEntity;
import org.recap.model.jpa.InstitutionEntity;
import org.recap.repository.BibliographicDetailsRepository;
import org.recap.util.DBReportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by premkb on 23/8/16.
 */
public class SCSBXmlFormatterServiceUT extends BaseTestCase {

    private static final Logger logger = LoggerFactory.getLogger(SCSBXmlFormatterServiceUT.class);

    @Mock
    private Map itemStatusMap;

    @Mock
    private Map<String, Integer> institutionMap;

    @Mock
    private Map<String, Integer> collectionGroupMap;

    @Autowired
    BibliographicDetailsRepository bibliographicDetailsRepository;

    @Value("${etl.dump.directory}")
    private String dumpDirectoryPath;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    DBReportUtil dbReportUtil;

    private String bibContent = "<collection>\n"+
            "                <record>\n"+
            "                    <controlfield tag=\"001\">NYPG002000036-B</controlfield>\n"+
            "                    <controlfield tag=\"005\">20001116192424.2</controlfield>\n"+
            "                    <controlfield tag=\"008\">850225r19731907nyu b 001 0 ara</controlfield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"010\">\n"+
            "                        <subfield code=\"a\">   77173005  </subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"040\">\n"+
            "                        <subfield code=\"c\">NN</subfield>\n"+
            "                        <subfield code=\"d\">NN</subfield>\n"+
            "                        <subfield code=\"d\">CStRLIN</subfield>\n"+
            "                        <subfield code=\"d\">WaOLN</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"043\">\n"+
            "                        <subfield code=\"a\">ff-----</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"0\" ind2=\"0\" tag=\"050\">\n"+
            "                        <subfield code=\"a\">DS36.6</subfield>\n"+
            "                        <subfield code=\"b\">.I26 1973</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"0\" ind2=\"0\" tag=\"082\">\n"+
            "                        <subfield code=\"a\">910.031/767</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"1\" ind2=\" \" tag=\"100\">\n"+
            "                        <subfield code=\"a\">Ibn Jubayr, MuhÌ£ammad ibn AhÌ£mad,</subfield>\n"+
            "                        <subfield code=\"d\">1145-1217.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"1\" ind2=\"0\" tag=\"245\">\n"+
            "                        <subfield code=\"a\">RihÌ£lat</subfield>\n"+
            "                        <subfield code=\"b\">AbÄ« al-Husayn Muhammad ibn Ahmad ibn Jubayr al-KinÄ\u0081nÄ« al-AndalusÄ«\n"+
            "                            al-BalinsÄ«.\n"+
            "                        </subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"250\">\n"+
            "                        <subfield code=\"a\">2d ed.</subfield>\n"+
            "                        <subfield code=\"b\">rev. by M. J. de Goeje and printed for the Trustees of the \"E. J. W. Gibb\n"+
            "                            memorial\"\n"+
            "                        </subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"260\">\n"+
            "                        <subfield code=\"a\">[New York,</subfield>\n"+
            "                        <subfield code=\"b\">AMS Press,</subfield>\n"+
            "                        <subfield code=\"c\">1973] 1907.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"300\">\n"+
            "                        <subfield code=\"a\">363, 53 p.</subfield>\n"+
            "                        <subfield code=\"c\">23 cm.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"500\">\n"+
            "                        <subfield code=\"a\">Added t.p.: The travels of Ibn Jubayr. Edited from a ms. in the University\n"+
            "                            Library of Leyden by William Wright.\n"+
            "                        </subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"500\">\n"+
            "                        <subfield code=\"a\">Original ed. issued as v. 5 of \"E.J.W. Gibb memorial\" series.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"504\">\n"+
            "                        <subfield code=\"a\">Includes bibliographical references and index.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\"0\" tag=\"651\">\n"+
            "                        <subfield code=\"a\">Islamic Empire</subfield>\n"+
            "                        <subfield code=\"x\">Description and travel.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"1\" ind2=\" \" tag=\"700\">\n"+
            "                        <subfield code=\"a\">Wright, William,</subfield>\n"+
            "                        <subfield code=\"d\">1830-1889.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"1\" ind2=\" \" tag=\"700\">\n"+
            "                        <subfield code=\"a\">Goeje, M. J. de</subfield>\n"+
            "                        <subfield code=\"q\">(Michael Jan),</subfield>\n"+
            "                        <subfield code=\"d\">1836-1909.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\"0\" ind2=\" \" tag=\"740\">\n"+
            "                        <subfield code=\"a\">Travels of Ibn Jubayr.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\"0\" tag=\"830\">\n"+
            "                        <subfield code=\"a\">\"E.J.W. Gibb memorial\" series ;</subfield>\n"+
            "                        <subfield code=\"v\">v.5.</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"907\">\n"+
            "                        <subfield code=\"a\">.b100006279</subfield>\n"+
            "                        <subfield code=\"c\">m</subfield>\n"+
            "                        <subfield code=\"d\">a</subfield>\n"+
            "                        <subfield code=\"e\">-</subfield>\n"+
            "                        <subfield code=\"f\">ara</subfield>\n"+
            "                        <subfield code=\"g\">nyu</subfield>\n"+
            "                        <subfield code=\"h\">0</subfield>\n"+
            "                        <subfield code=\"i\">3</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"952\">\n"+
            "                        <subfield code=\"h\">*OAC (\"E. J. W. Gibb memorial\" series. v. 5)</subfield>\n"+
            "                    </datafield>\n"+
            "                    <datafield ind1=\" \" ind2=\" \" tag=\"952\">\n"+
            "                        <subfield code=\"h\">*OFV 87-659</subfield>\n"+
            "                    </datafield>\n"+
            "                    <leader>01814cam a2200409 450000</leader>\n"+
            "                </record>\n"+
            "            </collection>";

    private String holdingContent = "<collection>\n" +
            "                    <record>\n" +
            "                        <datafield ind1='0' ind2='1' tag='852'>\n" +
            "                            <subfield code='b'>rcppa</subfield>\n" +
            "                            <subfield code='t'>1</subfield>\n" +
            "                            <subfield code='h'>DF802</subfield>\n" +
            "                            <subfield code='i'>.xP45</subfield>\n" +
            "                            <subfield code='x'>tr fr anxafst</subfield>\n" +
            "                        </datafield>\n" +
            "                        <datafield ind1=' ' ind2='0' tag='866'>\n" +
            "                            <subfield code='x'>DESIGNATOR: t.</subfield>\n" +
            "                        </datafield>\n" +
            "                    </record>\n" +
            "                </collection>";

    @Autowired
    SCSBXmlFormatterService scsbXmlFormatterService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBibRecords() throws Exception{
        DataDumpRequest dataDumpRequest = new DataDumpRequest();
        List<Integer> cgIds = new ArrayList<>();
        cgIds.add(1);
        cgIds.add(2);
        dataDumpRequest.setCollectionGroupIds(cgIds);
        Map<String,Object> successAndFailureFormattedList = (Map<String,Object>)scsbXmlFormatterService.getFormattedOutput(Arrays.asList(getBibliographicEntity()));
        BibRecords bibRecords = xmlStringToBibRecords((String)successAndFailureFormattedList.get(ReCAPConstants.DATADUMP_FORMATTEDSTRING));
        assertNotNull(bibRecords);
        assertNotNull(bibRecords.getBibRecords());
        assertNotNull(bibRecords.getBibRecords().get(0).getBib());
        assertEquals("1",bibRecords.getBibRecords().get(0).getBib().getOwningInstitutionBibId());
        assertEquals("NYPL",bibRecords.getBibRecords().get(0).getBib().getOwningInstitutionId());
    }

    private BibliographicEntity getBibliographicEntity() throws URISyntaxException, IOException {
        BibliographicEntity bibliographicEntity = new BibliographicEntity();
        bibliographicEntity.setContent(bibContent.getBytes());
        bibliographicEntity.setCreatedDate(new Date());
        bibliographicEntity.setLastUpdatedDate(new Date());
        bibliographicEntity.setCreatedBy("tst");
        bibliographicEntity.setLastUpdatedBy("tst");
        bibliographicEntity.setOwningInstitutionBibId("1");
        bibliographicEntity.setOwningInstitutionId(3);
        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setInstitutionId(1);
        institutionEntity.setInstitutionCode("NYPL");
        institutionEntity.setInstitutionName("New York Public Library");
        bibliographicEntity.setInstitutionEntity(institutionEntity);
        return bibliographicEntity;
    }

    private BibRecords xmlStringToBibRecords(String xmlString) throws JAXBException {
        BibRecords bibRecords = (BibRecords)JAXBHandler.getInstance().unmarshal(xmlString,BibRecords.class);
        return bibRecords;
    }
}
