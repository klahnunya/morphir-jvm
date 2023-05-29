package morphir.ir

/**
 * Generated based on IR.Path
 */
object Path {

  type Path = morphir.sdk.List.List[morphir.ir.Name.Name]

  def fromList(
    names: morphir.sdk.List.List[morphir.ir.Name.Name]
  ): morphir.ir.Path.Path =
    names

  def fromString(
    string: morphir.sdk.String.String
  ): morphir.ir.Path.Path =
    fromList(
      "[^\\w\\s]+".r
        .split(string)
        .map(Name.fromString)
        .toList
    )

  def isPrefixOf(
    path: morphir.ir.Path.Path
  )(
    prefix: morphir.ir.Path.Path
  ): morphir.sdk.Basics.Bool =
    (prefix, path) match {
      case (Nil, _) =>
        true
      case (_, Nil) =>
        false
      case (pathHead :: pathTail, prefixHead :: prefixTail) =>
        if (morphir.sdk.Basics.equal(prefixHead)(pathHead)) {
          morphir.ir.Path.isPrefixOf(prefixTail)(pathTail)
        } else {
          false
        }
    }

  def toList(
    names: morphir.ir.Path.Path
  ): morphir.sdk.List.List[morphir.ir.Name.Name] =
    names

  def _toString(
    nameToString: morphir.ir.Name.Name => morphir.sdk.String.String
  )(
    sep: morphir.sdk.String.String
  )(
    path: morphir.ir.Path.Path
  ): morphir.sdk.String.String =
    morphir.sdk.String.join(sep)(morphir.sdk.List.map(nameToString)(morphir.ir.Path.toList(path)))

}
