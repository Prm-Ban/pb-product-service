package com.sunwell.product.model;

import java.io.Serializable;

public class ProductImagePK implements Serializable
{
	int product ;
	
	public ProductImagePK() {
		
	}
	
	public ProductImagePK(int _product) {
		product = _product;
	}

	public int getProduct ()
	{
		return product;
	}

	public void setProduct (int _product)
	{
		product = _product;
	}
	
	@Override
	public int hashCode() {
		return product ;
	}
	
	@Override
	public boolean equals(Object _o) {
		return product == ((ProductImagePK) _o).product ;
	}
}
