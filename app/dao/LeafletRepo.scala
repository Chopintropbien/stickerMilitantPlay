package dao

import javax.inject.Inject

import models.{Category, SizeFormat, Leaflet}
import models.SizeFormat._
import models.Category._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
 * Created by laurianemollier on 24/12/2016.
 */


class LeafletRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.driver.api._

  private val Leaflets = TableQuery[LeafletTable]


  private def _findById(id: Long): DBIO[Option[Leaflet]] =
    Leaflets.filter(_.id === id).result.headOption

  private def _findByTitle(title: String): Query[LeafletTable, Leaflet, List] =
    Leaflets.filter(_.title === title).to[List]

  def findById(id: Long): Future[Option[Leaflet]] =
    db.run(_findById(id))

  def findByTitle(tilte: String): Future[List[Leaflet]] =
    db.run(_findByTitle(tilte).result)

  def all: Future[List[Leaflet]] =
    db.run(Leaflets.to[List].result)

  def create(title: String, img: String, price: Double, format: SizeFormat.Value, category: Category.Value, sticker: Boolean, description: String): Future[Long] = {
    val leaflet = Leaflet(0, title, img, price, format, category, sticker, description)
    add(leaflet)
  }
  def add(leaflet: Leaflet): Future[Long] ={
    db.run(Leaflets returning Leaflets.map(_.id) += leaflet)
  }

  //  def delete(name: String): Future[Int] = {
  //    val query = _findByName(name)
  //
  //    val interaction = for {
  //      projects        <- query.result
  //      _               <- DBIO.sequence(projects.map(p => taskRepo._deleteAllInProject(p.id)))
  //      projectsDeleted <- query.delete
  //    } yield projectsDeleted
  //
  //    db.run(interaction.transactionally)
  //  }
  //
  //  def addTask(color: String, projectId: Long): Future[Long] = {
  //    val interaction = for {
  //      Some(project) <- _findById(projectId)
  //      id <- taskRepo.insert(Task(0, color, TaskStatus.ready, project.id))
  //    } yield id
  //
  //    db.run(interaction.transactionally)
  //  }


  private class LeafletTable(tag: Tag) extends Table[Leaflet](tag, "LEAFLET") {

    def id = column[Long]("ID", O.AutoInc, O.PrimaryKey)

    def title = column[String]("TITLE")

    def img = column[String]("IMG")

    def price = column[Double]("PRICE")

    def format = column[SizeFormat]("FORMAT")

    def category = column[Category]("CATEGORY")

    def sticker = column[Boolean]("STICKER")

    def description = column[String]("DESCRIPTION")



    implicit val categoryMapper = MappedColumnType.base[Category, String](
      e => e.toString,
      s => Category.withName(s)
    )
    implicit val sizeFormatMapper = MappedColumnType.base[SizeFormat, String](
      e => e.toString,
      s => SizeFormat.withName(s)
    )

    def * = (id, title, img, price, format, category, sticker, description) <>((Leaflet.apply _).tupled, Leaflet.unapply)

    def ? = (id.?, title.?, img.?, price.?, format.?, category.?, sticker.?, description.?).shaped.<>({ r => import r._;
      _1.map(_ =>
        Leaflet.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))
    }, (_: Any) =>
      throw new Exception("Inserting into ? projection not supported."))

  }

}

