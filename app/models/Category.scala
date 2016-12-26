package models

import models.TaskStatus._

/**
 * Created by laurianemollier on 24/12/2016.
 */
object Category extends Enumeration{
  type Category = String

  val Feminism = Value("Feminism")
  val Racism = Value("Racism")
  val Capitalism = Value("Capitalism")
  val Politic = Value("Politic")
}
