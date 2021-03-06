package org.recap.model.jaxb.marc;

import javax.xml.bind.annotation.*;

/**
 * Created by SheikS on 6/22/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "content", propOrder = {
        "collection"
})

@XmlRootElement(name = "content")
public class ContentType {
    @XmlElement(required = true, nillable = true)
    protected CollectionType collection;

    public CollectionType getCollection() {
        return collection;
    }

    public void setCollection(CollectionType collection) {
        this.collection = collection;
    }
}
