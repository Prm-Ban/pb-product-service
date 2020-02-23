/**
 * ItemCategory.java
 * 
 * Created on 2006
 */
package com.sunwell.product.model;

import java.io.Serializable;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

//import sunwell.permaisuri.core.entity.cred.UserGroup;

@Entity
@Table( name = "itemcategory" )
public class ItemCategory
{
    @Id
    @Column( name ="systemid" )
    @SequenceGenerator (name = "itemcategory_systemid_seq", sequenceName = "itemcategory_systemid_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "itemcategory_systemid_seq")
    @Basic(optional = false)
    private int systemId ;
    
    /** UNIQUE 3 karakter kode kategori. */
    @NotNull(message="{error_no_category_code}")
    @Column( name = "code_category")
    private String code;
    
    @NotNull(message="{error_no_name}")
    @Column( name = "name")
    private String name;
    
    @Column( name = "memo" )
    private String memo;

    /**
     * @roseuid 45C967CA0213
     */
    public ItemCategory() 
    {
        code = null;
        name = null;
        memo = null;
    }
    
    public ItemCategory(int id) 
    {
        this.systemId = id;
    }

    /**
     *
     * @param _code
     * @param _name
     * @param _memo
     */
    public ItemCategory(String _code, String _name, String _memo)
    {
        code = _code;
        name = _name;
        memo = _memo;
    }
    
    public void setSystemId (int _systemId )
    {
        systemId = _systemId ;
    }
    
    public int getSystemId()
    {
        return systemId ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String _kode_s3) {
        this.code = _kode_s3;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String _memo) {
        this.memo = (_memo != null) ? _memo : "";
    } 

    @Override
    public String toString ()
    {
        return this.name;
    }
    
    @Override
    public boolean equals (Object _ic)
    {
    	if(_ic == null)
    		return false;
        if(!(_ic instanceof ItemCategory))
            return false;
        ItemCategory other = (ItemCategory) _ic;
        return systemId == other.systemId;
    }

    @Override
    public int hashCode ()
    {
    	return systemId;
    }
}
