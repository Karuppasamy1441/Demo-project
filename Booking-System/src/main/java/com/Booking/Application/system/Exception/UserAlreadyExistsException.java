package com.Booking.Application.system.Exception;

public class UserAlreadyExistsException extends RuntimeException{
     public UserAlreadyExistsException(String msg){
         super(msg);
     }
}
