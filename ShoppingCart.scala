package com.shop

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Map
import java.text.DecimalFormat


class ShoppingCart extends ShoppingCartTrait {

  var itemMap = Map[String, Item]()

  itemMap += ("Apple" -> new Item(101,"Apple", 0.60f))
  itemMap += ("Orange" -> new Item(1012,"Orange", 0.25f))
  
  var discountMap = Map[String,DiscountDetail]()
  discountMap += ("Apple" -> new DiscountDetail(101,2,1))
  discountMap += ("Orange" -> new DiscountDetail(102,3,1))

  var orderItem = ListBuffer[String]()

  
  override def AddOrder(itemName: String) = {

    if (!itemMap.contains(itemName)) {

      throw new RuntimeException("Item not available");
    }

    orderItem += itemName 

  }

  override def removeOrder(itemName: String) = {

    if (!itemMap.contains(itemName)) {

      throw new RuntimeException("Item not available");
    }

    orderItem -= itemName

  }
  
  override def getOrderSize():Int = {
    
    orderItem.size
  }

  override def generateBill():String = {

    var total: Double = 0.0;

    for (oItem <- orderItem) {

      val currentItem: Item = itemMap.getOrElse(oItem, null)

      if (null != currentItem) {
        total = total + currentItem.ItemPrice
      }

    }
    
    
    
  total = calculateDiscount(total)
    
    val formatter = new DecimalFormat("#.##")
    

    formatter.format(total);
  }
  
  def calculateDiscount(total:Double):Double = {
    
    var countMap = scala.collection.mutable.Map[String,Int]()
    
    var totalPrice = total
    
   for (oItem <- orderItem) {
     
     val currentItem: Item = itemMap.getOrElse(oItem, null)

      if (null != currentItem) {
        
       
        
          if(countMap.contains(currentItem.ItemName)) {
            
            var curCount = countMap.getOrElse(currentItem.ItemName, 0)
            curCount = curCount + 1;
            countMap(currentItem.ItemName) = curCount
            
          } else {
            countMap(currentItem.ItemName) = 1
          }
        
      }
     
   }
    
     for (itemCountMap <- countMap) {
       
       val key:String = itemCountMap._1
       val value = itemCountMap._2
       
        
       
       val currentItem: Item = itemMap.getOrElse(key, null)
       val discountList = discountMap.getOrElse(key, null);

      if (null != currentItem && null !=discountList) {
        val deduct = value / discountList.buyTotal
       
       
        totalPrice = totalPrice - (deduct * currentItem.ItemPrice)
      }
       
       
     }
    
    
    totalPrice
    
  }
  

}
