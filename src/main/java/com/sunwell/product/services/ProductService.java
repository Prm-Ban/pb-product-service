package com.sunwell.product.services;

import java.util.List;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.sunwell.product.model.Item;
import com.sunwell.product.model.ItemCategory;
import com.sunwell.product.model.Merk;
import com.sunwell.product.model.Metrics;
import com.sunwell.product.model.Product;
import com.sunwell.product.model.ProductImage;
import com.sunwell.product.model.SellPriceLevel;

@PreAuthorize("isFullyAuthenticated()")
public interface ProductService
{
	@PreAuthorize("hasAuthority('READ_ITEM')")
	public Item findItem(@NotNull(message="{error_no_id}") Integer _id) ;
	
	@PreAuthorize("#oauth2.hasScope('READ_ITEM')")
	public Item findItemByName(String _name) ;
	
	public Page<Item> findAllItems(Pageable _page) ;
	public Page<Item> findByCategory(ItemCategory _ic, Pageable _page) ;
	public Page<Item> findByCategoryId(Integer _id, Pageable _page) ;
	public List<Item> findAllItems() ;
	public Item addItem(
    		@Valid @NotNull(message="{error_no_item}") Item _i, ProductImage _pi) ;
	public Item editItem(
    		@Valid @NotNull(message="{error_no_item}") Item _i, ProductImage _pi);   
    public Item deleteItem(@NotNull(message="{error_no_id}") Integer _id) ;
	public ItemCategory findCategory(@NotNull(message="{error_no_id}") Integer _id) ;
	public ItemCategory findCategoryByName(String _name) ;
	public ItemCategory findCategoryByCode(String _code) ;
	public Page<ItemCategory> findAllCategories(Pageable _page) ;
	public List<ItemCategory> findAllCategories() ;
	public ItemCategory addCategory(
    		@Valid @NotNull(message="{error_no_category}") ItemCategory _ctgr) ;
    public ItemCategory editCategory(
    		@Valid @NotNull(message="{error_no_category}") ItemCategory _ctgr) ;
    public ItemCategory deleteCategory(@NotNull(message="{error_no_id}") Integer _id) ;
    public Merk findMerk(@NotNull(message="{error_no_id}")Integer _id) ;
	public Merk findMerkByName(String _name) ;
	public Page<Merk> findAllMerks(Pageable _page) ;
	public Merk addMerk(
    		@Valid @NotNull(message="{error_no_merk}") Merk _merk) ;
    public Merk editMerk(
    		@Valid @NotNull(message="{error_no_category}") Merk _merk) ;
    public Merk deleteMerk(@NotNull(message="{error_no_id}") Integer _id) ;
    public ProductImage findProductImage(@NotNull(message="{error_no_product}")Product _p);
    public ProductImage findProductImageByProductName(@NotNull(message="{error_no_name}")String _p) ;
    public ProductImage addProductImage(@Valid @NotNull(message="{error_no_product_image}") ProductImage _pi) ;
    public ProductImage editProductImage(@Valid @NotNull(message="{error_no_product_image}") ProductImage _pi) ;
    public ProductImage deleteProductImage(@NotNull(message="{error_no_product}")Product _p);
	public List<Merk> findAllMerks() ;
	public Metrics findMetrics(String _name) ;
	public Page<Metrics> findAllMetrics(Pageable _page) ;
	public SellPriceLevel findSellPriceLevelByName(String _name) ;
}
