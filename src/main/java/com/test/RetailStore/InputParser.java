package com.test.RetailStore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.test.parse.Store;
import com.test.parse.StoreActionImpl;

public class InputParser {

	StoreActionImpl storeAction;
	Store store;
	
	public InputParser() {
		storeAction = new StoreActionImpl();
		store = new Store();
    }
	
	public void parseTextInput(String inputString) {
		String[] inputs = inputString.split(" ");
		switch(inputs.length) {
			case 1:
				try {
                    Method method = storeAction.storeMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(store);
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
			case 2:
				try {
                    Method method = storeAction.storeMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(store, inputs[1]);
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
			case 3:
				try {
                    Method method = storeAction.storeMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(store, inputs[1], Boolean.valueOf(inputs[2]));
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
			case 4:
				try {
                    Method method = storeAction.storeMap.get(inputs[0]);
                    if (method != null) {
                    	if(method.getName() == "addProduct")
                    		method.invoke(store, inputs[1], Double.valueOf(inputs[2]), inputs[3]);
                    	else if(method.getName() == "addProductToCart")
                    		method.invoke(store, inputs[1], inputs[2], Integer.valueOf(inputs[3]));
                    	else
                    		method.invoke(store, inputs[1], inputs[2], inputs[3]);
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
			default:
				System.out.println("Invalid input");
		}
		
	}

}
