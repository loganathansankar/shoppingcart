package com.shop

trait ShoppingCartTrait {
  
  def AddOrder(itemName: String):Unit
  
  def removeOrder(itemName: String):Unit
  
  def getOrderSize():Int
  
  def generateBill():String 

}