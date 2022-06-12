package Utility

object RandomName extends Enumeration {
  type RandomNameGenerator = Value
  val John: RandomName.Value = Value("John Prodman")
  val Aurelius: RandomName.Value = Value("Aurelius Mc Vanheim")
}
