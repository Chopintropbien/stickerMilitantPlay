package controllers


import dao.LeafletRepo
import models.Category
import models.ContactData.contactForm
import models.Category._
import models._
import models.SizeFormat.A6
import play.api.mvc.{Action, Controller}
import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
import settings.Global._

import play.api.libs.concurrent.Execution.Implicits.defaultContext


// email
import play.api.libs.mailer._
import java.io.File
import org.apache.commons.mail.EmailAttachment

/**
 * Created by laurianemollier on 22/12/2016.
 */
class MainController @Inject()(leafletRepo: LeafletRepo, val messagesApi: MessagesApi, mailerClient: MailerClient) extends Controller with I18nSupport {



  val img = "0.png"


  val cards = Card(CardItem(2, Leaflet(0, "Le best ", img, 0.3, A6, Capitalism, true, "je suis le meiller")) ::
    CardItem(7, Leaflet(0, "Le best ", img, 0.5, A6, Capitalism, true, "je suis le meiller")) ::
    CardItem(1, Leaflet(0, "Le best ", img, 0.5, A6, Capitalism, true, "je suis le meiller")) ::
    Nil)




  val leaflets = Leaflet(0, "Le best ", img, 0.5, A6, Capitalism, true, "je suis le meiller") ::
    Leaflet(1, "Le best ", img, 0.5, A6, Capitalism, true, "je suis le meiller") ::
    Leaflet(2, "Le best ", img, 0.5, A6, Capitalism, true, "je suis le meiller") ::
    Leaflet(3, "Le best ", img, 0.5, A6, Politic, true, "je suis le meiller") ::
    Leaflet(4, "Le best ", img, 0.5, A6, Feminism, true, "je suis le meiller") ::
    Leaflet(5, "Le best ", img, 0.5, A6, Racism, true, "je suis le meiller") ::
    Leaflet(6, "Le best ", img, 0.5, A6, Environment, true, "je suis le meiller") ::
    Leaflet(7, "Le best ", img, 0.5, A6, Capitalism, true, "je suis le meiller") ::
    Leaflet(8, "Le best ", img, 0.5, A6, Politic, true, "je suis le meiller") ::
    Leaflet(9, "Le best ", img, 0.5, A6, Politic, true, "je suis le meiller") ::
    Leaflet(10, "Le best ", img, 0.5, A6, Feminism, true, "je suis le meiller") ::
    Leaflet(11, "Le best ", img, 0.5, A6, Racism, true, "je suis le meiller") ::
    Leaflet(12, "Le best ", img, 0.5, A6, Environment, true, "je suis le meiller") ::
    Nil



  def init = Action.async { implicit request =>
    for(l <- leaflets){
      leafletRepo.add(l)
    }
    leafletRepo.all.map(s => Ok(s.map(l => l.toString).mkString(" ")))
  }


  def getLeaflet = Action.async { implicit request =>
    leafletRepo.all.map(s => Ok(s.map(l => l.toString).mkString(" ")))
  }

  def home = Action { implicit request =>
    //Ok(views.html.home.home(leaflets, cards, Some(CardItem(2, Leaflet(8, "Le best ", img, 0.5, A6, Politic, true, "je suis le meiller"))), companyData, false))
    Ok(views.html.home.home(leaflets, cards, None, companyData, false))

  }

  def items(category: String) = Action { implicit request =>
    val selectedCategory = Category.withNameOpt(category) match {
      case None => Category.apply(0)
      case Some(c) => c // redirection
    }
    Ok(views.html.items.items(leaflets, selectedCategory, cards, None, companyData, false))
  }

  def item(id: Long) = Action { implicit request =>
    val l = Leaflet(1, "Le best ", img, 0.5, A6, Capitalism, true, "je suis le meiller")
    Ok(views.html.item.item(l, cards, None, companyData, false))
  }


  def idea = Action { implicit request =>
    Ok(views.html.idea.idea(cards, None, companyData, false))
  }

  def contactUs = Action { implicit request =>
    Ok(views.html.contactUs.contactUs(contactForm, cards, None, companyData, false))
  }

  def checkout = Action { implicit request =>
    Ok(views.html.checkout.checkout(cards, None, companyData, false))
  }

  def card = Action { implicit request =>
    Ok(views.html.card.card(cards, None, companyData, false))
  }
}
