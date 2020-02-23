/**
 * ItemCategory.java
 * 
 * Created on 2006
 */
package com.sunwell.product.dto;

import com.sunwell.product.model.ItemCategory;

public class ItemCategoryDTO extends StandardDTO
{
    private Integer systemId ;
    private String code;
    private String name;
    private String memo;

    /**
     * @roseuid 45C967CA0213
     */
    public ItemCategoryDTO() 
    {
       
    }

    /**
     *
     * @param _code
     * @param _name
     * @param _memo
     */
    public ItemCategoryDTO(ItemCategory _ic)
    {
        setData(_ic);
    }
    
    public void setData(ItemCategory _ic) {
    	systemId = _ic.getSystemId();
    	code = _ic.getCode();
    	name = _ic.getName();
    	memo = _ic.getMemo();
    }
    
    public ItemCategory getData() {
    	ItemCategory ic = new ItemCategory();
    	if(systemId != null)
    		ic.setSystemId(systemId);
    	ic.setCode(code);
    	ic.setName(name);
    	ic.setMemo(memo);
    	return ic;
    }
    
    public void setSystemId (Integer _systemId )
    {
        systemId = _systemId ;
    }
    
    public Integer getSystemId()
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
        this.memo = _memo;
    } 

    @Override
    public String toString ()
    {
        return this.name;
    }    
}
