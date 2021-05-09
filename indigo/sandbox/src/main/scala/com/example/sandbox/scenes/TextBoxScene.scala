package com.example.sandbox.scenes

import indigo._
import indigo.scenes._
import com.example.sandbox.SandboxStartupData
import com.example.sandbox.SandboxGameModel
import com.example.sandbox.SandboxViewModel

object TextBoxScene extends Scene[SandboxStartupData, SandboxGameModel, SandboxViewModel] {

  type SceneModel     = SandboxGameModel
  type SceneViewModel = SandboxViewModel

  def eventFilters: EventFilters =
    EventFilters.Restricted

  def modelLens: indigo.scenes.Lens[SandboxGameModel, SandboxGameModel] =
    Lens.keepOriginal

  def viewModelLens: Lens[SandboxViewModel, SandboxViewModel] =
    Lens.keepOriginal

  def name: SceneName =
    SceneName("textbox")

  def subSystems: Set[SubSystem] =
    Set()

  def updateModel(
      context: FrameContext[SandboxStartupData],
      model: SandboxGameModel
  ): GlobalEvent => Outcome[SandboxGameModel] =
    _ => Outcome(model)

  def updateViewModel(
      context: FrameContext[SandboxStartupData],
      model: SandboxGameModel,
      viewModel: SandboxViewModel
  ): GlobalEvent => Outcome[SandboxViewModel] =
    _ => Outcome(viewModel)

  val hello: TextBox =
    TextBox("Hello!", 200, 100)
      .modifyStyle(_.withColor(RGB.Magenta).modifyStroke(_.withWidth(Pixels(3)).withColor(RGB.Cyan)))

  def present(
      context: FrameContext[SandboxStartupData],
      model: SandboxGameModel,
      viewModel: SandboxViewModel
  ): Outcome[SceneUpdateFragment] =
    val tb = TextBox("Indigo... with fonts?", 200, 100)
      .modifyStyle(
        _.withColor(RGB.White)
      )
      .moveTo(50, 50)

    Outcome(
      SceneUpdateFragment(
        Layer(
          Graphic(
            Rectangle(0, 0, 40, 40),
            4,
            LightingAssets.junctionBoxMaterialOn.modifyLighting(_ => LightingModel.Unlit)
          ).moveTo(10, 10),
          Shape.Box(context.findBounds(tb).getOrElse(Rectangle.zero), Fill.None).withStroke(Stroke(1, RGBA.Cyan)),
          tb.withDepth(Depth(3)).modifyStyle(_.bold),
          tb.moveTo(50, 65).withDepth(Depth(3)),
          tb.moveTo(50, 80)
            .withDepth(Depth(3))
            .modifyStyle(
              _.withFontFamily(FontFamily.cursive)
                .withSize(Pixels(16))
                .modifyStroke(_.withColor(RGB.Red).withWidth(Pixels(1)))
            ),
          hello.modifyStyle(_.withSize(Pixels(20)))
            .moveTo(Signal.Orbit(Point(180, 70), 20).affectTime(0.25).at(context.running).toPoint)
            .withDepth(Depth(2)),
          model.dude.dude.sprite.play().withDepth(Depth(1))
        )
      )
    )

}
