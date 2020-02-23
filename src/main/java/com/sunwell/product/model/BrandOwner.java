package com.sunwell.product.model;
//package sunwell.permaisuri.core.entity.inventory;
//
//
///*
//* BrandOwner.java
// *
// * Created on June 16, 2014, 18:02
// */
//
//import java.sql.PreparedStatement;
//
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityManager;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Query;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//import javax.validation.constraints.NotNull;
//
///**
// * Diacu dari {@link http://www.organisasi.org/1970/01/strategi-jenis-macam-dan-pengertian-merek-merk-brand-produk-barang-dan-jasa-manajemen-pemasaran.html}
// * <BR><BR>
// * Definisi Pengertian Merek / Merk / Brand<BR>
// * Merek adalah suatu nama, simbol, tanda, desain atau gabungan di antaranya untuk 
// * dipakai sebagai identitas suatu perorangan, organisasi atau perusahaan pada 
// * barang dan jasa yang dimiliki untuk membedakan dengan produk jasa lainnya. 
// * Merek yang kuat ditandai dengan dikenalnya suatu merek dalam masyarakat, 
// * asosiasi merek yang tinggi pada suatu produk, persepsi positif dari pasar dan 
// * kesetiaan konsumen terhadap merek yang tinggi.<BR>
// * Dengan adanya merek yang membuat produk yang satu beda dengan yang lain 
// * diharapkan akan memudahkan konsumen dalam menentukan produk yang akan dikonsumsinya 
// * berdasarkan berbagai pertimbangan serta menimbulkan kesetiaan terhadap suatu 
// * merek (brand loyalty).<BR>
// * Kesetiaan konsumen terhadap suatu merek atau brand yaitu dari pengenalan, pilihan 
// * dan kepatuhan pada suatu merek.
// * <BR><BR>
// * Merek dapat dipahami lebih dalam pada tiga hal berikut ini:<ol>
// * <li>
// *    Contoh brand name (nama) : nintendo, aqua, bata, rinso, kfc, acer, windows, 
// *    toyota, zyrex, sugus, gery, bagus, mister baso, gucci, c59, dan lain sebagainya.</li>
// * <li>
// *    Contoh mark (simbol) : gambar atau simbol sayap pada motor honda, gambar 
// *    jendela pada windows, gambar kereta kuda pada california fried chicken (cfc), 
// *    simbol orang tua berjenggot pada brand orang tua (ot) dan kentucky friend chicken (kfc), 
// *    simbol bulatan hijau pada sony ericsson, dan masih banyak contoh-contoh 
// *    lainnya yang dapat kita temui di kehidupan sehari-hari.</li>
// * <li> Contoh trade character (karakter dagang) : ronald mcdonald pada restoran mcdonalds,
// *    si domar pada indomaret, burung dan kucing pada produk makanan gery, dan lain sebagainya.</li>
// * </ol>
// * <BR>
// * Jenis-Jenis Dan Macam-Macam Merek<ol>
// * <li>
// *    Manufacturer Brand. Manufacturer brand atau merek perusahaan adalah merek 
// *    yang dimiliki oleh suatu perusahaan yang memproduksi produk atau jasa. 
// *    Contohnya seperti soffel, capilanos, ultraflu, so klin, philips, tessa, 
// *    benq, faster, nintendo wii, vit, vitacharm, vitacimin, dan lain-lain.</li>
// * <li>
// *    Private brand atau merek pribadi adalah merek yang dimiliki oleh distributor 
// *    atau pedagang dari produk atau jasa seperti zyrex ubud yang menjual laptop 
// *    cloud everex, hipermarket giant yang menjual kapas merek giant, carrefour 
// *    yang menjual produk elektrinik dengan merek bluesky, supermarket hero yang
// *    menjual gula dengan merek hero, dan lain sebagainya.</li>
// * </ol>
// * Ada juga produk generik yang merupakan produk barang atau jasa yang dipasarkan 
// * tanpa menggunakan merek atau identitas yang membedakan dengan produk lain baik 
// * dari produsen maupun pedagang. Contoh seperti sayur-mayur, minyak goreng curah, 
// * abu gosok, buah-buahan, gula pasir curah, bunga, tanaman, dan lain sebagainya.
// * <BR>
// * Pemilik brand (brand owner) juga kadang disebut `principal`.
// * 
// * @author irfin
// * @author Daisy
// * 
// * @version 1.1 - December 15, 2014. Adaptasi dengan fitur jpa.
// * @version 1.2 - April 17, 2016 ; Implemnt interface ISimpleObjectStringAdapter
// */
//@Entity
//@Table(name="brandowner")
////@NamedQueries ({
////    @NamedQuery(name = "BrandOwner.findAll", query = "SELECT b FROM BrandOwner b ORDER BY b.m_ownerName"),
////    @NamedQuery(name = "BrandOwner.findBySystemId", query = "SELECT b FROM BrandOwner b WHERE b.m_systemid = :sysid"),
////    @NamedQuery(name = "BrandOwner.findByName", query = "SELECT b FROM BrandOwner b WHERE b.m_ownerName = :name")
////})
//public class BrandOwner 
//{
//    /** PRIMARY KEY */
//	@Id
//    @SequenceGenerator (name = "brandowner_systemid_seq", sequenceName = "brandowner_systemid_seq", allocationSize = 1)
//    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "brandowner_systemid_seq" )
//    @Column (name = "systemid")
//    private int systemId;
//    
//	@NotNull(message="{error_no_name}")
//    @Column(name="ownername", unique=true, nullable=false)
//    private String name;
//    
//    @Column(name="memo")
//    private String memo;
//    
//    
//    public BrandOwner ()
//    {
//        systemId = -1;
//    }
//    
//    public BrandOwner (int _id)
//    {
//        systemId = _id;
//    }
//
//    public int getSystemId ()
//    {
//        return systemId;
//    }
//
//    public void setSystemId (int _systemid)
//    {
//        this.systemId = _systemid;
//    }
//
//    public String getName ()
//    {
//        return name;
//    }
//
//    public void setName (String _ownerName)
//    {
//        this.name = _ownerName;
//    }
//
//    public String getMemo ()
//    {
//        return memo;
//    }
//
//    public void setMemo (String _memo)
//    {
//        this.memo = _memo;
//    }
//
//    
//    @Override
//    public String toString ()
//    {
//        return name;
//    }
//
//    @Override
//    public boolean equals (Object _obj)
//    {
//        if (_obj == null)
//            return false;
//        
//        if (!(_obj instanceof BrandOwner))
//            return false;
//        
//        BrandOwner other = (BrandOwner) _obj;
////        if ((this.systemId == null && other.systemId != null) || 
////            (this.systemId != null && !this.systemId.equals(other.systemId))) {
////            return false;
////        }
////        return true;
//        return systemId == other.systemId;
//        
////        return ((BrandOwner) _obj).getSystemId().equals(systemId);
//    }
//
//    @Override
//    public int hashCode ()
//    {
////        return systemId != null ? systemId.hashCode() : 0;
//    	return systemId;
//    }
//}
