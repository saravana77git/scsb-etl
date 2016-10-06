package org.recap.controller.swagger;

import io.swagger.annotations.*;
import org.recap.ReCAPConstants;
import org.recap.model.accession.ILSResponse;
import org.recap.service.accession.AccessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by saravanakumarp on 20/9/16.
 */


@RestController
@RequestMapping("/accession")
@Api(value="bibInfo", description="Get Item and customer code", position = 1)
public class AccessionRestController {

    private static final Logger logger = LoggerFactory.getLogger(AccessionRestController.class);

    @Autowired
    ApplicationContext appContext;

    @Autowired
    AccessionService accessionService;


    @RequestMapping(value="/accessionItem", method = RequestMethod.POST)

    @ResponseBody
    public ResponseEntity exportItemBarCodeandCustomerCode(@ApiParam(value = "itemBarCode" , required = true, name = "itemBarCode") @RequestParam String itemBarCode,
                                         @ApiParam(value = "customerCode ",required=true, name = "customerCode") @RequestParam String customerCode
                                         )

    {
        accessionService.saveBibliographic(  accessionService.getBibliographicEntity( accessionService.getILSResponseObject()));

        return  new ResponseEntity(ReCAPConstants.DATADUMP_EXPORT_SUCCESS, HttpStatus.OK);
    }













}
