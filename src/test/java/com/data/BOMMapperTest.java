/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        int bomId = 0;
        BillOfMaterials expResult = null;
//        BillOfMaterials result = BOMMapper.getBOM(bomId);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateBOM() throws Exception
    {
        System.out.println("createBOM");
        BillOfMaterials BOM = null;
//        BOMMapper.createBOM(BOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateBOM() throws Exception
    {
        System.out.println("updateBOM");
        BillOfMaterials BOM = null;
        BillOfMaterials newBOM = null;
//        BOMMapper.updateBOM(BOM, newBOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteBOM() throws Exception
    {
        System.out.println("deleteBOM");
        BillOfMaterials BOM = null;
//        BOMMapper.deleteBOM(BOM);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
