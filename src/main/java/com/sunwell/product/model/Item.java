/*
 * Product.java
 *
 * Created on December 12, 2007, 9:00 PM
 */
package com.sunwell.product.model;


import java.sql.*;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table( name = "item" )
@Inheritance( strategy = InheritanceType.JOINED )
public class Item extends Product
{
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="itemcategorytag",
	    joinColumns=
	        @JoinColumn(name="id_item", referencedColumnName="systemid"),
	    inverseJoinColumns=
	        @JoinColumn(name="id_ctgr", referencedColumnName="systemid")
    )
    @Fetch (FetchMode.SELECT)
    private List<ItemCategory> categories ;
    
    @NotNull(message="{error_no_merk}")
    @ManyToOne()
    @JoinColumn( name = "id_merk")
    private Merk merk = null;
    
    /** Satuan/Unit of Measurement/Metric yg digunakan utk inventory */
    @ManyToOne()
    @JoinColumn( name = "name_s50_inv")
    private Metrics metric;
    
    /** User defined code for item. Must be UNIQUE */
    @NotNull(message="{error_no_item_code}")
    @Column( name = "code_item_s50" )
    private String code;
    
    /** Stock Keeping Unit, which is encoded in the item's barcode */
    @Column( name = "sku")
    private String sku = "";
    
//    @Column( name = "image")
//    private String image = "";
    
    @Column( name = "imagebytes")
    private Byte[] imageBytes = null;
    
    @Column( name = "memo")
    private String memo = "";
    
    /** Minimum stock berdasarkan metrik inventory */
    @Column( name ="min_stock" )
    private Float minStock;
    
    /**
     * @roseuid 45C9A40001F4
     */
    public Item ()
    {
//        this (-1);
//        super.setDtype("Item");
    }
    
    public Item(String _name) {
    	setName(_name);
    }

    public Item (int _id)
    {
        super (_id);
    }
    
    public String getCode () { return code; }
    
    public void setCode (String _code_item_s50) 
    {
        this.code = _code_item_s50;
    }
    
    public List<ItemCategory> getCategories () { return categories ; }
        
    public void setCategories ( List<ItemCategory> _categories ) 
    {
        categories = _categories ;
    }
    
    
    public Merk getMerk () { return merk; }
    
    public void setMerk (Merk _merk)
    {
        merk = _merk;
    }
    
    public String getSku () { return sku; }
    
    public void setSku (String _sku)
    {
        this.sku = (_sku != null) ? _sku : "";
    }
    
//    public Metrics getMetricPurchase () { return metricPO; }
//
//    public void setMetricPurchase (Metrics m_metricPO)
//    {
//        this.metricPO = m_metricPO;
//    }

    public Metrics getMetric () { return metric; }

    public void setMetric (Metrics _m)
    {
        this.metric = _m;
    }

    public String getMemo () { return memo; }
    
    public void setMemo (String _memo)
    {
        this.memo = (_memo != null) ? _memo : "";
    }
    
    /**
     * Informasi angka stok minimal berdasarkan satuan/metrik inventory.
     * 
     * @return 
     */
    public Float getMinimumStock ()
    {
        return minStock;
    }
    
    /**
     * Set informasi angka stok minimal berdasarkan satuan/metrik inventory.
     * @param _d 
     */
    public void setMinimumStock (Float _d)
    {
        minStock = _d;
    }
    
    public void setImageBytes (Byte[] _imgBytes)
    {
        this.imageBytes = _imgBytes;
    }
    
    public Byte[] getImageBytes() {
    	return imageBytes;
    }


    @Override
    public String toString ()
    {
        return getName () ;
    }

//	public String getImage ()
//	{
//		return image;
//	}
//
//	public void setImage (String _image)
//	{
//		image = _image;
//	}
}
