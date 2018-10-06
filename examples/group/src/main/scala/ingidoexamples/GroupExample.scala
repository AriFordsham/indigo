package ingidoexamples

import indigo._
import indigoexts._

object GroupExample extends IndigoGameBasic[Unit, Unit, Unit] {

  val config: GameConfig = defaultGameConfig

  val assets: Set[AssetType] = Set(AssetType.Image("graphics", "assets/graphics.png"))

  val fonts: Set[FontInfo] = Set()

  val animations: Set[Animations] = Set()

  def setup(assetCollection: AssetCollection): Either[StartupErrors, Unit] =
    Right(())

  def initialModel(startupData: Unit): Unit =
    ()

  def update(gameTime: GameTime, model: Unit): GameEvent => Unit =
    _ => model

  def initialViewModel(startupData: Unit): Unit => Unit = _ => ()

  def updateViewModel(gameTime: GameTime, model: Unit, viewModel: Unit, frameInputEvents: FrameInputEvents): Unit =
    ()

  def present(gameTime: GameTime, model: Unit, viewModel: Unit, frameInputEvents: FrameInputEvents): SceneUpdateFragment =
    SceneUpdateFragment().addGameLayerNodes(
      Group(
        Graphic(0, 0, 256, 256, 1, "graphics").moveTo(64, 10).moveBy(-50, -50),
        Graphic(0, 0, 32, 32, 1, "graphics").withCrop(32, 0, 32, 32).moveBy(-50, -50),
        Graphic(0, 0, 128, 128, 1, "graphics")
          .moveTo(0, 128)
          .withCrop(128, 0, 128, 128)
          .withTint(0, 1, 1)
          .withAlpha(0.5)
          .moveBy(-50, -50)
      ).moveTo(100, 100)
    )
}