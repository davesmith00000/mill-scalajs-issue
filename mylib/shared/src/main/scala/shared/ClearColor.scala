package shared

final case class ClearColor(r: Double, g: Double, b: Double, a: Double) {
  def forceOpaque: ClearColor      = this.copy(a = 1d)
  def forceTransparent: ClearColor = this.copy(a = 0d)
  def withR(v: Double): ClearColor = this.copy(r = v)
  def withG(v: Double): ClearColor = this.copy(g = v)
  def withB(v: Double): ClearColor = this.copy(b = v)
  def withA(v: Double): ClearColor = this.copy(a = v)
}
