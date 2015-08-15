/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.serviceprovider.parsers;

import com.lyncode.xml.XmlReader;
import com.lyncode.xml.exceptions.XmlReaderException;
import org.dspace.xoai.model.oaipmh.Record;
import org.dspace.xoai.serviceprovider.exceptions.CannotDisseminateFormatException;
import org.dspace.xoai.serviceprovider.exceptions.IdDoesNotExistException;
import org.dspace.xoai.serviceprovider.exceptions.InvalidOAIResponse;
import org.dspace.xoai.serviceprovider.model.Context;
import org.hamcrest.Matcher;

import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

import static com.lyncode.xml.matchers.QNameMatchers.localPart;
import static com.lyncode.xml.matchers.XmlEventMatchers.elementName;
import static org.dspace.xoai.model.oaipmh.OAIPMHError.Code.CANNOT_DISSEMINATE_FORMAT;
import static org.dspace.xoai.model.oaipmh.OAIPMHError.Code.ID_DOES_NOT_EXIST;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRecordParser {

    private final XmlReader reader;
    private Context context;
    private String metadataPrefix;

    public GetRecordParser(InputStream stream, Context context, String metadataPrefix) {
        this.context = context;
        this.metadataPrefix = metadataPrefix;
        try {
            this.reader = new XmlReader(stream);
        } catch (XmlReaderException e) {
            throw new InvalidOAIResponse(e);
        }
    }

    public Record parse () throws IdDoesNotExistException, CannotDisseminateFormatException {
        try {
            reader.next(errorElement(), recordElement());
            if (reader.current(errorElement())) {
                String code = reader.getAttributeValue(localPart(equalTo("code")));
                if (ID_DOES_NOT_EXIST.code().equals(code))
                    throw new IdDoesNotExistException();
                else if (CANNOT_DISSEMINATE_FORMAT.code().equals(code))
                    throw new CannotDisseminateFormatException();
                else
                    throw new InvalidOAIResponse("OAI responded with error code: "+code);
            } else {
                return new RecordParser(context, metadataPrefix).parse(reader);
            }
        } catch (XmlReaderException e) {
            throw new InvalidOAIResponse(e);
        }
    }


    private Matcher<XMLEvent> errorElement() {
        return elementName(localPart(equalTo("error")));
    }

    private Matcher<XMLEvent> recordElement() {
        return elementName(localPart(equalTo("record")));
    }
}
