package com.sunwell.product.dto;
//package sunwell.permaisuri.bus.dto.inventory;
//
//import sunwell.permaisuri.core.entity.inventory.ItemCategory;
//
//public class Metrics
//{
//    private String name;
//    private Double units;
//    private String memo;
////    private Metrics metricRinci;
//
//    /**
//     * @roseuid 45C72FEE01D6
//     */
//    public Metrics() {
//    }
//
//    public Metrics(Metrics _m) {
//        setData(_m)
//    }
//
//    public void setData(Metrics _m) {
//    	name = _m.getName();
//    	units = _m.getUnits();
//    	memo = _m.getDesc();
//    }
//
//    public Metrics getData() {
//    	Metrics m = new Metrics();
//    	m.setSystemId(systemId);
//    	m.setUnits(units);
//    	ic.setName(name);
//    	ic.setMemo(memo);
//    	return ic;
//    }
//   
//
////    @Deprecated
////    public Metrics getMetricRinci() {
////        if (m_metricRinci == null) {
////            return null;
////        } else {
////            try {
////                m_metricRinci = this.queryByName(m_metricRinci.getNamaMetric());
////            } catch (SQLException x) {
////                return null;
////            }
////        }
////
////        return m_metricRinci;
////    }
//    
//    public String setName() {
//        return nameS50;
//    }
//
//    public String getName() {
//        return nameS50;
//    }
//
//    @Deprecated
//    public void setMetricRinci(Metrics _metricRinci) {
//        this.metricRinci = _metricRinci;
//    }
//
//    @Deprecated
//    public void setUnits(double _units) {
//        this.units = _units;
//    }
//
//    @Deprecated
//    public double getUnits() {
//        return units;
//    }
//
//    public void setDesc(String _memo) {
//        memo = _memo;
//    }
//
//    public String getDesc() {
//        return memo;
//    }
//
//    public void setNamaMetric(String _name_s50) {
//        this.nameS50 = _name_s50;
//    }
//    
//    @Override
//    public boolean equals (Object _m)
//    {
//        if (! (_m instanceof Metrics))
//            return false;
//        
//        return nameS50.equals (((Metrics) _m).getName ());
//    }
//
//    @Override
//    public int hashCode ()
//    {
//        int hash = 3;
//        hash = 53 * hash + Objects.hashCode (this.nameS50);
//        return hash;
//    }    
//}
