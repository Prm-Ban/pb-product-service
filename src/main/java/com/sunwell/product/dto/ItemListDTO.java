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

import com.sunwell.product.model.Item;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemListDTO extends StandardDTO
{
    
    private List<ItemDTO> listItem;
    
    public ItemListDTO() {
        
    }
    
    public ItemListDTO(List<Item> _users) {
        setData (_users);
    }
    
    public void setData(List<Item> _items) {
        if(_items != null && _items.size () > 0) {
        	listItem = new LinkedList<> ();
            for (Item i : _items) {
                listItem.add (new ItemDTO (i));
            }
        }
        else
            listItem = null;
    }

    /**
     * @return the listUser
     */
    public List<ItemDTO> getListItem ()
    {
        return listItem;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListUserGroup (List<ItemDTO> _list)
    {
        this.listItem = _list;
    }
}
