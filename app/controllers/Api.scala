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


class Api @Inject()(val messagesApi: MessagesApi, mailerClient: MailerClient) extends Controller with I18nSupport {

  def contactUs = Action{ implicit request =>
    contactForm.bindFromRequest.fold(
      errorForm => {
        Ok("")//BadRequest(views.html.loginRegisterContact.loginRegisterContact(LogRegCont.contact, registerForm, loginForm, errorForm, companyData))
      },
      contactData => {
        val email = Email(
          "Simple email",
          "Square it FROM <" + contactData.email +">",
          Seq("Miss TO <mollierlauriane@gmail.com>"),
          bodyText = Some("From:" +  contactData.email + " \n \n " + contactData.text)
        )
        mailerClient.send(email)
        Ok("")
      }
    )
  }

  def login = Action { implicit request =>
    Ok("")
  }

  def logout = Action { implicit request =>
    Ok("")
  }


  def addUser = Action { implicit request =>
    Ok("")
  }

}



