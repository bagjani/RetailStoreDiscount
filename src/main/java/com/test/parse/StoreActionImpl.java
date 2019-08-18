package com.test.parse;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class StoreActionImpl {
	
	public Map<String, Method> storeMap;

    public StoreActionImpl() {
        storeMap = new HashMap<String, Method>();
        try {
            populateCartHashMap();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void populateCartHashMap() throws NoSuchMethodException {
    	storeMap.put("add_customer", Store.class.getMethod("addCustomer", String.class, String.class, String.class));
    	storeMap.put("add_product", Store.class.getMethod("addProduct", String.class, Double.class, String.class));
    	storeMap.put("show_products", Store.class.getMethod("showAllProducts"));
    	storeMap.put("add_prodToCart", Store.class.getMethod("addProductToCart", String.class, String.class, Integer.class));
    	storeMap.put("create_cart", Store.class.getMethod("createCart", String.class, Boolean.class));
    	storeMap.put("total_bill", Store.class.getMethod("totalBill", String.class));
    }

}
