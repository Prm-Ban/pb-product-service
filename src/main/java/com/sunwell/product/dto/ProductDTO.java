package com.sunwell.product.dto;



/*

 * Product.java
 *
 * Created on March 12, 2007, 9:00 PM
 */

import java.sql.*;





import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.sunwell.product.model.Product;
import com.sunwell.product.model.ProductImage;
import com.sunwell.product.model.ProductSellPrice;



public class ProductDTO extends StandardDTO
{
    private Integer systemId;
    private String name;
//    private String brandOwner;
    private String memo;
    private Boolean active = true;
    private String imageData ;
//    private List<ProductPurchasePriceDTO> purchasePrices ;
    private List<ProductSellPriceDTO> sellPrices ;
//    private List<ProductDiscountsDTO> discounts ;    
    
    private Product product;

    /**
     * @roseuid 45F62F27031C
     */
    public ProductDTO ()
    {
    }
    
    
    public void setData(Product _p) {
    	systemId = _p.getSystemId();
    	name = _p.getName();
//    	setBrandOwner(_p.getBrandOwner());
    	memo = _p.getMemo();
    	active = _p.isActive();
//    	if(_p.getPurchasePrices() != null && _p.getPurchasePrices().size() > 0) {
//    		purchasePrices = new LinkedList<>();
//    		for(ProductPurchasePrice ppp : _p.getPurchasePrices()) {
//    			purchasePrices.add(new ProductPurchasePriceDTO(ppp));
//    		}
//    	}
    	
    	if(_p.getSellPrices() != null && _p.getSellPrices().size() > 0) {
    		sellPrices = new LinkedList<>();
    		for(ProductSellPrice psp : _p.getSellPrices()) {
    			sellPrices.add(new ProductSellPriceDTO(psp));
    		}
    	}
    	
//    	if(_p.getDiscounts() != null && _p.getDiscounts().size() > 0) {
//    		sellPrices = new LinkedList<>();
//    		for(ProductDiscounts pd : _p.getDiscounts()) {
//    			discounts.add(new ProductDiscountsDTO(pd));
//    		}
//    	}
    }
    
    public Product getData() {
    	Product p = new Product();
    	if(systemId != null)
    		p.setSystemId(systemId);
    	p.setName(name);
//    	p.setBrandOwner(getBrandOwner());
    	p.setMemo(memo);
    	if(active != null)
    		p.setActive(active);
//    	if(purchasePrices != null && purchasePrices.size() > 0) {
//    		List<ProductPurchasePrice> listPPP = new LinkedList<>();
//    		for(ProductPurchasePriceDTO _dto : purchasePrices) {
//    			ProductPurchasePrice ppp = _dto.getData();
//    			ppp.setProduct(p);
//    			listPPP.add(ppp);
//    		}
//    		p.setPurchasePrices(listPPP);
//    	}
    	
    	if(sellPrices != null && sellPrices.size() > 0) {
    		List<ProductSellPrice> listPSP = new LinkedList<>();
    		for(ProductSellPriceDTO _dto : sellPrices) {
    			ProductSellPrice psp = _dto.getData();
    			psp.setProduct(p);
    			listPSP.add(psp);
    		}
    		p.setSellPrices(listPSP);
    	}
    	
//    	if(discounts != null && discounts.size() > 0) {
//    		List<ProductDiscounts> listD = new LinkedList<>();
//    		for(ProductDiscountsDTO _dto : discounts) {
//    			ProductDiscounts pd = _dto.getData();
//    			pd.setProduct(p);
//    			listD.add(pd);
//    		}
//    		p.setDiscounts(listD);
//    	}
    	
    	return p;
    }
    
    public ProductImage getProductImage() {
    	Product p = getData();
    	if(getImageData() != null) {
    		System.out.println("Image data is not null");
    		ProductImage pi = new ProductImage();
    		pi.setProduct(p);
    		pi.setName(p.getName());
    		pi.setImageData(Base64.getDecoder ().decode (getImageData()));
    		return pi;
    	}
    	else
    		return null;
    }

    
    public Integer getSystemId () { return systemId; }
    
    public void setSystemId (Integer _id_product)
    {
        this.systemId = _id_product;
    }
    
    public String getName () { return name; }
    
    public void setName (java.lang.String m_name)
    {
        this.name = m_name;
    }
    
    public String getMemo () { return memo; }
    
    public void setMemo (String _desc)
    {
        this.memo = _desc;
    }
    
    public Boolean isActive () { return active; }
    
    public void setActive (Boolean _status)
    {
        active = _status;
    }
    
//    public List<ProductPurchasePriceDTO> getPurchasePrices () 
//    { 
//        return purchasePrices; 
//    }
//    
//    public void setPurchasePrices (List<ProductPurchasePriceDTO> _listPurchPrice)
//    {
//        purchasePrices = _listPurchPrice;
//        
//    }
    
//    public List<ProductDiscountsDTO> getDiscounts () 
//    { 
//        return discounts;
//    }
//    
//    public void setDiscounts (List<ProductDiscountsDTO> _discounts)
//    {        
//        discounts = _discounts;
//    }
    
    public List<ProductSellPriceDTO> getSellPrices () 
    { 
        return sellPrices;
    }
    
    public void setSellPrices (List<ProductSellPriceDTO> _psps)
    {
        sellPrices = _psps ;
    }


	public String getImageData ()
	{
		return imageData;
	}


	public void setImageData (String _imageData)
	{
		imageData = _imageData;
	}
    
//	public String getBrandOwner ()
//	{
//		return brandOwner;
//	}
//
//
//	public void setBrandOwner (String _brandOwner)
//	{
//		brandOwner = _brandOwner;
//	}    
}
