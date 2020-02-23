package com.sunwell.product.model;



/**
 
* ProductSellPrice.java
 * 
 * Created on 2007
 */

import java.io.Serializable;

import java.sql.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 
 * @version 1.0 - 2007 ; initial version.
 * @version 1.1 - June 16, 2014 ; penambahan atribut m_profitFactor yg berfungsi
 *    sebagai acuan menentukan harga jual.
 * @version 1.3 - March 6, 2015 ; tambahan method getMaxPrice() dan getMinPrice().
 * 
 * @author Irfin A
 * @author Benny
 */
@Entity
@Table( name = "productsellprice" )
@IdClass( ProductSellPricePK.class )
public class ProductSellPrice 
{
    @NotNull(message="{error_no_price_level}")
    @Id
    @ManyToOne
    @JoinColumn( name = "id_pricelevel" )
    private SellPriceLevel priceLevel;
    
    @NotNull(message="{error_no_product}")
    @Id
    @ManyToOne
    @JoinColumn( name = "id_product" )
    private Product product;
    
    @NotNull(message="{error_no_price}")
    @Column( name = "price" )
    private double price;
    
    @NotNull(message="{error_no_profit_factor}")
    @Column( name = "profitfactor" )
    private float profitFactor;
    
    /**
     * @roseuid 45D6098C0203
     */
    public ProductSellPrice ()
    {
        product = null;
        priceLevel = null;
    }
    
    public ProductSellPrice (Product p, SellPriceLevel sp)
    {
        product = p;
        priceLevel = sp;
    }
    
    public double getPrice () { return price; }

    public void setPrice (double m_price)
    {
        this.price = m_price;
    }

    public Product getProduct () { return product; }

    public void setProduct (Product m_item)
    {
        this.product = m_item;
    }

    public SellPriceLevel getPriceLevel () { return priceLevel; }

    public void setPriceLevel (SellPriceLevel m_priceLevel)
    {
        this.priceLevel = m_priceLevel;
    }
    
    public float getProfitFactor ()
    {
        return profitFactor;
    }
    
    public void setProfitFactor (float _f)
    {
        profitFactor = _f;
    }   

    /**
     * Mencari dan mengembalikan elemen array ProductSellPrice yang memiliki
     * harga jual paling tinggi.
     * 
     * @param _arrPSP
     * @return 
     */
    public static ProductSellPrice getMaxPrice (ProductSellPrice[] _arrPSP)
    {
        if (_arrPSP == null)
            return null;
        
        int maxIdx = 0;
        for (int i = 1; i < _arrPSP.length; i++) {
            if (_arrPSP[i].price > _arrPSP[maxIdx].price)
                maxIdx = i;
        }
        
        return _arrPSP[maxIdx];
    }
    
    /**
     * Mencari dan mengembalikan elemen array ProductSellPrice yang memiliki
     * harga jual paling rendah.
     * 
     * @param _arrPSP
     * @return 
     */
    public static ProductSellPrice getMinPrice (ProductSellPrice[] _arrPSP)
    {
        if (_arrPSP == null)
            return null;
        
        int minIdx = 0;
        for (int i = 1; i < _arrPSP.length; i++) {
            if (_arrPSP[i].price < _arrPSP[minIdx].price)
                minIdx = i;
        }
        
        return _arrPSP[minIdx];
    }
    
    @Override
    public int hashCode ()
    {
    	int hash = 0;
        hash += product != null ? product.hashCode() : 0;
        hash += priceLevel != null ? priceLevel.hashCode () : 0;
        return hash;
    }

    @Override
    public boolean equals (Object _obj)
    {
    	if(_obj == null)
    		return false;
        if (!(_obj instanceof ProductSellPrice)) {
            return false;
        }
        
        ProductSellPrice other = (ProductSellPrice) _obj;
        if ((this.product == null && other.product != null) || 
            (this.product != null && !this.product.equals(other.product))) {
            return false;
        }
        
        if ((this.priceLevel == null && other.priceLevel != null) || 
            (this.priceLevel != null && !this.priceLevel.equals(other.priceLevel))) {
            return false;
        }
        
        return true;
    }
}
