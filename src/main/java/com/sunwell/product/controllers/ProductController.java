/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * LoginService.java
 *
 * Created on Oct 16, 2017, 10:46:29 AM
 */

package com.sunwell.product.controllers;


import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sunwell.product.annotation.Example;
import com.sunwell.product.dto.ItemCategoryDTO;
import com.sunwell.product.dto.ItemDTO;
import com.sunwell.product.dto.MerkDTO;
import com.sunwell.product.model.Item;
import com.sunwell.product.model.ItemCategory;
import com.sunwell.product.model.Merk;
import com.sunwell.product.services.ProductService;
import com.sunwell.product.utils.ServiceUtil;

/**
 *
 * @author Benny
 */
@RestController
public class ProductController
{
	@Autowired
	ProductService productService;
	
	@Autowired
    ServiceUtil svcUtil;
    
    @Autowired
    ServletContext sCtx;
    
    @Autowired
    MessageSource messageSource;
    
    @Autowired
    HttpServletRequest request;  
    
    
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    @RequestMapping(value = "resources/categories", method = RequestMethod.GET,
			produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> getCategories(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam(value="systemId", required = false) Integer _systemId,
    		@RequestParam(value="name", required = false) String _name,
    		@RequestParam(value="code", required = false) String _code,
    		Pageable _page
    		) throws Exception 
    {
    	logger.info("getCategories from ProductController is called");
		Map<String,Object> retData = null;
		
		try {
			
			Object mainData = null;
			ItemCategory ic = null;
    		Page<ItemCategory> pageCategory = null ;
    		int totalPages = 0;
			long totalItems = 0;
            
            if(_systemId != null) {
    			ic = productService.findCategory(_systemId);
    		}
    		else if(_name != null) {
    			ic = productService.findCategoryByName(_name);
    		}
    		else if(_code != null) {
    			ic = productService.findCategoryByCode(_code);
    		}
            else {
            	pageCategory = productService.findAllCategories(_page);
            }
            
            if(pageCategory != null && pageCategory.getNumberOfElements() > 0) {
    			List<ItemCategory> categories = pageCategory.getContent();
    			List<ItemCategoryDTO> categoriesData = new LinkedList<>();
    			for(ItemCategory i : categories) {
    				categoriesData.add(new ItemCategoryDTO(i));
    			}
    			mainData = categoriesData;
    			totalPages = pageCategory.getTotalPages();
    			totalItems = pageCategory.getTotalElements();
    		}
    		else if (ic != null) {
    			mainData = new ItemCategoryDTO(ic);
    			totalPages = 1;
    			totalItems = 1;
    		}
            
            retData = svcUtil.returnSuccessfulData(mainData, totalPages, totalItems);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
    	logger.info("getCategories from ProductController is returning");
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "resources/categories", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> addCategory(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody ItemCategoryDTO _dto) throws Exception 
    {
		Map<String,Object> retData = null;
		try {
			ItemCategory data = productService.addCategory(_dto.getData());
			retData = svcUtil.returnSuccessfulData(new ItemCategoryDTO(data), 1, 1);
			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
			return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
    
    @RequestMapping(value = "resources/categories", method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> editCategory(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody ItemCategoryDTO _dto) throws Exception 
    {
		Map<String,Object> retData = null;
		
		try {
			ItemCategory data = productService.addCategory(_dto.getData());
			retData = svcUtil.returnSuccessfulData(new ItemCategoryDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "resources/categories", method = RequestMethod.DELETE,
			produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> deleteCategory(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam("systemId") Integer _systemId) throws Exception 
    {
		Map<String,Object> retData = null;
		try {
			ItemCategory data = productService.deleteCategory(_systemId);
			retData = svcUtil.returnSuccessfulData(new ItemCategoryDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
    
    @RequestMapping(value = "resources/merks", method = RequestMethod.GET,
			produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> getMerks(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam(value="systemId", required = false) Integer _systemId,
    		@RequestParam(value="name", required = false) String _name,
    		Pageable _page
    		) throws Exception 
    {
		Map<String,Object> retData = null;
		
		try {			
			Object mainData = null;
			Merk merk = null;
    		Page<Merk> pageMerk = null ;
    		int totalPages = 0;
			long totalItems = 0;
            
            if(_systemId != null) {
            	System.out.println("findMerk is called");
    			merk = productService.findMerk(_systemId);
    		}
    		else if(_name != null) {
    			System.out.println("findMerkByName is called");
    			merk = productService.findMerkByName(_name);
    		}
            else {
            	System.out.println("findAllMerks is called");
            	pageMerk = productService.findAllMerks(_page);
            	System.out.println("Num of elements: " + pageMerk.getNumberOfElements());
            }
            
            if(pageMerk != null && pageMerk.getNumberOfElements() > 0) {
    			List<Merk> merks = pageMerk.getContent();
    			List<MerkDTO> merksData = new LinkedList<>();
    			for(Merk m : merks) {
    				merksData.add(new MerkDTO(m));
    			}
    			mainData = merksData;
    			totalPages = pageMerk.getTotalPages();
    			totalItems = pageMerk.getTotalElements();
    		}
    		else if (merk != null) {
    			mainData = new MerkDTO(merk);
    			totalPages = 1;
    			totalItems = 1;
    		}
            
            retData = svcUtil.returnSuccessfulData(mainData, totalPages, totalItems);
		}
		catch(Exception e) {
			System.out.println("EXCPT OCCUR: " + e.getMessage());
			e.printStackTrace();
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "resources/merks", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> addMerk(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody MerkDTO _dto) throws Exception 
    {
		Map<String,Object> retData = null;
		try {
			Merk data = productService.addMerk(_dto.getData());
			retData = svcUtil.returnSuccessfulData(new MerkDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "resources/merks", method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> editMerk(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody MerkDTO _dto) throws Exception 
    {
		Map<String,Object> retData = null;
		
		try {
			Merk data = productService.addMerk(_dto.getData());
			retData = svcUtil.returnSuccessfulData(new MerkDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "resources/merks", method = RequestMethod.DELETE,
			produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> deleteMerk(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam("systemId") Integer _systemId) throws Exception 
    {
		Map<String,Object> retData = null;
		try {
			Merk data = productService.deleteMerk(_systemId);
			retData = svcUtil.returnSuccessfulData(new MerkDTO(data), 1, 1);
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "resources/items", method = RequestMethod.GET,
			produces = "application/json"
	)
    @Example(msg="message")
    public ResponseEntity<Map<String,Object>> getItems(
    		@RequestHeader(value="Authorization", required = false) String _auth,
			@RequestParam(value="systemId", required = false) Integer _systemId,
			@RequestParam(value="name", required = false) String _name,
			@RequestParam(value="categoryId", required = false) Integer _categoryId,
			Pageable _page ) throws Exception 
    {
		Map<String,Object> retData = null;
        try {
        	
			Object mainData = null;
        	Item item = null;
            Page<Item> pageItems = null;
            int totalPages = 0;
			long totalItems = 0;
			
            if(_systemId != null) {
    			item = productService.findItem(_systemId);
    		}
    		else if(_name != null) {
    			item = productService.findItemByName(_name);
    		}
    		else {
    			if(_categoryId != null) {
        			pageItems = productService.findByCategoryId(_categoryId, _page);
        		}
    			else 
    				pageItems = productService.findAllItems(_page);
    		}
            if(pageItems != null && pageItems.getNumberOfElements() > 0) {
            	List<Item> items = pageItems.getContent();
    			List<ItemDTO> itemsData = new LinkedList<>();
    			for(Item i : items) {
    				itemsData.add(new ItemDTO(i));
    			}
    			mainData = itemsData;
    			totalPages = pageItems.getTotalPages();
    			totalItems = pageItems.getTotalElements();
    		}
    		else if (item != null) {
    			mainData = new ItemDTO(item);
    			totalPages = 1;
    			totalItems = 1;
    		}
            retData = svcUtil.returnSuccessfulData(mainData, totalPages, totalItems);
        }
        catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
    
    @RequestMapping(value = "resources/items", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> addItem(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody ItemDTO _dto) throws Exception  
    {        
		Map<String,Object> retData = null;
		try {
            Item data = _dto.getData();
            data = productService.addItem(data, _dto.getProductImage());
            retData = svcUtil.returnSuccessfulData(new ItemDTO(data), 1, 1) ;
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "resources/items", method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> editItem(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestBody ItemDTO _dto) throws Exception  
    {        
		Map<String,Object> retData = null;
		try {
			Item data = _dto.getData();
            data = productService.editItem(data, _dto.getProductImage());
            retData = svcUtil.returnSuccessfulData(new ItemDTO(data), 1, 1) ;
		}
		catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "resources/items", method = RequestMethod.DELETE,
			produces = "application/json"
	)
    public ResponseEntity<Map<String,Object>> deleteItem(
    		@RequestHeader(value="Authorization", required = false) String _auth,
    		@RequestParam("systemId") Integer _systemId) throws Exception 
    {
		Map<String,Object> retData = null;
    	try {
    		Item data = productService.deleteItem(_systemId);
            retData = svcUtil.returnSuccessfulData(new ItemDTO(data), 1, 1);
    	}
    	catch(Exception e) {
			retData = svcUtil.handleException(e);
		}
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }
}



