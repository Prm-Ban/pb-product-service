/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ProdCategoryListDTO.java
 *
 * Created on Oct 18, 2017, 3:27:27 PM
 */

package com.sunwell.product.dto;

import java.util.LinkedList;




import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.sunwell.product.model.ItemCategory;

//import sunwell.permaisuri.core.entity.cred.UserGroup;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemCategoryListDTO extends StandardDTO
{
    
    private List<ItemCategoryDTO> listItemCategory;
    
    public ItemCategoryListDTO() {
        
    }
    
    public ItemCategoryListDTO(List<ItemCategory> _users) {
        setData (_users);
    }
    
    public void setData(List<ItemCategory> _categories) {
        if(_categories != null && _categories.size () > 0) {
        	listItemCategory = new LinkedList<> ();
            for (ItemCategory ic : _categories) {
                listItemCategory.add (new ItemCategoryDTO (ic));
            }
        }
        else
            listItemCategory = null;
    }

    /**
     * @return the listUser
     */
    public List<ItemCategoryDTO> getListUserGroup ()
    {
        return listItemCategory;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListUserGroup (List<ItemCategoryDTO> _list)
    {
        this.listItemCategory = _list;
    }
}
