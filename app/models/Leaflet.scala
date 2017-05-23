package models


import models.SizeFormat.SizeFormat
import models.Category.Category



case class Leaflet(id: Long, title: String, img: String, price: Double, format: SizeFormat, category: Category, sticker: Boolean, description: String)

// price reduciton
