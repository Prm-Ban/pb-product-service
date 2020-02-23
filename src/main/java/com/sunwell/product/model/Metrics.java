package com.sunwell.product.model;



/*
 
* Merk.java
 *
 * Created on February 8, 2007, 4:49 PM
 */

import java.io.Serializable;

import java.sql.*;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Satuan (metrics) yg digunakan utk stok bahan dan stok barang.
 *
 * @author Irfin A.
 *
 * @version 1.0 - February 8, 2007 ; initial version.
 * @version 1.3 - January 19, 2010 ; penambahan method returnAllFromDB().
 * @version 1.5 - December 15, 2014 ; adaptasi dengan fitur jpa.
 */
@Entity
@Table(name="metrics")
@NamedQueries ({
@NamedQuery(name = "Metrics.findDefaultMetric", query = "SELECT m FROM Metrics m WHERE m.name = 'Pcs'")
})
public class Metrics implements Serializable
{
    /**
     * PRIMARY KEY ; length 50 chars
     */
    @NotNull(message="{error_no_name}")
    @Id
    @Column(name="name_s50")
    private String name;
    
    @NotNull(message="{error_no_units}")
    @Column(name="units")
    private Double units;
    
    @Column(name="memo")
    private String memo;
    
    @ManyToOne
    @JoinColumn(name="metric_rinci")
    private Metrics metricRinci;

    /**
     * @roseuid 45C72FEE01D6
     */
    public Metrics() {
    }
    
 
    public Metrics(String _name) {
        name = _name;
    }

    public String toString() {
        return name;
    }

//    @Deprecated
//    public Metrics(String _name, double _units, Metrics _metricRinci) {
//        setNamaMetric(_name);
//        setUnits(_units);
//        setMetricRinci(_metricRinci);
//
//        memo = null;
//    }

//    @Deprecated
//    public Metrics getMetricRinci() {
//        if (m_metricRinci == null) {
//            return null;
//        } else {
//            try {
//                m_metricRinci = this.queryByName(m_metricRinci.getNamaMetric());
//            } catch (SQLException x) {
//                return null;
//            }
//        }
//
//        return m_metricRinci;
//    }
    
    public String setName() {
        return name;
    }

    public String getName() {
        return name;
    }

    @Deprecated
    public void setMetricRinci(Metrics _metricRinci) {
        this.metricRinci = _metricRinci;
    }

    @Deprecated
    public void setUnits(double _units) {
        this.units = _units;
    }

    @Deprecated
    public double getUnits() {
        return units;
    }

    public void setDesc(String _memo) {
        memo = _memo;
    }

    public String getDesc() {
        return memo;
    }

    public void setNamaMetric(String _name_s50) {
        this.name = _name_s50;
    }
    
    @Override
    public boolean equals (Object _m)
    {
    	if(_m == null)
    		return false;
        if (!(_m instanceof Metrics)) {
            return false;
        }
        
        Metrics other = (Metrics) _m;
        if ((this.name == null && other.name != null) || 
            (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
    	return name != null ? name.hashCode() : 0;
    }    
}
