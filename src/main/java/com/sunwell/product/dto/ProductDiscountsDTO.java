package com.sunwell.product.dto;
//package sunwell.permaisuri.bus.dto.inventory;
//
//
//import java.sql.*;
//
//import sunwell.permaisuri.bus.dto.StandardDTO;
//import sunwell.permaisuri.core.entity.inventory.ProductDiscounts;
//
//public class ProductDiscountsDTO extends StandardDTO
//{
//    private String memo;
//    private Double percentAmount;
//   
//    public ProductDiscountsDTO ()
//    {
//        
//    }
//    
//    public ProductDiscountsDTO (ProductDiscounts _p)
//    {
//        setData(_p);
//    }
//    
//    public void setData(ProductDiscounts _p) {
//    	memo = _p.getMemo();
//    	percentAmount = _p.getPercentAmount();
//    }
//    
//    public ProductDiscounts getData() {
//    	ProductDiscounts pd = new ProductDiscounts();
//    	pd.setMemo(memo);
//    	if(percentAmount != null)
//    		pd.setPercentAmount(percentAmount);
//    	return pd;
//    }
//
//    public ProductDiscountsDTO (String _memo, Double _percent, ProductDTO _product)
//    {
//        setDesc (_memo);
//        setPercentAmount (_percent);
//    }
//
//    public String getDesc ()
//    {
//        return memo;
//    }
//
//    public void setDesc (String _memo)
//    {
//        this.memo = _memo;
//    }
//
//    public Double getPercentAmount ()
//    {
//        return percentAmount;
//    }
//
//    public void setPercentAmount (Double _percent_amount)
//    {
//        this.percentAmount = _percent_amount;
//    }
//
//    
//}
