package com.sunwell.product.model;

import java.io.Serializable;

/**
 * ProductSellPricePK.java
/**
 *
 * @author Benny
 */
public class ProductSellPricePK implements Serializable
{
    int product ;
    int priceLevel ;
    
    public ProductSellPricePK() {
    	
    }
    
    public ProductSellPricePK(int _prod, int _pl) {
    	product = _prod;
    	priceLevel = _pl;
    }
    
    public int getProduct ()
    {
        return product;
    }

    public void setProduct (int _product)
    {
        this.product = _product;
    }

    public int getPriceLevel ()
    {
        return priceLevel;
    }

    public void setPriceLevel (int _priceLevel)
    {
        this.priceLevel = _priceLevel;
    }
    
    
    
    @Override
    public int hashCode ()
    {
    	int hash = 0;
    	hash += product;
    	hash += priceLevel;
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductSellPricePK)) {
            return false;
        }
        ProductSellPricePK other = (ProductSellPricePK) object;
        
        if(product != other.product)
        	return false;
        
        if(priceLevel != other.priceLevel)
        	return false;
        
        return true;
    }
}