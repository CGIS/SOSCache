package org.cgis.dev.SOSResource;

import org.cgis.dev.ds.Offering;
import org.cgis.dev.ds.SOS;

/**
 * Created by Zil on 2016/9/17.
 */
public interface Resource {

    SOS getSOS();
    Offering getOffering(String name);
    void close();
}
