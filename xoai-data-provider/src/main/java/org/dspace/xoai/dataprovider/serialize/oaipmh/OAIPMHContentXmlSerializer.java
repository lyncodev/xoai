/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.serialize.oaipmh;

import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import org.dspace.xoai.dataprovider.serialize.XmlSerializer;
import org.dspace.xoai.dataprovider.xml.XmlWriter;
import org.dspace.xoai.model.oaipmh.OAIPMHContent;

import java.util.Map;

public class OAIPMHContentXmlSerializer implements XmlSerializer<OAIPMHContent> {
    private final Map<Class, XmlSerializer> serializers;

    public OAIPMHContentXmlSerializer(Map<Class, XmlSerializer> serializers) {
        this.serializers = serializers;
    }

    @Override
    public void serialize(XmlWriter xmlWriter, final OAIPMHContent input) {
        Optional.fromNullable(serializers.get(input.getClass()))
                .or(this.<XmlSerializer>throwException(String.format("No serializers for type '%s' found", input.getClass())))
                .serialize(xmlWriter, input);
    }

    private <T> Supplier<T> throwException(final String message) {
        return new Supplier<T>() {
            @Override
            public T get() {
                throw new IllegalArgumentException(message);
            }
        };
    }
}
