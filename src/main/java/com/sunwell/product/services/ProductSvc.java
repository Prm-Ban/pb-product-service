package com.sunwell.product.services;

import java.io.File;



import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sunwell.product.exception.OperationException;
import com.sunwell.product.model.Item;
import com.sunwell.product.model.ItemCategory;
import com.sunwell.product.model.Merk;
import com.sunwell.product.model.Metrics;
import com.sunwell.product.model.Product;
import com.sunwell.product.model.ProductImage;
import com.sunwell.product.model.ProductSellPrice;
import com.sunwell.product.model.SellPriceLevel;
import com.sunwell.product.repository.ItemCategoryRepo;
import com.sunwell.product.repository.ItemRepo;
import com.sunwell.product.repository.MerkRepo;
import com.sunwell.product.repository.MetricRepo;
import com.sunwell.product.repository.ProductImageRepo;
import com.sunwell.product.repository.ProductSellPriceRepo;
import com.sunwell.product.repository.SellPriceLevelRepo;
import com.sunwell.product.utils.Filters;
import com.sunwell.product.utils.GenericSpecification;
import com.sunwell.product.utils.StandardConstant;


@Service
@Transactional
@Validated
public class ProductSvc implements ProductService
{	
	@Autowired
	ItemRepo itemRepo;
	
	@Autowired
	ProductSellPriceRepo pspRepo;
	
	@Autowired
	ItemCategoryRepo icRepo;
	
	@Autowired
	MerkRepo merkRepo;
	
	@Autowired
	MetricRepo metricRepo;;
	
	@Autowired
	SellPriceLevelRepo splRepo;
	
	@Autowired
	ProductImageRepo piRepo;
	
//	@Autowired
//	ItemShipmentInfoRepo shipmentRepo;
	
	public ItemRepo getItemRepo() {
		return itemRepo;
	}

	public void setItemRepo(ItemRepo itemRepo) {
		this.itemRepo = itemRepo;
	}

	public ProductSellPriceRepo getPspRepo() {
		return pspRepo;
	}

	public void setPspRepo(ProductSellPriceRepo pspRepo) {
		this.pspRepo = pspRepo;
	}

	public ItemCategoryRepo getIcRepo() {
		return icRepo;
	}

	public void setIcRepo(ItemCategoryRepo icRepo) {
		this.icRepo = icRepo;
	}

	public MerkRepo getMerkRepo() {
		return merkRepo;
	}

	public void setMerkRepo(MerkRepo merkRepo) {
		this.merkRepo = merkRepo;
	}

	public MetricRepo getMetricRepo() {
		return metricRepo;
	}

	public void setMetricRepo(MetricRepo metricRepo) {
		this.metricRepo = metricRepo;
	}

	public SellPriceLevelRepo getSplRepo() {
		return splRepo;
	}

	public void setSplRepo(SellPriceLevelRepo splRepo) {
		this.splRepo = splRepo;
	}

	public ProductImageRepo getPiRepo() {
		return piRepo;
	}

	public void setPiRepo(ProductImageRepo piRepo) {
		this.piRepo = piRepo;
	}

	public ProductSvc() {
		
	}
	
//	public ProductSvc()
	
	public Item findItem(@NotNull(message="{error_no_id}") Integer _id) {
		return itemRepo.findById(_id).orElse(null);
	}
	
	public Item findItemByName(String _name) {
		return itemRepo.findByName(_name);
	}
	
	public Page<Item> findAllItems(Pageable _page) {
		return itemRepo.findAll(_page);
	}
	
	public Page<Item> findItems(Filters _f, Pageable _page) throws Exception {
		return itemRepo.findAll(new GenericSpecification<Item>(_f, Item.class), _page);
	}
		
	public Page<Item> findByCategory(ItemCategory _ic, Pageable _page) {
		return itemRepo.findByCategoriesIn(_ic, _page);
	}
	
	public Page<Item> findByCategoryId(Integer _id, Pageable _page) {
		return itemRepo.findByCategories_SystemIdIn(_id, _page);
	}
	
	public List<Item> findAllItems() {
		return itemRepo.findAll();
	}	
	
	public Item addItem(
    		@Valid @NotNull(message="{error_no_item}") Item _i, ProductImage _pi) 
    {     
		prepareItem(_i);
        Item item = itemRepo.saveAndFlush(_i);
        _pi.setProduct(item);
        if(_pi != null) 
        	addProductImage(_pi);
        
        return item;
//		if(_i.getSellPrices() != null && _i.getSellPrices().size() > 0) {
//			List<ProductSellPrice> listPSP = new LinkedList<>();
//        	for(ProductSellPrice psp : _i.getSellPrices()) {
//        		ProductSellPrice sellPrice = pspRepo.findOne(new ProductSellPricePK(psp.getProduct().getSystemId(), psp.getPriceLevel().getSystemId()));
//        		if(sellPrice == null)
//        			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SELL_PRICE, null);
//        	}
//        	_i.setSellPrices(listPSP);
//        }
//		
//		if(_i.getCategories() != null && _i.getCategories().size() > 0) {
//			List<ItemCategory> listCategories = new LinkedList<>();
//        	for(ItemCategory ic : _i.getCategories()) {
//        		ItemCategory category = icRepo.findOne(ic.getSystemId());
//        		if(category == null)
//        			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CATEGORY, null);
//        	}
//        	_i.setCategories(listCategories);
//        }
//		
//		if(_i.getMerk() != null) {
//			Merk merk = merkRepo.findOne(_i.getMerk().getSystemId());
//			if(merk == null)
//    			throw new OperationException(StandardConstant.ERROR_CANT_FIND_MERK, null);
//		}
//		
//		if(_i.getMetric() != null) {
//			Metrics metric = metricRepo.findOne(_i.getMetric().getName());
//			if(metric == null)
//    			throw new OperationException(StandardConstant.ERROR_CANT_FIND_METRIC, null);
//		}
		
		// karena nested attribute maka harus dimanage
		// dari luar mungkin udah diambil entity utuh tapi jangan berasumsi entity tersebut sudah dimanage
		// kalau diambil di kontroler maka kontroler tersebut tidak berada dalam transaksi sehingga entitynya bukan managed entity
		
//		if(_i.getSellPrices() != null && _i.getSellPrices().size() > 0) {
//        	for(ProductSellPrice psp : _i.getSellPrices()) {
//        		System.out.println("Item: " + psp.getProduct().getName() + " sell price lv: " + psp.getPriceLevel().getSystemId());
//        		psp.setPriceLevel(splRepo.findOne(psp.getPriceLevel().getSystemId()));
//        	}
//        }
    }
	
	public Item editItem(
    		@Valid @NotNull(message="{error_no_item}") Item _i, ProductImage _pi) 
    {
		Item item = itemRepo.findById(_i.getSystemId()).orElse(null);
		
		if(item == null) 
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);	
		
		prepareItem(_i);
		
		for(ProductSellPrice psp : _i.getSellPrices()) {
			psp.setProduct(item);
		}
		
		if(item.getCategories() != null) {
			item.setCategories(null);
		}
		
		if(item.getSellPrices() != null) {
			pspRepo.deleteAll(item.getSellPrices());
//			for(ProductSellPrice psp : item.getSellPrices()) {
//				pspRepo.de
//			}
			item.setSellPrices(null);
		}
		
		itemRepo.flush();	
		
		item.setCategories(_i.getCategories());
		item.setSellPrices(_i.getSellPrices());
		item.setActive(_i.isActive());
		item.setCode(_i.getCode());
		item.setMemo(_i.getMemo());
		item.setMerk(_i.getMerk());
		item.setMetric(_i.getMetric());
		item.setMinimumStock(_i.getMinimumStock());
		item.setName(_i.getName());
		item.setSku(_i.getSku());
		
		if(_pi != null) 
        	editProductImage(_pi);
		else {
			ProductImage pi = findProductImage(item);
			if(pi != null) 
				deleteProductImage(item);
		}
		
		return item;
    }
        
    public Item deleteItem(@NotNull(message="{error_no_id}") Integer _id) {
		try {
    		Item item = itemRepo.findById(_id).orElse(null);
    		if(item == null) 
    			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
		    
//    		ProductImage pi = findProductImage(new ProductImagePK(item.getSystemId()));
    		ProductImage pi = findProductImage(item);
		    if(pi != null) 
		    	deleteProductImage(item);
    		
    		itemRepo.deleteById(_id);
		    		    
		    return item;
		}
		catch(IllegalArgumentException e) {
			throw new OperationException(e, StandardConstant.ERROR_CANT_FIND_PRODUCT);
		}
    }
	
	public ItemCategory findCategory(@NotNull(message="{error_no_id}") Integer _id) {
		return icRepo.findById(_id != null ? _id : -1).orElse(null);
	}
	
	public ItemCategory findCategoryByName(String _name) {
		return icRepo.findByName(_name);
	}
	
	public ItemCategory findCategoryByCode(String _code) {
		return icRepo.findByCode(_code);
	}
	
	public Page<ItemCategory> findAllCategories(Pageable _page) {
		return icRepo.findAll(_page);
	}
	
	public Page<ItemCategory> findCategories(Filters _f, Pageable _page) throws Exception {
		return icRepo.findAll(new GenericSpecification<ItemCategory>(_f, ItemCategory.class), _page);
	}
	
	public List<ItemCategory> findAllCategories() {
		return icRepo.findAll();
	}
	
	public ItemCategory addCategory(
    		@Valid @NotNull(message="{error_no_category}") ItemCategory _ctgr) 
    {
		return icRepo.save(_ctgr);
    }
    
    public ItemCategory editCategory(
    		@Valid @NotNull(message="{error_no_category}") ItemCategory _ctgr) 
    {
		ItemCategory ctgr = icRepo.findById(_ctgr.getSystemId()).orElse(null);
		if(ctgr == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CATEGORY, null);
		return icRepo.save(_ctgr);
    }
    
    public ItemCategory deleteCategory(@NotNull(message="{error_no_id}") Integer _id) {
    	ItemCategory ctgr = icRepo.findById(_id).orElse(null);
		if(ctgr == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CATEGORY, null);
	    icRepo.delete(ctgr);
	    return ctgr;
    }
    
    public Merk findMerk(@NotNull(message="{error_no_id}")Integer _id) {
		return merkRepo.findById(_id).orElse(null);
	}
	
	public Merk findMerkByName(String _name) {
		return merkRepo.findByName(_name);
	}
	
	public Page<Merk> findMerks(Filters _f, Pageable _page) throws Exception {
		return merkRepo.findAll(new GenericSpecification<Merk>(_f, Merk.class), _page);
	}
	
	public Page<Merk> findAllMerks(Pageable _page) {
		return merkRepo.findAll(_page);
	}
	
	public Merk addMerk(
    		@Valid @NotNull(message="{error_no_merk}") Merk _merk) 
    {
		return merkRepo.save(_merk);
    }
    
    public Merk editMerk(
    		@Valid @NotNull(message="{error_no_category}") Merk _merk) 
    {
		Merk merk = merkRepo.findById(_merk.getSystemId()).orElse(null);
		if(merk == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_MERK, null);
		return merkRepo.save(_merk);
    }
    
    public Merk deleteMerk(@NotNull(message="{error_no_id}") Integer _id) {
    	Merk merk = merkRepo.findById(_id).orElse(null);
		if(merk == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_MERK, null);
	    merkRepo.delete(merk);
	    return merk;
    }
    
    public ProductImage findProductImage(@NotNull(message="{error_no_product}")Product _p) {
		return piRepo.findById(_p.getSystemId()).orElse(null);
	}
    
    public ProductImage findProductImageByProductName(@NotNull(message="{error_no_name}")String _p) {
		return piRepo.findByProductName(_p);
	}
    
    public ProductImage addProductImage(@Valid @NotNull(message="{error_no_product_image}") ProductImage _pi) {
    	prepareProductImage(_pi);
		return piRepo.save(_pi);
	}
    
    public ProductImage editProductImage(@Valid @NotNull(message="{error_no_product_image}") ProductImage _pi) {
		ProductImage pi = piRepo.findByProduct(_pi.getProduct());
		if(pi == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT_IMAGE, null);
		prepareProductImage(_pi);
		piRepo.save(pi);
		return pi;
	}
    
    public ProductImage deleteProductImage(@NotNull(message="{error_no_product}")Product _p) {
    	ProductImage pi = piRepo.findById(_p.getSystemId()).orElse(null);
		if(pi == null)
			throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT_IMAGE, null);
	    piRepo.delete(pi);
	    return pi;
    }
	
	public List<Merk> findAllMerks() {
		return merkRepo.findAll();
	}
	
	public Metrics findMetrics(String _name) {
		return metricRepo.findById(_name).orElse(null);
	}
	
	public Page<Metrics> findAllMetrics(Pageable _page) {
		return metricRepo.findAll(_page);
	}
	
	public SellPriceLevel findSellPriceLevelByName(String _name) {
		return splRepo.findByName(_name);
	}
	
	private void prepareItem(Item _i) {
		
		_i.setMetric(metricRepo.findDefaultMetric());
		
		if(_i.getSellPrices() != null && _i.getSellPrices().size() > 0) {
        	for(ProductSellPrice psp : _i.getSellPrices()) {
        		SellPriceLevel spl = null;
        		if(psp.getPriceLevel().getSystemId() > 0) 
        			spl = splRepo.findById(psp.getPriceLevel().getSystemId()).orElse(null);
        		else if(psp.getPriceLevel().getName() != null)
        			spl = splRepo.findByName(psp.getPriceLevel().getName());
        		if(spl == null)
        			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SELL_PRICE, null);
        		psp.setPriceLevel(spl);
        	}
        }
		
		if(_i.getCategories() != null && _i.getCategories().size() > 0) {
			System.out.println("Categories size: " + _i.getCategories().size());
			List<ItemCategory> listCategories = new LinkedList<>();
        	for(ItemCategory ic : _i.getCategories()) {
        		ItemCategory category = null;
        		System.out.println("Category id: " + ic.getSystemId());
        		if(ic.getSystemId() > 0)
        			category = icRepo.findById(ic.getSystemId()).orElse(null);
        		else if(ic.getName() != null)
        			category = icRepo.findByName(ic.getName());
        		if(category == null)
        			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CATEGORY, null);
        		listCategories.add(category);
        	}
        	_i.setCategories(listCategories);
        }
		
		if(_i.getMerk() != null) {
			Merk merk = null ;
			if(_i.getMerk().getSystemId() > 0) 
				merk = merkRepo.findById(_i.getMerk().getSystemId()).orElse(null);
			else if(_i.getMerk().getName() != null) 
				merk = merkRepo.findByName(_i.getMerk().getName());
			if(merk == null)
    			throw new OperationException(StandardConstant.ERROR_CANT_FIND_MERK, null);
			
			_i.setMerk(merk);
		}
		
		if(_i.getMetric() != null) {
			Metrics metric = null;
			if(_i.getMetric().getName() != null)
				metric = metricRepo.findById(_i.getMetric().getName()).orElse(null);;
			if(metric == null)
    			throw new OperationException(StandardConstant.ERROR_CANT_FIND_METRIC, null);
			
			_i.setMetric(_i.getMetric());
		}
	}
	
	private void prepareProductImage(ProductImage _pi) {
    	Item item = findItem(_pi.getProduct().getSystemId());
    	if(item == null)
    		throw new OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
    	
    	_pi.setProduct(item);
    }
		
	
//	public Item addItem(
//    		@Valid @NotNull(message="{error_no_item}") Item _i, String _imgData, String _imgPath) 
//    {        
//        if(_imgData != null) {
//	    		String uploadedFileLocation = _imgPath + "items/" + _i.getAlternativeToItem();
//			File dir = new File(_imgPath + "items/");
//			if(!dir.exists ()) {
//			    dir.mkdir ();
//			}
//			
//	      Util.writeToFile(Base64.getDecoder ().decode (
//	        		_imgData), uploadedFileLocation);
//        }
//        
//        return itemRepo.save(_i);
//    }
	
//	public ItemShipmentInfo findShipment(Integer _id) {
//		return shipmentRepo.findOne(_id);
//	}		
//	
//	public ItemShipmentInfo findShipmentByName(String _name) {
//		return shipmentRepo.findByName(_name);
//	}
//	
//	public List<ItemShipmentInfo> findAllShipments() {
//		return shipmentRepo.findAll();
//	}
//	
//	public Page<ItemShipmentInfo> findAllShipments(Pageable _page) {
//		return shipmentRepo.findAll(_page);
//	}
	
//  public Item editItem(
//	@Valid @NotNull(message="{error_no_item}") Item _i, String _imgData, String _imgPath) 
//{
//Item item = itemRepo.findOne(_i.getSystemId());
//
//if(item == null) 
//	throw new sunwell.permaisuri.bus.exception.OperationException(StandardConstant.ERROR_CANT_FIND_PRODUCT, null);
//				
//if(_imgData != null) {
//		String uploadedFileLocation = _imgPath + "items/"  + _i.getImage();
//	File dir = new File(_imgPath + "products/");
//	if(!dir.exists ()) {
//	    dir.mkdir ();
//	}
//    Util.writeToFile(Base64.getDecoder ().decode (
//    		_imgData), uploadedFileLocation);
//}	
//
//if(item.getCategories() != null) {
//	item.setCategories(null);
//	itemRepo.flush();
//}
//
//return itemRepo.save(_i);
//}
		
	
//	1. Imagenya bisa langsung dibuka dan diperiksa
//	2. Memudahkan ketika AJAX request karena disimpan sebagai static resource, kita cuman perlu kasih url ke static resourcenya
//	3. Tanpa AJAX data imagenya harus langsung disertakan beserta data lain
//	3. Performa database menurun dengan besarnya data binari ( dari yang saya baca )
	
}
