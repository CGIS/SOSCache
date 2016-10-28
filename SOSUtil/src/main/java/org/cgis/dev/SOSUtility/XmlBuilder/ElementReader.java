package org.cgis.dev.SOSUtility.XmlBuilder;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zil on 2016/10/27.
 */
public class ElementReader {

    private Element e;

    public ElementReader(Element e) {
        this.e = e;
    }

    public String text(){
        return e.getTextContent();
    }

    public String textOf(String tag){
        NodeList elementsByTagName = e.getElementsByTagName(tag);
        if (elementsByTagName.getLength() > 0)
            return elementsByTagName.item(0).getTextContent();
        return null;
    }

    public Set<ElementReader> setOf(String tag){
        Set<ElementReader> set = new HashSet<ElementReader>();
        NodeList list = e.getElementsByTagName(tag);
        for( int i = 0 ; i < list.getLength(); i++ ) {
            ElementReader reader = new ElementReader((Element) list.item(i));
            set.add(reader);
        }
        return set;
    }
}
