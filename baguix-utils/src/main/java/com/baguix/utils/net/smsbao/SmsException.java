/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/4.
 */
package com.baguix.utils.net.smsbao;

/**
 * <b>短息发送异常</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class SmsException extends RuntimeException{
    SmsException(String msg){
        super(msg);
    }
}
