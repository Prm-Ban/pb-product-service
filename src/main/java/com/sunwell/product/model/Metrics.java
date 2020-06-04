package com.sunwell.product.model;



/*
 
* Merk.java
 *
 * Created on February 8, 2007, 4:49 PM
 */

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
