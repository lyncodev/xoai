/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.dataprovider.xml;

import org.codehaus.stax2.XMLOutputFactory2;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.OutputStream;

public class XmlWriter implements XMLStreamWriter {
    private static XMLOutputFactory factory = XMLOutputFactory2.newFactory();
    private XMLStreamWriter writer;
    private OutputStream outputStream;

    public XmlWriter(OutputStream output) {
        try {
            this.writer = factory.createXMLStreamWriter(output);
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
        this.outputStream = output;
    }

    public void writeStartElement(String localName) {
        try {
            this.writer.writeStartElement(localName);
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void writeStartElement(String namespaceURI, String localName) {
        try {
            this.writer.writeStartElement(namespaceURI, localName);
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void writeStartElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        this.writer.writeStartElement(prefix, localName, namespaceURI);
    }

    public void writeEmptyElement(String namespaceURI, String localName) throws XMLStreamException {
        this.writer.writeEmptyElement(namespaceURI, localName);
    }

    public void writeEmptyElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        this.writer.writeEmptyElement(prefix, localName, namespaceURI);
    }

    public void writeEmptyElement(String localName) throws XMLStreamException {
        this.writer.writeEmptyElement(localName);
    }

    public void writeEndElement() {
        try {
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void writeEndDocument() throws XMLStreamException {
        this.writer.writeEndDocument();
    }

    public void close() {
        try {
            this.writer.close();
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void flush() {
        try {
            this.writer.flush();
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void writeAttribute(String localName, String value) {
        try {
            this.writer.writeAttribute(localName, value);
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void writeAttribute(String prefix, String namespaceURI, String localName, String value) {
        try {
            this.writer.writeAttribute(prefix, namespaceURI, localName, value);
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void writeAttribute(String namespaceURI, String localName, String value) throws XMLStreamException {
        this.writer.writeAttribute(namespaceURI, localName, value);
    }

    public void writeNamespace(String prefix, String namespaceURI) {
        try {
            this.writer.writeNamespace(prefix, namespaceURI);
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void writeDefaultNamespace(String namespaceURI) {
        try {
            this.writer.writeDefaultNamespace(namespaceURI);
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void writeComment(String data) throws XMLStreamException {
        this.writer.writeComment(data);
    }

    public void writeProcessingInstruction(String target) throws XMLStreamException {
        this.writer.writeProcessingInstruction(target);
    }

    public void writeProcessingInstruction(String target, String data) throws XMLStreamException {
        this.writer.writeProcessingInstruction(target, data);
    }

    public void writeCData(String data) throws XMLStreamException {
        this.writer.writeCData(data);
    }

    public void writeDTD(String dtd) throws XMLStreamException {
        this.writer.writeDTD(dtd);
    }

    public void writeEntityRef(String name) throws XMLStreamException {
        this.writer.writeEntityRef(name);
    }

    public void writeStartDocument() throws XMLStreamException {
        this.writer.writeStartDocument();
    }

    public void writeStartDocument(String version) throws XMLStreamException {
        this.writer.writeStartDocument(version);
    }

    public void writeStartDocument(String encoding, String version) throws XMLStreamException {
        this.writer.writeStartDocument(encoding, version);
    }

    public void writeCharacters(String text) {
        try {
            this.writer.writeCharacters(text);
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void writeCharacters(char[] text, int start, int len) throws XMLStreamException {
        this.writer.writeCharacters(text, start, len);
    }

    public String getPrefix(String uri) throws XMLStreamException {
        return this.writer.getPrefix(uri);
    }

    public void setPrefix(String prefix, String uri) throws XMLStreamException {
        this.writer.setPrefix(prefix, uri);
    }

    public void setDefaultNamespace(String uri) {
        try {
            this.writer.setDefaultNamespace(uri);
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }

    public void setNamespaceContext(NamespaceContext context) throws XMLStreamException {
        this.writer.setNamespaceContext(context);
    }

    public NamespaceContext getNamespaceContext() {
        return this.writer.getNamespaceContext();
    }

    public Object getProperty(String name) throws IllegalArgumentException {
        return this.writer.getProperty(name);
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public void writeElement(String name, String value) {
        try {
            this.writer.writeStartElement(name);
            this.writer.writeCharacters(value);
            this.writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new XmlWriteException(e);
        }
    }
}
