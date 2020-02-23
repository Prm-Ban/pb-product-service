/**
 * ItemCategory.java
 * 
 * Created on 2006
 */
package com.sunwell.product.dto;

import com.sunwell.product.model.Merk;

public class MerkDTO extends StandardDTO
{
    private Integer systemId ;
    private String name;
    private String manufacturer;

    /**
     * @roseuid 45C967CA0213
     */
    public MerkDTO() 
    {
       
    }

    /**
     *
     * @param _code
     * @param _name
     * @param _memo
     */
    public MerkDTO(Merk _merk)
    {
        setData(_merk);
    }
    
    public void setData(Merk _merk) {
    	systemId = _merk.getSystemId();
    	name = _merk.getName();
    	manufacturer = _merk.getManufacturerName();
    }
    
    public Merk getData() {
    	Merk merk = new Merk();
    	if(systemId != null)
    		merk.setSystemId(systemId);
    	merk.setName(name);
    	merk.setManufacturerName(manufacturer);
    	return merk;
    }
    
    public void setSystemId (Integer _systemId )
    {
        systemId = _systemId ;
    }
    
    public Integer getSystemId()
    {
        return systemId ;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String _manufacturer) {
        this.manufacturer = _manufacturer;
    } 

    @Override
    public String toString ()
    {
        return this.name;
    }    
}
