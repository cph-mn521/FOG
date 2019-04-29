package com.data;

import com.entities.dto.BillOfMaterials;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author martin
 */
public class BOMMapperTest
{
    
    public BOMMapperTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetBOM() throws Exception
    {
        System.out.println("getBOM");
        int orderId = 0;
        BOMMapper instance = new BOMMapper();
        BillOfMaterials expResult = null;
        BillOfMaterials result = instance.getBOM(orderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateBOM() throws Exception
    {
        System.out.println("createBOM");
        BillOfMaterials BOM = null;
        BOMMapper instance = new BOMMapper();
        instance.createBOM(BOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateBOM() throws Exception
    {
        System.out.println("updateBOM");
        BillOfMaterials BOM = null;
        BillOfMaterials newBOM = null;
        BOMMapper instance = new BOMMapper();
        instance.updateBOM(BOM, newBOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteBOM() throws Exception
    {
        System.out.println("deleteBOM");
        BillOfMaterials BOM = null;
        BOMMapper instance = new BOMMapper();
        instance.deleteBOM(BOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
