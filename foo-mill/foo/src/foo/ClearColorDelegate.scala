package foo

import scala.scalajs.js.annotation._
import shared.ClearColor

@SuppressWarnings(Array("org.wartremover.warts.Any"))
@JSExportTopLevel("ClearColor")
final class ClearColorDelegate(_r: Double, _g: Double, _b: Double, _a: Double) {

  @JSExport
  var r = _r
  r = 1.0

  @JSExport
  val g = _g

  @JSExport
  val b = _b

  @JSExport
  val a = _a

  def toInternal: ClearColor =
    new ClearColor(r, g, b, a)

  // Nonsense method to show we're using the imported lib.
  @JSExport
  def alphaFromOpaqueInternal(): Double =
    toInternal.forceOpaque.a
}
