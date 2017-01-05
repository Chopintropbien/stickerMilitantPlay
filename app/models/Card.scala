package models

/**
 * Created by laurianemollier on 04/01/2017.
 */
case class Card(items: List[(Int, Leaflet)]){

  def numberItem = items.foldRight(0.0)((it, acc) => acc + it._1)

  def totalPriceItems = items.foldRight(0.0)((it, acc) => acc + it._2.price)

  def deliveryPrice = 1.40

  def totalPrice = deliveryPrice + totalPriceItems

}
