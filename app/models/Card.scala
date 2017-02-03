package models

case class Card(items: List[CardItem]){

  def numberItem = items.foldRight(0.0)((it, acc) => acc + it.numberItem)

  def totalPriceItems = items.foldRight(0.0)((it, acc) => acc + it.item.price)

  def deliveryPrice = 1.40

  def totalPrice = deliveryPrice + totalPriceItems

}


case class CardItem(numberItem: Int, item: Leaflet){
  def totalPrice = numberItem * item.price
}


