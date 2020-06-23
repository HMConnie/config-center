package cn.connie.config.center.common.utils;

import org.apache.commons.lang.math.RandomUtils;

public class Dui1DuiRandomUtils {

    public static String getRandomString(int length) {

        char code[]=
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x','y','z'};
       
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++){
            
            sb.append(code[RandomUtils.nextInt(35)]);
        }
        
        return sb.toString();
    }
    
    public static String getMerchantNo(){
        
        return getRandomString(10);
    }
    
    public static void main(String args[]){
        
        System.out.println( getRandomString(16));
        System.out.println(Dui1DuiStringUtils.generateUUID());
    }
    
    
}
