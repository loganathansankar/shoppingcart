package com.test

import com.shop.ShoppingCart
import org.junit.Test
import org.junit.Assert._
import org.junit.Before
import org.junit.After
import java.lang.Math

class ShoppingCartTest {
  
  var shoppingCart:ShoppingCart = null
  
  @Before
  def createShoppingCartInstance():Unit = {
    
      shoppingCart = new ShoppingCart
    
  }
  
  @After
  def nullifyShoppingCart():Unit = {
   
    shoppingCart = null;
    
  }
  
  @Test(expected=classOf[RuntimeException]) 
  def exceptionWhenItemNotFound():Unit = {
      
    shoppingCart.AddOrder("Lime")
    
  }
  @Test
  def addAppleToOrderItem():Unit  = {
    
        
    shoppingCart.AddOrder("Apple")
    
    val cartSize = shoppingCart.getOrderSize
    
    assertTrue(cartSize ==  1)
    
  }
  @Test(expected=classOf[RuntimeException])
  def exceptionWhenRemoveItemNotFount():Unit = {
    
    shoppingCart.removeOrder("Milk")
  }
  
  
  @Test
  def removeAppleToOrderItem():Unit = {
    
    
    var shoppingCart = new ShoppingCart
    
    shoppingCart.AddOrder("Apple")
    shoppingCart.removeOrder("Apple")
    
    val cartSize = shoppingCart.getOrderSize
    
    assertTrue(cartSize ==  0)
    
  }
  
   @Test
  def checkoutPrice():Unit = {
    
    shoppingCart.AddOrder("Apple")
    shoppingCart.AddOrder("Apple")
    shoppingCart.AddOrder("Orange")
    shoppingCart.AddOrder("Orange")
     shoppingCart.AddOrder("Orange")
     shoppingCart.AddOrder("Orange")
    
    val totalPrice = shoppingCart.generateBill
    
   
    assertTrue("1.35".equals(totalPrice))
    
  }
  
  @Test
   def checkoutWithOffer():Unit = {
    
    shoppingCart.AddOrder("Apple")
    shoppingCart.AddOrder("Apple")
    shoppingCart.AddOrder("Apple")
    shoppingCart.AddOrder("Orange")
    shoppingCart.AddOrder("Orange")
     shoppingCart.AddOrder("Orange")
     shoppingCart.AddOrder("Orange")
    
    val totalPrice = shoppingCart.generateBill
    
   
    assertFalse("1.95".equals(totalPrice))
    
  }

}
