/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.xoai.serviceprovider;

import java.util.Iterator;

public class ServiceProviderTest extends AbstractServiceProviderTest {
//    private final ServiceProvider underTest = new ServiceProvider(theContext());
//
//    @Test
//    public void validIdentifyResponse() throws Exception {
//        theDataRepositoryConfiguration()
//                .withRepositoryName("NAME")
//                .withDeleteMethod(PERSISTENT);
//        Identify identify = underTest.identify();
//        assertThat(identify.getRepositoryName(), equalTo("NAME"));
//        assertThat(identify.getDeletedRecord(), equalTo(PERSISTENT));
//    }
//
//    @Test
//    public void validListMetadataFormatsResponse () throws Exception {
//        Iterator<MetadataFormat> metadataFormatIterator = underTest.listMetadataFormats();
//
//        assertThat(metadataFormatIterator.hasNext(), is(true));
//        MetadataFormat metadataFormat = metadataFormatIterator.next();
//        assertThat(metadataFormat.getMetadataPrefix(), equalTo(FORMAT));
//    }
//
//    @Test(expected = IdDoesNotExistException.class)
//    public void recordNotFoundForGetRecord () throws Exception {
//        underTest.getRecord(GetRecordParameters.request().withIdentifier("asd").withMetadataFormatPrefix(FORMAT));
//    }
//
//    @Test(expected = CannotDisseminateFormatException.class)
//    public void recordDoesNotSupportFormatForGetRecord () throws Exception {
//        theDataProviderContext().withMetadataFormat(FORMAT, identity(), alwaysFalseCondition());
//        theDataItemRepository().withItem(item().withDefaults().withIdentifier("asd").withSet("one"));
//        underTest.getRecord(GetRecordParameters.request().withIdentifier("asd").withMetadataFormatPrefix(FORMAT));
//    }
//
//    @Test(expected = NoSetHierarchyException.class)
//    public void listSetsWithNoSupportForSets () throws Exception {
//        theDataSetRepository().doesNotSupportSets();
//        underTest.listSets();
//    }
//
//    @Test
//    public void listSetsWithNoSets () throws Exception {
//        assertThat(underTest.listSets().hasNext(), is(false));
//    }
//
//    @Test
//    public void listSetsWithSetsOnePage() throws Exception {
//        theDataSetRepository().withRandomSets(5);
//        assertThat(count(underTest.listSets()), equalTo(5));
//    }
//
//    @Test
//    public void listSetsWithSetsTwoPages() throws Exception {
//        theDataRepositoryConfiguration().withMaxListSets(5);
//        theDataSetRepository().withRandomSets(10);
//        assertThat(count(underTest.listSets()), equalTo(10));
//    }
//
//
//    @Test
//    public void listIdentifiersWithNoItems () throws Exception {
//        assertThat(underTest.listIdentifiers(ListIdentifiersParameters.request().withMetadataPrefix(FORMAT)).hasNext(), is(false));
//    }
//
//    @Test
//    public void listIdentifiersWithOnePage() throws Exception {
//        theDataItemRepository().withRandomItems(5);
//        assertThat(count(underTest.listIdentifiers(ListIdentifiersParameters.request().withMetadataPrefix(FORMAT))), equalTo(5));
//    }
//
//    @Test
//    public void listIdentifiersWithTwoPages() throws Exception {
//        theDataRepositoryConfiguration().withMaxListIdentifiers(5);
//        theDataItemRepository().withRandomItems(10);
//        assertThat(count(underTest.listIdentifiers(ListIdentifiersParameters.request().withMetadataPrefix(FORMAT))), equalTo(10));
//    }
//
//
//    @Test
//    public void listRecordsWithNoItems () throws Exception {
//        assertThat(underTest.listRecords(ListRecordsParameters.request().withMetadataPrefix(FORMAT)).hasNext(), is(false));
//    }
//
//    @Test
//    public void listRecordsWithOnePage() throws Exception {
//        theDataItemRepository().withRandomItems(5);
//        assertThat(count(underTest.listRecords(ListRecordsParameters.request().withMetadataPrefix(FORMAT))), equalTo(5));
//    }
//
//    @Test
//    public void listRecordsWithTwoPages() throws Exception {
//        theDataRepositoryConfiguration().withMaxListRecords(5);
//        theDataItemRepository().withRandomItems(10);
//        assertThat(count(underTest.listRecords(ListRecordsParameters.request().withMetadataPrefix(FORMAT))), equalTo(10));
//    }

    private int count(Iterator<?> iterator) {
        int i = 0;
        while (iterator.hasNext()) {
            iterator.next();
            i++;
        }
        return i;
    }
}
