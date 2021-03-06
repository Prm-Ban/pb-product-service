package com.sunwell.product.model;


/*


 * SellPriceLevel.java
 *
 * Created on March 12, 2007, 9:00 PM
 */


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sellpricelevel")
public class SellPriceLevel 
{
    /** PRIMARY KEY ; auto-increment */
    @Id
    @SequenceGenerator (name = "sellpricelevel_id_pricelevel_seq", sequenceName = "sellpricelevel_id_pricelevel_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "sellpricelevel_id_pricelevel_seq" )
    @Basic(optional = false)
    @Column(name = "id_pricelevel")
    private int systemId;
    
    /**  Must be UNIQUE */
    @NotNull(message="{error_no_name}")
    @Column(name="name")
    private String name;
    
    @Column(name="memo")
    private String memo = "";
    
    @Column(name="passworded")
    private boolean passworded = false;
    
    @Column(name="usebydefault")
    private boolean useByDefault = false;

    /**
     * @roseuid 45D5F03D01E4
     */
    public SellPriceLevel() 
    {
        this(null, null);
    }
    
    public SellPriceLevel(int id) {
    	this.systemId = id;
    }

    public SellPriceLevel(String _name, String _memo) 
    {
        name = _name;
        memo = _memo;
        passworded = false;
        useByDefault = false;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int _id) {
        this.systemId = _id;
    }

    public boolean isPassworded() {
        return passworded;
    }

    public void setPassworded(boolean _status) {
        passworded = _status;
    }

    public String getName() {
        return name;
    }

    /**
     * Name must be UNIQUE.
     */
    public void setName (String _s) 
    {
        this.name = _s;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String _memo) {
        this.memo = _memo;
    }

    public Boolean isUseByDefault() {
        return useByDefault;
    }

    public void setUseByDefault(Boolean _b) {
        useByDefault = _b;
    }

    @Override
    public int hashCode ()
    {
    	return systemId;
    }

    @Override
    public boolean equals (Object obj)
    {
    	if(obj == null)
    		return false;
        if (!(obj instanceof SellPriceLevel)) {
            return false;
        }
        
        SellPriceLevel other = (SellPriceLevel) obj;
        return systemId == other.systemId;
    }

    @Override
    public String toString ()
    {
        return name;
    }
}
