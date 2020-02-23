/*
 * Product.java
 *
 * Created on December 12, 2007, 9:00 PM
 */
package com.sunwell.product.dto;

import java.util.Calendar;




import java.util.LinkedList;
import java.util.List;

import com.sunwell.product.model.Item;
import com.sunwell.product.model.ItemCategory;
import com.sunwell.product.model.Merk;
import com.sunwell.product.model.Metrics;
import com.sunwell.product.model.ProductSellPrice;


public class ItemDTO extends ProductDTO
{
    private List<ItemCategoryDTO> categories ;
    private String categoriesString;
    private String shipmentInfo;
    private String merk;
    private String metric;
    private String code;
    private String sku = "";
    private String imageData = null;
    private Double shelfLife ;
    private Float minimumStock;
    
    
    /**
     * @roseuid 45C9A40001F4
     */
    public ItemDTO ()
    {
    }
    
    public ItemDTO (Item _item)
    {
    	setData(_item);
    }
    
    public void setData(Item _item) {
    	super.setData(_item);
//    	shipmentInfo = _item.getShipmentInfo() != null ? _item.getShipmentInfo().getName() : null;
    	merk = _item.getMerk() != null ? _item.getMerk().getName() : null;
    	metric = _item.getMetric() != null ? _item.getMetric().getName() : null;
    	code = _item.getCode();
    	sku = _item.getSku();
    	minimumStock = _item.getMinimumStock();
    	
    	if(_item.getCategories() != null && _item.getCategories().size() > 0) {
    		categories = new LinkedList<>();
    		for(ItemCategory ic : _item.getCategories()) {
    			categories.add(new ItemCategoryDTO(ic));
    		}
    	}

    }
    
    public Item getData() {
    	Item item = new Item();
    	if(getSystemId() != null)
    		item.setSystemId(getSystemId());
    	item.setName(getName());
    	item.setMemo(getMemo());
    	item.setActive(isActive());
    	item.setCode(code);
    	item.setSku(sku);
    	item.setMinimumStock(minimumStock);
    	
//    	if(shipmentInfo != null) 
//    		item.setShipmentInfo(new ItemShipmentInfo(shipmentInfo));
    	
    	if(merk != null) 
    		item.setMerk(new Merk(merk));
    	
    	if(metric != null)
    		item.setMetric(new Metrics(metric));    	
    	
    	if(getCategories() != null && getCategories().size() > 0) {
    		List<ItemCategory> listIC = new LinkedList<>();
    		for(ItemCategoryDTO _dto : getCategories()) {
    			listIC.add(_dto.getData());
    		}
    		item.setCategories(listIC);
    	}
    	    	
    	if(getSellPrices() != null && getSellPrices().size() > 0) {
    		List<ProductSellPrice> listPSP = new LinkedList<>();
    		for(ProductSellPriceDTO _dto : getSellPrices()) {
    			ProductSellPrice psp = _dto.getData();
    			psp.setProduct(item);
    			listPSP.add(psp);
    		}
    		item.setSellPrices(listPSP);
    	}
    	
    	
    	return item;
    }
    

//    public Item (int _id)
//    {
//        super (_id);
//        super.setDtype("Item");
//        categories = null;
//        shipmentInfo = null ;
//        merk = null;
//        imageBytes = null;
//
//        metricInv = null;
//        metricPO = null;
//        metricSales = null;
//
//        sku = "";
//        memo = "";
//        setMemo ("");
//
//        lbp = 0.0;
//        lbpAutoUpdate = false;
//        Calendar cal = Calendar.getInstance ();
//        cal.set (1980, 9, 9);
//        lbpDate = cal;
//
//        alternativeTo = null;
//        minStock = 0.0f;
//    }
    
    public String getCode () { return code; }
    
    public void setCode (String _code) 
    {
        this.code = _code;
    }
    
    public List<ItemCategoryDTO> getCategories () { return categories ; }
        
    public void setCategories ( List<ItemCategoryDTO> _categories ) 
    {
        categories = _categories ;
    }
    
    
    public String getShipmentInfo()   { return shipmentInfo ; }
    
    public void setShipmentInfo ( String _shipmentInfo )
    {
        this.shipmentInfo = _shipmentInfo ;
    }
    
    public String getMerk () { return merk; }
    
    public void setMerk (String _merk)
    {
        merk = _merk;
    }
    
    public String getSku () { return sku; }
    
    public void setSku (String _sku)
    {
        this.sku = (_sku != null) ? _sku : "";
    }
    
    public String getMetric () { return metric; }

    public void setMetric (String _m)
    {
        this.metric = _m;
    }

    
    /**
     * Informasi angka stok minimal berdasarkan satuan/metrik inventory.
     * 
     * @return 
     */
    public Float getMinimumStock ()
    {
        return minimumStock;
    }
    
    /**
     * Set informasi angka stok minimal berdasarkan satuan/metrik inventory.
     * @param _d 
     */
    public void setMinimumStock (Float _d)
    {
        minimumStock = _d;
    }
    
    public String getImageData() {
    	return imageData;
    }
    
    public void setImageData (String _imgBytes)
    {
        this.imageData = _imgBytes;
    }
    
    public Double getShelfLife () { return shelfLife; }
    
    public void setShelfLife (Double _shelfLife) 
    {
        this.shelfLife = _shelfLife;
    }

	public String getCategoriesString ()
	{
		return categoriesString;
	}

	public void setCategoriesString (String _categoriesString)
	{
		categoriesString = _categoriesString;
	}   

   
}
