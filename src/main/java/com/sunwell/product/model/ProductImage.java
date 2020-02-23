package com.sunwell.product.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="productimage")
//@IdClass(ProductImagePK.class)
public class ProductImage //implements Serializable
{
	@Id
	int systemId;
	
	@NotNull(message="{error_no_product}")
	@MapsId
	@OneToOne
	@JoinColumn(name="product")
	private Product product ;
	
	@NotNull(message="{error_no_name}")
	@Column(name="name")
	private String name ;
	
	@NotNull(message="{error_no_image_data}")
	@Column(name="imagedata")
	private byte[] imageData ;
	
	public Product getProduct ()
	{
		return product;
	}

	public void setProduct (Product _product)
	{
		product = _product;
	}
	

	public String getName ()
	{
		return name;
	}

	public void setName (String _name)
	{
		name = _name;
	}
	
	public byte[] getImageData ()
	{
		return imageData;
	}

	public void setImageData (byte[] _imageData)
	{
		imageData = _imageData;
	}
}
