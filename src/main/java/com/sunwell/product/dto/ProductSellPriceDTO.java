package com.sunwell.product.dto;



import java.io.Serializable;





import java.sql.*;
import java.util.*;

import com.sunwell.product.model.ProductSellPrice;
import com.sunwell.product.model.SellPriceLevel;


public class ProductSellPriceDTO extends StandardDTO
{
	private Integer systemId;
    private Double price;
    private Float profitFactor;
    private String name;
    private String memo = "";
    private Boolean passworded;
    private Boolean useByDefault;
    
    /**
     * @roseuid 45D6098C0203
     */
    public ProductSellPriceDTO ()
    {
        
    }
    
    public ProductSellPriceDTO (ProductSellPrice _p)
    {
        setData(_p);
    }
    
      
    public void setData(ProductSellPrice _psp) {
    	price = _psp.getPrice();
    	profitFactor = _psp.getProfitFactor();
    	if(_psp.getPriceLevel() != null) {
    		SellPriceLevel spl = _psp.getPriceLevel();
    		systemId = spl.getSystemId();
    		name = spl.getName();
    		memo = spl.getMemo();
    		passworded = spl.isPassworded();
    		useByDefault = spl.isUseByDefault();
    	}
    }
    
    public ProductSellPrice getData() {
    	ProductSellPrice psp = new ProductSellPrice();
    	if(price != null)
    		psp.setPrice(price);
    	
    	if(profitFactor != null)
    		psp.setProfitFactor(profitFactor);
    	
    	if(systemId != null) {
    		SellPriceLevel spl = new SellPriceLevel();
    		spl.setSystemId(systemId);
    		spl.setName(name);
    		spl.setMemo(memo);
    		if(passworded != null)
    			spl.setPassworded(passworded);
    		
    		if(useByDefault != null)
    			spl.setUseByDefault(useByDefault);
    		
    		psp.setPriceLevel(spl);
    	}
    	return psp;
    }
    
    public Double getPrice () { return price; }

    public void setPrice (Double m_price)
    {
        this.price = m_price;
    }
  
    public Float getProfitFactor ()
    {
        return profitFactor;
    }
    
    public void setProfitFactor (Float _f)
    {
        profitFactor = _f;
    }

	public String getName ()
	{
		return name;
	}

	public void setName (String _name)
	{
		name = _name;
	}

	public String getMemo ()
	{
		return memo;
	}

	public void setMemo (String _memo)
	{
		memo = _memo;
	}

	public Boolean getPassworded ()
	{
		return passworded;
	}

	public void setPassworded (Boolean _passworded)
	{
		passworded = _passworded;
	}

	public Boolean getUseByDefault ()
	{
		return useByDefault;
	}

	public void setUseByDefault (Boolean _useByDefault)
	{
		useByDefault = _useByDefault;
	}

	public Integer getSystemId ()
	{
		return systemId;
	}

	public void setSystemId (Integer _systemId)
	{
		systemId = _systemId;
	}   
}
