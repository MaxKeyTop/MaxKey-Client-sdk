package org.maxkey.rest;

import org.maxkey.client.http.ParameterList;

public class ParameterListTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ParameterList pl =new ParameterList();
        pl.add("maxkey", "能想");
        pl.add("maxkey2", "acb");
        
        String url = pl.appendTo("http://www.maxkey.top/tt?");
        System.out.println(pl.asFormUrlEncodedString());
        
        System.out.println(url);
    }

}
