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
//        hash += product != null ? product.hashCode() : 0;
//        hash += priceLevel != null ? priceLevel.hashCode () : 0;
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
//        if (!(product == null && other.product == null) || (product == null && other.product != null) || !this.product.equals(other.product))
//            return false;
//        if (!(priceLevel == null && other.priceLevel == null) || (priceLevel == null && other.priceLevel != null) || !this.priceLevel.equals(other.priceLevel))
//            return false;
        
//        if(product != null)
//        	if(!product.equals(other.product))
//        		return false;
//        else 
//	    	if(other.product != null)
//	    		return false;
//        
//        
//        if(priceLevel != null)
//        	if(!priceLevel.equals(other.priceLevel))
//        		return false;
//        else
//        	if(other.priceLevel != null)
//        		return false;
        
        if(product != other.product)
        	return false;
        
        if(priceLevel != other.priceLevel)
        	return false;
        
        return true;
    }
}