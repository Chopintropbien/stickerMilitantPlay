package models

import play.api.i18n.Lang

object Category extends Enum{
  type Category = Value

  val Feminism = Value("feminism")
  val Racism = Value("racism")
  val Capitalism = Value("capitalism")
  val Politic = Value("politic")
  val Environment = Value("environment")

  def getNameEn(c: Category.Value): String = {
    c match {
      case Feminism => "Feminism"
      case Racism => "Racism"
      case Capitalism => "Capitalism"
      case Politic => "Politic"
      case Environment => "Environment"
    }
  }

  def getName(c: Category, lang: Lang): String = {
    lang.code match {
      case "en" => getNameEn(c)
      case "en-US" => getNameEn(c)
    }
  }

  def getDescriptionEn(c: Category.Value): String = {
    c match {
      case Feminism => "Anti-sexism"
      case Racism => "Racism"
      case Capitalism => "Capitalism"
      case Politic => "Politic"
      case Environment => "Environment"
    }
  }

  def getDescription(c: Category, lang: Lang): String = {
    lang.code match {
      case "en" => getDescriptionEn(c)
      case "en-US" => getDescriptionEn(c)
    }
  }


  def getColorClass(c: Category.Value): String = {
    c match {
      case Feminism => "red"
      case Racism => "green"
      case Capitalism => "orange"
      case Politic => "lblue"
      case Environment => "blue"
    }
  }

  def getFaIcon(c: Category.Value): String = {
    c match {
      case Feminism => "fa-truck"
      case Racism => "fa-truck"
      case Capitalism => "fa-truck"
      case Politic => "fa-truck"
      case Environment => "fa-truck"
    }
  }

}
