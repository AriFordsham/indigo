package indigo.shared.platform

import indigo.shared.ClearColor

final class RendererConfig(val viewport: Viewport, val clearColor: ClearColor, val magnification: Int, val maxBatchSize: Int, val antiAliasing: Boolean)
