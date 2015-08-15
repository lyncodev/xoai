/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.model.oaipmh;

import com.google.common.base.Optional;

public class OAIPMHError {
    private final String message;
    private final Optional<Code> code;

    public OAIPMHError(String message, Optional<Code> code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Optional<Code> getCode() {
        return code;
    }
    //    @Override
//    public void write(XmlDataProviderWriter writer) throws XmlWriteException {
//        try {
//            if (this.code != null)
//                writer.writeAttribute("code", this.code.toString());
//
//            writer.writeCharacters(value);
//        } catch (XMLStreamException e) {
//            throw new XmlWriteException(e);
//        }
//    }

    public static enum Code {

        CANNOT_DISSEMINATE_FORMAT("cannotDisseminateFormat"),
        ID_DOES_NOT_EXIST("idDoesNotExist"),
        BAD_ARGUMENT("badArgument"),
        BAD_VERB("badVerb"),
        NO_METADATA_FORMATS("noMetadataFormats"),
        NO_RECORDS_MATCH("noRecordsMatch"),
        BAD_RESUMPTION_TOKEN("badResumptionToken"),
        NO_SET_HIERARCHY("noSetHierarchy");

        private final String code;

        Code(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }



        public static Code fromCode(String code) {
            for (Code c : Code.values()) {
                if (c.code.equals(code)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(code);
        }

        @Override
        public String toString() {
            return code;
        }
    }
}
