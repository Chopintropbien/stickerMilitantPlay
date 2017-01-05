package models

trait Enum extends Enumeration {
  def withNameOpt(n: String): Option[Value] = values find { _.toString == n }

  def forName(n: String): Value = withNameOpt(n) getOrElse {
    throw new java.util.NoSuchElementException(s"No such ${this.toString} value: $n")
  }
}
