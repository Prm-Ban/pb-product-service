package com.sunwell.product.model;



import java.io.Serializable;


/*

 * Product.java
 *
 * Created on March 12, 2007, 9:00 PM
 */

import java.sql.*;

import java.util.Calendar;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@Table(name = "product")
public class Product implements Serializable
{
    /** PRIMARY KEY ; auto-increment */
    @Id
    @SequenceGenerator (name = "product_id_product_seq", sequenceName = "product_id_product_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "product_id_product_seq")
    @Basic(optional = false)
    @Column(name = "systemid")
    private int systemId;
    
    @OneToMany( targetEntity = ProductSellPrice.class, mappedBy = "product", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch (FetchMode.SELECT)
    private List<ProductSellPrice> listPSP ;
    
    /** Must be UNIQUE. */
    @NotNull(message="{error_no_name}")
    @Column(name = "name", unique = true)
    @Basic(optional = false)
    private String name;
    
    @Column( name = "memo" )
    private String memo;
    
    @Column( name = "active" )
    private boolean active = true;
    
    @Column(name="last_sync")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar lastSync = Calendar.getInstance();
    
    /**
     * @roseuid 45F62F27031C
     */
    public Product ()
    {
    }

    public Product (int _systemid)
    {
        systemId = _systemid;
    }
    
    public int getSystemId () { return systemId; }
    
    public void setSystemId (int _id_product)
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
    
    public boolean isActive () { return active; }
    
    public void setActive (boolean _status)
    {
        active = _status;
    }
 
    public List<ProductSellPrice> getSellPrices () 
    { 
        
        return listPSP;
    }
    
    public void setSellPrices (List<ProductSellPrice> _psps)
    {
        listPSP = _psps;
    }

    /**
     *
     * @return
     */
    public ProductSellPrice getDefaultSellPrice ()
    {
    	if(listPSP == null)
    		return null;
    	
        for (ProductSellPrice psp : listPSP) {
            if (psp.getPriceLevel ().isUseByDefault ()) {
                return psp;
            }
        }

        // jika tdk ada juga ditemukan ProductSellPrice yg berupa harga default,
        // maka kembalikan elemen array pertama
        return null;
    }
    
    
    public int hashCode() {
        System.out.println(" Product hashCode(); called");
    	return systemId;
    }

    @Override
    public boolean equals (Object obj)
    {
        System.out.println(" Product equals(); called");

        if (!(obj instanceof Product)) {
            return false;
        }
        
        Product other = (Product) obj;
        return systemId == other.systemId;
    }


	public Calendar getLastSync ()
	{
		return lastSync;
	}

	public void setLastSync (Calendar _lastSync)
	{
		lastSync = _lastSync;
	}    
   
}
