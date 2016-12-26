package controllers


import models.ContactData.contactForm
import play.api.mvc.{Action, Controller}
import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
import settings.Global._


// email
import play.api.libs.mailer._
import java.io.File
import org.apache.commons.mail.EmailAttachment

/**
 * Created by laurianemollier on 22/12/2016.
 */
class MainController @Inject()(val messagesApi: MessagesApi, mailerClient: MailerClient) extends Controller with I18nSupport {

  def home = Action { implicit request =>
    Ok(views.html.home.home(companyData, false))
  }

  def items = Action { implicit request =>
    Ok(views.html.items.items(companyData, false))
  }

  def idea = Action { implicit request =>
    Ok(views.html.idea.idea(companyData, false))
  }

  def contactUs = Action { implicit request =>
    Ok(views.html.contactUs.contactUs(contactForm, companyData, false))
  }
}
